package com.example.tanuki.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.tanuki.R
import com.google.android.material.tabs.TabLayout
import android.view.MenuInflater





class ChatFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_chat, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu.findItem(com.example.tanuki.R.id.chat_item)

        if (item != null)
            item.isVisible = false
    }

    override fun onResume() {
        super.onResume()
        var act = (activity as AppCompatActivity)

        act.findViewById<TabLayout>(R.id.tabs).setVisibility(View.GONE)
    }

    override fun onStop() {
        super.onStop()
        var act = (activity as AppCompatActivity)
        act.supportActionBar!!.setDisplayShowTitleEnabled(false)
        act.findViewById<TabLayout>(R.id.tabs).setVisibility(View.VISIBLE)
    }
}
