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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.tanuki.R
import com.example.tanuki.adapter.CalendarAdapter
import com.example.tanuki.adapter.FinanceAdapter
import com.example.tanuki.model.FeedModel
import com.example.tanuki.model.PaymentEntity
import com.example.tanuki.utils.Data
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.*

class FinanceFragment : Fragment() {
    private lateinit var pageViewModel: PageViewModel
    private lateinit var financeList: RecyclerView
    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var mAdapter: FinanceAdapter

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
        mDatabase = FirebaseDatabase.getInstance()

        val root = inflater.inflate(R.layout.fragment_finance, container, false)
        val testDataArray = Data().createCalendar()
        // Recycler View
        financeList = root.findViewById(R.id.finance_list)
        financeList.layoutManager = LinearLayoutManager(context)

        mAdapter = FinanceAdapter(testDataArray)

        financeList.adapter = mAdapter

        queryPayments()

        return root
    }

    private fun queryPayments() {
        var usersRef = mDatabase.getReference("Users")
        var query = usersRef.orderByKey()

        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val financeFeed: ArrayList<PaymentEntity.Payment> = ArrayList()

                dataSnapshot.children.forEach{
                    if (it.child("payments").exists()) {
                        Log.d("payments","exists!")
                        it.child("payments").children.forEach{
                            Log.d("payments","forEach!")

                            var date: Date = Date(it.child("date").getValue().toString())
                            var amount: Double = it.child("amount").getValue().toString().toDouble()
                            var type: String = it.child("type").getValue().toString()
                            var backgroundStr: Int = it.child("backgroundStr").getValue().toString().toInt()
                            var iconStr: Int = it.child("iconStr").getValue().toString().toInt()

                            var feedObject = PaymentEntity.Payment("",date,amount,type,iconStr,backgroundStr)

                            financeFeed.add(feedObject)
                        }
                    }
                }
                mAdapter.updateFeed(financeFeed)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }

        query.addValueEventListener(listener)
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
        fun newInstance(sectionNumber: Int): FinanceFragment {
            return FinanceFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
