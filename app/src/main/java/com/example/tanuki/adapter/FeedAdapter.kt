package com.example.tanuki.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tanuki.R
import com.example.tanuki.model.FeedModel
import kotlinx.android.synthetic.main.feed_item.view.*
import java.text.SimpleDateFormat

class FeedAdapter(private val allposts: ArrayList<FeedModel>) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    private val posts: ArrayList<FeedModel> = allposts

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FeedViewHolder(inflater.inflate(R.layout.feed_item, parent, false))
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: FeedModel) {

            itemView.feed_username.text = post.name
            itemView.feed_amount.text = "$" + post.money.toString()
            itemView.feed_message.text = post.message
            if(post.earning)
                itemView.feed_earning.text = "earned"
            else
                itemView.feed_earning.text = "paid"
            val sdf = SimpleDateFormat("MM/dd/yy")
            itemView.feed_date.text = "" + sdf.format(post.date)

        }
    }
}