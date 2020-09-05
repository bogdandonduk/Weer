package weer.elytrondesign.core

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Looper
import android.os.Message
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import org.json.JSONArray
import org.json.JSONObject
import weer.elytrondesign.core.java.WallpaperManager
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
        lateinit var authDevicesList: MutableList<String>
        lateinit var currentAndroidInstallId: String
        var currentAuthDevice: String = "unknown"
        var currentAuthDeviceIndex: Int = -1
        var isAuth: Boolean = false
        var isWelcomed: Boolean = false
        var infoConnectionRetries = 0
        lateinit var currentHomeCoverBg: Drawable
        lateinit var currentHomeRlBg: Drawable
        lateinit var homeWallpaperThread: WallpaperThread
        lateinit var last5homeRlWallpapers: ArrayList<Int>
        lateinit var appFilesDir: File
        lateinit var thumbnailDecoderThread: ThumbnailDecoderThread
        lateinit var taleList: MutableList<Tale>

        fun fetchInfo() {
            val savedInfo = File(appFilesDir, Core.FB_CACHED_INFO_FN)

            infoCallback = object : Callback {
                override fun onFailure(request: Request?, e: IOException?) {
                    infoConnectionRetries++

                    if(infoConnectionRetries >= 3) {

                        if(savedInfo.exists()) {
                            info = Core.readFile(savedInfo)

                            initProps()
                            notifyHome()
                        } else {
                            Looper.prepare()
                            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Core.fetch(Core.FB_INFO_URL, this)
                    }
                }

                override fun onResponse(response: Response?) {
                    val infoFetchedResponse = response!!.body().string()

                    if(savedInfo.exists()) {
                        info = Core.readFile(savedInfo)
                        initProps()

                        if(password == JSONObject(infoFetchedResponse).getString("pW") && JSONObject(infoFetchedResponse).getString("cU").equals("n")) {
                            Log.d("TAG", "onResponse: 0")
                            if(isAuth || isWelcomed) {
                                Log.d("TAG", "onResponse: 1")
                                FirebaseStorage.getInstance().reference.child(Core.FB_INFO_FN).putFile(
                                    Uri.fromFile(Core.writeFile(
                                        appFilesDir, Core.FB_CACHED_INFO_FN, Core.readFile(savedInfo).toByteArray(), false)))
                            } else {
                                Log.d("TAG", "onResponse: 2")
                                info = infoFetchedResponse
                                initProps()
                            }
                        } else {
                            info = infoFetchedResponse
                            Core.writeFile(appFilesDir, Core.FB_CACHED_INFO_FN, info.toByteArray(), false)
                            initProps()
                        }

                    } else {
                        info = infoFetchedResponse
//                        Core.writeFile(appFilesDir, Core.FB_CACHED_INFO_FN, info.toByteArray(), false)
                        initProps()
                    }

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

            homeWallpaperThread = WallpaperThread()
            homeWallpaperThread.start()

        }

        fun fireThumbnailDecoderThread() {
            thumbnailDecoderThread = ThumbnailDecoderThread()
            thumbnailDecoderThread.start()
        }
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        currentAndroidInstallId =
            Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

        currentHomeCoverBg = resources.getDrawable(WallpaperManager.pickRandomInit())
        currentHomeRlBg = currentHomeCoverBg

        appFilesDir = filesDir

        last5homeRlWallpapers = ArrayList(5)

        fetchInfo()
    }

}