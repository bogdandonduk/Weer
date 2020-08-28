package weer.elytrondesign.present.collection.player

import android.os.Handler
import android.os.Looper
import android.os.Message

class PlayerThread() : Thread() {

    companion object {
        lateinit var playerHandler: Handler
    }

    init {
        Looper.prepare()
        playerHandler = PlayerHandler()

    }

    class PlayerHandler() : Handler() {
        override fun handleMessage(msg: Message) {

        }
    }
}