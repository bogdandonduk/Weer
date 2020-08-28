package weer.elytrondesign.present.collection.player

import android.app.Application
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import org.json.JSONArray
import org.json.JSONObject
import weer.elytrondesign.R
import weer.elytrondesign.core.Core
import weer.elytrondesign.databinding.FragmentPlayerBinding
import weer.elytrondesign.present.Home
import weer.elytrondesign.present.collection.TaleModel

class Player() : Fragment() {
    companion object {
        lateinit var binding: FragmentPlayerBinding
        lateinit var tale: JSONObject

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

        tale = JSONObject(Home.weerInfo).getJSONArray("tales").getJSONObject(Core.getPreference(Core.PK_LAST_TALE_INDEX, JSONObject(Home.weerInfo).getInt("lastTaleIndex")) as Int)

        val intent = Intent(Home.context, PlayerService::class.java)
        intent.putExtra("recUrl", tale.getString("recUrl"))

        activity!!.startService(intent)

        binding.playerController.

        binding.tale = TaleModel(tale.getString("name"), tale.getString("recUrl"))

        return binding.root
    }
}