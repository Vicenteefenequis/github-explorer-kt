package com.devicente.githubexplorer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devicente.githubexplorer.service.listeners.APIListener
import com.devicente.githubexplorer.service.listeners.ValidationListener
import com.devicente.githubexplorer.service.model.GithubUserModel
import com.devicente.githubexplorer.service.repository.GithubInfoRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mGithubInfoRepository = GithubInfoRepository(application)

    private val mInfoList = MutableLiveData<GithubUserModel>()
    var infoList: LiveData<GithubUserModel> = mInfoList

    private val mValidation = MutableLiveData<ValidationListener>()
    var validation: LiveData<ValidationListener> = mValidation


    fun load(name: String) {
        mGithubInfoRepository.load(name, object: APIListener<GithubUserModel>{
            override fun onSuccess(model: GithubUserModel) {
                mInfoList.value = model
                mValidation.value = ValidationListener()
            }
            override fun onFailure(str: String) {
                mValidation.value = ValidationListener(str)
            }
        })
    }




}