package weer.elytrondesign.present

import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import kotlinx.android.synthetic.main.activity_home.view.*
import org.json.JSONObject
import weer.elytrondesign.core.Core
import weer.elytrondesign.core.ThemedView
import weer.elytrondesign.core.ViewTransformer
import weer.elytrondesign.databinding.ActivityHomeBinding
import weer.elytrondesign.present.collection.TaleCollection
import weer.elytrondesign.present.welcome.Welcome
import java.io.File
import java.io.FileOutputStream
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

        Core.fetch(Core.WEER_CU_URl, object : Callback {
            override fun onFailure(request: Request?, e: IOException?) {

            }

            override fun onResponse(response: Response?) {
                val stringResponse = response!!.body().string()

                if(JSONObject(stringResponse!!).getString("cU").equals("y")) {
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
                } else {
                    if(!File(getExternalFilesDir(null), "WeeInfo.json").exists()) {
                        Core.fetch(Core.WEER_INFO_URL, object : Callback {
                            override fun onFailure(request: Request?, e: IOException?) {

                            }

                            override fun onResponse(response: Response?) {
                                val stringResponse2: String = response!!.body().string()

                                weerInfo = stringResponse2

                                val weerInfoFile = File(getExternalFilesDir(null), "WeerInfo.json")
                                    weerInfoFile.createNewFile()

                                val outputStream = FileOutputStream(weerInfoFile, true)
                                    outputStream.write(stringResponse2.toByteArray())
                                    outputStream.flush()
                                    outputStream.close()
                            }

                        })
                    } else {

                    }
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

        Toast.makeText(context, Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID), Toast.LENGTH_SHORT).show()

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