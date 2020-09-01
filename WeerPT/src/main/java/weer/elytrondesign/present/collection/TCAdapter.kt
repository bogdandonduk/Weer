package weer.elytrondesign.present.collection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import weer.elytrondesign.R
import weer.elytrondesign.core.AppLoader
import weer.elytrondesign.core.Core
import weer.elytrondesign.core.TaleModel
import weer.elytrondesign.databinding.HolderTaleCardBinding
import weer.elytrondesign.present.Home
import weer.elytrondesign.present.collection.player.Player

class TCAdapter(val tales: MutableList<TaleModel>) : RecyclerView.Adapter<TCAdapter.TaleHolder>() {

    class TaleHolder(val binding: HolderTaleCardBinding, var index: Int = 0) :  RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.holderTaleCardRl.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            Core.loadFragment(Player.getInstance(), Home.binding.homeContentL.id, "null")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaleHolder {
        return TaleHolder(DataBindingUtil.inflate(LayoutInflater.from(AppLoader.context), R.layout.holder_tale_card, parent, false))
    }

    override fun onBindViewHolder(holder: TaleHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.index = position
        holder.binding.tale = tales[position]
    }

    override fun getItemCount(): Int {
        return tales.size
    }

}