package weer.elytrondesign.ui.collection.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import weer.elytrondesign.R
import weer.elytrondesign.databinding.FragmentPlayerBinding

class Player() : Fragment() {
    companion object {
        lateinit var binding: FragmentPlayerBinding

        fun getInstance() : Player {
            return Player()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player, container, false)

        return binding.root
    }
}