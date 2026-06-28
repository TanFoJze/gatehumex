package tfj.gatehumex.Settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import java.util.HashMap;
import java.util.Map;

public class HideBackend {
    private static Map<Class<?>, Boolean> sDefaultHideMap = new HashMap<>();


    public static void setDefaultHide(Class<?> activityClass, boolean hide) {
        sDefaultHideMap.put(activityClass, hide);
    }


    public static void applyDefaultIfNeeded(Activity activity) {
        Boolean hide = sDefaultHideMap.get(activity.getClass());
        if (hide != null) {
            setCurrentActivityHide(activity, hide);
            sDefaultHideMap.remove(activity.getClass()); 
        }
    }


    private static void setCurrentActivityHide(Activity activity, boolean hide) {

        // 实际使用 recreate() 
        Intent intent = activity.getIntent();
        if (hide) {
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        } else {
            intent.setFlags(intent.getFlags() & ~Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        }
        activity.recreate();
    }

    public static void startWithHide(Context context, Class<? extends Activity> cls, boolean hide) {
        Intent intent = new Intent(context, cls);
        if (hide) {
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        }
        context.startActivity(intent);
    }
}
