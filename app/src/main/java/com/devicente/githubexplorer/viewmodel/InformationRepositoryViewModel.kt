package com.devicente.githubexplorer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devicente.githubexplorer.service.listeners.APIListener
import com.devicente.githubexplorer.service.listeners.ValidationListener
import com.devicente.githubexplorer.service.model.GithubRepoInfoModel
import com.devicente.githubexplorer.service.repository.GithubInfoRepository

class InformationRepositoryViewModel(application: Application) : AndroidViewModel(application) {

    private val mGithubRepositoryInformation = GithubInfoRepository(application)
    private val mInfosRepository = MutableLiveData<GithubRepoInfoModel>()
    val infosRepository: LiveData<GithubRepoInfoModel> = mInfosRepository

    private val mValidation = MutableLiveData<ValidationListener>()
    var validation: LiveData<ValidationListener> = mValidation


    fun loadInformation(name: String, repository: String) {
        mGithubRepositoryInformation.loadInformationRepos(
            name,
            repository,
            object : APIListener<GithubRepoInfoModel> {
                override fun onSuccess(model: GithubRepoInfoModel) {
                    mInfosRepository.value = model;
                }

                override fun onFailure(str: String) {
                    mValidation.value = ValidationListener(str)
                }

            })
    }


}