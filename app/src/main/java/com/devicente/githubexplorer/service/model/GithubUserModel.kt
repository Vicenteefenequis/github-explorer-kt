package com.devicente.githubexplorer.service.model

import com.google.gson.annotations.SerializedName

class GithubUserModel {
    @SerializedName("login")
    var login = ""

    @SerializedName("avatar_url")
    var avatar_url = ""

    @SerializedName("bio")
    var bio = ""

    @SerializedName("public_repos")
    var public_repos = ""

    @SerializedName("public_gists")
    var public_gists = ""

    @SerializedName("followers")
    var followers = ""

}