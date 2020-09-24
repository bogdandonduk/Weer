package weer.elytrondesign.ui.welcome

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.storage.FirebaseStorage
import org.json.JSONObject
import weer.elytrondesign.core.*
import weer.elytrondesign.core.CommonUtils
import weer.elytrondesign.databinding.FragmentWelcomeBinding
import weer.elytrondesign.ui.Home
import weer.elytrondesign.ui.collection.TaleCollection

class WelcomeAdapter() : Fragment() {
    companion object {
        lateinit var binding: FragmentWelcomeBinding
        lateinit var pages: Array<Fragment>

        fun getInstance() : WelcomeAdapter {
            return WelcomeAdapter()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        pages = arrayOf(PageOne.getInstance(), PageTwo.getInstance(), PageThree.getInstance())

        binding.welcomePageVp.adapter = Adapter()

        if(savedInstanceState == null) {
            introAnim()
        }

        binding.welcomeRl.setOnClickListener {
            binding.welcomeTitle.animate().alpha(0f).setDuration(400).setStartDelay(300).start()
            binding.welcomeTitle.animate().translationX(-30f).setDuration(400).setStartDelay(300).start()
            binding.welcomePageRl.animate().alpha(0f).setDuration(400).setStartDelay(700).start()
            binding.welcomePageRl.animate().translationX(-30f).setDuration(400).setStartDelay(700).start()

            binding.welcomeContentBgHolder.animate().alpha(0f).setDuration(500).setStartDelay(1100).start()
            binding.welcomeContentBgHolder.animate().translationX(-30f).setDuration(500).setStartDelay(1100).start()
            binding.welcomeContentL.animate().alpha(0f).setDuration(500).setStartDelay(1100).start()
            binding.welcomeContentL.animate().translationX(0f).setDuration(500).setStartDelay(1100).start()

            val authRecords = AppLoader.aDJsonArray
            val newWelcomedRecord = JSONObject(AppLoader.aDList[0])
                newWelcomedRecord.put(NetworkHandler.FB_INFO_PN_AD_ID, AppLoader.curAndroidInstallId)
                newWelcomedRecord.put(NetworkHandler.FB_INFO_PN_AD_WELCOMED, true)

            authRecords.put(AppLoader.curADIndex, newWelcomedRecord)

            val newInfo = JSONObject(AppLoader.info).put(NetworkHandler.FB_INFO_PN_AD, authRecords)

            val newInfoFile = FileHandler.writeFile(AppLoader.appFilesDir, NetworkHandler.FB_CACHED_INFO_FN, newInfo.toString().toByteArray(), false)
            FirebaseStorage.getInstance().reference.child(NetworkHandler.FB_INFO_FN).putFile(Uri.fromFile(newInfoFile))

            FileHandler.deleteFile(newInfoFile)

            AppLoader.info = newInfo.toString()
            AppLoader.initAllInfoProps()

            CommonUtils.runDelayed(1800) {
                FragmentHandler.loadFragment(TaleCollection.getInstance(), Home.binding.homeContentL.id)
            }
        }

        return binding.root
    }

    fun introAnim() {
        binding.welcomeContentBgHolder.alpha = 0f
        binding.welcomeContentBgHolder.translationX = 30f

        binding.welcomeContentL.alpha = 0f
        binding.welcomeContentL.translationX = 30f

        binding.welcomeTitle.alpha = 0f
        binding.welcomeTitle.translationX = 20f

        binding.welcomePageRl.alpha = 0f
        binding.welcomePageRl.translationX = 20f

        binding.welcomeContentBgHolder.animate().alpha(0.8f).setDuration(1000).start()
        binding.welcomeContentBgHolder.animate().translationX(0f).setDuration(1000).start()

        binding.welcomeContentL.animate().alpha(1f).setDuration(1000).start()
        binding.welcomeContentL.animate().translationX(0f).setDuration(1000).start()

        binding.welcomeTitle.animate().alpha(1f).setDuration(500).setStartDelay(1200).start()
        binding.welcomeTitle.animate().translationX(1f).setDuration(500).setStartDelay(1200).start()

        binding.welcomePageRl.animate().alpha(1f).setDuration(500).setStartDelay(2000).start()
        binding.welcomePageRl.animate().translationX(1f).setDuration(500).setStartDelay(2000).start()
    }
}