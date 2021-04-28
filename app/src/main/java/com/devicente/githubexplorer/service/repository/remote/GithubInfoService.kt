package com.devicente.githubexplorer.service.repository.remote

import com.devicente.githubexplorer.data.GithubFollowers
import com.devicente.githubexplorer.data.GithubRepository
import com.devicente.githubexplorer.service.model.GithubRepoInfoModel
import com.devicente.githubexplorer.service.model.GithubUserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubInfoService {
    @GET("/users/{name}")
    fun load(@Path(value = "name", encoded = true) name: String): Call<GithubUserModel>

    @GET("/users/{name}/repos")
    fun loadRepos(
        @Path(value = "name", encoded = true) name: String,
    ): Call<List<GithubRepository>>

    @GET("/users/{name}/followers")
    fun loadFollowers(
        @Path(value = "name", encoded = true) name: String,
    ): Call<List<GithubFollowers>>

    @GET("/users/{name}/following")
    fun loadFollowing(
        @Path(
            value = "name",
            encoded = true
        ) name: String
    ): Call<List<GithubFollowers>>

    @GET("/repos/{name}/{repository}")
    fun loadInformationRepository(
        @Path(value = "name", encoded = true) name: String,
        @Path(value = "repository", encoded = true) repository: String
    ): Call<GithubRepoInfoModel>

}