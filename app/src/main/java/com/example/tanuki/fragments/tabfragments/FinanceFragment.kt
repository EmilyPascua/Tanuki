package com.example.tanuki.fragments.tabfragments

import android.os.Bundle
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
import com.example.tanuki.utils.Data
import java.text.SimpleDateFormat
import java.util.*

class FinanceFragment : Fragment() {
    private lateinit var pageViewModel: PageViewModel
    private lateinit var financeList: RecyclerView

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
        val root = inflater.inflate(R.layout.fragment_finance, container, false)
        val testDataArray = Data().createCalendar()
        // Recycler View
        financeList = root.findViewById(R.id.finance_list)
        financeList.layoutManager = LinearLayoutManager(context)
        financeList.adapter = FinanceAdapter(testDataArray)

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
        fun newInstance(sectionNumber: Int): FinanceFragment {
            return FinanceFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
