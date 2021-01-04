package com.example.gitgubrepo.helper.RetrofitHelper

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper
{
    private var retrofit : Retrofit? = null

    private var  service : GithupApi? = null

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val Client : Retrofit?
        get()
        {
            if(retrofit == null)
                retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build()

            return retrofit
        }


    val Call : GithupApi?
        get()
        {
            if(service == null)
                service =  Client!!.create(
                    GithupApi::class.java)

            return service
        }

}