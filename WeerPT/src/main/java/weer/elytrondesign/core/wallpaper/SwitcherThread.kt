package weer.elytrondesign.core.wallpaper

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Message
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import weer.elytrondesign.core.AppLoader
import weer.elytrondesign.core.FileHandler
import weer.elytrondesign.core.NetworkHandler
import weer.elytrondesign.ui.Home
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class SwitcherThread() : Thread() {

    override fun run() {
        while(true) {
            val refreshTime = System.currentTimeMillis() + 10 * 1000

            while(System.currentTimeMillis() < refreshTime) {
                sleep(1 * 1000)
            }

            val uniqueUrlPair = pickUniqueUrlPair()
            val downloadedWallpaper = File(AppLoader.appFilesDir, uniqueUrlPair[0].toString() + ".jpg")

            if (downloadedWallpaper.exists()) {
                val wallpaperBa = ByteArray(downloadedWallpaper.length().toInt())
                val fis = FileInputStream(downloadedWallpaper)
                    fis.read(wallpaperBa)
                    fis.close()

                AppLoader.curHomeRlNewBg = BitmapDrawable(
                    BitmapFactory.decodeByteArray(wallpaperBa, 0, wallpaperBa.size)
                )

            } else {
                NetworkHandler.fetch(
                    uniqueUrlPair[1].toString(),
                    object : Callback {
                        override fun onFailure(request: Request?, e: IOException?) {

                        }

                        override fun onResponse(response: Response?) {
                            val wallpaperResponse = response!!.body().bytes()

                            FileHandler.writeFile(
                                AppLoader.appFilesDir,
                                uniqueUrlPair[0].toString() + ".jpg",
                                wallpaperResponse,
                                false
                            )

                            AppLoader.curHomeRlNewBg = BitmapDrawable(
                                BitmapFactory.decodeByteArray(
                                    wallpaperResponse,
                                    0,
                                    wallpaperResponse.size
                                )
                            )

                        }
                    })
            }

            val msg = Message.obtain()
                msg.obj = "Root"
            Home.handler.sendMessage(msg)
        }
    }

    fun pickUniqueUrlPair(): Array<Any> {
        var urlPair = WpHandler.pickRandomUrlPair()

        while(!checkUrlUniquenessAmongLast5(urlPair[0] as Int)) {
            urlPair = WpHandler.pickRandomUrlPair()
        }

        addUrlIndexToLast5Stack(urlPair[0] as Int)

        return urlPair
    }

    fun addUrlIndexToLast5Stack(index: Int) {
        if(AppLoader.last5homeRlWPs.size >= 6) {
            AppLoader.last5homeRlWPs.remove(0)
            AppLoader.last5homeRlWPs[0] = AppLoader.last5homeRlWPs[1]
            AppLoader.last5homeRlWPs[1] = AppLoader.last5homeRlWPs[2]
            AppLoader.last5homeRlWPs[2] = AppLoader.last5homeRlWPs[3]
            AppLoader.last5homeRlWPs[3] = AppLoader.last5homeRlWPs[4]
            AppLoader.last5homeRlWPs[4] = index
        } else {
            AppLoader.last5homeRlWPs.add(index)
        }
    }

    fun checkUrlUniquenessAmongLast5(index: Int): Boolean {
        return !AppLoader.last5homeRlWPs.contains(index)
    }
}