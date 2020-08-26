package weer.elytrondesign.present.welcome

import android.content.Context
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.FragmentManager
import com.google.firebase.storage.FirebaseStorage
import weer.elytrondesign.core.Core
import weer.elytrondesign.core.ThemedView
import weer.elytrondesign.core.ViewTransformer
import weer.elytrondesign.databinding.ActivityHomeBinding
import weer.elytrondesign.present.collection.Collection
import java.io.File

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
            Core.loadFragment(Welcome.getInstance(), binding.homeContentL.id)
        } else {
            Core.loadFragment(Collection.getInstance(), binding.homeContentL.id)
        }

        val firebaseStorageReference = FirebaseStorage.getInstance().reference

        val uri: Uri = Uri.fromFile(File("/storage/emulated/0/Android/uhdnature143.jpg"))
        val uhdRef = firebaseStorageReference.child("Android/uhdnature143.jpg")

        uhdRef.putFile(uri)
    }

}