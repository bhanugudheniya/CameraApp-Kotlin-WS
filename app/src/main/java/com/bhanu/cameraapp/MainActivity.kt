package com.bhanu.cameraapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.bhanu.cameraapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
            binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.camera.setOnClickListener{
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraLauncher.launch(cameraIntent)
        }
        binding.gallery.setOnClickListener{}

        binding.show.setOnClickListener{
            //add icon for the dialog
            //button on dialog
            AlertDialog.Builder(this)
                .setTitle("Downloading PDF")
                .setIcon(R.mipmap.ic_launcher_round)
                .setMessage("Do you want to continue")
                    //show Toast on click of yes button
                .setPositiveButton("Yes",) {dialog, which ->
                    Toast.makeText(this, "Yes button", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No", ) {dialog, which ->
                    Log.i("NEGATIVETAG", "Button Clicked")
                }
                .setNeutralButton("Cancel", null)
                .show()
        }
    }

    var cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result->
            if(result.resultCode == Activity.RESULT_OK){
                //pass the image to the imageview
                var photo : Bitmap = result.data?.extras?.get("data") as Bitmap
                binding.imageView.setImageBitmap(photo)
            }

    }
}