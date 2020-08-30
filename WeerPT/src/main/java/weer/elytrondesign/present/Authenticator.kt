package weer.elytrondesign.present

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.storage.FirebaseStorage
import weer.elytrondesign.core.Core
import weer.elytrondesign.databinding.FragmentAuthenticatorBinding
import weer.elytrondesign.present.collection.TaleCollection
import weer.elytrondesign.present.welcome.Welcome
import java.io.File
import java.io.FileOutputStream

class Authenticator() : Fragment() {

    companion object {
        lateinit var binding: FragmentAuthenticatorBinding

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
                if (binding.authPasswordEt.text.toString() == Home.password) {
                    val file = File(activity!!.filesDir, "yes.json")
                        file.createNewFile()

                    val fOs = FileOutputStream(file, false)
                        fOs.write("YEAH".toByteArray())
                        fOs.flush()
                        fOs.close()

                    FirebaseStorage.getInstance().reference.child("yes.json").putFile(Uri.fromFile(file))

                    if(!Home.isWelcomed) {
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