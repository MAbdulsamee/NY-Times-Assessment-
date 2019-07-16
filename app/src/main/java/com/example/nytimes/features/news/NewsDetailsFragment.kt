package com.example.nytimes.features.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nytimes.AppBundles
import com.example.nytimes.R
import com.example.nytimes.data.models.NewsItem
import com.example.nytimes.helpers.loadUrl
import kotlinx.android.synthetic.main.fragment_news_details.*

class NewsDetailsFragment: Fragment() {

    companion object {
        fun buildBundle(newsItem: NewsItem): Bundle {
            val bundle = Bundle()
            bundle.putParcelable(AppBundles.DATA, newsItem)
            return bundle
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getParcelable<NewsItem>(AppBundles.DATA)

        data?.let {

            imageView.loadUrl(data.media[0].mediaMetadata[0].url)
            tvDate.text = it.published_date
            tvTitle.text = it.title
            tvDetails.text = it.abstract
        }

    }
}