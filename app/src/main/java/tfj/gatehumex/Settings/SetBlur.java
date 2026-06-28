//安卓系统壁纸背景模糊    SetBlur.apply(this, 自定义数字);


package tfj.gatehumex.Settings;

import android.app.Activity;
import android.os.Build;
import android.view.WindowManager;

import java.lang.reflect.Method;

public class SetBlur {
    public static void apply(Activity activity, int radius) {
        if (activity == null) return;
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER);
        if (Build.VERSION.SDK_INT >= 31) {
            try {
                activity.getWindow().addFlags(0x00000080);
                WindowManager.LayoutParams params = activity.getWindow().getAttributes();
                Method setBlurBehindRadius = params.getClass().getMethod("setBlurBehindRadius", int.class);
                setBlurBehindRadius.invoke(params, radius);
                activity.getWindow().setAttributes(params);
            } catch (Exception ignored) {
            }
        }
    }

    public static boolean isSupported() {
        return Build.VERSION.SDK_INT >= 31;
    }
}
