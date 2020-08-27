package weer.elytrondesign.present

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.FragmentManager
import weer.elytrondesign.core.Core
import weer.elytrondesign.core.ThemedView
import weer.elytrondesign.core.ViewTransformer
import weer.elytrondesign.databinding.ActivityHomeBinding
import weer.elytrondesign.present.collection.TaleCollection

class Home : AppCompatActivity() {

    companion object {
        lateinit var context: Context
        lateinit var binding: ActivityHomeBinding
        lateinit var fm: FragmentManager
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

        if(Core.getPreference(Core.PK_FIRST_LAUNCH, true) as Boolean) {
            Core.loadFragment(Authenticator.getInstance(), binding.homeContentL.id)
        } else {
            Core.loadFragment(TaleCollection.getInstance(), binding.homeContentL.id)
        }

    }

}