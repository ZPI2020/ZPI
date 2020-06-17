package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_menu.*


class Activity_main_menu : AppCompatActivity() {
    var mode=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
    }
    fun updateModeIcon() {

        if(mode==0) {
            val image_resource = resources.getIdentifier("@drawable/icon_pvp", null, packageName)
            val drawable_icon_resource = getResources().getDrawable(image_resource);
            val text_resource = resources.getIdentifier("@drawable/text_pvp", null, packageName)
            val drawable_text_resource = getResources().getDrawable(text_resource);

            icon_mode_image_view.setImageDrawable(drawable_icon_resource);
            text_mode_image_view.setImageDrawable(drawable_text_resource);
        }
        else if(mode==1) {
            val image_resource = resources.getIdentifier("@drawable/icon_pveb", null, packageName)
            val drawable_icon_resource = getResources().getDrawable(image_resource);
            val text_resource = resources.getIdentifier("@drawable/text_pveb", null, packageName)
            val drawable_text_resource = getResources().getDrawable(text_resource);

            icon_mode_image_view.setImageDrawable(drawable_icon_resource);
            text_mode_image_view.setImageDrawable(drawable_text_resource);
        }
        else if(mode==2) {
            val image_resource = resources.getIdentifier("@drawable/icon_pvmb", null, packageName)
            val drawable_icon_resource = getResources().getDrawable(image_resource);
            val text_resource = resources.getIdentifier("@drawable/text_pvmb", null, packageName)
            val drawable_text_resource = getResources().getDrawable(text_resource);

            icon_mode_image_view.setImageDrawable(drawable_icon_resource);
            text_mode_image_view.setImageDrawable(drawable_text_resource);
        }
        else if(mode==3) {
            val image_resource = resources.getIdentifier("@drawable/icon_pvhb", null, packageName)
            val drawable_icon_resource = getResources().getDrawable(image_resource);
            val text_resource = resources.getIdentifier("@drawable/text_pvhb", null, packageName)
            val drawable_text_resource = getResources().getDrawable(text_resource);

            icon_mode_image_view.setImageDrawable(drawable_icon_resource);
            text_mode_image_view.setImageDrawable(drawable_text_resource);
        }

    }
    fun rightArrowOnClick(v : View) {
        if(mode<3) {
            mode++;
        }
        else {
            mode=0;
        }
        updateModeIcon();
    }
    fun leftArrowOnClick(v : View) {
        if(mode>0) {
            mode--;
        }
        else {
            mode=3;
        }
        updateModeIcon();
    }

    fun goToSettings(view: View?) {
        val intent = Intent(this, Activity_settings::class.java)
        startActivity(intent)
    }
    fun goToMatchHistory(view: View?) {
        val intent = Intent(this, Activity_match_history::class.java)
        startActivity(intent)
    }
    fun goToGame(view: View?) {
        val intent = Intent(this, Activity_game::class.java)
        intent.putExtra("GAMEMODE", mode)
        startActivity(intent)
    }
}
