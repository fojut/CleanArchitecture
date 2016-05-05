package org.fojut.sample.presentation.base.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * 屏幕信息获取数值的转换
 */
public class DensityUtils {

	public DensityUtils() {
		throw new AssertionError();
	}

	private static int[] deviceWidthHeight = new int[2];
	public static int[] getDeviceInfo(Context context) {
		if ((deviceWidthHeight[0] == 0) && (deviceWidthHeight[1] == 0)) {
			DisplayMetrics metrics = new DisplayMetrics();
			((Activity) context).getWindowManager().getDefaultDisplay()
					.getMetrics(metrics);

			deviceWidthHeight[0] = metrics.widthPixels;
			deviceWidthHeight[1] = metrics.heightPixels;
		}
		return deviceWidthHeight;
	}
	/**
	 * dp 转 px
	 * @param context 上下文
	 * @param dpValue dp数值
	 * @return dp to  px
	 */
	public static int dp2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);

	}
	/**
	 * 获取屏幕尺寸
	 */
	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public static Point getScreenSize(Context context){
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
			return new Point(display.getWidth(), display.getHeight());
		}else{
			Point point = new Point();
			display.getSize(point);
			return point;
		}
	}
	/**
	 *  px 转 dp
	 * @param context    上下文
	 * @param pxValue  px的数值
	 * @return  px to dp
	 */
	public static int px2dp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);

	}

	/**
	 * pt 转 dp, 1pt＝1/72英寸
	 * @param context
	 * @param ptValue
     * @return pt to dp
     */
	public static int pt2dp(Context context, float ptValue){
		final float scale = context.getResources().getDisplayMetrics().xdpi;
		return (int) (ptValue * scale * (1.0f / 72));
	}

	/**
	 * sp 转 dp
	 * @param context
	 * @param spValue
     * @return sp to dp
     */
	public static int sp2dp(Context context, float spValue){
		final float scale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * scale);
	}

	/**
	 * in(英寸) 转 dp
	 * @param context
	 * @param inValue
     * @return in to dp
     */
	public static int in2dp(Context context, float inValue){
		final float scale = context.getResources().getDisplayMetrics().xdpi;
		return (int) (inValue * scale);
	}

	/**
	 * mm(毫米) 转 dp
	 * @param context
	 * @param mmValue
     * @return mm to dp
     */
	public static int mm2dp(Context context, float mmValue){
		final float scale = context.getResources().getDisplayMetrics().xdpi;
		return (int) (mmValue * scale * (1.0f / 25.4f));
	}
}
