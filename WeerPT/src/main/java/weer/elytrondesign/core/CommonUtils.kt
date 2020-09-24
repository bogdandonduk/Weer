package weer.elytrondesign.core

import android.os.Handler

abstract class CommonUtils {
    companion object {
        fun runDelayed(delay: Int, runnable: Runnable) {
            Handler().postDelayed(runnable, delay.toLong())
        }
    }
}
