package com.devicente.githubexplorer.service.model

import com.squareup.moshi.Json


data class GithubRepoInfoModel(
    @Json(name = "stargazers_count") val star: String,
    @Json(name = "watchers_count") val watcher: String,
    @Json(name = "open_issue") val open_issue: String,
    @Json(name = "forks_count") val forks: String
)