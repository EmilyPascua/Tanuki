package com.example.tanuki.utils

import com.example.tanuki.model.PaymentEntity
import java.util.*
import kotlin.collections.ArrayList
import com.example.tanuki.R
import java.text.SimpleDateFormat;
import kotlin.collections.HashMap

class Data {
        //        val id: Int,
        //        val Date
        //        val payment: Double,
        //        val type: String,
        //
        //        val iconStr: String,
        //        val backgroundStr: String,
        //
        //        val isBill: Boolean
        fun createCalendar(): ArrayList<PaymentEntity.Payment> {
            var testData = ArrayList<PaymentEntity.Payment>()
            testData.add(PaymentEntity.Payment(0, Date(2019,11,6), 60.75, "Test-01", R.drawable.wifi_foreground, R.color.colorAccent,true))
            testData.add(PaymentEntity.Payment(1, Date(2019,11,6), 60.75, "Test-02", R.drawable.wifi_foreground, R.color.colorAccent,true))
            testData.add(PaymentEntity.Payment(2, Date(2019,11,6), 60.75, "Test-03", R.drawable.wifi_foreground, R.color.colorAccent,true))
            testData.add(PaymentEntity.Payment(3, Date(2019,11,6), 60.75, "Test-04", R.drawable.wifi_foreground, R.color.colorAccent,true))
            testData.add(PaymentEntity.Payment(4, Date(2020,0,6), 60.75, "Test-05", R.drawable.wifi_foreground, R.color.colorAccent,true))
            testData.add(PaymentEntity.Payment(5, Date(2020,0,6), 60.75, "Test-05", R.drawable.wifi_foreground, R.color.colorAccent,false))
            return testData
        }

    fun getCalendarSortedByDate(): HashMap<String, ArrayList<PaymentEntity.Payment>> {
        // Nowe we have the calendar data
        val calendarHm:HashMap<String, ArrayList<PaymentEntity.Payment>> = HashMap()
        val sdf = SimpleDateFormat("mm-dd-yyyy")
        var testData = createCalendar()
        for (item in testData){
            System.out.println(sdf.format(item.date))
        }
        for (item in testData){
            // If key does exist, get it then add to array
            if (calendarHm.containsKey(sdf.format(item.date).toString())){
            // If the key already exists, get the key and add the object
                calendarHm.get(sdf.format(item.date).toString())?.add(item)
                //
            }else{
                val newArray = ArrayList<PaymentEntity.Payment>()
                newArray.add(item)
                calendarHm.put(sdf.format(item.date).toString(),newArray)
                // Else, add it as a new key
            }
        }
        return calendarHm
    }
}