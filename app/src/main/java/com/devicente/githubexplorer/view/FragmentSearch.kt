package com.devicente.githubexplorer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devicente.githubexplorer.R
import com.devicente.githubexplorer.databinding.FragmentSearchBinding
import com.devicente.githubexplorer.service.constants.GithubExplorerConstants
import com.devicente.githubexplorer.view.Comunicator.Communicator
import com.devicente.githubexplorer.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_search.*


class FragmentSearch : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var communicator: Communicator
    private lateinit var mViewModel: MainViewModel;
    private var mNameUser = ""
    private var mNameUserBundle = "";


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        communicator = activity as Communicator;

        mNameUserBundle =
            arguments?.getString(GithubExplorerConstants.BUNDLE.GITHUB_NAME_USER, "").toString()




        observer()

        binding.buttonSearch.setOnClickListener {
            searchUser()
        }

        binding.editextSearching.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editext_searching.error = null
            }
        }

        binding.buttonPublicRepos.setOnClickListener {
            communicator.passDataCom(
                binding.textUserName.text.toString(),
                GithubExplorerConstants.FILTER.REPOS
            )
        }

        binding.buttonFollowers.setOnClickListener {
            communicator.passDataCom(
                binding.textUserName.text.toString(),
                GithubExplorerConstants.FILTER.FOLLOWERS
            )
        }

        binding.buttonFollowing.setOnClickListener {
            communicator.passDataCom(
                binding.textUserName.text.toString(),
                GithubExplorerConstants.FILTER.FOLLOWING
            )
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (mNameUserBundle != "" && mNameUserBundle != "null")
            mViewModel.load(mNameUserBundle)

    }


    private fun observer() {
        mViewModel.infoList.observe(viewLifecycleOwner, Observer {
            text_description.text = it.bio
            Picasso.with(context).load(it.avatar_url).into(image_user)
            text_user_name.text = it.login
            followers.text = it.followers
            text_following.text = it.following
            public_repos.text = it.public_repos
            wrapper_horizontal_information.visibility = View.VISIBLE;
            wrapper_profile_info.visibility = View.VISIBLE;
            showLoading(false)
        })

        mViewModel.validation.observe(viewLifecycleOwner, Observer {
            if (!it.success()) {
                showLoading(false)
                editext_searching.error = it.failure()
            } else {
                editext_searching.setText("")
            }
        })
    }


    private fun searchUser() {
        val query = editext_searching.text.toString()
        mNameUser = query;
        if (query.isEmpty()) return
        showLoading(true)
        mViewModel.load(query)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            loadingBar.visibility = View.VISIBLE
        } else {
            loadingBar.visibility = View.GONE
        }
    }


}