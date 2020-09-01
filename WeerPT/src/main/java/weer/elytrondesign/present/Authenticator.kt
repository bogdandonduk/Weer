package weer.elytrondesign.present

import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.storage.FirebaseStorage
import org.json.JSONObject
import weer.elytrondesign.core.AppLoader
import weer.elytrondesign.core.Core
import weer.elytrondesign.databinding.FragmentAuthenticatorBinding
import weer.elytrondesign.present.collection.TaleCollection
import weer.elytrondesign.present.welcome.Welcome

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

                if (p0.toString() == AppLoader.password) {
                    val authRecords = AppLoader.aDArray
                    val newAuthRecord = JSONObject(AppLoader.authDevicesList[0])
                        newAuthRecord.put(Core.FB_INFO_PN_AD_ID, AppLoader.currentAndroidInstallId)
                        newAuthRecord.put(Core.FB_INFO_PN_AD_WELCOMED, false)

                    authRecords.put(authRecords.length(), newAuthRecord)

                    val newInfo = JSONObject(AppLoader.info).put(Core.FB_INFO_PN_AD, authRecords)

                    FirebaseStorage.getInstance().reference.child(Core.FB_INFO_FN).putFile(Uri.fromFile(Core.writeFile(activity!!.filesDir, Core.FB_INFO_FN, newInfo.toString(), false)))

                    if (!AppLoader.isWelcomed) {
                        Core.loadFragment(Welcome.getInstance(), Home.binding.homeContentL.id)
                    } else {
                        Core.loadFragment(
                            TaleCollection.getInstance(),
                            Home.binding.homeContentL.id
                        )
                    }
                }
            }
        })

        return binding.root
    }

}