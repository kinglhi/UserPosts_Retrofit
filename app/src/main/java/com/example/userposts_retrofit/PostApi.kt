package com.example.userposts_retrofit

import com.example.userposts_retrofit.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface PostApi {
    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>
}