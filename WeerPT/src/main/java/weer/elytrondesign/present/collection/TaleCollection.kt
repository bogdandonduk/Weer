package weer.elytrondesign.present.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import weer.elytrondesign.databinding.FragmentCollectionBinding

class TaleCollection() : Fragment() {
    companion object {
        lateinit var binding: FragmentCollectionBinding

        fun getInstance() : TaleCollection {
            return TaleCollection()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCollectionBinding.inflate(inflater, container, false)

        return binding.root
    }
}