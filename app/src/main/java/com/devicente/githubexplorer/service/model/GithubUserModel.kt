package com.devicente.githubexplorer.service.model

import com.squareup.moshi.Json


data class GithubUserModel(
    @Json(name = "login") val login: String,
    @Json(name = "avatar_url") val avatar_url: String,
    @Json(name = "bio") val bio: String,
    @Json(name = "public_repos") val public_repos: String,
    @Json(name = "public_gists") val public_gists: String,
    @Json(name = "followers") val followers: String,
    @Json(name = "following") val following: String
)