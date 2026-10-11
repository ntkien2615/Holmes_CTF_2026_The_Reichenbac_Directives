package com.tw.core.utils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class FileProvider extends ContentProvider {
    private static final String[] eM = {"_display_name", "_size"};
    private static final File eN = new File(MqttTopic.TOPIC_LEVEL_SEPARATOR);
    private static HashMap eP = new HashMap();
    private InterfaceC0045s eO;

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (!providerInfo.grantUriPermissions) {
            throw new SecurityException("Provider must grant uri permissions");
        }
        this.eO = io(context, providerInfo.authority);
    }

    public static Uri ip(Context context, String str, File file) {
        return io(context, str).it(file);
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int i;
        File is = this.eO.is(uri);
        if (strArr == null) {
            strArr = eM;
        }
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int length = strArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            String str3 = strArr[i2];
            if ("_display_name".equals(str3)) {
                strArr3[i3] = "_display_name";
                i = i3 + 1;
                objArr[i3] = is.getName();
            } else if ("_size".equals(str3)) {
                strArr3[i3] = "_size";
                i = i3 + 1;
                objArr[i3] = Long.valueOf(is.length());
            } else {
                i = i3;
            }
            i2++;
            i3 = i;
        }
        String[] ik = ik(strArr3, i3);
        Object[] il = il(objArr, i3);
        MatrixCursor matrixCursor = new MatrixCursor(ik, 1);
        matrixCursor.addRow(il);
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        File is = this.eO.is(uri);
        int lastIndexOf = is.getName().lastIndexOf(46);
        if (lastIndexOf >= 0) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(is.getName().substring(lastIndexOf + 1));
            if (mimeTypeFromExtension != null) {
                return mimeTypeFromExtension;
            }
            return "application/octet-stream";
        }
        return "application/octet-stream";
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return this.eO.is(uri).delete() ? 1 : 0;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) {
        return ParcelFileDescriptor.open(this.eO.is(uri), iq(str));
    }

    private static InterfaceC0045s io(Context context, String str) {
        InterfaceC0045s interfaceC0045s;
        synchronized (eP) {
            interfaceC0045s = (InterfaceC0045s) eP.get(str);
            if (interfaceC0045s == null) {
                try {
                    try {
                        interfaceC0045s = ir(context, str);
                        eP.put(str, interfaceC0045s);
                    } catch (IOException e) {
                        throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e);
                    }
                } catch (XmlPullParserException e2) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e2);
                }
            }
        }
        return interfaceC0045s;
    }

    private static InterfaceC0045s ir(Context context, String str) {
        File file;
        C0046t c0046t = new C0046t(str);
        XmlResourceParser loadXmlMetaData = context.getPackageManager().resolveContentProvider(str, 128).loadXmlMetaData(context.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
        if (loadXmlMetaData == null) {
            throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
        }
        while (true) {
            int next = loadXmlMetaData.next();
            if (next != 1) {
                if (next == 2) {
                    String name = loadXmlMetaData.getName();
                    String attributeValue = loadXmlMetaData.getAttributeValue(null, "name");
                    String attributeValue2 = loadXmlMetaData.getAttributeValue(null, "path");
                    if ("root-path".equals(name)) {
                        file = eN;
                    } else if ("files-path".equals(name)) {
                        file = context.getFilesDir();
                    } else if ("cache-path".equals(name)) {
                        file = context.getCacheDir();
                    } else if ("external-path".equals(name)) {
                        file = Environment.getExternalStorageDirectory();
                    } else if ("external-files-path".equals(name)) {
                        File[] in = in(context, null);
                        if (in.length > 0) {
                            file = in[0];
                        }
                        file = null;
                    } else if ("external-cache-path".equals(name)) {
                        File[] im = im(context);
                        if (im.length > 0) {
                            file = im[0];
                        }
                        file = null;
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        if ("external-media-path".equals(name)) {
                            File[] externalMediaDirs = context.getExternalMediaDirs();
                            if (externalMediaDirs.length > 0) {
                                file = externalMediaDirs[0];
                            }
                        }
                        file = null;
                    } else {
                        file = null;
                    }
                    if (file != null) {
                        c0046t.iu(attributeValue, ij(file, attributeValue2));
                    }
                }
            } else {
                return c0046t;
            }
        }
    }

    private static int iq(String str) {
        if ("r".equals(str)) {
            return ClientDefaults.MAX_MSG_SIZE;
        }
        if ("w".equals(str) || "wt".equals(str)) {
            return 738197504;
        }
        if ("wa".equals(str)) {
            return 704643072;
        }
        if ("rw".equals(str)) {
            return 939524096;
        }
        if ("rwt".equals(str)) {
            return 1006632960;
        }
        throw new IllegalArgumentException("Invalid mode: " + str);
    }

    private static File ij(File file, String... strArr) {
        int length = strArr.length;
        int i = 0;
        File file2 = file;
        while (i < length) {
            String str = strArr[i];
            i++;
            file2 = str != null ? new File(file2, str) : file2;
        }
        return file2;
    }

    private static String[] ik(String[] strArr, int i) {
        String[] strArr2 = new String[i];
        System.arraycopy(strArr, 0, strArr2, 0, i);
        return strArr2;
    }

    private static Object[] il(Object[] objArr, int i) {
        Object[] objArr2 = new Object[i];
        System.arraycopy(objArr, 0, objArr2, 0, i);
        return objArr2;
    }

    public static File[] in(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 19) {
            return context.getExternalFilesDirs(str);
        }
        return new File[]{context.getExternalFilesDir(str)};
    }

    public static File[] im(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            return context.getExternalCacheDirs();
        }
        return new File[]{context.getExternalCacheDir()};
    }
}
