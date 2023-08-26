package com.example.testapp.presentation.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

private fun shimmerDrawable(): ShimmerDrawable {
    val shimmer =
        Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
            .setDuration(1800) // how long the shimmering animation takes to do one full sweep
            .setBaseAlpha(0.7f) //the alpha of the underlying children
            .setHighlightAlpha(0.6f) // the shimmer alpha amount
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setAutoStart(true)
            .build()

    // This is the placeholder for the imageView
    return ShimmerDrawable().apply {
        setShimmer(shimmer)
    }
}

fun View.swipeAnim(isCamera: Boolean, itemView: View, view: View, editView: View? = null) {
    this.setOnTouchListener(object : View.OnTouchListener {
        private var startX: Float = 0.toFloat()
        private var startY: Float = 0.toFloat()

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    startX = event.x
                    startY = event.y
                    return true
                }

                MotionEvent.ACTION_UP -> {
                    val deltaX = event.x - startX
                    if (deltaX < -45) {
                        if (isCamera == true) {
                            runSwipeAnimation(0f, -160f, itemView, view, editView = editView)
                        } else {
                            runSwipeAnimation(0f, -280f, itemView, view, editView = editView)
                        }

                    } else if (deltaX > 35) {
                        if (isCamera == true) {
                            runSwipeAnimation(-160f, 0f, itemView, view, editView = editView)
                        } else {
                            runSwipeAnimation(-280f, 0f, itemView, view, editView = editView)
                        }
                    }
                    return true
                }

                else -> return false
            }
        }
    })
}

fun runSwipeAnimation(
    startX: Float,
    endX: Float,
    itemView: View,
    animView: View,
    editView: View? = null
) {
    val slideAnimation = ObjectAnimator.ofFloat(itemView, "translationX", startX, endX)
    slideAnimation.duration = 200

    val fadeAnimation = ObjectAnimator.ofFloat(animView, "translationX", startX, endX)
    fadeAnimation.duration = 200

    if (editView == null) {
        val animSet = AnimatorSet()
        animSet.play(slideAnimation).with(fadeAnimation)
        animSet.start()
    } else {
        val fadeEditAnimation = ObjectAnimator.ofFloat(editView!!, "translationX", startX, endX)
        fadeEditAnimation.duration = 200
        val animSet = AnimatorSet()
        animSet.play(slideAnimation).with(fadeAnimation).with(fadeEditAnimation)
        animSet.start()
    }

}


val Int.toDp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()


fun Context.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun ImageView.showRoundedImage(
    roundedSize: Int = 8.toDp,
    imageUrl: String,
) {
    val requestOptions = RequestOptions()
        .transforms(CenterCrop(), RoundedCorners(roundedSize))
        .timeout(3000)
        .placeholder(shimmerDrawable())
    Glide.with(this)
        .load(imageUrl)
        .apply(requestOptions)
        .into(this)
}


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun Fragment.runWithArgumentsOrSkip(block: (bundle: Bundle) -> Unit) =
    this.arguments?.let(block) ?: Unit


fun DialogFragment?.tuneLyricsDialog() {
    this?.dialog?.let { dialog ->
        val window = dialog.window ?: return
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}

inline fun bindingLifecycleError(): Nothing = throw IllegalStateException(
    "This property is only valid between onCreateView and onDestroyView."
)


fun DialogFragment.showOnlyOne(fragmentManager: FragmentManager) {
    if (fragmentManager.findFragmentByTag(this.javaClass.name) == null)
        this.show(fragmentManager, this.javaClass.name)
}

