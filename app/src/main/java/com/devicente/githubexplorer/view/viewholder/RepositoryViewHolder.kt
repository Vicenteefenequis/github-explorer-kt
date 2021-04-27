package com.devicente.githubexplorer.view.viewholder


import androidx.recyclerview.widget.RecyclerView
import com.devicente.githubexplorer.data.GithubRepository
import com.devicente.githubexplorer.databinding.RowGithubListBinding

class RepositoryViewHolder(
    private val binding: RowGithubListBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: GithubRepository) {
        binding.textNameRepository.text = item.name
    }
}
