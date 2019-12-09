package com.example.tanuki

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

//TabLayout

//Navigation Drawer
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.core.view.GravityCompat
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.viewpager.widget.ViewPager
import com.example.tanuki.databinding.ActivityMainBinding
import com.example.tanuki.fragments.tabfragments.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth

private val TAB_ICONS = arrayOf(
    R.drawable.feed_icon,
    R.drawable.finance_icon,
    R.drawable.calendar_icon
)

class MainActivity : AppCompatActivity() {
    // The main activity will lead to the following pages:
    // 1. Feed
    // 2. Calendar
    // 3. The "chat" - or finances
    // These are all fragments as they will inherit the same navigation bar

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mainElements: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainElements = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // set title to false so that the title does not appear in the toolbar
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        drawerNavSetup()
        sectionsPagerSetup()
//        setupUI()
    }

//    private fun setupUI() {
//        mainElements.logout.setOnClickListener {
//            signOut()
//        }
//    }

    private fun signOut() {
        startActivity(Intent(this,Login::class.java))
        FirebaseAuth.getInstance().signOut();
        finish()
    }

    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
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
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        val id = item.getItemId()
//
//        if (id == R.id.feed_tab) {
//            navController.navigate(R.id.tab_feed)
//            Toast.makeText(this,"Feed Tab",Toast.LENGTH_SHORT).show()
//            return true
//        }
//        else if (id == R.id.finances_tab) {
//            navController.navigate(R.id.tab_finance)
//            Toast.makeText(this,"Finance Tab",Toast.LENGTH_SHORT).show()
//            return true
//        }
//        else if (id == R.id.calendar_tab) {
//            navController.navigate(R.id.tab_calendar)
//            Toast.makeText(this,"Calendar Tab",Toast.LENGTH_SHORT).show()
//            return true
//        }
//        else {
//            return false
//        }
//    }

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
