package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_settings.*

class Activity_settings : AppCompatActivity() {
    var gb_size_mode=0
    var fm_mode=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }
    fun rightArrowGBSizeOnClick(v : View) {
        if(gb_size_mode<2) {
            gb_size_mode++
        }
        else {
            gb_size_mode=0
        }
        updateGBSizeModeIcon()
    }
    fun leftArrowGBSizeOnClick(v : View) {
        if(gb_size_mode>0) {
            gb_size_mode--
        }
        else {
            gb_size_mode=2
        }
        updateGBSizeModeIcon()
    }
    fun rightArrowFMOnClick(v : View) {
        if(fm_mode<2) {
            fm_mode++
        }
        else {
            fm_mode=0
        }
        updateFMModeIcon()
    }
    fun leftArrowFMOnClick(v : View) {
        if(fm_mode>0) {
            fm_mode--
        }
        else {
            fm_mode=2
        }
        updateFMModeIcon()
    }
    fun updateGBSizeModeIcon() {
        Log.d("GBmode",""+gb_size_mode)
        if(gb_size_mode==0) {

            val text_resource = resources.getIdentifier("@drawable/text_8x8", null, packageName)
            val drawable_text_resource = getResources().getDrawable(text_resource)


            text_gb_size_image_view.setImageDrawable(drawable_text_resource)
        }
        else if(gb_size_mode==1) {

            val text_resource = resources.getIdentifier("@drawable/text_10x10", null, packageName)
            val drawable_text_resource = getResources().getDrawable(text_resource)


            text_gb_size_image_view.setImageDrawable(drawable_text_resource)
        }
        else if(gb_size_mode==2) {

            val text_resource = resources.getIdentifier("@drawable/text_12x12", null, packageName)
            val drawable_text_resource = getResources().getDrawable(text_resource)


            text_gb_size_image_view.setImageDrawable(drawable_text_resource)
        }


    }
    fun updateFMModeIcon() {
        Log.d("FMmode",""+fm_mode)
        if(fm_mode==0) {

            val text_resource = resources.getIdentifier("@drawable/text_fm_rand", null, packageName)
            val drawable_text_resource = getResources().getDrawable(text_resource)


            text_fm_image_view.setImageDrawable(drawable_text_resource)
        }
        else if(fm_mode==1) {

            val text_resource = resources.getIdentifier("@drawable/text_fm_pl1", null, packageName)
            val drawable_text_resource = getResources().getDrawable(text_resource)


            text_fm_image_view.setImageDrawable(drawable_text_resource)
        }
        else if(fm_mode==2) {

            val text_resource = resources.getIdentifier("@drawable/text_fm_pl2", null, packageName)
            val drawable_text_resource = getResources().getDrawable(text_resource)


            text_fm_image_view.setImageDrawable(drawable_text_resource)
        }


    }
}
