package by.dz.kotlincoroutines.util

import android.widget.ImageView
import by.dz.kotlincoroutines.R
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

private var req = RequestOptions()
    .timeout(30_000)
    .placeholder(R.drawable.bg_placeholder_circle)
    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    .fitCenter()
    .error(R.drawable.bg_placeholder_circle)

fun ImageView.load(
    url: String?,
    autoClean: Boolean = true,
    showPlaceholder: Boolean = true
) {
    if (autoClean) {
        this.clearImage()
    }

    if (showPlaceholder) {
        req =
            req.placeholder(R.drawable.bg_placeholder_circle)
                .error(R.drawable.bg_placeholder_circle)
    } else {
        req = req.placeholder(android.R.color.transparent)
    }

    if (url != null) {
        GlideApp.with(this.context)
            .load(url)
            .apply(req)
            .into(this)
    }
}

fun ImageView.clearImage() {
    GlideApp.with(this.context).clear(this)
}