package me.ernestzamelczyk.espeotask.ui.utils

import android.content.Context
import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.v7.widget.RecyclerView
import android.view.View

class SpaceItemDecoration(context: Context, @DimenRes dimen: Int) : RecyclerView.ItemDecoration() {

    private val space: Int = context.resources.getDimension(dimen).toInt()

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.getChildAdapterPosition(view) == parent.adapter.itemCount - 1) {
            outRect.bottom = space
        }
        outRect.top = space
        outRect.left = space
        outRect.right = space
    }

}