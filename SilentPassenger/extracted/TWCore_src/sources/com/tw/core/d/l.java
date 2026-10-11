package com.tw.core.d;

import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.InterfaceC0033g;
import java.io.File;

/* loaded from: classes.dex */
final class l implements InterfaceC0033g {
    final /* synthetic */ e gP;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(e eVar) {
        this.gP = eVar;
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bz(String str) {
        File file;
        String str2;
        String str3;
        file = this.gP.gB;
        str2 = this.gP.gC;
        C0030d.gU(file, str2);
        str3 = this.gP.gC;
        C0034h.hr("LogUploader", "rename file %s", str3);
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bB(String str) {
        File file;
        File file2;
        String str2;
        File file3;
        File file4;
        String str3;
        C0034h.hr("LogUploader", "log upload result %s", str);
        file = this.gP.gB;
        boolean delete = file.delete();
        file2 = this.gP.gE;
        boolean delete2 = file2.delete();
        str2 = this.gP.gA;
        if (str2 != null) {
            str3 = this.gP.gA;
            com.tw.core.c.b.eY(str3);
        }
        file3 = this.gP.gB;
        C0034h.hr("LogUploader", "%s delete result %s", file3.getName(), Boolean.valueOf(delete));
        file4 = this.gP.gE;
        C0034h.hr("LogUploader", "%s delete result %s", file4.getName(), Boolean.valueOf(delete2));
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bA() {
        File file;
        File file2;
        String str;
        File file3;
        String str2;
        file = this.gP.gB;
        if (file != null) {
            file2 = this.gP.gB;
            if (file2.exists()) {
                str = this.gP.gC;
                C0034h.hr("LogUploader", "rename file", str);
                file3 = this.gP.gB;
                str2 = this.gP.gC;
                C0030d.gU(file3, str2);
            }
        }
    }
}
