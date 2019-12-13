package com.example.tanuki.fragments.tabfragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tanuki.R
import com.example.tanuki.adapter.FeedAdapter
import com.example.tanuki.model.FeedModel
import com.example.tanuki.model.PaymentEntity
import com.example.tanuki.utils.Data
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_activity_feed.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FeedFragment : Fragment() {
    private lateinit var pageViewModel: PageViewModel
    private lateinit var feedList: RecyclerView
    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var mAdapter: FeedAdapter

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
        val root = inflater.inflate(R.layout.fragment_activity_feed, container, false)

        mDatabase = FirebaseDatabase.getInstance()
        val testDataFeed = Data().createFeed()
        feedList = root.findViewById(R.id.feed)
        feedList.layoutManager = LinearLayoutManager(context)
        mAdapter = FeedAdapter(testDataFeed)
        feedList.adapter = mAdapter

        queryPayments()
        return root
    }

    private fun queryPayments() {
        var usersRef = mDatabase.getReference("Users")
        var query = usersRef.orderByKey()

        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val paymentFeed: ArrayList<FeedModel> = ArrayList()

                dataSnapshot.children.forEach{
                    var name = it.child("name").getValue().toString()
                    var gender = it.child("gender").getValue().toString()
                    var budgetType = it.child("budgetType").getValue().toString()

                    Log.d("queryParams",it.toString() + " CHHEHCHEHHCHCHHECH")

                    if (it.child("payments").exists()) {
                        Log.d("payments","exists!")
                        it.child("payments").children.forEach{
                            Log.d("payments","forEach!")

                            var id = it.key.toString()
                            var date: Date = Date(it.child("date").getValue().toString())
                            var amount: Double = it.child("amount").getValue().toString().toDouble()
                            var message: String = it.child("message").getValue().toString()

                            var feedObject = FeedModel(id,name,gender,budgetType,amount,date,message)

                            paymentFeed.add(feedObject)
                        }
                    }
                }
                mAdapter.updateFeed(paymentFeed)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }

        query.addValueEventListener(listener)
    }

    //when user submits payment and reaches an amount their goal

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
        fun newInstance(sectionNumber: Int): FeedFragment {
            return FeedFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
