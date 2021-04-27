package com.devicente.githubexplorer.service.listeners

interface APIListener<T> {

    fun onSuccess(model: T)
    fun onFailure(str: String)
}