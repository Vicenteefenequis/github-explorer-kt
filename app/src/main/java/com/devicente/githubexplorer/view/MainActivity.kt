package com.devicente.githubexplorer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devicente.githubexplorer.R
import com.devicente.githubexplorer.service.constants.GithubExplorerConstants
import com.devicente.githubexplorer.view.Comunicator.Communicator

class MainActivity : AppCompatActivity(), Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fragmentSearch = SearchFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_main_container, fragmentSearch).commit()

    }

    override fun passDataCom(name: String, action: Int) {
        val bundle = Bundle()
        bundle.putString(GithubExplorerConstants.BUNDLE.GITHUB_NAME_USER, name)
        bundle.putInt(GithubExplorerConstants.BUNDLE.GITHUBFILTER, action)

        val transaction = this.supportFragmentManager.beginTransaction()
        val fragmentResult = ResultFragment()
        fragmentResult.arguments = bundle

        transaction.replace(R.id.fragment_main_container, fragmentResult)
        transaction.addToBackStack(null).commit()
    }
}