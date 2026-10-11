package com.android.ex.editstyledtext;

import android.view.MenuItem;

/* loaded from: classes.dex */
class i implements MenuItem.OnMenuItemClickListener {
    final /* synthetic */ d kB;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ i(d dVar, i iVar) {
        this(dVar);
    }

    private i(d dVar) {
        this.kB = dVar;
    }

    @Override // android.view.MenuItem.OnMenuItemClickListener
    public boolean onMenuItemClick(MenuItem menuItem) {
        return this.kB.onTextContextMenuItem(menuItem.getItemId());
    }
}
