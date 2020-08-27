package weer.elytrondesign.present.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import org.json.JSONObject
import weer.elytrondesign.core.Core
import weer.elytrondesign.databinding.FragmentTaleCollectionBinding
import java.io.IOException

class TaleCollection() : Fragment() {
    companion object {
        lateinit var binding: FragmentTaleCollectionBinding
        lateinit var tales: MutableList<TaleModel>

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

        Core.fetch(Core.WEER_INFO_URL, object : Callback {
            override fun onFailure(request: Request?, e: IOException?) {

            }

            override fun onResponse(response: Response?) {
                val rawResponse = response!!.body().string()

                val talesArray = JSONObject(rawResponse).getJSONArray("tales")
                for(i in 0..talesArray.length()) {
                    tales.add(TaleModel(talesArray.getJSONObject(i).getString("name"), talesArray.getJSONObject(i).getString("recUrl")))
                }
            }
        })

        return binding.root
    }

    fun onTalesFetched() {

    }
}