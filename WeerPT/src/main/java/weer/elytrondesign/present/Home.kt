package weer.elytrondesign.present

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentManager
import weer.elytrondesign.core.AppLoader
import weer.elytrondesign.core.Core
import weer.elytrondesign.databinding.ActivityHomeBinding
import weer.elytrondesign.present.collection.TaleCollection
import weer.elytrondesign.present.welcome.Welcome

class Home : AppCompatActivity() {

    companion object {
        lateinit var binding: ActivityHomeBinding
        lateinit var toolbar: ActionBar
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

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        fm = supportFragmentManager

        binding.homeCoverRl.visibility = View.GONE
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        binding.homeCoverRl.visibility = View.VISIBLE
        binding.homeCoverRl.background = AppLoader.currentHomeCoverBg
        binding.homeRl.background = AppLoader.currentHomeRlBg
    }

    class HomeHandler : Handler() {
        override fun handleMessage(msg: Message) {
            if(msg.obj as String == "Cover") {
                binding.homeCoverRl.visibility = View.VISIBLE
                binding.homeCoverRl.background = AppLoader.currentHomeCoverBg

                onInfoFetched()
            } else {
                binding.homeRl.background = AppLoader.currentHomeRlBg
            }
        }
    }
}
