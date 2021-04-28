package com.devicente.githubexplorer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.devicente.githubexplorer.R
import com.devicente.githubexplorer.data.GithubFollowers
import com.devicente.githubexplorer.data.GithubRepository
import com.devicente.githubexplorer.databinding.FragmentResultBinding
import com.devicente.githubexplorer.service.constants.GithubExplorerConstants
import com.devicente.githubexplorer.view.adapter.GithubListGenericAdapter
import com.devicente.githubexplorer.viewmodel.GithubViewModel
import java.lang.IllegalArgumentException


class ResultFragment : Fragment() {

    private val mViewModel: GithubViewModel by viewModels();
    private lateinit var binding: FragmentResultBinding;
    private var mGithubFilter = 0
    private var mNameUserGithub = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentResultBinding.inflate(inflater, container, false)

        mGithubFilter = arguments?.getInt(GithubExplorerConstants.BUNDLE.GITHUBFILTER, 0)!!
        mNameUserGithub = arguments?.getString(
            GithubExplorerConstants.BUNDLE.GITHUB_NAME_USER,
            "vicenteefenequis"
        )!!

        binding.recyclerAllTasks.layoutManager = LinearLayoutManager(context)

        setupLoadInfo()
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        binding.loadingBar.visibility = View.VISIBLE
        mViewModel.loadGithub(mNameUserGithub, mGithubFilter)
    }


    private fun setupLoadInfo() {
        when (mGithubFilter) {
            GithubExplorerConstants.FILTER.REPOS -> {
                binding.recyclerAllTasks.adapter =
                    object : GithubListGenericAdapter<GithubRepository>(
                        R.layout.row_github_list,
                        bind = { item, holder, _ ->
                            with(holder.itemView) {
                                this.findViewById<TextView>(R.id.text_name_repository).text =
                                    item.name
                                this.findViewById<CardView>(R.id.card_item_github)
                                    .setOnClickListener {
                                        val bundle = Bundle()
                                        bundle.putString(
                                            GithubExplorerConstants.BUNDLE.GITHUB_NAME_USER,
                                            mNameUserGithub
                                        );
                                        bundle.putString(
                                            GithubExplorerConstants.BUNDLE.GITHUBREPOSITORY,
                                            item.name
                                        )
                                        setupNavigationFragment(
                                            InformationRepositoryFragment(),
                                            bundle
                                        )


                                    }
                            }
                        }
                    ) {}.apply {
                        mViewModel.githubList.observe(viewLifecycleOwner) { repositories ->
                            submitList(repositories)
                            binding.loadingBar.visibility = View.GONE
                        }
                    }
            }
            GithubExplorerConstants.FILTER.FOLLOWERS -> {
                binding.recyclerAllTasks.adapter = object :
                    GithubListGenericAdapter<GithubFollowers>(
                        R.layout.row_github_list,
                        bind = { item, holder, _ ->
                            with(holder.itemView) {
                                this.findViewById<TextView>(R.id.text_name_repository).text =
                                    item.login
                                this.findViewById<CardView>(R.id.card_item_github)
                                    .setOnClickListener {
                                        val bundle = Bundle()
                                        bundle.putString(
                                            GithubExplorerConstants.BUNDLE.GITHUB_NAME_USER,
                                            item.login
                                        );
                                        setupNavigationFragment(SearchFragment(), bundle)
                                    }
                            }
                        }
                    ) {}.apply {
                    mViewModel.listFollowers.observe(viewLifecycleOwner) { followers ->
                        submitList(followers)
                        binding.loadingBar.visibility = View.GONE
                    }
                }
            }
            GithubExplorerConstants.FILTER.FOLLOWING -> {
                binding.recyclerAllTasks.adapter = object :
                    GithubListGenericAdapter<GithubFollowers>(
                        R.layout.row_github_list,
                        bind = { item, holder, _ ->
                            with(holder.itemView) {
                                this.findViewById<TextView>(R.id.text_name_repository).text =
                                    item.login
                                this.findViewById<CardView>(R.id.card_item_github)
                                    .setOnClickListener {
                                        val bundle = Bundle()
                                        bundle.putString(
                                            GithubExplorerConstants.BUNDLE.GITHUB_NAME_USER,
                                            item.login
                                        );
                                        setupNavigationFragment(SearchFragment(), bundle)
                                    }
                            }
                        }
                    ) {}.apply {
                    mViewModel.listFollowers.observe(viewLifecycleOwner) { followers ->
                        submitList(followers)
                        binding.loadingBar.visibility = View.GONE
                    }
                }
            }
            else -> {
                throw IllegalArgumentException()
            }
        }

    }


    fun setupNavigationFragment(fragment: Fragment, bundle: Bundle) {
        val transaction = fragmentManager?.beginTransaction()
        fragment.arguments = bundle
        transaction?.replace(
            R.id.fragment_main_container,
            fragment
        )
        transaction?.addToBackStack(null)?.commit()
    }

}