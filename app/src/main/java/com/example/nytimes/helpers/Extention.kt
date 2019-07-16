package com.example.nytimes.helpers

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nytimes.interfaces.OnItemClickListener

fun ImageView.loadUrl(url: Any) {
    Glide.with(context)
        .load(url)
//        .placeholder(placeHolder)
//        .error(placeHolder)
        .into(this)
}

fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewDetachedFromWindow(view: View) {
            view.setOnClickListener(null)
        }

        override fun onChildViewAttachedToWindow(view: View) {
            view.setOnClickListener { view ->
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition)
            }
        }
    })
}