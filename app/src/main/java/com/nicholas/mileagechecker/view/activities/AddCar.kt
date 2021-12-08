package com.nicholas.mileagechecker.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.nicholas.mileagechecker.R
import com.nicholas.mileagechecker.databinding.ActivityAddCarBinding

class AddCar : AppCompatActivity() , View.OnClickListener{
//    Not when you use viewBinding feature, each xml file generates a
//    binding class that allows access to all html elements eg. result_profile-
//    generates a binding class ResultingProfileBinding
    private lateinit var mBinding: ActivityAddCarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddCarBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        //to the actionBar you have to call the method here
        setUpActionBar()
        //the onclick method(assigning our onclick listener the view)
        mBinding.addCarPhotos.setOnClickListener(this)
    }
    //set up action bar
    private fun setUpActionBar(){
        setSupportActionBar(mBinding.addCarToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.addCarToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    override fun onClick(v: View?) {
        if (v != null) {//null check makes sure that the view is not null
            when(v.id){
                    R.id.addCarPhotos->{
                        Toast.makeText(this, "car added", Toast.LENGTH_SHORT)
                            .show()
                        return

                    }
            }
        }
    }
    private fun showCustomDialog(){

    }
}