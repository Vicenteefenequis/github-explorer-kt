package com.devicente.githubexplorer.service.repository

import android.content.Context
import android.util.Log
import com.devicente.githubexplorer.data.GithubFollowers
import com.devicente.githubexplorer.data.GithubRepository
import com.devicente.githubexplorer.service.constants.GithubExplorerConstants
import com.devicente.githubexplorer.service.listeners.APIListener
import com.devicente.githubexplorer.service.model.GithubRepositoryInformation
import com.devicente.githubexplorer.service.model.GithubUserModel
import com.devicente.githubexplorer.service.repository.remote.GithubInfoService
import com.devicente.githubexplorer.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubInfoRepository(val context: Context) {

    private val mRemote = RetrofitClient.createService(GithubInfoService::class.java)

    fun load(name: String, listener: APIListener<GithubUserModel>) {
        val call: Call<GithubUserModel> = mRemote.load(name)



        call.enqueue(object : Callback<GithubUserModel> {
            override fun onResponse(
                call: Call<GithubUserModel>,
                response: Response<GithubUserModel>
            ) {
                val code = response.code()
                if (code != GithubExplorerConstants.HTTP.SUCCESS) {
                    listener.onFailure("User Not Found")
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<GithubUserModel>, t: Throwable) {
                listener.onFailure("Um erro inesperado ocorreu , tente novamente mais tarde")
            }

        })

    }


    private fun <T> list(call: Call<List<T>>, listener: APIListener<List<T>>) {
        call.enqueue(object : Callback<List<T>> {
            override fun onResponse(
                call: Call<List<T>>,
                response: Response<List<T>>
            ) {
                val code = response.code()
                if (code != GithubExplorerConstants.HTTP.SUCCESS) {
                    listener.onFailure("Followers Not Found")
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<List<T>>, t: Throwable) {
                listener.onFailure("Um erro inesperado ocorreu , tente novamente mais tarde")
            }

        })
    }


    fun loadFollowers(name: String, listener: APIListener<List<GithubFollowers>>) {
        val call: Call<List<GithubFollowers>> = mRemote.loadFollowers(name)
        list(call, listener)
    }

    fun loadRepos(name: String, listener: APIListener<List<GithubRepository>>) {
        val call: Call<List<GithubRepository>> = mRemote.loadRepos(name)
        list(call, listener)
    }

    fun loadFollowing(name: String, listener: APIListener<List<GithubFollowers>>){
        val call: Call<List<GithubFollowers>> = mRemote.loadFollowing(name)
        list(call, listener)
    }

    fun loadInformationRepos(
        name: String,
        repository: String,
        listener: APIListener<GithubRepositoryInformation>
    ) {
        val call: Call<GithubRepositoryInformation> =
            mRemote.loadInformationRepository(name, repository)

        call.enqueue(object : Callback<GithubRepositoryInformation> {
            override fun onResponse(
                call: Call<GithubRepositoryInformation>,
                response: Response<GithubRepositoryInformation>
            ) {
                val code = response.code()
                if (code != GithubExplorerConstants.HTTP.SUCCESS) {
                    listener.onFailure("Followers Not Found")
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<GithubRepositoryInformation>, t: Throwable) {
                listener.onFailure("Um erro inesperado ocorreu , tente novamente mais tarde")
            }

        })
    }

}