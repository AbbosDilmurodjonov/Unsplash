package uz.dilmurodjonov_a.unsplash.ui._binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

class ImageDataBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(view: ImageView, resourceUrl: String?) {
            if (resourceUrl != null && !resourceUrl.isEmpty()) {
                Glide.with(view.context)
                    .load(resourceUrl)
                    .into(view)

            }
        }

        @JvmStatic
        @BindingAdapter("loadUserImage")
        fun loadUserImage(view: ShapeableImageView, resourceUrl: String?) {
            if (resourceUrl != null && !resourceUrl.isEmpty()) {
                Glide.with(view.context)
                    .load(resourceUrl)
                    .into(view)

            }
        }
    }

}