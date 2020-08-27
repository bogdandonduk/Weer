package weer.elytrondesign.present.collection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import weer.elytrondesign.R
import weer.elytrondesign.databinding.HolderTaleCardBinding
import weer.elytrondesign.present.Home

class TCAdapter(val tales: MutableList<TaleModel>) : RecyclerView.Adapter<TCAdapter.TaleHolder>() {

    class TaleHolder(val binding: HolderTaleCardBinding) :  RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaleHolder {
        return TaleHolder(DataBindingUtil.inflate(LayoutInflater.from(Home.context), R.layout.holder_tale_card, parent, false))
    }

    override fun onBindViewHolder(holder: TaleHolder, position: Int) {
        holder.binding.tale = tales[position]
    }

    override fun getItemCount(): Int {
        return tales.size
    }

}