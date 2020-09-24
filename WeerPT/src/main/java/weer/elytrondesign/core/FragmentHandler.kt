package weer.elytrondesign.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import weer.elytrondesign.ui.Home

class FragmentHandler {

    companion object {
        fun loadFragment(fragment: Fragment, containerId: Int, backStackTag: String? = null, transition: Int = FragmentTransaction.TRANSIT_NONE) {
            if(Home.fm.findFragmentById(containerId) != null)
                when(backStackTag) {
                    null ->
                        Home.fm.beginTransaction().replace(containerId, fragment).setTransition(transition).commit()
                    "null" ->
                        Home.fm.beginTransaction().replace(containerId, fragment).addToBackStack(null).setTransition(transition).commit()
                    else ->
                        Home.fm.beginTransaction().replace(containerId, fragment).addToBackStack(backStackTag).setTransition(transition).commit()
                }
            else
                when(backStackTag) {
                    null ->
                        Home.fm.beginTransaction().add(containerId, fragment).setTransition(transition).commit()
                    "null" ->
                        Home.fm.beginTransaction().add(containerId, fragment).addToBackStack(null).setTransition(transition).commit()
                    else ->
                        Home.fm.beginTransaction().add(containerId, fragment).addToBackStack(backStackTag).setTransition(transition).commit()

                }
        }
    }

}