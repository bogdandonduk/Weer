package weer.elytrondesign.present.welcome

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.firebase.storage.FirebaseStorage
import org.json.JSONObject
import weer.elytrondesign.R
import weer.elytrondesign.core.AppLoader
import weer.elytrondesign.core.Core
import weer.elytrondesign.core.java.ArcTools
import weer.elytrondesign.databinding.FragmentWelcomeBinding
import weer.elytrondesign.databinding.FragmentWelcomePageBinding
import weer.elytrondesign.present.Home
import weer.elytrondesign.present.collection.TaleCollection

class Welcome() : DialogFragment(), View.OnClickListener {
    companion object {
        lateinit var binding: FragmentWelcomeBinding
        lateinit var pages: Array<Fragment>

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

        pages = arrayOf(PageOne.getInstance(), PageTwo.getInstance(), PageThree.getInstance())

        binding.welcomePageVp.adapter = PageAdapter()

        if(savedInstanceState == null) {
            introAnim()
        }

        binding.welcomeRl.setOnClickListener(this)

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

    class PageOne() : Fragment() {
        companion object {
            lateinit var binding: FragmentWelcomePageBinding

            fun getInstance() : PageOne {
                return PageOne()
            }
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentWelcomePageBinding.inflate(inflater, container, false)

            binding.welcomePageTitle.text = resources.getString(R.string.welcomePageTitle1)
            binding.welcomePageText.text = resources.getString(R.string.welcomePageTitle1)

            return binding.root
        }


    }

    class PageTwo() : Fragment() {
        companion object {
            lateinit var binding: FragmentWelcomePageBinding

            fun getInstance() : PageTwo {
                return PageTwo()
            }
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentWelcomePageBinding.inflate(inflater, container, false)

            binding.welcomePageTitle.text = resources.getString(R.string.welcomePageTitle2)
            binding.welcomePageText.text = resources.getString(R.string.welcomePageTitle2)

            return binding.root
        }
    }

    class PageThree() : Fragment() {
        companion object {
            lateinit var binding: FragmentWelcomePageBinding

            fun getInstance() : PageThree {
                return PageThree()
            }
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentWelcomePageBinding.inflate(inflater, container, false)

            binding.welcomePageTitle.text = resources.getString(R.string.welcomePageTitle3)
            binding.welcomePageText.text = resources.getString(R.string.welcomePageTitle3)

            return binding.root
        }
    }

    class PageAdapter() : FragmentStatePagerAdapter(Home.fm) {
        override fun getCount(): Int {
            return pages.size
        }

        override fun getItem(position: Int): Fragment {
            return pages[position]
        }
    }

    override fun onClick(p0: View?) {
        binding.welcomeTitle.animate().alpha(0f).setDuration(400).setStartDelay(300).start()
        binding.welcomeTitle.animate().translationX(-30f).setDuration(400).setStartDelay(300).start()
        binding.welcomePageRl.animate().alpha(0f).setDuration(400).setStartDelay(700).start()
        binding.welcomePageRl.animate().translationX(-30f).setDuration(400).setStartDelay(700).start()

        binding.welcomeContentBgHolder.animate().alpha(0f).setDuration(500).setStartDelay(1100).start()
        binding.welcomeContentBgHolder.animate().translationX(-30f).setDuration(500).setStartDelay(1100).start()
        binding.welcomeContentL.animate().alpha(0f).setDuration(500).setStartDelay(1100).start()
        binding.welcomeContentL.animate().translationX(0f).setDuration(500).setStartDelay(1100).start()

        val authRecords = AppLoader.aDArray
        val newWelcomedRecord = JSONObject(AppLoader.authDevicesList[0])
            newWelcomedRecord.put(Core.FB_INFO_PN_AD_ID, AppLoader.currentAndroidInstallId)
            newWelcomedRecord.put(Core.FB_INFO_PN_AD_WELCOMED, true)

        authRecords.put(AppLoader.currentAuthDeviceIndex, newWelcomedRecord)

        val newInfo = JSONObject(AppLoader.info).put(Core.FB_INFO_PN_AD, authRecords)

        val newInfoFile = Core.writeFile(AppLoader.appFilesDir, Core.FB_CACHED_INFO_FN, newInfo.toString().toByteArray(), false)
        FirebaseStorage.getInstance().reference.child(Core.FB_INFO_FN).putFile(Uri.fromFile(newInfoFile))

        AppLoader.info = newInfo.toString()
        AppLoader.initProps()

        ArcTools.runPostDelayed(1800) {
            Core.loadFragment(TaleCollection.getInstance(), Home.binding.homeContentL.id)
        }
    }
}