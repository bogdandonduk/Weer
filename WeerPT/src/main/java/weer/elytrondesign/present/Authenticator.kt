package weer.elytrondesign.present

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.okhttp.*
import org.json.JSONObject
import weer.elytrondesign.core.Core
import weer.elytrondesign.databinding.FragmentAuthenticatorBinding
import weer.elytrondesign.present.collection.TaleCollection
import weer.elytrondesign.present.welcome.Welcome
import java.io.IOException
import java.util.*

class Authenticator() : Fragment() {

    companion object {
        lateinit var binding: FragmentAuthenticatorBinding
        val password: String = JSONObject(Home.weerInfo).getString("password")

        fun getInstance() : Authenticator {
            return Authenticator()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthenticatorBinding.inflate(inflater, container, false)

        binding.authPasswordEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if(binding.authPasswordEt.text.toString().toLowerCase(Locale.ROOT) == password.toLowerCase(Locale.ROOT)) {
                    Core.editPreference(Core.PK_AUTHENTICATED, true)

                    if(Core.getPreference(Core.PK_FIRST_LAUNCH, true) as Boolean) {
                        Core.loadFragment(Welcome.getInstance(), Home.binding.homeContentL.id)
                    } else {
                        Core.loadFragment(TaleCollection.getInstance(), Home.binding.homeContentL.id)
                    }
                }
            }
        })

        return binding.root
    }

}