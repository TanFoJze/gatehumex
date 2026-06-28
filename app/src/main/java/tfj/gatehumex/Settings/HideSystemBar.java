/*
 * 文件名：HideSystemBar.java
 * 功能：隐藏导航栏或状态栏，可单独隐藏，也可同时隐藏。
 *      与 SystemBarBackgroundColors 完全兼容。请务必先调用 SystemBarBackgroundColors
 *      设置你想要的背景颜色（透明或纯色），再调用本类的方法隐藏系统栏。
 *      这样一来，从边缘划出系统栏时，临时显示的背景将是你设定的颜色，而不是系统默认的半透明灰色。
 * 
 * 调用方式：
 *   // 隐藏状态栏（保留导航栏）
 *   HideSystemBar.hideStatusBar(this);
 *   
 *   // 隐藏导航栏（保留状态栏）
 *   HideSystemBar.hideNavigationBar(this);
 *   
 *   // 同时隐藏两者，使用粘性沉浸模式（划出后松手自动再隐藏）
 *   HideSystemBar.hideBoth(this, true);
 *   
 *   // 同时隐藏两者，使用普通沉浸模式（划出后保持显示，需再次交互才隐藏）
 *   HideSystemBar.hideBoth(this, false);
 *
 * 参数说明：
 *   activity - 当前 Activity 实例（在 onCreate 或 onResume 中调用）
 *   sticky   - true: 划出时临时显示，松手后自动重新隐藏（适用于视频播放等场景）
 *              false: 划出后保持显示，需要用户再次滑动或点击才会重新隐藏
 *
 * 注意：需要 API 21+，并且务必先设置颜色再调用隐藏方法。
 */

package tfj.gatehumex.Settings;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class HideSystemBar {

    private static void ensureSystemBarDrawing(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
    }

    private static void applyFlags(Activity activity, int flags, boolean sticky) {
        ensureSystemBarDrawing(activity);
        View decorView = activity.getWindow().getDecorView();
        if (sticky && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            flags |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            flags |= View.SYSTEM_UI_FLAG_IMMERSIVE;
        }
        decorView.setSystemUiVisibility(flags);
    }

    public static void hideStatusBar(Activity activity) {
        int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_FULLSCREEN;
        applyFlags(activity, flags, true);
    }

    public static void hideNavigationBar(Activity activity) {
        int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        applyFlags(activity, flags, true);
    }

    public static void hideBoth(Activity activity, boolean sticky) {
        int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        applyFlags(activity, flags, sticky);
    }
}
