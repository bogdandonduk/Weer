package weer.elytrondesign.core

import weer.elytrondesign.present.Home

class WallpaperThread() : Thread() {

    override fun run() {
        while(true) {
            val refreshTime = System.currentTimeMillis() + 10*1000

            while(System.currentTimeMillis() < refreshTime) {
                sleep(1*1000)
            }

            Home.refreshWallpaper()
        }
    }
}