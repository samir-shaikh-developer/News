package com.example.news.ui.home_news

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.data.model.News
import com.example.news.data.network.ApiClient
import com.example.news.data.network.ApiClientInterface
import com.example.news.data.network.FetchNewsData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeNewsRepo {

    companion object {
        /**
         * Fetch Data from server
         * @param country_code String
         * @return @Nullable MutableLiveData<News>
         */
        fun fetchNewsByCountry(country_code: String): MutableLiveData<News> {

            val allNews = MutableLiveData<News>()
            val apiClientInterface: ApiClientInterface = ApiClient.retrofit.create(ApiClientInterface::class.java)
            val news: Call<News> = apiClientInterface.getAllNewsByCountry(country_code)

            news.enqueue(object : Callback<News> {
                override fun onFailure(call: Call<News>, t: Throwable) {}

                override fun onResponse(call: Call<News>, response: Response<News>) {
                    allNews.value = response.body()
                }
            })
            return allNews
        }
    }
}