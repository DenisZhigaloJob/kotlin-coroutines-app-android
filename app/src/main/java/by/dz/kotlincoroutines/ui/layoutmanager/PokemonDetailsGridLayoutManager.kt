package by.dz.kotlincoroutines.ui.layoutmanager

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

class PokemonDetailsGridLayoutManager(context: Context?, spanCount: Int, imageCount: Int) :
    GridLayoutManager(context, spanCount) {

    init {
        spanSizeLookup = ContentsSpanSizeLookup(getSpanCount(), imageCount)
    }

    private class ContentsSpanSizeLookup(val spanCount: Int, val imageCount: Int) :
        SpanSizeLookup() {

        override fun getSpanSize(position: Int) = if (position < imageCount) 1 else spanCount

    }

}