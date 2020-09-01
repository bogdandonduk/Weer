package weer.elytrondesign.core

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Message
import android.provider.Settings
import android.util.Log
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import org.json.JSONArray
import org.json.JSONObject
import weer.elytrondesign.core.java.WallpaperManager
import weer.elytrondesign.present.Home
import java.io.IOException

class AppLoader() : Application() {

    companion object {
        lateinit var context: Context
        lateinit var starterCallback: Callback
        lateinit var info: String
        lateinit var password: String
        lateinit var aDArray: JSONArray
        lateinit var authDevicesList: MutableList<String>
        lateinit var currentAndroidInstallId: String
        var currentAuthDevice: String = "unknown"
        var currentAuthDeviceIndex: Int = -1
        var isAuth: Boolean = false
        var isWelcomed: Boolean = false
        var connectionRetries = 0
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        fetchInfo()
    }

    fun fetchInfo() {
        starterCallback = object : Callback {
            override fun onFailure(request: Request?, e: IOException?) {
                connectionRetries++

                if(connectionRetries >= 3) {
                    Log.d("TAG", "onFailure: connection down")
                } else {
                    Core.fetch(Core.FB_INFO_URL, this)
                }
            }

            override fun onResponse(response: Response?) {
                info = response!!.body().string()

                initProps()
                notifyHome()
            }
        }

        Core.fetch(Core.FB_INFO_URL, starterCallback)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if(info == null) {
            fetchInfo()
        } else {
            notifyHome()
        }
    }

    fun initProps() {
        password = JSONObject(info).getString(Core.FB_INFO_PN_PASSWORD)

        aDArray = JSONObject(info).getJSONArray(Core.FB_INFO_PN_AD)

        authDevicesList = MutableList(aDArray.length()) {
            aDArray.getJSONObject(it).toString()
        }

        currentAndroidInstallId =
            Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

        for (i in 0 until authDevicesList.size) {
            if (JSONObject(authDevicesList[i]).getString(Core.FB_INFO_PN_AD_ID)
                    .equals(currentAndroidInstallId)
            ) {
                currentAuthDevice = authDevicesList[i]
                currentAuthDeviceIndex = i
                isAuth = true

                if (JSONObject(authDevicesList[i]).getBoolean(Core.FB_INFO_PN_AD_WELCOMED)) {
                    isWelcomed = true
                }
            }
        }
    }

    fun notifyHome() {
        val msg = Message.obtain()
            msg.obj = WallpaperManager.pickRandomInit()
        Home.handler.sendMessage(msg)
    }

}