package com.example.nytimes.features.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytimes.R
import com.example.nytimes.data.models.NewsItem
import com.example.nytimes.data.models.NewsResponse
import com.example.nytimes.helpers.addOnItemClickListener
import com.example.nytimes.interfaces.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject


class NewsFragment : Fragment() {

    @Inject
    lateinit var viewModel: NewsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)

        loadNews()
    }


    private fun loadNews() {
        viewModel.getNewsRepository().observe(this, Observer<NewsResponse> { data ->
            data?.results?.let {
                setupNews(it)
            }
        })
    }

    private fun setupNews(data: List<NewsItem>) {

        val adapter = NewsCellAdapter(data)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int) {
                activity?.let {
                    Navigation.findNavController(it, R.id.nav_host_fragment).navigate(R.id.action_newsFragment_to_newsDetailsFragment, NewsDetailsFragment.buildBundle(data[position]))
                }
            }
        })

    }


}