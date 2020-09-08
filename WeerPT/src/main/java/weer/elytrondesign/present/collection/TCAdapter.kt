package weer.elytrondesign.present.collection

import android.app.ActionBar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import weer.elytrondesign.R
import weer.elytrondesign.core.AppLoader
import weer.elytrondesign.core.Core
import weer.elytrondesign.databinding.HolderTaleCardBinding
import weer.elytrondesign.present.Home
import weer.elytrondesign.present.collection.player.Player

class TCAdapter() : RecyclerView.Adapter<TCAdapter.TaleHolder>() {

    class TaleHolder(val binding: HolderTaleCardBinding, var index: Int = 0) :  RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.holderTaleCardRl.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            Core.loadFragment(Player.getInstance(), Home.binding.homeContentL.id, "null")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaleHolder {
        val holder = TaleHolder(DataBindingUtil.inflate(LayoutInflater.from(AppLoader.context), R.layout.holder_tale_card, parent, false))
            holder.setIsRecyclable(false)

        return holder
    }

    override fun onBindViewHolder(holder: TaleHolder, position: Int) {
        holder.setIsRecyclable(false)

        holder.binding.holderTaleCardContentLBgHolder.alpha = 0f
        holder.binding.holderTaleCardContentL.alpha = 0f

        holder.binding.holderTaleCardContentLBgHolder.animate().alpha(1f).setDuration(1000).start()
        holder.binding.holderTaleCardContentL.animate().alpha(1f).setDuration(1000).start()

        holder.binding.tale = AppLoader.taleList[position]

        if(position in 0..2) {
            val layoutParams =
                ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
                layoutParams.topMargin = 50

            holder.binding.holderTaleCardRl.layoutParams = layoutParams
        }

//        if(AppLoader.taleList.size > 3) {
//            when(AppLoader.taleList.size % 3) {
//                0 ->
//            }
//        }

        if(AppLoader.taleList.size >= 6) {
            Log.d("TAG", "onBindViewHolder: no remainder" + AppLoader.taleList.size)
            if(position in AppLoader.taleList.size - 3 until AppLoader.taleList.size) {
                val layoutParams =
                    ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
                    layoutParams.bottomMargin = 50

                holder.binding.holderTaleCardRl.layoutParams = layoutParams
            }
        }

    }

    override fun getItemCount(): Int {
        return AppLoader.taleList.size
    }

}