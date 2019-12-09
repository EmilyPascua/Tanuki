package com.example.tanuki.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.tanuki.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onResume() {
        super.onResume()
        var act = (activity as AppCompatActivity)
        println("ON RESUME!!!!!!!!!!!!!!!!!!")
        act.findViewById<ViewPager>(R.id.view_pager).setVisibility(View.VISIBLE)
    }

    override fun onStop() {
        super.onStop()
        var act = (activity as AppCompatActivity)
        println("ON STOP!!!!!!!!!!!!!!!!!!")
        act.findViewById<ViewPager>(R.id.view_pager).setVisibility(View.GONE)
    }
}