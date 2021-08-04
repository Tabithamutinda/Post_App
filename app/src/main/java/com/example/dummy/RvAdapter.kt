package com.example.dummy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent

class RvAdapter(var context: List<Post>?, var postList: Context):RecyclerView.Adapter<Item>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):Item {
        val itemView=
            LayoutInflater.from(parent.context).inflate(R.layout.row_item,parent,false)
        return Item(itemView)
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        var currentPost=postList.get(position)
        holder.tvuserid.text=currentPost.userId.toString()
        holder.tvid.text=currentPost.id.toString()
        holder.tvtitle.text=currentPost.title
        holder.tvbody.text=currentPost.body
        holder.cvpost.setOnClickListener {
            var intent = Intent(context, ViewOne::class.java)
            intent.putExtra("POST_ID",currentPost.id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return postList.size
    }
}


class Item(itemView: View): RecyclerView.ViewHolder(itemView){
   var rvpost=itemView.findViewById<RecyclerView>(R.id.tvtitle)
    var cvpost=itemView.findViewById<CardView>(R.id.cvpost)
    var tvtitle=itemView.findViewById<TextView>(R.id.tvtitle)
    var tvuserid=itemView.findViewById<TextView>(R.id.tvuserid)
    var tvid=itemView.findViewById<TextView>(R.id.tvid)
    var tvbody=itemView.findViewById<TextView>(R.id.tvbody)
}