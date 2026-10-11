package com.tw.core.track;

import java.util.Map;

/* loaded from: classes.dex */
public final class f extends com.tw.core.track.model.f {
    private Map ai;

    public Map bn() {
        return this.ai;
    }

    public f() {
    }

    public f(long j, String str, long j2, Map map) {
        super(j, str, j2);
        this.ai = map;
    }

    @Override // com.tw.core.track.model.f
    public String toString() {
        return "AddPointsRequest [trackPoints=" + this.ai + "]";
    }
}
