package weer.elytrondesign.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import weer.elytrondesign.R
import weer.elytrondesign.databinding.FragmentWelcomePageBinding

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