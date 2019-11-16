package com.andreamw96.moviecatalogue.utils

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

interface OnRecyclerViewItemSwiped {
    fun onSwiped(viewHolder: RecyclerView.ViewHolder)
}

class ItemTouchHelper(private val onRecyclerViewItemSwiped: OnRecyclerViewItemSwiped) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onRecyclerViewItemSwiped.onSwiped(viewHolder)
    }

}