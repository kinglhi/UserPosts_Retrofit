package com.example.userposts_retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userposts_retrofit.data.DataSource
import com.example.userposts_retrofit.databinding.ItemPostBinding
import com.example.userposts_retrofit.model.Post

class PostAdapter(
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private val dataset: List<Post> = DataSource().loadPosts()

    inner class PostViewHolder(val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root)

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