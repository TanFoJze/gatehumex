package tfj.gatehumex.Settings;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

public class HideLauncherIcon {

    /**
     * 控制指定 Activity 的桌面图标是否显示
     * @param context      上下文
     * @param activityClass 目标 Activity 的 Class（例如 MainActivity.class）
     * @param show          true 显示图标，false 隐藏图标
     */
    public static void setIconVisible(Context context, Class<?> activityClass, boolean show) {
        PackageManager pm = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, activityClass);
        int newState = show ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        pm.setComponentEnabledSetting(componentName, newState, PackageManager.DONT_KILL_APP);
    }

    /**
     * 检查指定 Activity 的图标当前是否显示
     * @return true 表示图标显示（enabled），false 表示隐藏（disabled）
     */
    public static boolean isIconVisible(Context context, Class<?> activityClass) {
        PackageManager pm = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, activityClass);
        int state = pm.getComponentEnabledSetting(componentName);
        return state == PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
    }
}
