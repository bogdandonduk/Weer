package weer.elytrondesign.core

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Message
import android.provider.Settings
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import org.json.JSONArray
import org.json.JSONObject
import weer.elytrondesign.core.models.Tale
import weer.elytrondesign.present.Home
import weer.elytrondesign.present.collection.ThumbnailDecoderThread
import java.io.File
import java.io.IOException
import java.util.*

class AppLoader() : Application() {

    companion object {
        lateinit var context: Context
        lateinit var infoCallback: Callback
        lateinit var info: String
        lateinit var password: String
        lateinit var aDArray: JSONArray
        lateinit var aDList: MutableList<String>
        lateinit var curAndroidInstallId: String
        var curAD: String = "unknown"
        var curADIndex: Int = -1
        var isAuth: Boolean = false
        var isWelcomed: Boolean = false
        lateinit var curHomeCoverBg: Drawable
        lateinit var curHomeRlBg: Drawable
        lateinit var homeWPThread: WallpaperThread
        lateinit var last5homeRlWPs: ArrayList<Int>
        lateinit var appFilesDir: File
        lateinit var thumbnailDecoderThread: ThumbnailDecoderThread
        lateinit var taleList: MutableList<Tale>

        fun fetchInfo() {
            infoCallback = object : Callback {
                override fun onFailure(request: Request?, e: IOException?) {

                }

                override fun onResponse(response: Response?) {
                    info = response!!.body().string()

                    initProps()
                    notifyHome()
                }
            }

            Core.fetch(Core.FB_INFO_URL, infoCallback)
        }

        fun initProps() {
            password = JSONObject(info).getString(Core.FB_INFO_PN_PASSWORD)

            aDArray = JSONObject(info).getJSONArray(Core.FB_INFO_PN_AD)

            aDList = MutableList(aDArray.length()) {
                aDArray.getJSONObject(it).toString()
            }

            for (i in 0 until aDList.size) {
                if (JSONObject(aDList[i]).getString(Core.FB_INFO_PN_AD_ID)
                        .equals(curAndroidInstallId)
                ) {
                    curAD = aDList[i]
                    curADIndex = i
                    isAuth = true

                    if (JSONObject(aDList[i]).getBoolean(Core.FB_INFO_PN_AD_WELCOMED)) {
                        isWelcomed = true
                    }
                }
            }

            val taleArray = JSONObject(info).getJSONArray(Core.FB_INFO_PN_TALES)

            taleList = MutableList(taleArray.length()) {
                Tale(taleArray.getJSONObject(it).getString(Core.FB_INFO_PN_TALES_NAME),
                    taleArray.getJSONObject(it).getString(Core.FB_INFO_PN_TALES_THUMBNAIL),
                    taleArray.getJSONObject(it).getString(Core.FB_INFO_PN_TALES_URL))
            }
        }

        fun notifyHome() {
            val msg = Message.obtain()
                msg.obj = "Cover"

            Home.handler.sendMessage(msg)

            homeWPThread = WallpaperThread()
            homeWPThread.start()
        }

        fun fireThumbnailDecoderThread() {
            thumbnailDecoderThread = ThumbnailDecoderThread()
            thumbnailDecoderThread.start()
        }
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        curAndroidInstallId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

        curHomeCoverBg = resources.getDrawable(WallpaperManager.pickRandomInit())
        curHomeRlBg = curHomeCoverBg

        appFilesDir = filesDir

        last5homeRlWPs = ArrayList(5)

        fetchInfo()
    }

}