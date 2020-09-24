package weer.elytrondesign.ui

import android.graphics.drawable.TransitionDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.FragmentManager
import weer.elytrondesign.core.AppLoader
import weer.elytrondesign.core.FragmentHandler
import weer.elytrondesign.databinding.ActivityHomeBinding
import weer.elytrondesign.ui.collection.TaleCollection
import weer.elytrondesign.ui.welcome.WelcomeAdapter

class Home : AppCompatActivity() {

    companion object {
        lateinit var binding: ActivityHomeBinding
        var isFirstLoaded: Boolean = false
        lateinit var fm: FragmentManager
        var handler: HomeHandler = HomeHandler()

        fun loadNextFragment() {
            if(!AppLoader.isAuth) {
                FragmentHandler.loadFragment(Authenticator.getInstance(), binding.homeContentL.id)
            } else {
                if(!AppLoader.isWelcomed) {
                    FragmentHandler.loadFragment(WelcomeAdapter.getInstance(), binding.homeContentL.id)
                } else {
                    FragmentHandler.loadFragment(TaleCollection.getInstance(), binding.homeContentL.id)
                }
            }
        }

        fun introTbAnim() {
            binding.homeTb.alpha = 0f
            binding.homeTb.translationY = -30f
            binding.homeTb.animate().alpha(1f).setDuration(1000).start()
            binding.homeTb.animate().translationY(0f).setDuration(1000).start()
        }

        fun introCoverAnim() {
            binding.homeCoverRl.animate().alpha(1f).setStartDelay(500).start()
        }

        fun runOnMainThread(runnable: Runnable) {
            runnable.run()
        }

        fun initCover() {
            binding.homeCoverRl.visibility = View.VISIBLE
            
            binding.homeCoverRl.background = AppLoader.curHomeCoverBg
            binding.homeRl.background = AppLoader.curHomeRlOldBg

            introCoverAnim()
            introTbAnim()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        fm = supportFragmentManager

        if(savedInstanceState != null && isFirstLoaded) {
            binding.homeCoverRl.background = AppLoader.curHomeCoverBg
            binding.homeRl.background = AppLoader.curHomeRlOldBg
        } else {
            binding.homeCoverRl.visibility = View.GONE
            binding.homeCoverRl.alpha = 0f
        }
    }

    class HomeHandler : Handler() {
        override fun handleMessage(msg: Message) {
            when(msg.obj as String) {
                "Cover" -> {
                    initCover()
                    isFirstLoaded = true
                }
                "Root" -> {
                    val transitionDrawable = TransitionDrawable(arrayOf(AppLoader.curHomeRlOldBg, AppLoader.curHomeRlNewBg))

                    binding.homeRl.background = transitionDrawable
                    transitionDrawable.startTransition(500)

                    AppLoader.curHomeRlOldBg = AppLoader.curHomeRlNewBg
                }
                "InfoFetched" -> {
                    loadNextFragment()
                }
                else -> {

                }
            }
        }
    }
}
