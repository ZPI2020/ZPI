package com.example.myapplication

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.util.DisplayMetrics
import android.view.MotionEvent
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_match_history_popup.*

class Activity_match_history_popup  : Activity(), Game_Board_View.PositionClickedListener {

    lateinit var gameboard : Array<IntArray>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_history_popup)


        val dm=DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        window.setLayout((dm.widthPixels*0.9).toInt(), (dm.widthPixels*0.9).toInt())

        val board = intent.extras!!.get("board")
        gameboard=board as Array<IntArray>
        popupGameBoard.positionClickedListener=this
        popupGameBoard.drawFill=true
        popupGameBoard.resetGame()
        popupGameBoard.gameBoardArray = gameboard
        popupGameBoard.invalidate()
        val resID = this.resources.getIdentifier("board_gradient", "drawable", this.packageName)
        popupGameBoard.background= ActivityCompat.getDrawable(this,resID)
        //popupGameBoard.setBackgroundColor(Color.rgb(33, 33, 51))

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.finish()
        return true
    }

    override fun onPositionClicked(x: Int, y: Int) {
        this.finish()
    }
}
