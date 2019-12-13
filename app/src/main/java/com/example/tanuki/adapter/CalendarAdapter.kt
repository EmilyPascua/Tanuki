package com.example.tanuki.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tanuki.R

import androidx.recyclerview.widget.RecyclerView
import com.example.tanuki.model.PaymentEntity
import kotlinx.android.synthetic.main.calendar_item.view.*
import java.text.SimpleDateFormat

class CalendarAdapter(bills: ArrayList<PaymentEntity.Payment>) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    private var bills: ArrayList<PaymentEntity.Payment> = bills

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CalendarViewHolder(inflater.inflate(R.layout.calendar_item, parent, false))
    }

    fun updateBills(items: ArrayList<PaymentEntity.Payment>) {
        bills = items
        notifyDataSetChanged()
        Log.d("updateFeed","has ran")
    }

    fun getBills(): ArrayList<PaymentEntity.Payment> {
        return this.bills
    }

    override fun getItemCount(): Int {
        return bills.size
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(bills[position])
    }

    class CalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(payment: PaymentEntity.Payment) {
            itemView.calendar_icon.setImageResource(payment.iconStr)
            itemView.calendar_icon.setCircleBackgroundColorResource(payment.backgroundStr)
            itemView.calendar_icon.borderColor = payment.backgroundStr
            itemView.calendar_item_header.text = payment.type + " Bill"
            itemView.calendar_item_amount.text = "Amount Due: $" + payment.amount
            val sdf = SimpleDateFormat("MM/dd/yy")

            itemView.calendar_item_date.text = "Due Date: " + sdf.format(payment.date)
        }

    }
}