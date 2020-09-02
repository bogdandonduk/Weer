package weer.elytrondesign.core.java;

import android.os.Handler;

public class ArcTools {

    public static void runPostDelayed(int delay, Runnable runnable) {
        new Handler().postDelayed(runnable, delay);
    }
}
