package com.nicholas.mileagechecker.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nicholas.mileagechecker.databinding.ActivityAddCarBinding

class AddCar : AppCompatActivity() {
//    Not when you use viewBinding feature, each xml file generates a
//    binding class that allows access to all html elements eg. result_profile-
//    generates a binding class ResultingProfileBinding
    private lateinit var mBinding: ActivityAddCarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAddCarBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }
}