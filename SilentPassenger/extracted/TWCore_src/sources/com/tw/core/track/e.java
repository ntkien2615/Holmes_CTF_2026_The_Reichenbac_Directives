package com.tw.core.track;

/* loaded from: classes.dex */
public class e extends com.tw.core.track.model.f {
    private String ag;
    private com.tw.core.track.model.c ah;

    public String bl() {
        return this.ag;
    }

    public com.tw.core.track.model.c bm() {
        return this.ah;
    }

    public e() {
    }

    public e(long j, String str, long j2, String str2, com.tw.core.track.model.c cVar) {
        super(j, str, j2);
        this.ag = str2;
        this.ah = cVar;
    }

    @Override // com.tw.core.track.model.f
    public String toString() {
        return "AddPointRequest [entityName=" + this.ag + ", trackPoint=" + this.ah + "]";
    }
}
