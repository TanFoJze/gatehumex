/*
 * 文件名：SystemBarBackgroundColors.java
 * 功能：设置状态栏背景颜色、导航栏背景颜色，或同时设置。
 * 内置约35种预设颜色，同时支持自定义颜色编码（如 "#FF252525"）。
 *
 * 调用方式：   (用自定义的颜色#FF…需要打引号   括号中的this其他的Activity 在一控制其他)
 *   1. 仅设置状态栏颜色：SystemBarBackgroundColors.setStatusBarColor(this, 预设颜色常量);
 *      或 SystemBarBackgroundColors.setStatusBarColor(this, "#FF252525");
 *   2. 仅设置导航栏颜色：SystemBarBackgroundColors.setNavBarColor(this, 预设颜色常量);
 *      或 SystemBarBackgroundColors.setNavBarColor(this, "#FF252525");
 *   3. 同时设置两者：   SystemBarBackgroundColors.setBothColors(this, 状态栏颜色值, 导航栏颜色值);  (自定义要打引号)
 *      参数可为预设常量或颜色字符串。
 *
 * 预设颜色常量列表（共35种，可直接使用类名.常量名调用）：
 *   RED, PINK, PURPLE, DEEP_PURPLE, INDIGO, BLUE, LIGHT_BLUE, CYAN, TEAL, GREEN,
 *   LIGHT_GREEN, LIME, YELLOW, AMBER, ORANGE, DEEP_ORANGE, BROWN, GREY, BLUE_GREY,
 *   BLACK, WHITE, DARK_GREY, LIGHT_GREY, GOLD, SILVER, NAVY, OLIVE, LAVENDER, TURQUOISE,
 *   MAGENTA, CORAL, PEACH, MINT, CREAM, ALMOND
 *
 * 注意：需要 API 21+，调用后颜色会立即生效，内容区域可能需调整布局边距。
 */

package tfj.gatehumex.Settings;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SystemBarBackgroundColors {


    public static final int RED = Color.parseColor("#F44336");
    public static final int PINK = Color.parseColor("#E91E63");
    public static final int PURPLE = Color.parseColor("#9C27B0");
    public static final int DEEP_PURPLE = Color.parseColor("#673AB7");
    public static final int INDIGO = Color.parseColor("#3F51B5");
    public static final int BLUE = Color.parseColor("#2196F3");
    public static final int LIGHT_BLUE = Color.parseColor("#03A9F4");
    public static final int CYAN = Color.parseColor("#00BCD4");
    public static final int TEAL = Color.parseColor("#009688");
    public static final int GREEN = Color.parseColor("#4CAF50");
    public static final int LIGHT_GREEN = Color.parseColor("#8BC34A");
    public static final int LIME = Color.parseColor("#CDDC39");
    public static final int YELLOW = Color.parseColor("#FFEB3B");
    public static final int AMBER = Color.parseColor("#FFC107");
    public static final int ORANGE = Color.parseColor("#FF9800");
    public static final int DEEP_ORANGE = Color.parseColor("#FF5722");
    public static final int BROWN = Color.parseColor("#795548");
    public static final int GREY = Color.parseColor("#9E9E9E");
    public static final int BLUE_GREY = Color.parseColor("#607D8B");
    public static final int BLACK = Color.parseColor("#000000");
    public static final int WHITE = Color.parseColor("#FFFFFF");
    public static final int DARK_GREY = Color.parseColor("#424242");
    public static final int LIGHT_GREY = Color.parseColor("#BDBDBD");
    public static final int GOLD = Color.parseColor("#FFD700");
    public static final int SILVER = Color.parseColor("#C0C0C0");
    public static final int NAVY = Color.parseColor("#000080");
    public static final int OLIVE = Color.parseColor("#808000");
    public static final int LAVENDER = Color.parseColor("#E6E6FA");
    public static final int TURQUOISE = Color.parseColor("#40E0D0");
    public static final int MAGENTA = Color.parseColor("#FF00FF");
    public static final int CORAL = Color.parseColor("#FF7F50");
    public static final int PEACH = Color.parseColor("#FFDAB9");
    public static final int MINT = Color.parseColor("#98FB98");
    public static final int CREAM = Color.parseColor("#FFFDD0");
    public static final int ALMOND = Color.parseColor("#FFEBCD");


    private static void applyStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);

            int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(flags);
        }
    }

    private static void applyNavBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setNavigationBarColor(color);
            int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            window.getDecorView().setSystemUiVisibility(flags);
        }
    }


    private static int parseColor(String colorString) {
        try {
            return Color.parseColor(colorString);
        } catch (IllegalArgumentException e) {
            return Color.BLACK;
        }
    }


    public static void setStatusBarColor(Activity activity, int color) {
        applyStatusBarColor(activity, color);
    }

    public static void setNavBarColor(Activity activity, int color) {
        applyNavBarColor(activity, color);
    }

    public static void setBothColors(Activity activity, int statusColor, int navColor) {
        applyStatusBarColor(activity, statusColor);
        applyNavBarColor(activity, navColor);
    }

    public static void setStatusBarColor(Activity activity, String colorString) {
        int color = parseColor(colorString);
        applyStatusBarColor(activity, color);
    }

    public static void setNavBarColor(Activity activity, String colorString) {
        int color = parseColor(colorString);
        applyNavBarColor(activity, color);
    }

    public static void setBothColors(Activity activity, String statusColorString, String navColorString) {
        int statusColor = parseColor(statusColorString);
        int navColor = parseColor(navColorString);
        applyStatusBarColor(activity, statusColor);
        applyNavBarColor(activity, navColor);
    }
}
