package weer.elytrondesign.present.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import weer.elytrondesign.databinding.FragmentWelcomeBinding

class Welcome() : Fragment() {
    companion object {
        lateinit var binding: FragmentWelcomeBinding

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

        Home.binding.homeTopBarL.visibility = View.GONE
        return binding.root
    }

    class ScreenOne() : Fragment() {
        companion object {

        }
    }

    class ScreenTwo() : Fragment() {
        companion object {

        }
    }

    class ScreenThree() : Fragment() {
        companion object {

        }
    }

}