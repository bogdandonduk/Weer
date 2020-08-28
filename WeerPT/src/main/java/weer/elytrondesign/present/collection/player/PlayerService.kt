package weer.elytrondesign.present.collection.player

import android.app.Service
import android.content.Intent
import android.media.browse.MediaBrowser
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.service.media.MediaBrowserService
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import weer.elytrondesign.present.Home

class PlayerService() : Service() {

    companion object {
        lateinit var playerThread: Thread
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        playerThread.start()

        return START_REDELIVER_INTENT
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

}