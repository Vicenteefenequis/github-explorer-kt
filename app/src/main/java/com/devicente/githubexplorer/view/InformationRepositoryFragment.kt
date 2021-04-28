package com.devicente.githubexplorer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.devicente.githubexplorer.R
import com.devicente.githubexplorer.service.constants.GithubExplorerConstants
import com.devicente.githubexplorer.viewmodel.InformationRepositoryViewModel
import kotlinx.android.synthetic.main.fragment_information_repository.*

class InformationRepositoryFragment : Fragment() {
    private val mViewModel: InformationRepositoryViewModel by viewModels();
    private var mNameUser = "";
    private var mRepoName = "";


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_information_repository, container, false)
        mNameUser = arguments?.getString(
            GithubExplorerConstants.BUNDLE.GITHUB_NAME_USER,
            "vicenteefenequis"
        )!!;
        mRepoName =
            arguments?.getString(GithubExplorerConstants.BUNDLE.GITHUBREPOSITORY, "bin2dec")!!;

        observe()
        return root;
    }

    override fun onResume() {
        super.onResume()
        mViewModel.loadInformation(mNameUser, mRepoName)
    }

    private fun observe() {
        mViewModel.infosRepository.observe(viewLifecycleOwner, Observer {
            text_num_forks.text = it.forks
            text_num_open_issues.text = it.open_issue
            text_num_starts.text = it.star
            text_num_watchers.text = it.watcher

            loadingBar_Infos.visibility = View.GONE
            view_card_infos_repository.visibility = View.VISIBLE
        })
    }
}