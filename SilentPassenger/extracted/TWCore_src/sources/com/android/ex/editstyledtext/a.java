package com.android.ex.editstyledtext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.style.ImageSpan;
import android.util.Log;
import java.io.InputStream;

/* loaded from: classes.dex */
public class a extends ImageSpan {
    public int jJ;
    private Context jK;
    public int jL;
    private final int jM;
    private Drawable jN;
    Uri jO;

    @Override // android.text.style.ImageSpan, android.text.style.DynamicDrawableSpan
    public Drawable getDrawable() {
        Bitmap decodeStream;
        if (this.jN != null) {
            return this.jN;
        }
        if (this.jO != null) {
            System.gc();
            try {
                InputStream openInputStream = this.jK.getContentResolver().openInputStream(this.jO);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(openInputStream, null, options);
                openInputStream.close();
                InputStream openInputStream2 = this.jK.getContentResolver().openInputStream(this.jO);
                int i = options.outWidth;
                int i2 = options.outHeight;
                this.jL = i;
                this.jJ = i2;
                if (options.outWidth > this.jM) {
                    i = this.jM;
                    i2 = (i2 * this.jM) / options.outWidth;
                    decodeStream = BitmapFactory.decodeStream(openInputStream2, new Rect(0, 0, i, i2), null);
                } else {
                    decodeStream = BitmapFactory.decodeStream(openInputStream2);
                }
                this.jN = new BitmapDrawable(this.jK.getResources(), decodeStream);
                this.jN.setBounds(0, 0, i, i2);
                openInputStream2.close();
            } catch (Exception e) {
                Log.e("EditStyledTextSpan", "Failed to loaded content " + this.jO, e);
                return null;
            } catch (OutOfMemoryError e2) {
                Log.e("EditStyledTextSpan", "OutOfMemoryError");
                return null;
            }
        } else {
            this.jN = super.getDrawable();
            nW(this.jN);
            this.jL = this.jN.getIntrinsicWidth();
            this.jJ = this.jN.getIntrinsicHeight();
        }
        return this.jN;
    }

    private void nW(Drawable drawable) {
        Log.d("EditStyledTextSpan", "--- rescaleBigImage:");
        if (this.jM < 0) {
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Log.d("EditStyledTextSpan", "--- rescaleBigImage:" + intrinsicWidth + "," + intrinsicHeight + "," + this.jM);
        if (intrinsicWidth > this.jM) {
            intrinsicWidth = this.jM;
            intrinsicHeight = (intrinsicHeight * this.jM) / intrinsicWidth;
        }
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
    }
}
