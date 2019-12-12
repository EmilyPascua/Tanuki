package com.example.tanuki.utils

import com.example.tanuki.model.PaymentEntity
import java.util.*
import kotlin.collections.ArrayList
import com.example.tanuki.R
import com.example.tanuki.model.FeedModel
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

    // Make sure to add only if is bill is true
    fun createCalendar(): ArrayList<PaymentEntity.Payment> {
        var testData = ArrayList<PaymentEntity.Payment>()
        testData.add(PaymentEntity.Payment(0, Date(2019,11,6), 60.75, "Test-01", R.drawable.celebration_80, R.color.medium_blue,false))
        testData.add(PaymentEntity.Payment(1, Date(2019,11,6), 60.75, "Test-02", R.drawable.travel_80, R.color.violet,true))
        testData.add(PaymentEntity.Payment(2, Date(2019,11,6), 60.75, "Test-03", R.drawable.sick_80, R.color.gainsboro,true))
        testData.add(PaymentEntity.Payment(3, Date(2019,11,6), 60.75, "Test-04", R.drawable.fitness_80, R.color.crimson,false))
        testData.add(PaymentEntity.Payment(4, Date(2019,11,6), 60.75, "Test-06", R.drawable.grocery_80, R.color.chocolate,false))
        testData.add(PaymentEntity.Payment(5, Date(2019,11,6), 60.75, "Test-04", R.drawable.fitness_80, R.color.crimson,false))
        testData.add(PaymentEntity.Payment(6, Date(2019,11,6), 60.75, "Test-06", R.drawable.grocery_80, R.color.chocolate,false))
        testData.add(PaymentEntity.Payment(7, Date(2019,11,6), 60.75, "Test-04", R.drawable.fitness_80, R.color.crimson,false))
        testData.add(PaymentEntity.Payment(8, Date(2019,11,6), 60.75, "Test-06", R.drawable.grocery_80, R.color.chocolate,false))
        testData.add(PaymentEntity.Payment(9, Date(2019,11,6), 60.75, "Test-04", R.drawable.fitness_80, R.color.crimson,false))
        testData.add(PaymentEntity.Payment(10, Date(2020,11,6), 60.75, "Test-06", R.drawable.grocery_80, R.color.chocolate,false))
        testData.add(PaymentEntity.Payment(11, Date(2020,0,6), 60.75, "Test-07", R.drawable.home_80, R.color.dark_orchid,true))
        testData.add(PaymentEntity.Payment(12, Date(2020,0,6), 60.75, "Test-08", R.drawable.celebration_80, R.color.medium_aquamarine,true))
        testData.add(PaymentEntity.Payment(13, Date(2020,0,6), 60.75, "Test-09", R.drawable.sick_80, R.color.dark_khaki,true))
        testData.add(PaymentEntity.Payment(14, Date(2020,0,6), 60.75, "Test-10", R.drawable.travel_80, R.color.sienna,true))
        testData.add(PaymentEntity.Payment(15, Date(2020,0,7), 60.75, "Test-11", R.drawable.celebration_80, R.color.medium_aquamarine,true))

        return testData
    }

    fun createFeed():ArrayList<FeedModel>{
        var testDataFeed = ArrayList<FeedModel>()
        testDataFeed.add(FeedModel(0,"Conan Yee",489,Date(2020,12,12),"Please pass me. Check your PayPal",false))
        testDataFeed.add(FeedModel(10,"David Adams",2,Date(2020,1,12),"Do your math homework!",true))
        testDataFeed.add(FeedModel(20,"Chad",2147483647 ,Date(2015,4,1),"Who needs to pass when you got gains",true))
        testDataFeed.add(FeedModel(30,"William Lee",3,Date(2019,8,12),"TFT tournament winnings",true))
        testDataFeed.add(FeedModel(40,"Luella Bates Washington Jones",10,Date(2020,12,12),"XD",false))
        testDataFeed.add(FeedModel(50,"Jerry Berry",0,Date(2020,12,12),"Clothing budget",false))
        testDataFeed.add(FeedModel(60,"Yiliang Peng",200000,Date(2020,12,12),"Liquid check",true))
        return testDataFeed
    }

    fun getCalendarSortedByDate(): HashMap<String, ArrayList<PaymentEntity.Payment>> {
        // Nowe we have the calendar data
        val calendarHm:HashMap<String, ArrayList<PaymentEntity.Payment>> = HashMap()
        val sdf = SimpleDateFormat("MM-dd-yyyy")
        var testData = createCalendar()
        System.out.println("TESTDATA[0] " + sdf.format(testData[0].date).toString())

        for (item in testData){
            System.out.println(sdf.format(item.date))
        }
        for (item in testData){
            if(item.isBill){
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
            // If key does exist, get it then add to array
        }
        System.out.println("These are the keys: " + calendarHm.keys)

        return calendarHm
    }

    fun createFinance(): HashMap<String, ArrayList<PaymentEntity.Payment>> {
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