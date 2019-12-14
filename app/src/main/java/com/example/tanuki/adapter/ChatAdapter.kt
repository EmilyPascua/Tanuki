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
import java.util.*
import kotlin.collections.ArrayList

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
            val sdf = SimpleDateFormat("dd/M/yyyy")
            val currentDate = sdf.format(Date())

            itemView.chat_message.text = bill[0]
            itemView.chat_date.text =currentDate.toString()

            itemView.tanuki_repsonse.text = bill[1]
            itemView.tanuki_response_date.text = currentDate.toString()
        }
    }
}