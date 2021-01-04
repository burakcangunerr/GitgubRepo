package com.example.gitgubrepo.helper.RetrofitHelper

import com.example.gitgubrepo.model.ReposModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GithupApi{

    @GET("users/{user}/repos")
    fun getRepos(@Path("user") user: String?): Call<List<ReposModel>>


}