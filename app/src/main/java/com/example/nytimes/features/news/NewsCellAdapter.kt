package com.example.nytimes.features.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimes.R
import com.example.nytimes.data.models.NewsItem
import com.example.nytimes.helpers.loadUrl
import kotlinx.android.synthetic.main.cell_news.view.*

class NewsCellAdapter (private val list: List<NewsItem>): RecyclerView.Adapter<NewsCellAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cell_news, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.image.loadUrl(item.media[0].mediaMetadata[0].url)
        holder.title.text = item.title
        holder.abstract.text = item.abstract

    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.imageView
        val title: TextView = itemView.tvTitle
        val abstract: TextView = itemView.tvAbstract
    }
}