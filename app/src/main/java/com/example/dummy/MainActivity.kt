package com.example.dummy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var rvpost:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPosts()
    }
    fun getPosts(){
        val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = retrofit.getPosts()
        request.enqueue(object: Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response:
            Response<List<Post>>
            ) {
                if (response.isSuccessful){
                    var posts = response.body()
                    rvpost=findViewById(R.id.rvpost)
                    var postAdapter=RvAdapter(posts,baseContext)
                    rvpost.layoutManager=LinearLayoutManager(baseContext)
                    rvpost.adapter=postAdapter


                    Toast.makeText(baseContext, "${posts!!.size} posts", Toast.
                    LENGTH_LONG).show()

                    var postDate= mutableListOf<Post>()
                    for (x in 1..posts.size){
                        postDate.add(Post(4,5,"Tabitha","You are an excellent android developer"))
                    }


                }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
}

