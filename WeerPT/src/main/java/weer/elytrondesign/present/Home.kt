package weer.elytrondesign.present

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
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
import java.lang.StringBuilder
import java.util.*

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
                val cuResponse = response!!.body().string()

                if(JSONObject(cuResponse!!).getString("cU").equals("y")) {
                    Core.fetch(Core.WEER_INFO_URL, object : Callback {
                        override fun onFailure(request: Request?, e: IOException?) {

                        }

                        override fun onResponse(response: Response?) {
                            val weerInfoResponse = response!!.body().string()

                            weerInfo = weerInfoResponse

                            val overwrittenWeerInfoFile = File(filesDir, Core.WEER_INFO_FILENAME)
                                overwrittenWeerInfoFile.createNewFile()

                            val overwrittenWeerInfoOutputStream = FileOutputStream(overwrittenWeerInfoFile, true)
                                overwrittenWeerInfoOutputStream.write(weerInfoResponse.toByteArray())
                                overwrittenWeerInfoOutputStream.flush()
                                overwrittenWeerInfoOutputStream.close()

                            runOnUiThread {
                                onContentUpdatedFetched()
                            }
                        }

                    })
                } else {
                    if(!File(filesDir, Core.WEER_INFO_FILENAME).exists()) {
                        Core.fetch(Core.WEER_INFO_URL, object : Callback {
                            override fun onFailure(request: Request?, e: IOException?) {

                            }

                            override fun onResponse(response: Response?) {
                                val weerInfoResponse: String = response!!.body().string()

                                weerInfo = weerInfoResponse

                                val weerInfoFile = File(filesDir, Core.WEER_INFO_FILENAME)
                                    weerInfoFile.createNewFile()

                                val weerInfoOutputStream = FileOutputStream(weerInfoFile, true)
                                    weerInfoOutputStream.write(weerInfoResponse.toByteArray())
                                    weerInfoOutputStream.flush()
                                    weerInfoOutputStream.close()
                            }

                        })

                    } else {
                        val readWeerInfoFile = File(filesDir, Core.WEER_INFO_FILENAME)
                        val builder = StringBuilder()

                        val scanner = Scanner(readWeerInfoFile)

                        while(scanner.hasNextLine()) {
                            builder.append(scanner.nextLine())
                        }

                        weerInfo = builder.toString()
                    }


                }
            }

        })

    }

    fun onContentUpdatedFetched() {
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