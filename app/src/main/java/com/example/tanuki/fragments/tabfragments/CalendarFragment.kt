package com.example.tanuki.fragments.tabfragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProviders

import com.example.tanuki.R
import com.example.tanuki.utils.Data
import com.example.tanuki.adapter.CalendarAdapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tanuki.model.PaymentEntity
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

//routines
import kotlinx.coroutines.*
import kotlin.system.*

class CalendarFragment : Fragment() {
    private lateinit var calendarList:RecyclerView
    private lateinit var calendar: CalendarView
    private lateinit var noneExist: LinearLayout
    private lateinit var mAdapter: CalendarAdapter
    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var mAuth: FirebaseAuth
    private var data: Data = Data()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_calendar, container, false)

        mAdapter = CalendarAdapter(data.createCalendar())
        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance()

        getPayments()
        handleCalendarClicks(root)

        return root
    }

    fun handleCalendarClicks(root: View) {
        val dataHash = data.getCalendarSortedByDate(data.createCalendar())

        val sdf = SimpleDateFormat("MM-dd-yyyy")
        // Text View if No items exists
        noneExist = root.findViewById(R.id.calendar_information)
        // Calendar
        calendar = root.findViewById(R.id.calendarView)

        // Recycler View
        calendarList = root.findViewById(R.id.calendar_list)
        calendarList.layoutManager = LinearLayoutManager(context)

        val date = sdf.format(Date())
        val paymentsForCertainDay = dataHash.get(date.toString())

        if(paymentsForCertainDay != null){
            calendarList.setVisibility(View.VISIBLE)
            noneExist.setVisibility(View.GONE)

            mAdapter.updateBills(paymentsForCertainDay)

            calendarList.adapter = mAdapter
        }else{
            calendarList.setVisibility(View.GONE)
            noneExist.setVisibility(View.VISIBLE)

            System.out.println("Display no payments")
            // Display Payments
        }

        // Create Calendar for initial load-up

        // Get current day and check
        calendar.setOnDateChangeListener {view, year, month, dayOfMonth ->
            val date = Date(year,month,dayOfMonth)
            val paymentsForCertainDay = dataHash.get(sdf.format(date))
            System.out.println()
            if(paymentsForCertainDay != null){
                calendarList.setVisibility(View.VISIBLE)
                noneExist.setVisibility(View.GONE)

                mAdapter.updateBills(paymentsForCertainDay)

                calendarList.adapter = mAdapter
            }else{
                calendarList.setVisibility(View.GONE)
                noneExist.setVisibility(View.VISIBLE)

                System.out.println("Display no payments")
                // Display Payments
            }

        }
    }

    fun getPayments() {
        var userRef = mDatabase.getReference("Users/" + mAuth.currentUser!!.uid.toString() + "/payments")

        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val financeFeed: ArrayList<PaymentEntity.Payment> = ArrayList()

                Log.d("update payments","exists!")
                dataSnapshot.children.forEach{
                    Log.d("update payments","forEach!" + it.toString())

                    var date: Date = Date(it.child("date").getValue().toString())
                    var amount: Double = it.child("amount").getValue().toString().toDouble()
                    var type: String = it.child("type").getValue().toString()
                    var backgroundStr: Int = it.child("backgroundStr").getValue().toString().toInt()
                    var iconStr: Int = it.child("iconStr").getValue().toString().toInt()

                    var feedObject = PaymentEntity.Payment("",date,amount,type,iconStr,backgroundStr)

                    Log.d("update payments","forEach!" + feedObject.toString())
                    financeFeed.add(feedObject)
                }
                data.updatePayments(financeFeed)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        userRef.addListenerForSingleValueEvent(listener)
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */

        @JvmStatic
        fun newInstance(sectionNumber: Int): CalendarFragment {
            return CalendarFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
