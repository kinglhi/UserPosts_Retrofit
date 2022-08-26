package com.example.userposts_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userposts_retrofit.databinding.ActivityMainBinding
import com.example.userposts_retrofit.databinding.ItemPostBinding
import com.example.userposts_retrofit.model.Post
import okhttp3.Response
import retrofit2.HttpException
import java.io.IOException
import javax.sql.DataSource

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressbar.isVisible = true
            val response = try {
                RetrofitInstance.api.getPosts()
            } catch(e: IOException) {
                binding.progressbar.isVisible = false
//                Log.d(TAG, "IOException, check your internet connection & try again.")
                binding.tvError.text = "IOException, check your internet connection & try again."
                return@launchWhenCreated
            } catch(e: HttpException) {
                binding.progressbar.isVisible = false
//                Log.d(TAG, "Http Exception, unexpected response.")
                binding.tvError.text = "Http Exception, unexpected response."
                return@launchWhenCreated
            }

            if(response.isSuccessful && response.body() != null) {
                postAdapter.dataset = response.body()!!
            } else {
                Log.d(TAG, "Response unsuccessful.")
            }

            binding.progressbar.isVisible = false
        }
    }

    private fun setupRecyclerView() = binding.rvPosts.apply {
        postAdapter = PostAdapter()
        adapter = postAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}