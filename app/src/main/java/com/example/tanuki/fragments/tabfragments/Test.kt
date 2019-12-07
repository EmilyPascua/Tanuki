package com.example.tanuki.fragments.tabfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.example.tanuki.R
import com.example.tanuki.fragments.HomeViewModel
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Test.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Test.newInstance] factory method to
 * create an instance of this fragment.
 */
class Test : Fragment() {
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_test, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        return root
    }
//
    override fun onResume() {
        super.onResume()
        var act = (activity as AppCompatActivity)
        act.findViewById<TabLayout>(R.id.tabs).setVisibility(View.GONE)
    }

    override fun onStop() {
        super.onStop()

        var act = (activity as AppCompatActivity)
        act.findViewById<TabLayout>(R.id.tabs).setVisibility(View.VISIBLE)
    }
}
