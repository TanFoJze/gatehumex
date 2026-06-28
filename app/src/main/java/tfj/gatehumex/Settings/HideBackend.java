package tfj.gatehumex.Settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import java.util.HashMap;
import java.util.Map;

public class HideBackend {
    private static Map<Class<?>, Boolean> sDefaultHideMap = new HashMap<>();

    // 预先设置某个 Activity 的默认隐藏状态（不立即启动）
    public static void setDefaultHide(Class<?> activityClass, boolean hide) {
        sDefaultHideMap.put(activityClass, hide);
    }

    // 在目标 Activity 的 onCreate 中调用，应用预先设置的隐藏状态
    public static void applyDefaultIfNeeded(Activity activity) {
        Boolean hide = sDefaultHideMap.get(activity.getClass());
        if (hide != null) {
            setCurrentActivityHide(activity, hide);
            sDefaultHideMap.remove(activity.getClass()); // 可选：应用后移除
        }
    }

    // 控制当前 Activity（不重启，直接修改标志，但不会立即生效到最近任务）
    private static void setCurrentActivityHide(Activity activity, boolean hide) {
        // 注意：这个方法不能改变已经存在的任务栈显示，只能在下次启动时生效
        // 实际使用建议用 recreate() 或重新启动
        Intent intent = activity.getIntent();
        if (hide) {
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        } else {
            intent.setFlags(intent.getFlags() & ~Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        }
        // 需要重启才能看到效果
        activity.recreate();
    }

    // 启动并设置隐藏（推荐方式）
    public static void startWithHide(Context context, Class<? extends Activity> cls, boolean hide) {
        Intent intent = new Intent(context, cls);
        if (hide) {
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        }
        context.startActivity(intent);
    }
}
