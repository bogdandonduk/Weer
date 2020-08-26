package weer.elytrondesign.present.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import weer.elytrondesign.databinding.FragmentWelcomeBinding
import weer.elytrondesign.databinding.FragmentWelcomePageBinding

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