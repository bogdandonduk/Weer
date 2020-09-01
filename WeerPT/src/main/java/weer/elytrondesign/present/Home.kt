package weer.elytrondesign.present

import android.animation.ObjectAnimator
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentManager
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import weer.elytrondesign.core.AppLoader
import weer.elytrondesign.core.Core
import weer.elytrondesign.core.WallpaperThread
import weer.elytrondesign.core.java.WallpaperManager
import weer.elytrondesign.databinding.ActivityHomeBinding
import weer.elytrondesign.present.collection.TaleCollection
import weer.elytrondesign.present.welcome.Welcome
import java.io.IOException

class Home : AppCompatActivity() {

    companion object {
        lateinit var binding: ActivityHomeBinding
        lateinit var toolbar: ActionBar
        lateinit var fm: FragmentManager
        lateinit var handler: HomeHandler
        lateinit var wallpaperThread: WallpaperThread

        fun onInfoFetched() {
            wallpaperThread = WallpaperThread()
            wallpaperThread.start()

            if(!AppLoader.isAuth) {
                Core.loadFragment(Authenticator.getInstance(), binding.homeContentL.id)
            } else {
                if(!AppLoader.isWelcomed) {
                    Core.loadFragment(Welcome.getInstance(), binding.homeContentL.id)
                } else {
                    Core.loadFragment(TaleCollection.getInstance(), binding.homeContentL.id)
                }
            }
        }

        fun refreshWallpaper() {
            Core.fetch(WallpaperManager.pickRandomUrl(),
                object : Callback {
                    override fun onFailure(request: Request?, e: IOException?) {

                    }

                    override fun onResponse(response: Response?) {
                        val wallpaperResponse = response!!.body().bytes()
                        val msg = Message.obtain()
                            msg.obj = BitmapDrawable(BitmapFactory.decodeByteArray(wallpaperResponse, 0, wallpaperResponse.size))
                        handler.sendMessage(msg)
                    }
                })
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setSupportActionBar(binding.homeTb)

        toolbar = supportActionBar!!
        toolbar.setDisplayShowTitleEnabled(false)

        fm = supportFragmentManager

        handler = HomeHandler()

        binding.homeCoverRl.visibility = View.GONE

    }

    class HomeHandler : Handler() {
        override fun handleMessage(msg: Message) {
            if(msg.obj is Int) {
                binding.homeCoverRl.visibility = View.VISIBLE
                binding.homeCoverRl.setBackgroundResource(msg.obj as Int)

                onInfoFetched()
            } else {
                binding.homeRl.background = msg.obj as BitmapDrawable
            }
        }
    }
}
