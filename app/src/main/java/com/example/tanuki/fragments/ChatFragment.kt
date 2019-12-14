package com.example.tanuki.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.tanuki.R
import com.google.android.material.tabs.TabLayout
import android.view.MenuInflater
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tanuki.adapter.ChatAdapter
import com.example.tanuki.adapter.FeedAdapter
import com.example.tanuki.model.PaymentEntity
import com.example.tanuki.utils.Data
import java.lang.NumberFormatException
import java.util.*
import kotlin.collections.ArrayList


class ChatFragment : Fragment() {
    private lateinit var submit: Button
    private lateinit var chatMessage: EditText
    private lateinit var chatList: RecyclerView
    private var messages = ArrayList<Array<String>>()

    val tanukiResponseFood: String = ("Consider doing some meal prep")
    val tanukiResponseTravel: String = ("You know there's MetroLink, Right?")
    val tanukiResponseGroceries: String = ("Ooo... Doing some cooking tonight?")
    val tanukiResponseOther: String = ("Alrighty!")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_chat, container, false)

        submit = root.findViewById(R.id.submit_button)
        chatMessage = root.findViewById(R.id.chat_message)
        chatList = root.findViewById(R.id.chat_list)

        chatList.layoutManager = LinearLayoutManager(context)

//      food -> save a bit, do meal prep
//      travel -> you know theres metrolink right
//      groceries ->
//      Other -> your wallet is gonna feel this one
        submit.setOnClickListener {
            val date: Date = Date()
            val messageResponse = arrayOf("Message","Response")
            val message: String = chatMessage.text.toString()
            var type: String = message
            val payment: Double = 0.0
            val isBill: Boolean = false
            val newAmount: PaymentEntity.Payment =
                PaymentEntity.Payment(0.toString(), Date(date.year,date.month,date.day), payment, type, R.drawable.celebration_80, R.color.medium_blue,isBill)

            val enteredAmount: String = ""
//            for (c in message) {
//                if (c.isDigit()){
////                    if (c == ".")
//                    payment
//                }
//            }

            val (digits, notDigits) = message.partition { it.isDigit() }

            var tanukiResponse: String = ""
            var yourMessage = ""
            try{
                digits.toDouble()
                if(notDigits.contains("food")){
                    type = "food"
                    tanukiResponse = tanukiResponseFood
                } else if (notDigits.contains("travel")){
                    type = "travel"
                    tanukiResponse = tanukiResponseTravel
                } else if (notDigits.contains("groceries")){
                    type = "groceries"
                    tanukiResponse = tanukiResponseGroceries
                } else if (notDigits.contains("other")){
                    type = "other"
                    tanukiResponse = tanukiResponseOther
                }
                val yourMessage = "You made a payment of $" + digits + " on " + type +"."
                tanukiResponse
            }catch (e: NumberFormatException){
                tanukiResponse = "Sorry, I didn't understand that. Can you format your message as \"\$Amount on type\"."
                yourMessage = message
            }
            messageResponse[0] = yourMessage
            messageResponse[1] = tanukiResponse
            messages.add(messageResponse)
            messages.forEach { System.out.println(it[0])}
            messages.reverse()
            chatList.adapter = ChatAdapter(messages)

        }
        return root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu.findItem(com.example.tanuki.R.id.chat_item)

        if (item != null)
            item.isVisible = false
    }

    override fun onResume() {
        super.onResume()
        var act = (activity as AppCompatActivity)
        println("ON RESUME!!!!!!!!!!!!!!!!!!")
        act.findViewById<TabLayout>(R.id.tabs).setVisibility(View.GONE)
    }

    override fun onStop() {
        super.onStop()
        var act = (activity as AppCompatActivity)
        println("ON STOP!!!!!!!!!!!!!!!!!!")
        act.findViewById<TabLayout>(R.id.tabs).setVisibility(View.VISIBLE)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
