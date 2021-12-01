package com.nicholas.mileagechecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nicholas.mileagechecker.fragments.CarsFragment
import com.nicholas.mileagechecker.fragments.HomeFragment
import com.nicholas.mileagechecker.fragments.MessagesFragment

class MainActivity : AppCompatActivity() {
//    //create variables for the fragments
//    private val home = HomeFragment()
//    private val cars = CarsFragment()
//    private val chats = MessagesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        //accessing widgets in Kotlin using their ID's
//        showFragments(home)
//        //to navigate through the items selected we need to select items from the bottomNavigation widget
//
//        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottomNav)
//        bottom_navigation.setOnNavigationItemSelectedListener() {
//            when(it.itemId){
//                R.id.homefrag -> showFragments(home)
//                R.id.carsfrag -> showFragments(cars)
//                R.id.communicationfrag ->showFragments(chats)
//            }
//            true
//        }
//
//    }
//
//    //Create a function Begin Transaction
//    private fun showFragments(fragment: Fragment){
//        if (fragment != null){
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragContainer,fragment)
//            transaction.commit()
//        }

    }
}