package com.example.dummy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.POST

class ViewOne : AppCompatActivity() {
    var PostId=0
    lateinit var tvPostTitle:TextView
    lateinit var tvPostBody:TextView
    lateinit var rvComments:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_one)
        PostId=intent.getIntExtra("POST_ID",0)
        tvPostTitle=findViewById(R.id.tvPostTitle)
        tvPostBody=findViewById(R.id.tvPostBody)
        rvComments=findViewById(R.id.rvComments)
        rvComments.layoutManager=LinearLayoutManager(baseContext)
        fetchPostById()
    }


    fun fetchPostById(){
        var apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
        var request=apiClient.getPostsId(PostId)
        request.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if(response.isSuccessful) {
                    var posts=response.body()
                    tvPostTitle.text=posts?.title
                    tvPostBody.text=posts?.body

                }
            }
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
    fun getComments(){
        var apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
        var request=apiClient.getComments(PostId.toString())
        request.enqueue(object : Callback<List<Comments>> {
            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                if (response.isSuccessful) {
                    var commentList = response.body()
                    if (commentList != null) {
                        var commentAdapter = CommentsRvAdapter(baseContext,commentList)
                        rvComments.adapter = commentAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }


}