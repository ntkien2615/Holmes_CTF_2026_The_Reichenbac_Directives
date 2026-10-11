package org.eclipse.paho.client.mqttv3.persist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.internal.FileLock;
import org.eclipse.paho.client.mqttv3.internal.MqttPersistentData;

/* loaded from: classes.dex */
public class MqttDefaultFilePersistence implements MqttClientPersistence {
    private static FilenameFilter FILENAME_FILTER = null;
    private static final String LOCK_FILENAME = ".lck";
    private static final String MESSAGE_BACKUP_FILE_EXTENSION = ".bup";
    private static final String MESSAGE_FILE_EXTENSION = ".msg";
    private File clientDir;
    private File dataDir;
    private FileLock fileLock;

    private static FilenameFilter getFilenameFilter() {
        if (FILENAME_FILTER == null) {
            FILENAME_FILTER = new PersistanceFileNameFilter(MESSAGE_FILE_EXTENSION);
        }
        return FILENAME_FILTER;
    }

    public MqttDefaultFilePersistence() {
        this(System.getProperty("user.dir"));
    }

    public MqttDefaultFilePersistence(String str) {
        this.clientDir = null;
        this.fileLock = null;
        this.dataDir = new File(str);
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void open(String str, String str2) {
        if (this.dataDir.exists() && !this.dataDir.isDirectory()) {
            throw new MqttPersistenceException();
        }
        if (!this.dataDir.exists() && !this.dataDir.mkdirs()) {
            throw new MqttPersistenceException();
        }
        if (!this.dataDir.canWrite()) {
            throw new MqttPersistenceException();
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (isSafeChar(charAt)) {
                stringBuffer.append(charAt);
            }
        }
        stringBuffer.append("-");
        for (int i2 = 0; i2 < str2.length(); i2++) {
            char charAt2 = str2.charAt(i2);
            if (isSafeChar(charAt2)) {
                stringBuffer.append(charAt2);
            }
        }
        synchronized (this) {
            if (this.clientDir == null) {
                this.clientDir = new File(this.dataDir, stringBuffer.toString());
                if (!this.clientDir.exists()) {
                    this.clientDir.mkdir();
                }
            }
            try {
                this.fileLock = new FileLock(this.clientDir, LOCK_FILENAME);
            } catch (Exception e) {
            }
            restoreBackups(this.clientDir);
        }
    }

    private void checkIsOpen() {
        if (this.clientDir == null) {
            throw new MqttPersistenceException();
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void close() {
        synchronized (this) {
            if (this.fileLock != null) {
                this.fileLock.release();
            }
            if (getFiles().length == 0) {
                this.clientDir.delete();
            }
            this.clientDir = null;
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void put(String str, MqttPersistable mqttPersistable) {
        checkIsOpen();
        File file = new File(this.clientDir, String.valueOf(str) + MESSAGE_FILE_EXTENSION);
        File file2 = new File(this.clientDir, String.valueOf(str) + MESSAGE_FILE_EXTENSION + MESSAGE_BACKUP_FILE_EXTENSION);
        if (file.exists() && !file.renameTo(file2)) {
            file2.delete();
            file.renameTo(file2);
        }
        try {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(mqttPersistable.getHeaderBytes(), mqttPersistable.getHeaderOffset(), mqttPersistable.getHeaderLength());
                if (mqttPersistable.getPayloadBytes() != null) {
                    fileOutputStream.write(mqttPersistable.getPayloadBytes(), mqttPersistable.getPayloadOffset(), mqttPersistable.getPayloadLength());
                }
                fileOutputStream.getFD().sync();
                fileOutputStream.close();
                if (file2.exists()) {
                    file2.delete();
                }
                if (file2.exists() && !file2.renameTo(file)) {
                    file.delete();
                    file2.renameTo(file);
                }
            } catch (IOException e) {
                throw new MqttPersistenceException(e);
            }
        } catch (Throwable th) {
            if (file2.exists() && !file2.renameTo(file)) {
                file.delete();
                file2.renameTo(file);
            }
            throw th;
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public MqttPersistable get(String str) {
        checkIsOpen();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(this.clientDir, String.valueOf(str) + MESSAGE_FILE_EXTENSION));
            int available = fileInputStream.available();
            byte[] bArr = new byte[available];
            for (int i = 0; i < available; i += fileInputStream.read(bArr, i, available - i)) {
            }
            fileInputStream.close();
            return new MqttPersistentData(str, bArr, 0, bArr.length, null, 0, 0);
        } catch (IOException e) {
            throw new MqttPersistenceException(e);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void remove(String str) {
        checkIsOpen();
        File file = new File(this.clientDir, String.valueOf(str) + MESSAGE_FILE_EXTENSION);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public Enumeration keys() {
        checkIsOpen();
        File[] files = getFiles();
        Vector vector = new Vector(files.length);
        for (File file : files) {
            String name = file.getName();
            vector.addElement(name.substring(0, name.length() - MESSAGE_FILE_EXTENSION.length()));
        }
        return vector.elements();
    }

    private File[] getFiles() {
        checkIsOpen();
        File[] listFiles = this.clientDir.listFiles(getFilenameFilter());
        if (listFiles == null) {
            throw new MqttPersistenceException();
        }
        return listFiles;
    }

    private boolean isSafeChar(char c) {
        return Character.isJavaIdentifierPart(c) || c == '-';
    }

    private void restoreBackups(File file) {
        File[] listFiles = file.listFiles(new PersistanceFileFilter(MESSAGE_BACKUP_FILE_EXTENSION));
        if (listFiles == null) {
            throw new MqttPersistenceException();
        }
        for (int i = 0; i < listFiles.length; i++) {
            File file2 = new File(file, listFiles[i].getName().substring(0, listFiles[i].getName().length() - MESSAGE_BACKUP_FILE_EXTENSION.length()));
            if (!listFiles[i].renameTo(file2)) {
                file2.delete();
                listFiles[i].renameTo(file2);
            }
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public boolean containsKey(String str) {
        checkIsOpen();
        return new File(this.clientDir, String.valueOf(str) + MESSAGE_FILE_EXTENSION).exists();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void clear() {
        checkIsOpen();
        for (File file : getFiles()) {
            file.delete();
        }
        this.clientDir.delete();
    }
}
