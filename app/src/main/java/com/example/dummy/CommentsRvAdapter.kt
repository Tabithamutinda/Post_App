package com.example.dummy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommentsRvAdapter(var context: Context, var commentsList:List<Comments>): RecyclerView.Adapter<CommentsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.comment_list,parent,false)
        return CommentsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        var currentComments=commentsList.get(position)
        holder.name.text=currentComments.name
        holder.email.text=currentComments.email
        holder.body.text=currentComments.body
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }
}

class CommentsViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var name=itemView.findViewById<TextView>(R.id.tvName)
    var email=itemView.findViewById<TextView>(R.id.tvEmail)
    var body=itemView.findViewById<TextView>(R.id.tvBody1)
}