package com.example.tanuki.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tanuki.R
import com.example.tanuki.model.FeedModel
import com.example.tanuki.model.PaymentEntity
import kotlinx.android.synthetic.main.message_item.view.*
import java.text.SimpleDateFormat

class ChatAdapter(private val payments: ArrayList<Array<String>>) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    private val bills: ArrayList<Array<String>> = payments

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ChatViewHolder(inflater.inflate(R.layout.message_item, parent, false))
    }

    override fun getItemCount(): Int {
        return bills.size
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(bills[position])
    }

    class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(bill: Array<String>) {
            itemView.chat_username.text = "UserName"
            itemView.chat_message.text = bill[0]
            itemView.chat_date.text = "Date"

            itemView.tanuki_repsonse.text = bill[1]
            itemView.chat_date.text = "Date"
        }
    }
}