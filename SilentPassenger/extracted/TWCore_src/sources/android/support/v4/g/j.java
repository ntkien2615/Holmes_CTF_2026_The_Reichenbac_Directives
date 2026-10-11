package android.support.v4.g;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class j extends d implements Map {
    e pe;

    private e tE() {
        if (this.pe == null) {
            this.pe = new f(this);
        }
        return this.pe;
    }

    @Override // java.util.Map
    public void putAll(Map map) {
        tg(this.oO + map.size());
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Set entrySet() {
        return tE().tr();
    }

    @Override // java.util.Map
    public Set keySet() {
        return tE().tq();
    }

    @Override // java.util.Map
    public Collection values() {
        return tE().tk();
    }
}
