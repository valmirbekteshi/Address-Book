package com.valmirb.addressbook.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.valmirb.addressbook.R

class MainActivity : AppCompatActivity() {

    private lateinit var navcontroller: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navcontroller  = Navigation.findNavController(this,R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this,navcontroller)



    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navcontroller,null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        navcontroller.addOnDestinationChangedListener { _, destination, _ ->
            item?.isVisible = destination.id != R.id.addContactFragment
        }

        return when (item?.itemId) {
            R.id.add_contact -> {

                navcontroller.navigate(R.id.addContactFragment)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
