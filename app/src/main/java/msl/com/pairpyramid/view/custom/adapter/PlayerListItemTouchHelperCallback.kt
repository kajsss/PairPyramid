package msl.com.pairpyramid.view.custom.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class PlayerListItemTouchHelperCallback constructor(var playerListAdapter :PlayerListAdapter) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        val dragFlags = 0 //ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = 0 or ItemTouchHelper.RIGHT
        return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
            val before = playerListAdapter.removedPosition
            playerListAdapter.itemSwiped(viewHolder!!.adapterPosition)
            playerListAdapter.notifyItemChanged(before)
    }

}