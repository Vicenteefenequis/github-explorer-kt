package com.devicente.githubexplorer.service.model

import com.google.gson.annotations.SerializedName

class GithubRepositoryInformation {
    @SerializedName("stargazers_count")
    var star = ""

    @SerializedName("watchers_count")
    var watcher = ""

    @SerializedName("open_issues_count")
    var open_issue = ""

    @SerializedName("forks_count")
    var forks = ""
}