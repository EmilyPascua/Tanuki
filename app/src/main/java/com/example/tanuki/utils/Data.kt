package com.example.tanuki.utils

import com.example.tanuki.model.PaymentEntity
import java.util.*
import kotlin.collections.ArrayList
import com.example.tanuki.R
import com.example.tanuki.model.FeedModel
import java.text.SimpleDateFormat;
import kotlin.collections.HashMap

class Data() {
    private var bills: ArrayList<PaymentEntity.Payment> = ArrayList()

    fun updatePayments(bills: ArrayList<PaymentEntity.Payment>) {
        this.bills = bills
    }

    // Make sure to add only if is bill is true4

    fun createCalendar(): ArrayList<PaymentEntity.Payment> {
        var testData = ArrayList<PaymentEntity.Payment>()
        testData.add(PaymentEntity.Payment(0.toString(), Date(2019,11,6), 60.75, "Test-01", R.drawable.celebration_80, R.color.medium_blue,false,"2"))
        testData.add(PaymentEntity.Payment(1.toString(), Date(2019,11,6), 60.75, "Test-02", R.drawable.travel_80, R.color.violet,true,"2"))
        testData.add(PaymentEntity.Payment(2.toString(), Date(2019,11,6), 60.75, "Test-03", R.drawable.sick_80, R.color.gainsboro,true,"2"))
        testData.add(PaymentEntity.Payment(3.toString(), Date(2019,11,6), 60.75, "Test-04", R.drawable.fitness_80, R.color.crimson,false,"2"))
        testData.add(PaymentEntity.Payment(4.toString(), Date(2019,11,6), 60.75, "Test-06", R.drawable.grocery_80, R.color.chocolate,false,"2"))
        testData.add(PaymentEntity.Payment(5.toString(), Date(2019,11,6), 60.75, "Test-04", R.drawable.fitness_80, R.color.crimson,false,"2"))
        testData.add(PaymentEntity.Payment(6.toString(), Date(2019,11,6), 60.75, "Test-06", R.drawable.grocery_80, R.color.chocolate,false,"2"))
        testData.add(PaymentEntity.Payment(7.toString(), Date(2019,11,6), 60.75, "Test-04", R.drawable.fitness_80, R.color.crimson,false,"2"))
        testData.add(PaymentEntity.Payment(8.toString(), Date(2019,11,6), 60.75, "Test-06", R.drawable.grocery_80, R.color.chocolate,false,"2"))
        testData.add(PaymentEntity.Payment(9.toString(), Date(2019,11,6), 60.75, "Test-04", R.drawable.fitness_80, R.color.crimson,false,"2"))
        testData.add(PaymentEntity.Payment(10.toString(), Date(2020,11,6), 60.75, "Test-06", R.drawable.grocery_80, R.color.chocolate,false,"2"))
        testData.add(PaymentEntity.Payment(11.toString(), Date(2020,0,6), 60.75, "Test-07", R.drawable.home_80, R.color.dark_orchid,true,"2"))
        testData.add(PaymentEntity.Payment(12.toString(), Date(2020,0,6), 60.75, "Test-08", R.drawable.celebration_80, R.color.medium_aquamarine,true,"2"))
        testData.add(PaymentEntity.Payment(13.toString(), Date(2020,0,6), 60.75, "Test-09", R.drawable.sick_80, R.color.dark_khaki,true,"2"))
        testData.add(PaymentEntity.Payment(14.toString(), Date(2020,0,6), 60.75, "Test-10", R.drawable.travel_80, R.color.sienna,true,"2"))
        testData.add(PaymentEntity.Payment(15.toString(), Date(2020,0,7), 60.75, "Test-11", R.drawable.celebration_80, R.color.medium_aquamarine,true,"2"))

        return testData
    }

    fun createFeed():ArrayList<FeedModel>{
        var testDataFeed = ArrayList<FeedModel>()
        testDataFeed.add(FeedModel(0.toString(),"Conan Yee","m","montly",489.00,Date(2020,12,12),"Please pass me. Check your PayPal",false))
        testDataFeed.add(FeedModel(10.toString(),"Emily Adams","f", "yearly",2.34,Date(2020,1,12),"Do your math homework!",true))
        testDataFeed.add(FeedModel(20.toString(),"Chad","m","montly",2147483647.32 ,Date(2015,4,1),"Who needs to pass when you got gains",true))
        testDataFeed.add(FeedModel(30.toString(),"William Lee","m","montly",3.54,Date(2019,8,12),"TFT tournament winnings",true))
        testDataFeed.add(FeedModel(40.toString(),"Luella Jones","f","montly",10.12,Date(2020,12,12),"XD",false))
        testDataFeed.add(FeedModel(50.toString(),"Jerry Berry", "f","yearly",0.00,Date(2020,12,12),"Clothing budget",false))
        testDataFeed.add(FeedModel(60.toString(),"Yiliang Peng","m","yearly",200000.32,Date(2020,12,12),"Liquid check",true))

        return testDataFeed
    }

    fun getCalendarSortedByDate(data: ArrayList<PaymentEntity.Payment>): HashMap<String, ArrayList<PaymentEntity.Payment>> {
        // Nowe we have the calendar data
        val calendarHm:HashMap<String, ArrayList<PaymentEntity.Payment>> = HashMap()
        val sdf = SimpleDateFormat("MM-dd-yyyy")
        var testData = data
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