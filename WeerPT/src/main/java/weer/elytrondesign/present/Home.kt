package weer.elytrondesign.present

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.FragmentManager
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import kotlinx.android.synthetic.main.activity_home.view.*
import weer.elytrondesign.core.Core
import weer.elytrondesign.core.ThemedView
import weer.elytrondesign.core.ViewTransformer
import weer.elytrondesign.databinding.ActivityHomeBinding
import weer.elytrondesign.present.collection.TaleCollection
import weer.elytrondesign.present.welcome.Welcome
import java.io.IOException

class Home : AppCompatActivity() {

    companion object {
        lateinit var context: Context
        lateinit var binding: ActivityHomeBinding
        lateinit var fm: FragmentManager
        lateinit var weerInfo: String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = applicationContext

        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        fm = supportFragmentManager

        binding.homeRl.visibility = View.GONE

        Core.fetch(Core.WEER_INFO_URL, object : Callback {
            override fun onFailure(request: Request?, e: IOException?) {
                finish()
            }

            override fun onResponse(response: Response?) {
                weerInfo = response!!.body().string()
                runOnUiThread {
                    onWeerInfoFetched()
                }
            }

        })

    }

    fun onWeerInfoFetched() {
        binding.homeRl.visibility = View.VISIBLE

        Core.loadTheme(arrayOf(ThemedView(
            binding.homeRl,
            object : ViewTransformer {
                override fun transform(view: View) {
                    binding.homeRl.setBackgroundColor(Color.WHITE)
                }
            },
            object : ViewTransformer {
                override fun transform(view: View) {
                    binding.homeRl.setBackgroundColor(Color.BLACK)
                }
            }
        )))

        if(!(Core.getPreference(Core.PK_AUTHENTICATED, false) as Boolean)) {
            Core.loadFragment(Authenticator.getInstance(), binding.homeContentL.id)
        } else {
            if(Core.getPreference(Core.PK_FIRST_LAUNCH, true) as Boolean) {
                Core.loadFragment(Welcome.getInstance(), binding.homeContentL.id)
            } else {
                Core.loadFragment(TaleCollection.getInstance(), binding.homeContentL.id)
            }
        }
    }
}