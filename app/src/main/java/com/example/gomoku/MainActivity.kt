package com.example.gomoku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.gomoku.GameBoardView.PositionClickedListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : AppCompatActivity(), PositionClickedListener {

    val manager = supportFragmentManager
    lateinit var gbView : GameBoardView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var gbView =GameBoardView(this)

        gameBoardView.positionClickedListener= this


    }

    fun click (View :View){
       val gameBoardArray = arrayOf(
                                    arrayOf(0,0,0,0,0,0,1,1),
                                    arrayOf(0,2,2,1,0,0,0,0),
                                   arrayOf(2,2,2,2,0,0,0,1),
                                   arrayOf(0,0,0,0,0,0,0,0,0),
                                   arrayOf(1,0,2,0,1,2,2,0),
                                   arrayOf(2,2,2,0,1,1,0,0),
                                   arrayOf(0,0,0,0,0,0,0,0),
                                   arrayOf(0,0,0,0,0,0,0,0)
       )
        gameBoardView.gameBoardArray = gameBoardArray
        gameBoardView.invalidate()
    }





    override fun onPositionClicked(x: Int, y: Int) {
        Log.d("listener","Clicked")
        Toast.makeText(this,x.toString()+" i "+y.toString(),Toast.LENGTH_SHORT).show()
    }


}

