package weer.elytrondesign.present

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.FragmentManager
import com.squareup.okhttp.Call
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import org.json.JSONObject
import weer.elytrondesign.core.Core
import weer.elytrondesign.databinding.ActivityHomeBinding
import weer.elytrondesign.present.collection.TaleCollection
import weer.elytrondesign.present.welcome.Welcome
import java.io.IOException
import java.lang.NullPointerException
import java.util.*

class Home : AppCompatActivity() {

    companion object {
        lateinit var context: Context
        lateinit var binding: ActivityHomeBinding
        lateinit var fm: FragmentManager
        lateinit var info: String
        lateinit var password: String
        lateinit var authDevicesList: MutableList<String>
        var isAuth: Boolean = false
        var isWelcomed: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = applicationContext

        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        fm = supportFragmentManager

        binding.homeRl.visibility = View.GONE

        Core.fetch(Core.FB_INFO_URL, object : Callback {
            override fun onFailure(request: Request?, e: IOException?) {

            }

            override fun onResponse(response: Response?) {
                info = response!!.body().string()

                runOnUiThread {
                    onInfoFetched()
                }
            }
        })
    }

    fun onInfoFetched() {
        binding.homeRl.visibility = View.VISIBLE

        password = JSONObject(info).getString(Core.FB_INFO_PN_PASSWORD)

        val aDArray = JSONObject(info).getJSONArray(Core.FB_INFO_PN_AD)
        authDevicesList = MutableList(aDArray.length()) {
            aDArray.getJSONObject(it).toString()
        }

        authDevicesList.forEach {
            if(JSONObject(it).getString(Core.FB_INFO_PN_AD_ID).equals(Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID))) {
                isAuth = true
                if(JSONObject(it).getBoolean(Core.FB_INFO_PN_AD_WELCOMED)) {
                    isWelcomed = true
                }
            }
        }

        if(!isAuth) {
            Core.loadFragment(Authenticator.getInstance(), binding.homeContentL.id)
        } else {
            if(!isWelcomed) {
                Core.loadFragment(Welcome.getInstance(), binding.homeContentL.id)
            } else {
                Core.loadFragment(TaleCollection.getInstance(), binding.homeContentL.id)
            }
        }
    }
}
