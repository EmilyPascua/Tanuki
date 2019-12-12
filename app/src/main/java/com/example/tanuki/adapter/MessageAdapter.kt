package com.example.tanuki.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tanuki.App
import com.example.tanuki.R
import com.example.tanuki.model.PaymentEntity
import kotlinx.android.synthetic.main.my_message.view.*
import kotlinx.android.synthetic.main.bot_message.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

private const val VIEW_TYPE_MY_MESSAGE = 1
private const val VIEW_TYPE_BOT_MESSAGE = 2

class MessageAdapter (val context: Context) : RecyclerView.Adapter<MessageViewHolder>() {

    private val messages: ArrayList<PaymentEntity.Payment> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return MyMessageViewHolder(inflater.inflate(R.layout.fragment_chat, parent, false))
//        return if(viewType == VIEW_TYPE_MY_MESSAGE) {
//            MyMessageViewHolder(LayoutInflater.from(context).inflate(R.layout.my_message, parent, false))
//        } else {
//            BotMessageViewHolder(LayoutInflater.from(context).inflate(R.layout.bot_message, parent, false))
//        }
    }

    fun addMessage(message: PaymentEntity.Payment){
        messages.add(message)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages.get(position)

        return if(App.user == message.user) {
            VIEW_TYPE_MY_MESSAGE
        } else  {
            VIEW_TYPE_BOT_MESSAGE
        }
    }



    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages.get(position)

        holder?.bind(message)
    }

    inner class MyMessageViewHolder (view: View) : MessageViewHolder(view) {
        private var messageText: TextView = view.txtMyMessage
        private var timeText: TextView = view.txtMyMessageTime

        override fun bind(message: PaymentEntity.Payment) {
            messageText.text = message.type
//            timeText.text = DateUtils.fromMillisToTimeString(message.time)
        }
    }

    inner class BotMessageViewHolder (view: View) : MessageViewHolder(view) {
        private var messageText: TextView = view.txtBotMessage
        private var userText: TextView = view.txtBotUser
        private var timeText: TextView = view.txtBotMessageTime

        override fun bind(message: PaymentEntity.Payment) {
            messageText.text = message.type
            userText.text = message.user
//            timeText.text = DateUtils.fromMillisToTimeString(message.time)
        }
    }
}

object DateUtils {
    fun fromMillisToTimeString(millis: Long) : String {
        val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return format.format(millis)
    }
}

open class MessageViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(message:PaymentEntity.Payment) {}
}