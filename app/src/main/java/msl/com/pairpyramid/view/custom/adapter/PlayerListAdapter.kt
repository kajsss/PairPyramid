package msl.com.pairpyramid.view.custom.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.item_player_list.view.*
import msl.com.pairpyramid.R
import msl.com.pairpyramid.model.Player
import org.jetbrains.anko.imageBitmap

class PlayerListAdapter : RecyclerView.Adapter<PlayerListAdapter.ViewHolder>() {

    var item: List<Player>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent!!.getContext())
        val view = inflater.inflate(R.layout.item_player_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.bind(item!!.get(position))
    }

    override fun getItemCount(): Int = item!!.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(player: Player) = with(itemView) {
            userName.text = player.name
            userEmail.text = player.email
            if(player.picture != null){
                userPicture.imageTintList = null
                userPicture.scaleType = ImageView.ScaleType.FIT_XY
                userPicture.imageBitmap = player.picture
            }

            setOnClickListener {
                player.checked = if(player.checked) false else true
                userCheck.visibility = if (player.checked) View.VISIBLE else View.INVISIBLE
            }
        }
    }
}