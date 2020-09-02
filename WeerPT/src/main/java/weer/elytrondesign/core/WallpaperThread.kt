package weer.elytrondesign.core

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Message
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import weer.elytrondesign.core.java.WallpaperManager
import weer.elytrondesign.present.Home
import java.io.IOException

class WallpaperThread() : Thread() {

    override fun run() {
        while (true) {
            val refreshTime = System.currentTimeMillis() + 10 * 1000

            while (System.currentTimeMillis() < refreshTime) {
                sleep(1 * 1000)
            }

            Core.fetch(
                pickNewUrl(),
                object : Callback {
                    override fun onFailure(request: Request?, e: IOException?) {

                    }

                    override fun onResponse(response: Response?) {
                        val wallpaperResponse = response!!.body().bytes()
                        val msg = Message.obtain()
                            msg.obj = "Root"

                        AppLoader.currentHomeRlBg = BitmapDrawable(
                            BitmapFactory.decodeByteArray(
                                wallpaperResponse,
                                0,
                                wallpaperResponse.size
                            )
                        )

                        Home.handler.sendMessage(msg)
                    }
                })
        }
    }

    fun pickNewUrl(): String {
        var urlPair = WallpaperManager.pickRandomUrlPair()

        while(!checkUrlUniquenessAmongLast5(urlPair[0] as Int)) {
            urlPair = WallpaperManager.pickRandomUrlPair()
        }

        addUrlIndexToLast5Stack(urlPair[0] as Int)

        return urlPair[1].toString()
    }

    fun addUrlIndexToLast5Stack(index: Int) {
        if(AppLoader.last5homeRlWallpapers.size >= 6) {
            AppLoader.last5homeRlWallpapers.remove(0)
            AppLoader.last5homeRlWallpapers[0] = AppLoader.last5homeRlWallpapers[1]
            AppLoader.last5homeRlWallpapers[1] = AppLoader.last5homeRlWallpapers[2]
            AppLoader.last5homeRlWallpapers[2] = AppLoader.last5homeRlWallpapers[3]
            AppLoader.last5homeRlWallpapers[3] = AppLoader.last5homeRlWallpapers[4]
            AppLoader.last5homeRlWallpapers[4] = index
        } else {
            AppLoader.last5homeRlWallpapers.add(index)
        }
    }

    fun checkUrlUniquenessAmongLast5(index: Int): Boolean {
        return !AppLoader.last5homeRlWallpapers.contains(index)
    }
}