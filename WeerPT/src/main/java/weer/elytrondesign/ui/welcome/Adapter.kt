package weer.elytrondesign.ui.welcome

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import weer.elytrondesign.ui.Home

class Adapter() : FragmentStatePagerAdapter(Home.fm) {
    override fun getCount(): Int {
        return WelcomeAdapter.pages.size
    }

    override fun getItem(position: Int): Fragment {
        return WelcomeAdapter.pages[position]
    }
}