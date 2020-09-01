package weer.elytrondesign.present.welcome

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.storage.FirebaseStorage
import org.json.JSONObject
import weer.elytrondesign.core.AppLoader
import weer.elytrondesign.core.Core
import weer.elytrondesign.databinding.FragmentWelcomeBinding
import weer.elytrondesign.databinding.FragmentWelcomePageBinding
import weer.elytrondesign.present.Home
import weer.elytrondesign.present.collection.TaleCollection

class Welcome() : Fragment() {
    companion object {
        lateinit var binding: FragmentWelcomeBinding
        lateinit var pageBinding: FragmentWelcomePageBinding

        fun getInstance() : Welcome {
            return Welcome()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        binding.welcomeStartBtn.setOnClickListener {
            val authRecords = AppLoader.aDArray
            val newWelcomedRecord = JSONObject(AppLoader.authDevicesList[0])
            newWelcomedRecord.put(Core.FB_INFO_PN_AD_ID, AppLoader.currentAndroidInstallId)
            newWelcomedRecord.put(Core.FB_INFO_PN_AD_WELCOMED, true)

            authRecords.put(AppLoader.currentAuthDeviceIndex, newWelcomedRecord)

            val newInfo = JSONObject(AppLoader.info).put(Core.FB_INFO_PN_AD, authRecords)

            FirebaseStorage.getInstance().reference.child(Core.FB_INFO_FN).putFile(Uri.fromFile(Core.writeFile(activity!!.filesDir, Core.FB_INFO_FN, newInfo.toString(), false)))

            Core.loadFragment(TaleCollection.getInstance(), Home.binding.homeContentL.id)
        }

        return binding.root
    }

    class PageOne() : Fragment() {
        companion object {
            lateinit var binding: FragmentWelcomePageBinding
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentWelcomePageBinding.inflate(inflater, container, false)

            return binding.root
        }
    }

    class PageTwo() : Fragment() {
        companion object {
            var binding: FragmentWelcomePageBinding = pageBinding
        }
    }

    class PageThree() : Fragment() {
        companion object {
            var binding: FragmentWelcomePageBinding = pageBinding
        }
    }

}