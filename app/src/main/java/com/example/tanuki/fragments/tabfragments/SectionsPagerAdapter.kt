package com.example.tanuki.fragments.tabfragments

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.tanuki.R

private val TAB_TITLES = arrayOf(
    R.string.feed_title,
    R.string.finances_title,
    R.string.calendar_title
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a place holderFragment (defined as a static inner class below).

        var fragment: Fragment = FeedFragment.newInstance(1)

        when(position) {
            0 -> {
                fragment = FeedFragment.newInstance(position + 1)
            }
            1 -> {
                fragment = FinanceFragment.newInstance(position + 1)
            }
            2 -> {
                fragment = CalendarFragment.newInstance(position + 1)
            }
        }
        return fragment
    }

//    override fun getPageTitle(position: Int): CharSequence? {
//        return context.resources.getString(TAB_TITLES[position])
//    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 3
    }
}