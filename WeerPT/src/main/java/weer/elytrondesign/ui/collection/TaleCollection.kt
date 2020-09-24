package weer.elytrondesign.ui.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import weer.elytrondesign.core.AppLoader
import weer.elytrondesign.databinding.FragmentTaleCollectionBinding

class TaleCollection() : Fragment() {
    companion object {
        lateinit var binding: FragmentTaleCollectionBinding

        fun getInstance() : TaleCollection {
            return TaleCollection()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaleCollectionBinding.inflate(inflater, container, false)

//        binding.collectionRV.adapter = TCAdapter()
        binding.collectionRV.layoutManager = GridLayoutManager(AppLoader.context, 3)

        return binding.root
    }

}