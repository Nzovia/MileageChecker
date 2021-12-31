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
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import java.io.*
import java.util.*

class AddCar : AppCompatActivity() , View.OnClickListener{
//    Not when you use viewBinding feature, each xml file generates a
//    binding class that allows access to all html elements eg. result_profile-
//    generates a binding class ResultingProfileBinding
    private lateinit var mBinding: ActivityAddCarBinding
    private var mImagePath: String = ""

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
                    report?.let {
                        if(report.areAllPermissionsGranted()){
                           //to open the camera we need to start an intent
                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            startActivityForResult(intent, CAMERA) //create a companion object for the Camera class
                        }
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

        //onclick to access images from the internal memory of the device
        binding.tvGallery.setOnClickListener(){
            //Action to request for permissions using the Dexter Libraries

            Dexter.withContext(this).withPermission(
                //Dexter library can hold single or multiple permissions
                Manifest.permission.READ_EXTERNAL_STORAGE
            ).withListener(object : PermissionListener{
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    val galleryIntent = Intent(Intent.ACTION_PICK,
                              MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(galleryIntent, GALLERY)

                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    TODO("Not yet implemented")
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    TODO("Not yet implemented")
                }
            }).onSameThread().check()
            dialog.dismiss()
        }
        //for the dialog to appear
        dialog.show()
    }

    //takes care of the results that we get from the permission

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){ //means the intent was successful we went over onto the camera
            if(requestCode == CAMERA){//means if the request code is the camera the assign it to the image view
              data?.extras?.let{
                  val thumbnail:Bitmap = data.extras!!.get("data") as Bitmap

                //  mBinding.imageBackground.setImageBitmap(thumbnail)
                  //using glide library
                  Glide.with(this)
                      .load(thumbnail)
                      .centerCrop()
                      .into( mBinding.imageBackground)
                  mImagePath = saveImagesToInternalStorage(thumbnail)
                  Log.i("ImagePath",mImagePath)

                  mBinding.addCarPhotos.setImageDrawable(ContextCompat.
                  getDrawable(this, R.drawable.ic_edit))
              }

            }
            if(requestCode == GALLERY){//means if the request code is the camera the assign it to the image view
                data?.let{
                    //this variable helps has understand the uri of the image when we set data.data
                    val selectedPhotoUri = data.data
//                    mBinding.imageBackground.setImageURI(selectedPhotoUri)
                    Glide.with(this)
                        .load(selectedPhotoUri)
                        .centerCrop()
                        .into( mBinding.imageBackground)

                    mBinding.addCarPhotos.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_edit))
                }

            }
        }else if (resultCode == RESULT_CANCELED){
            Log.e("cancelled","the operation has been cancelled")
        }
    }
//this is  an alert dialog that will be show in the instance
// that user does not grant access permission
    private fun showRationalDialogForPermissions(){
        AlertDialog.Builder(this).setMessage("Appears the permissions are Denied, " +
                "it is required for this feature to work optimally ")
    }
    //a function to facilitate saving of the images on the Cars Directory
    private fun saveImagesToInternalStorage(bitmap: Bitmap):String{
        //we need a context wrapper to understand where the image inside the application is assigned to
        val wrapper = ContextWrapper(applicationContext)

        var file = wrapper.getDir(IMAGE_DIRECTORY , Context.MODE_PRIVATE)
        file = File(file,"${UUID.randomUUID()}.jpg")

        //now create an image from that bitmap we have passed
        try{
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,stream)
            stream.flush()
            stream.close()
        }catch (e:IOException){
            e.printStackTrace()

        }
        //returns the file directory and the absolute path
        return file.absolutePath
    }


    //creating the companion object
    companion object{
        private const val CAMERA = 1
        private const val GALLERY = 2

        //creating  a directory where the images will be stored
        private const val IMAGE_DIRECTORY = "carPhotos"
    }
}