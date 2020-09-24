package weer.elytrondesign.ui

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.storage.FirebaseStorage
import org.json.JSONObject
import weer.elytrondesign.core.*
import weer.elytrondesign.databinding.FragmentAuthenticatorBinding
import weer.elytrondesign.ui.collection.TaleCollection
import weer.elytrondesign.ui.welcome.WelcomeAdapter

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

        binding.authenticatorContentBgHolder.alpha = 0f
        binding.authenticatorContentBgHolder.translationX = 30f

        binding.authenticatorContentL.alpha = 0f
        binding.authenticatorContentL.translationX = 30f

        binding.authenticatorContentBgHolder.animate().alpha(0.8f).setDuration(1000).start()
        binding.authenticatorContentBgHolder.animate().translationX(0f).setDuration(1000).start()
        binding.authenticatorContentL.animate().alpha(1f).setDuration(1000).start()
        binding.authenticatorContentL.animate().translationX(0f).setDuration(1000).start()

        binding.authPasswordEt.requestFocus()
        binding.authPasswordEt.isSelected = true

        binding.authPasswordEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

                if (p0.toString() == AppLoader.password) {
                    binding.authenticatorContentBgHolder.animate().alpha(0f).setDuration(500).setStartDelay(300).start()
                    binding.authenticatorContentBgHolder.animate().translationX(-30f).setDuration(500).setStartDelay(300).start()
                    binding.authenticatorContentL.animate().alpha(0f).setDuration(500).setStartDelay(300).start()
                    binding.authenticatorContentL.animate().translationX(-30f).setDuration(500).setStartDelay(300).start()

                    val authRecords = AppLoader.aDJsonArray
                    val newAuthRecord = JSONObject(AppLoader.aDList[0])
                        newAuthRecord.put(NetworkHandler.FB_INFO_PN_AD_ID, AppLoader.curAndroidInstallId)
                        newAuthRecord.put(NetworkHandler.FB_INFO_PN_AD_WELCOMED, false)

                    authRecords.put(authRecords.length(), newAuthRecord)

                    val newInfo = JSONObject(AppLoader.info).put(NetworkHandler.FB_INFO_PN_AD, authRecords)

                    val newInfoFile = FileHandler.writeFile(activity!!.filesDir, NetworkHandler.FB_CACHED_INFO_FN, newInfo.toString().toByteArray(), false)
                    FirebaseStorage.getInstance().reference.child(NetworkHandler.FB_INFO_FN).putFile(Uri.fromFile(newInfoFile))

                    FileHandler.deleteFile(newInfoFile)

                    AppLoader.info = newInfo.toString()
                    AppLoader.initAllInfoProps()

                    CommonUtils.runDelayed(800) {
                        if (!AppLoader.isWelcomed) {
                            FragmentHandler.loadFragment(WelcomeAdapter.getInstance(), Home.binding.homeContentL.id)
                        } else {
                            FragmentHandler.loadFragment(
                                TaleCollection.getInstance(),
                                Home.binding.homeContentL.id
                            )
                        }
                    }

                }
            }
        })

        return binding.root
    }

}