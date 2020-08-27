package weer.elytrondesign.present

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.FragmentManager
import com.squareup.okhttp.Callback
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import weer.elytrondesign.core.Core
import weer.elytrondesign.core.ThemedView
import weer.elytrondesign.core.ViewTransformer
import weer.elytrondesign.databinding.ActivityHomeBinding
import weer.elytrondesign.present.collection.TaleCollection
import java.io.IOException

class Home : AppCompatActivity() {

    companion object {
        lateinit var context: Context
        lateinit var binding: ActivityHomeBinding
        lateinit var fm: FragmentManager
        lateinit var authTokens: Array<Int>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = applicationContext

        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        fm = supportFragmentManager

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

        authTokens = arrayOf(0, 1, 2)

        if(Core.getPreference("authToken", 10001) == 10001) {
            Core.loadFragment(Authenticator.getInstance(), binding.homeContentL.id)
        } else {

        }

        if(Core.getPreference(Core.PK_FIRST_LAUNCH, true) as Boolean) {

        } else {
            Core.loadFragment(TaleCollection.getInstance(), binding.homeContentL.id)
        }

    }

    fun fetchAuthTokens() {
        OkHttpClient().newCall(Request.Builder()
            .url(Core.WEER_INFO_URL)
            .build()).enqueue(object : Callback {

            override fun onFailure(request: Request?, e: IOException?) {
                finish()
            }

            override fun onResponse(response: Response?) {
                val rawResponse = response!!.body().string()

                authTokens
            }
        })
    }
}