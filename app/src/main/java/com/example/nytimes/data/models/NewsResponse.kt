package com.example.nytimes.data.models

data class NewsResponse (
  val copyright: String,
  val num_results: Int,
  val results: List<NewsItem>,
  val status: String
 )