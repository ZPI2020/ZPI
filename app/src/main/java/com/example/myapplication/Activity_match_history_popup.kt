package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_match_history_popup.*

class Activity_match_history_popup  : AppCompatActivity(), Game_Board_View.PositionClickedListener {

    lateinit var gameboard : Array<Array<Int>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_history_popup)


        val dm=DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        window.setLayout(dm.widthPixels,dm.widthPixels)
        val board = intent.extras!!.get("board")
        gameboard=board as Array<Array<Int>>
        popupGameBoard.positionClickedListener=this
        popupGameBoard.resetGame()
        popupGameBoard.gameBoardArray=gameboard
        popupGameBoard.invalidate()

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.finish()
        return true
    }

    override fun onPositionClicked(x: Int, y: Int) {
        this.finish()
    }
}
