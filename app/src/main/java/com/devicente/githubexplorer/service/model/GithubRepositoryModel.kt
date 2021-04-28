package com.devicente.githubexplorer.service.model

import com.squareup.moshi.Json


data class GithubRepositoryModel(@Json(name = "name") val name: String)