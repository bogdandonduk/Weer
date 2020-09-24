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
import weer.elytrondesign.core.data.models.Tale
import weer.elytrondesign.core.wallpaper.WpHandler
import weer.elytrondesign.core.wallpaper.SwitcherThread
import weer.elytrondesign.ui.Home
import weer.elytrondesign.ui.collection.ThumbnailDecoderThread
import java.io.File
import java.io.IOException
import java.util.*

class AppLoader() : Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        curAndroidInstallId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

        curHomeCoverBg = resources.getDrawable(WpHandler.pickRandomInit())
        curHomeRlOldBg = curHomeCoverBg

        notifyHomeCover()

        appFilesDir = filesDir

        last5homeRlWPs = ArrayList(5)

        fetchInfo()
    }

    companion object {
        lateinit var context: Context
        lateinit var infoCallback: Callback
        lateinit var info: String
        lateinit var password: String
        lateinit var aDJsonArray: JSONArray
        lateinit var aDList: MutableList<String>
        lateinit var curAndroidInstallId: String
        var curAD: String = "unknown"
        var curADIndex: Int = -1
        var isAuth: Boolean = false
        var isWelcomed: Boolean = false
        lateinit var curHomeCoverBg: Drawable
        lateinit var curHomeRlOldBg: Drawable
        lateinit var curHomeRlNewBg: Drawable
        lateinit var homeWPSwitcherThread: SwitcherThread
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

                    initAllInfoProps()
                    notifyHomeInfoFetched()
                    fireWpSwitcherThread()
                }
            }

            NetworkHandler.fetch(NetworkHandler.FB_INFO_URL, infoCallback)
        }

        fun initPassword() {
            password = JSONObject(info).getString(NetworkHandler.FB_INFO_PN_PASSWORD)
        }

        fun initAds() {
            aDJsonArray = JSONObject(info).getJSONArray(NetworkHandler.FB_INFO_PN_AD)

            aDList = MutableList(aDJsonArray.length()) {
                aDJsonArray.getJSONObject(it).toString()
            }

            for (i in 0 until aDList.size) {
                if (JSONObject(aDList[i]).getString(NetworkHandler.FB_INFO_PN_AD_ID).equals(curAndroidInstallId)) {
                    curAD = aDList[i]
                    curADIndex = i
                    isAuth = true

                    if (JSONObject(aDList[i]).getBoolean(NetworkHandler.FB_INFO_PN_AD_WELCOMED)) {
                        isWelcomed = true
                    }
                }
            }
        }

        fun initTales() {
            val taleArray = JSONObject(info).getJSONArray(NetworkHandler.FB_INFO_PN_TALES)

            taleList = MutableList(taleArray.length()) {
                Tale(taleArray.getJSONObject(it).getString(NetworkHandler.FB_INFO_PN_TALES_NAME),
                    taleArray.getJSONObject(it).getString(NetworkHandler.FB_INFO_PN_TALES_THUMBNAIL),
                    taleArray.getJSONObject(it).getString(NetworkHandler.FB_INFO_PN_TALES_URL))
            }
        }

        fun initAllInfoProps() {
            initPassword()
            initAds()
            initTales()
        }

        fun notifyHomeCover() {
            val msg = Message.obtain()
                msg.obj = "Cover"

            Home.handler.sendMessage(msg)
        }

        fun notifyHomeInfoFetched() {
            val msg = Message.obtain()
            msg.obj = "InfoFetched"

            Home.handler.sendMessage(msg)
        }

        fun fireWpSwitcherThread() {
            homeWPSwitcherThread = SwitcherThread()
            homeWPSwitcherThread.start()
        }

        fun fireThumbnailDecoderThread() {
            thumbnailDecoderThread = ThumbnailDecoderThread()
            thumbnailDecoderThread.start()
        }
    }

}