package com.devicente.githubexplorer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devicente.githubexplorer.data.GithubFollowers
import com.devicente.githubexplorer.data.GithubRepository
import com.devicente.githubexplorer.service.constants.GithubExplorerConstants
import com.devicente.githubexplorer.service.listeners.APIListener
import com.devicente.githubexplorer.service.listeners.ValidationListener
import com.devicente.githubexplorer.service.repository.GithubInfoRepository

class GithubViewModel(application: Application) : AndroidViewModel(application) {
    private val mGithubRepository = GithubInfoRepository(application)
    private var mGithubFilter = 0;


    private val mValidation = MutableLiveData<ValidationListener>()
    var validation: LiveData<ValidationListener> = mValidation

    private val mListGithub = MutableLiveData<List<GithubRepository>>()
    var githubList: LiveData<List<GithubRepository>> = mListGithub

    private val mListFollowers = MutableLiveData<List<GithubFollowers>>()
    var listFollowers: LiveData<List<GithubFollowers>> = mListFollowers


    fun loadGithub(name: String, githubFilter: Int) {
        mGithubFilter = githubFilter

        val listener = object : APIListener<List<GithubRepository>> {
            override fun onSuccess(model: List<GithubRepository>) {
                mListGithub.value = model;
            }

            override fun onFailure(str: String) {
                mListGithub.value = arrayListOf()
                mValidation.value = ValidationListener(str)
            }
        }

        val listenerFollowers = object : APIListener<List<GithubFollowers>> {
            override fun onSuccess(model: List<GithubFollowers>) {
                mListFollowers.value = model;
            }

            override fun onFailure(str: String) {
                mListGithub.value = arrayListOf()
                mValidation.value = ValidationListener(str)
            }
        }



        when (mGithubFilter) {
            GithubExplorerConstants.FILTER.REPOS -> {
                mGithubRepository.loadRepos(name, listener)
            }
            GithubExplorerConstants.FILTER.FOLLOWERS -> {
                mGithubRepository.loadFollowers(name, listenerFollowers)
            }
            GithubExplorerConstants.FILTER.FOLLOWING -> {
                mGithubRepository.loadFollowing(name, listenerFollowers)
            }
        }
    }
}