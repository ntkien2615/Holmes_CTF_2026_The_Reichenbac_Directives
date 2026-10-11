package com.c.j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* loaded from: D:\hommie_Ctf\SilentPassenger\extracted\stage2_unzip\classes_fixed.dex */
public final class i implements InvocationHandler {
    private Object a;

    public i(Class<?> cls) {
        try {
            this.a = cls.newInstance();
        } catch (Exception e) {
        }
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        if (this.a == null) {
            return null;
        }
        try {
            this.a.getClass().getMethod(((hgm) method.getAnnotation(hgm.class)).av(), method.getParameterTypes()).invoke(this.a, objArr);
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
