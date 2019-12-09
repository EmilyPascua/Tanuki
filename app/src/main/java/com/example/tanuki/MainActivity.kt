package com.example.tanuki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

//drawer
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.core.view.GravityCompat
import androidx.navigation.NavController

//tab
import androidx.viewpager.widget.ViewPager
import com.example.tanuki.fragments.tabfragments.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout

private val TAB_ICONS = arrayOf(
    R.drawable.feed_icon,
    R.drawable.finance_icon,
    R.drawable.calendar_icon
)

class MainActivity : AppCompatActivity() {
    // The main activity will lead to the following pages:
    // 1. Feed
    // 2. CalendarFragment
    // 3. The "chat" - or finances
    // These are all fragments as they will inherit the same navigation bar

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // set title to false so that the title does not appear in the toolbar
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        drawerNavSetup()
        sectionsPagerSetup()
    }

    fun sectionsPagerSetup() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(TAB_ICONS[0])
        tabs.getTabAt(1)!!.setIcon(TAB_ICONS[1])
        tabs.getTabAt(2)!!.setIcon(TAB_ICONS[2])
    }

    fun drawerNavSetup() {
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_test
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    //this functions allows for navigation between fragments via the appbar
    //if there is an item on the appbar then you can use function to navigate to a specifiv
    //fragment. Define the fragments in the navigation/mobile.navigation
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()

        if (id == R.id.chat_item) {
            navController.navigate(R.id.tab_chat)
            return true
        }
        else {
            return false
        }
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
