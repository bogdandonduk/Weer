package weer.elytrondesign.core

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import weer.elytrondesign.present.Home
import java.lang.IllegalArgumentException

abstract class Core {

    companion object {
        const val PKEY = "weer.elytrondesign.preferences"
        const val PK_THEME = "theme"
        const val PK_FIRST_LAUNCH = "firstLaunch"
        const val PK_AUTHENTICATED = "authenticated"
        const val PK_LAST_TALE_INDEX = "lastTaleIndex"
        const val WEER_INFO_URL = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/WeerInfo.json?alt=media&token=d6f9558a-2ee8-4f70-a2fa-55679fba08b1"
        const val WEER_CU_URl = "https://firebasestorage.googleapis.com/v0/b/weer-9b1db.appspot.com/o/cU.json?alt=media&token=21e0283c-b70a-4b7a-9af7-a36b12f57ad4"
        const val WEER_INFO_FILENAME = "WeerInfo.json"

        private fun getPreferences(): SharedPreferences {
            return Home.context.getSharedPreferences(PKEY, Context.MODE_PRIVATE)
        }

        fun getPreference(key: String, defValue: Any): Any? {
            return when(defValue) {
                is Int -> getPreferences().getInt(key, defValue)
                is Long -> getPreferences().getLong(key, defValue)
                is Float -> getPreferences().getFloat(key, defValue)
                is String -> getPreferences().getString(key, defValue)
                is Boolean -> getPreferences().getBoolean(key, defValue)

                else -> throw IllegalArgumentException()
            }
        }

        fun editPreference(key: String, obj: Any) {
            val editor = getPreferences().edit()

            when(obj) {
                is Int -> editor.putInt(key, obj).apply()
                is Long -> editor.putLong(key, obj).apply()
                is Float -> editor.putFloat(key, obj).apply()
                is String -> editor.putString(key, obj).apply()
                is Boolean -> editor.putBoolean(key, obj).apply()

                else -> throw IllegalArgumentException()
            }
        }

        fun loadFragment(fragment: Fragment, containerId: Int, backStackTag: String? = null, transition: Int = FragmentTransaction.TRANSIT_NONE) {
            if(Home.fm.findFragmentById(containerId) != null)
                when(backStackTag) {
                    null ->
                        Home.fm.beginTransaction().replace(containerId, fragment).setTransition(transition).commit()
                    "null" ->
                        Home.fm.beginTransaction().replace(containerId, fragment).addToBackStack(null).setTransition(transition).commit()
                    else ->
                        Home.fm.beginTransaction().replace(containerId, fragment).addToBackStack(backStackTag).setTransition(transition).commit()
                }
            else
                when(backStackTag) {
                    null ->
                        Home.fm.beginTransaction().add(containerId, fragment).setTransition(transition).commit()
                    "null" ->
                        Home.fm.beginTransaction().add(containerId, fragment).addToBackStack(null).setTransition(transition).commit()
                    else ->
                        Home.fm.beginTransaction().add(containerId, fragment).addToBackStack(backStackTag).setTransition(transition).commit()

                }
        }

        fun loadTheme(views: Array<ThemedView>) {
            val theme: String = if(getPreference(PK_THEME, "auto") == "auto") {
                if (Home.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_NO)
                    "light"
                else
                    "dark"
            } else {
                getPreference(PK_THEME, "auto") as String
            }

            if (theme == "light") {
                views.forEach {
                    it.lightAction.transform(it.view)
                }
            } else {
                views.forEach {
                    it.darkAction.transform(it.view)
                }
            }
        }

        fun fetch(url: String, callback: Callback) {
            OkHttpClient().newCall(Request.Builder().url(url).build()).enqueue(callback)
        }

    }
}