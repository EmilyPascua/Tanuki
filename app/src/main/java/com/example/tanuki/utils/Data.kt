package com.example.tanuki.utils

import com.example.tanuki.model.PaymentEntity
import java.util.*
import kotlin.collections.ArrayList
import com.example.tanuki.R

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
            testData.add(PaymentEntity.Payment(0, Date(2019,12,6), 60.75, "Test-01", R.drawable.wifi_foreground, R.color.colorAccent,true))
            testData.add(PaymentEntity.Payment(1, Date(2019,12,6), 60.75, "Test-02", R.drawable.wifi_foreground, R.color.colorAccent,true))
            testData.add(PaymentEntity.Payment(2, Date(2019,12,6), 60.75, "Test-03", R.drawable.wifi_foreground, R.color.colorAccent,true))
            testData.add(PaymentEntity.Payment(3, Date(2019,12,6), 60.75, "Test-04", R.drawable.wifi_foreground, R.color.colorAccent,true))
            testData.add(PaymentEntity.Payment(4, Date(2019,1,6), 60.75, "Test-05", R.drawable.wifi_foreground, R.color.colorAccent,true))
            testData.add(PaymentEntity.Payment(5, Date(2019,1,6), 60.75, "Test-05", R.drawable.wifi_foreground, R.color.colorAccent,false))
              return testData
        }
}