package dlgx.gis.com.dlgx.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.Toast;

/**
 * Created by Joe on 16/5/6.
 */
public class CommonUtils {

    private CommonUtils() {

    }


    /**
     * 通过基准尺寸获取适配当前设备密度的尺寸
     *
     * @param context  上下文
     * @param baseSize 基准尺寸
     * @return 适配当前设备密度的尺寸
     */
    public static float getCalculatedSize(Context context, float baseSize) {
        return baseSize * getDensityDpi(context)
                / DisplayMetrics.DENSITY_DEFAULT;

    }

    /**
     * 通过基准尺寸获取适配当前设备密度的尺寸
     *
     * @param context  上下文
     * @param baseSize 基准尺寸
     * @return 适配当前设备密度的尺寸
     */
    public static int getCalculatedSize(Context context, int baseSize) {
        return baseSize * getDensityDpi(context)
                / DisplayMetrics.DENSITY_DEFAULT;
    }

    /**
     * 获取当前设备的屏幕密度
     *
     * @param context 上下文
     * @return 屏幕密度
     */
    public static int getDensityDpi(Context context) {
        int result = DisplayMetrics.DENSITY_DEFAULT;
        if (context instanceof Activity) {
            DisplayMetrics metric = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay()
                    .getMetrics(metric);

            result = metric.densityDpi;
        }
        return result;
    }

    /**
     * 使用Toast输出文本
     *
     * @param context  上下文
     * @param resid    资源标识
     * @param duration
     */
    public static void showToast(Context context, int resid, int duration) {
        showToast(context, context.getString(resid), duration);
    }

    /**
     * 使用Toast输出文本
     *
     * @param context  上下文对象
     * @param msg      文本
     * @param duration
     */
    public static void showToast(Context context, String msg, int duration) {
        Toast toast = Toast.makeText(context, msg, duration);
        toast.show();
    }

}
