package com.example.dummy

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts/{id}")
    fun getPostsId():Call<List<Post>>

    @GET("posts/id/{comments}")
    fun getComments(@Path("comments") Comments:String):Call<List<Comments>>
}