package weer.elytrondesign.present.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import org.json.JSONArray
import org.json.JSONObject
import weer.elytrondesign.core.Core
import weer.elytrondesign.databinding.FragmentTaleCollectionBinding
import weer.elytrondesign.present.Home
import java.io.IOException

class TaleCollection() : Fragment() {
    companion object {
        lateinit var binding: FragmentTaleCollectionBinding
        lateinit var tales: MutableList<TaleModel>
        val talesArray: JSONArray = Home.weerInfo.getJSONArray("tales")

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

        tales = MutableList(talesArray.length()) {
            TaleModel(talesArray.getJSONObject(it).getString("name"), talesArray.getJSONObject(it).getString("recUrl"))
        }

        binding.collectionRV.adapter = TCAdapter(tales)
        binding.collectionRV.layoutManager = LinearLayoutManager(Home.context)

        return binding.root
    }

}