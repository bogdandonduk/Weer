package weer.elytrondesign.present

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.FragmentManager
import weer.elytrondesign.core.AppLoader
import weer.elytrondesign.core.Core
import weer.elytrondesign.databinding.ActivityHomeBinding
import weer.elytrondesign.present.collection.TaleCollection
import weer.elytrondesign.present.welcome.Welcome

class Home : AppCompatActivity() {

    companion object {
        lateinit var binding: ActivityHomeBinding
        var isFirstLoaded: Boolean = false
        lateinit var fm: FragmentManager
        var handler: HomeHandler = HomeHandler()

        fun onInfoFetched() {
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

        fun introTbAnim() {
            binding.homeTb.alpha = 0f
            binding.homeTb.translationY = -30f
            binding.homeTb.animate().alpha(1f).setDuration(1000).start()
            binding.homeTb.animate().translationY(0f).setDuration(1000).start()

        }

        fun runOnMainThread(runnable: Runnable) {
            runnable.run()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        fm = supportFragmentManager

        if(savedInstanceState != null && isFirstLoaded) {
            binding.homeCoverRl.background = AppLoader.curHomeCoverBg
            binding.homeRl.background = AppLoader.curHomeRlBg
        } else {
            binding.homeCoverRl.visibility = View.GONE
            binding.homeCoverRl.alpha = 0f
        }
    }

    class HomeHandler : Handler() {
        override fun handleMessage(msg: Message) {
            if(msg.obj as String == "Cover") {
                binding.homeCoverRl.visibility = View.VISIBLE
                binding.homeCoverRl.background = AppLoader.curHomeCoverBg
                binding.homeCoverRl.animate().alpha(1f).setStartDelay(500).start()
                introTbAnim()
                isFirstLoaded = true
                onInfoFetched()
            } else if(msg.obj as String == "Root"){
                binding.homeRl.background = AppLoader.curHomeRlBg
            } else {
                runOnMainThread(msg.obj as Runnable)
            }
        }
    }
}
