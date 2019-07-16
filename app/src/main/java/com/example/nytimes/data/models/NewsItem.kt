package com.example.nytimes.data.models

import android.os.Parcel
import android.os.Parcelable

data class NewsItem(
    val abstract: String?,
    val media: List<Media>,
    val published_date: String?,
    val section: String?,
    val source: String?,
    val title: String?,
    val type: String?,
    val uri: String?,
    val url: String?,
    val views: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString(), arrayListOf<Media>().apply {
            parcel.readList(this, Media::class.java.classLoader)
        }, parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsItem> {
        override fun createFromParcel(parcel: Parcel): NewsItem {
            return NewsItem(parcel)
        }

        override fun newArray(size: Int): Array<NewsItem?> {
            return arrayOfNulls(size)
        }
    }
}