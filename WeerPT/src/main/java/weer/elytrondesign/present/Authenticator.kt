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
import java.io.IOException
import java.util.*

class Authenticator() : Fragment() {

    companion object {
        lateinit var binding: FragmentAuthenticatorBinding
        var password: String = "nullie"

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

        binding.authHintTv.visibility = View.GONE
        binding.authPasswordEt.visibility = View.GONE
        binding.authSpinnerPb.visibility = View.VISIBLE

        fetchPassword()

        binding.authPasswordEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if(binding.authPasswordEt.text.toString().toLowerCase(Locale.ROOT) == password.toLowerCase(Locale.ROOT)) {
                    Core.loadFragment(TaleCollection.getInstance(), Home.binding.homeContentL.id)
                }
            }
        })

        return binding.root
    }

    fun fetchPassword() {
        OkHttpClient().newCall(Request.Builder()
            .url(Core.WEER_INFO_URL)
            .build()).enqueue(object : Callback {

            override fun onFailure(request: Request?, e: IOException?) {
                activity!!.finish()
            }

            override fun onResponse(response: Response?) {
                val rawResponse = response!!.body().string()
                password = JSONObject(rawResponse).getString("password")
                activity!!.runOnUiThread {
                    onPasswordFetched()
                }
            }
        })

    }

    fun onPasswordFetched() {
        binding.authHintTv.visibility = View.VISIBLE
        binding.authPasswordEt.visibility = View.VISIBLE
        binding.authSpinnerPb.visibility = View.GONE
    }
}