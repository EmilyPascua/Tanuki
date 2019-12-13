package com.example.tanuki.fragments.tabfragments

import android.os.Bundle
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
import com.example.tanuki.utils.Data
import kotlinx.android.synthetic.main.fragment_activity_feed.*

class FeedFragment : Fragment() {
    private lateinit var pageViewModel: PageViewModel
    private lateinit var feedList: RecyclerView

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
        val testDataFeed = Data().createFeed()
        feedList = root.findViewById(R.id.feed)
        feedList.layoutManager = LinearLayoutManager(context)
        feedList.adapter = FeedAdapter(testDataFeed)
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
        fun newInstance(sectionNumber: Int): FeedFragment {
            return FeedFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
