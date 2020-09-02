package weer.elytrondesign.core

import android.app.Application
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Looper
import android.os.Message
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import org.json.JSONArray
import org.json.JSONObject
import weer.elytrondesign.core.java.WallpaperManager
import weer.elytrondesign.present.Home
import java.io.IOException
import java.util.ArrayList

class AppLoader() : Application() {

    companion object {
        lateinit var context: Context
        lateinit var infoCallback: Callback
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
        lateinit var currentHomeCoverBg: Drawable
        lateinit var currentHomeRlBg: Drawable
        lateinit var homeWallpaperThread: WallpaperThread
        lateinit var last5homeRlWallpapers: ArrayList<Int>
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        currentHomeCoverBg = resources.getDrawable(WallpaperManager.pickRandomInit())
        currentHomeRlBg = currentHomeCoverBg

        last5homeRlWallpapers = ArrayList(5)

        info = "{\"pW\":\"Oslo\",\"aD\":[{\"id\":\"542fbda8dba488d2\",\"w\":false},{\"id\":\"542fbda8dba488d0\",\"w\":false},{\"id\":\"4d69698161f3cb58\",\"w\":false}]}"

        initProps()
        notifyHome()
    }

    fun fetchInfo() {
        infoCallback = object : Callback {
            override fun onFailure(request: Request?, e: IOException?) {
                connectionRetries++

                if(connectionRetries >= 3) {
                    Looper.prepare()
                    Toast.makeText(context, "Connection down", Toast.LENGTH_SHORT).show()
                } else {
                    Core.fetch(Core.FB_INFO_URL, this)
                }
            }

            override fun onResponse(response: Response?) {
                info = response!!.body().string()

                Log.d("TAG", "onResponse: $response")

                initProps()
                notifyHome()
            }
        }

        Core.fetch(Core.FB_INFO_URL, infoCallback)
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
            msg.obj = "Cover"

        Home.handler.sendMessage(msg)

//        homeWallpaperThread = WallpaperThread()
//        homeWallpaperThread.start()
    }

}