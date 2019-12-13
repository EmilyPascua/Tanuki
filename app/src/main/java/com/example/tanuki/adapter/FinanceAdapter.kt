package com.example.tanuki.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tanuki.R

import androidx.recyclerview.widget.RecyclerView
import com.example.tanuki.model.PaymentEntity
import kotlinx.android.synthetic.main.calendar_item.view.*
import kotlinx.android.synthetic.main.finance_item.view.*
import java.text.SimpleDateFormat

class FinanceAdapter(private val billsin: ArrayList<PaymentEntity.Payment>) : RecyclerView.Adapter<FinanceAdapter.FinanceViewHolder>() {

    private var bills: ArrayList<PaymentEntity.Payment> = billsin

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinanceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FinanceViewHolder(inflater.inflate(R.layout.finance_item, parent, false))
    }

    fun updateFeed(feed: ArrayList<PaymentEntity.Payment>) {
        bills = feed
        notifyDataSetChanged()
        Log.d("updateFeed","has ran")
    }

    override fun getItemCount(): Int {
        return bills.size
    }

    override fun onBindViewHolder(holder: FinanceViewHolder, position: Int) {
        holder.bind(bills[position])
    }

    class FinanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(payment: PaymentEntity.Payment) {
            itemView.finance_usericon.setImageResource(payment.iconStr)
            itemView.finance_usericon.setCircleBackgroundColorResource(payment.backgroundStr)
            itemView.finance_usericon.borderColor = payment.backgroundStr
            System.out.println("border color: " + payment.backgroundStr)
            System.out.println("background color: " + payment.backgroundStr)

            if(payment.isBill){
                itemView.finance_bill_or_spending.text = "You have a bill of "
                itemView.finance_amount.text = "$" + payment.amount
                itemView.finance_type.text = " for " + payment.type + "."

            }else{
                itemView.finance_bill_or_spending.text = "You spent "
                itemView.finance_amount.text = "$" + payment.amount
                itemView.finance_type.text = " on " + payment.type + "."
            }
            val sdf = SimpleDateFormat("MM/dd/yy")
            itemView.finance_date.text = "" + sdf.format(payment.date)
        }

    }
}