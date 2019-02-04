package com.android.quard.utils;

import android.content.Context;

/**
 * Describe :
 * Created by Knight on 2019/2/2
 * 点滴之行,看世界
 **/
public class Density {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int px2dip(Context context, float pxValue) {

        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
