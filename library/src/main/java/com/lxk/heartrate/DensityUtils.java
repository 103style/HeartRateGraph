package com.lxk.heartrate;

import android.content.Context;

/**
 * @author https://github.com/103style
 * @date 2020/8/11 11:45
 */
class DensityUtils {
    public static int dpToPx(Context context, int value) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

}
