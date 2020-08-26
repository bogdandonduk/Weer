package weer.elytrondesign.present.welcome

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import weer.elytrondesign.core.Core
import weer.elytrondesign.databinding.ActivityHomeBinding
import weer.elytrondesign.present.collection.Collection

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

        if(Core.getPreference(Core.PK_FIRST_LAUNCH, true) as Boolean) {
            Core.loadFragment(Welcome.getInstance(), binding.homeContentL.id)
        } else {
            Core.loadFragment(Collection.getInstance(), binding.homeContentL.id)
        }

    }

}