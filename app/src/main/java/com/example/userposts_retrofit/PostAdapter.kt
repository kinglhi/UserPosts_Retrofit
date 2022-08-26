package com.example.userposts_retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.userposts_retrofit.data.DataSource
import com.example.userposts_retrofit.databinding.ItemPostBinding
import com.example.userposts_retrofit.model.Post

class PostAdapter(
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    /** Using local data source
     * private val dataset: List<Post> = DataSource().loadPosts()
     */

    inner class PostViewHolder(val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var dataset: List<Post>
        get() = differ.currentList
        set(value) { differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.binding.apply {
            val post = dataset[position]
            tvTitle.text = post.title
            tvBody.text = post.body
        }
    }

    override fun getItemCount(): Int = dataset.size
}