package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.Game_Board_View.PositionClickedListener
import kotlinx.android.synthetic.main.activity_game.*

class Activity_game : AppCompatActivity(), PositionClickedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        gameBoardView.positionClickedListener=this
        test()

    }
    //Zeby  wyswietlic tablice trzeba  gameBoardView.gameBoardArray= tablica  i gameBoardView.invalidate()

    override fun onPositionClicked(x: Int, y: Int) {
        TODO("Przekazuje ruch x y")
    }

    fun undoMoveClick(view: View){
        TODO("UNDO MOVE")
    }
    fun restartGameClick(view: View){
        TODO("RESTART GAME")
    }
    fun test (){
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
}
