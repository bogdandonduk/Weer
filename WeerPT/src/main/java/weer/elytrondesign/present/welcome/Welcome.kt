package weer.elytrondesign.present.welcome

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.firebase.storage.FirebaseStorage
import org.json.JSONObject
import weer.elytrondesign.R
import weer.elytrondesign.core.AppLoader
import weer.elytrondesign.core.Core
import weer.elytrondesign.databinding.FragmentWelcomeBinding
import weer.elytrondesign.databinding.FragmentWelcomePageBinding
import weer.elytrondesign.present.Home
import weer.elytrondesign.present.collection.TaleCollection

class Welcome() : Fragment() {
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

    fun introAnim() {
        binding.welcomeContentBgHolder.alpha = 0f
        binding.welcomeContentBgHolder.scaleX = 1.01f
        binding.welcomeContentBgHolder.scaleY = 1.01f

        binding.welcomeContentL.alpha = 0f
        binding.welcomeContentL.scaleX = 1.01f
        binding.welcomeContentL.scaleY = 1.01f

        binding.welcomeTitle.alpha = 0f
        binding.welcomeTitle.scaleX = 1.01f
        binding.welcomeTitle.scaleY = 1.01f

        binding.welcomeStartBtn.alpha = 0f
        binding.welcomeStartBtn.scaleX = 1.01f
        binding.welcomeStartBtn.scaleY = 1.01f

        binding.welcomeContentBgHolder.animate().alpha(0.7f).setDuration(200).setStartDelay(100).start()
        binding.welcomeContentBgHolder.animate().scaleX(1f).setDuration(200).setStartDelay(100).start()
        binding.welcomeContentBgHolder.animate().scaleY(1f).setDuration(200).setStartDelay(100).start()

        binding.welcomeContentL.animate().alpha(1f).setDuration(200).setStartDelay(100).start()
        binding.welcomeContentL.animate().scaleX(1f).setDuration(200).setStartDelay(100).start()
        binding.welcomeContentL.animate().scaleY(1f).setDuration(200).setStartDelay(100).start()

        binding.welcomeTitle.animate().alpha(1f).setDuration(500).setStartDelay(800).start()
        binding.welcomeTitle.animate().scaleX(1f).setDuration(500).setStartDelay(800).start()
        binding.welcomeTitle.animate().scaleY(1f).setDuration(500).setStartDelay(800).start()

        binding.welcomeStartBtn.animate().alpha(1f).setDuration(500).setStartDelay(1300).start()
        binding.welcomeStartBtn.animate().scaleX(1f).setDuration(500).setStartDelay(1300).start()
        binding.welcomeStartBtn.animate().scaleY(1f).setDuration(500).setStartDelay(1300).start()
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
}