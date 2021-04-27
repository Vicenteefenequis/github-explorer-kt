package com.devicente.githubexplorer.service.constants

class GithubExplorerConstants {
    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    object HTTP {
        const val SUCCESS = 200
    }

    object BUNDLE {
        const val GITHUB_NAME_USER = "searchname"
        const val GITHUBFILTER = "githubfilter"
        const val GITHUBREPOSITORY = "githubrepository"
    }

    object FILTER {
        const val REPOS = 0
        const val GISTS = 1
        const val FOLLOWERS = 2
        const val FOLLOWING = 3
    }



}