package com.example.tanuki.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.tanuki.R
import com.google.android.material.tabs.TabLayout
import android.view.MenuInflater
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.example.tanuki.adapter.MessageAdapter
import com.example.tanuki.fragments.tabfragments.PageViewModel
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment() {
    private lateinit var pageViewModel: PageViewModel
    private lateinit var chatHistory : RecyclerView
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply{
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_chat, container, false)
        messageList.layoutManager = LinearLayoutManager(this)
        adapter = MessageAdapter()
        messageList.adapter = adapter
        return root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu.findItem(com.example.tanuki.R.id.chat_item)

        if (item != null)
            item.isVisible = false
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
        fun newInstance(sectionNumber: Int): ChatFragment {
            return ChatFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        var act = (activity as AppCompatActivity)
        println("ON RESUME!!!!!!!!!!!!!!!!!!")
        act.findViewById<TabLayout>(R.id.tabs).setVisibility(View.GONE)
    }

    override fun onStop() {
        super.onStop()
        var act = (activity as AppCompatActivity)
        println("ON STOP!!!!!!!!!!!!!!!!!!")
        act.findViewById<TabLayout>(R.id.tabs).setVisibility(View.VISIBLE)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
