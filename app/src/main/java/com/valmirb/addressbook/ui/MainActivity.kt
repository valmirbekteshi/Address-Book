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
    private  var menuOption: Menu?= null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navcontroller  = Navigation.findNavController(this,R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this,navcontroller)

        navcontroller.addOnDestinationChangedListener { controller, destination, arguments ->

            navcontroller.addOnDestinationChangedListener { _, destination, _ ->
                if(destination.id == R.id.contactDetailFragment || destination.id == R.id.addContactFragment){
                    menuOption?.let {
                        it.findItem(R.id.search).setVisible(false)
                        it.findItem(R.id.add_contact).setVisible(false)
                    }
                } else {
                    menuOption?.let {
                        it.findItem(R.id.search).setVisible(true)
                        it.findItem(R.id.add_contact).setVisible(true)

                    }
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navcontroller,null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menuOption = menu
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
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
