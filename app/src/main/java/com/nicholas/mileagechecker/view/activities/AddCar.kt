package com.nicholas.mileagechecker.view.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.nicholas.mileagechecker.R
import com.nicholas.mileagechecker.databinding.ActivityAddCarBinding
import com.nicholas.mileagechecker.databinding.CustomphotodialogBinding
import android.Manifest
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

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
//                        Toast.makeText(this, "car added", Toast.LENGTH_SHORT)
//                            .show()
                        showCustomDialog()
                        return

                    }
            }
        }
    }
    private fun showCustomDialog(){
        val dialog = Dialog(this)
        val binding : CustomphotodialogBinding = CustomphotodialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        binding.tvCamera.setOnClickListener(){

            //Action to request for permissions using the Dexter Libraries

            Dexter.withContext(this).withPermissions(
            //Dexter library can hold single or multiple permissions
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ).withListener(object : MultiplePermissionsListener{
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if(report!!.areAllPermissionsGranted()){
                        Toast.makeText(this@AddCar,"permission granted",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                  //  TODO show your alert Dialog

                }

            }).onSameThread().check()
            dialog.dismiss()
        }

        binding.tvGallery.setOnClickListener(){
            Toast.makeText(this,"gallery clicked", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        //for the dialog to appear
        dialog.show()

    }
}