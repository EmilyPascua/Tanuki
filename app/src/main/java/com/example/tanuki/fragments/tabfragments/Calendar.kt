package com.example.tanuki.fragments.tabfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.tanuki.R
import com.example.tanuki.utils.Data
import com.example.tanuki.adapter.CalendarAdapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

class Calendar : Fragment() {
    private lateinit var pageViewModel: PageViewModel
    private lateinit var calendarList:RecyclerView
    private lateinit var calendar: CalendarView
    private lateinit var noneExist: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_calendar, container, false)

        val testDataHashmap = Data().getCalendarSortedByDate()
        val sdf = SimpleDateFormat("MM-dd-yyyy")
        // Text View if No items exists
        noneExist = root.findViewById(R.id.calendar_information)
        // Calendar
        calendar = root.findViewById(R.id.calendarView)

        // Recycler View
        calendarList = root.findViewById(R.id.calendar_list)
        calendarList.layoutManager = LinearLayoutManager(context)
        val date = sdf.format(Date())
        val paymentsForCertainDay = testDataHashmap.get(date.toString())
        if(paymentsForCertainDay != null){
            calendarList.setVisibility(View.VISIBLE)
            noneExist.setVisibility(View.GONE)

            calendarList.adapter = CalendarAdapter(paymentsForCertainDay)
        }else{
            calendarList.setVisibility(View.GONE)
            noneExist.setVisibility(View.VISIBLE)

            System.out.println("Display no payments")
            // Display Payments
        }

        // Create Calendar for initial load-up

        // Get current day and check
        calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = Date(year,month,dayOfMonth)
            val paymentsForCertainDay = testDataHashmap.get(sdf.format(date))
            System.out.println()
            if(paymentsForCertainDay != null){
                calendarList.setVisibility(View.VISIBLE)
                noneExist.setVisibility(View.GONE)

                calendarList.adapter = CalendarAdapter(paymentsForCertainDay)
            }else{
                calendarList.setVisibility(View.GONE)
                noneExist.setVisibility(View.VISIBLE)

                System.out.println("Display no payments")
                // Display Payments
            }

        }

        return root
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
        fun newInstance(sectionNumber: Int): Calendar {
            return Calendar().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
