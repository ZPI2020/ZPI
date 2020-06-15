package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.view.isVisible
import com.example.myapplication.Game_Board_View.PositionClickedListener
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

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

        showYourMove()
        showPlayer1Move()
        showPlayer2Move()
        showPlayer1Wins()
        showPlayer2Wins()
        showAiWins()
        showYouWin()
    }
    fun showYourMove(){
        image_game_notification.setImageResource(R.drawable.your_move)
        image_game_notification.visibility=View.VISIBLE
        Handler().postDelayed({
            image_game_notification.visibility = View.INVISIBLE
        }, 700)
    }
    fun showPlayer1Move(){
        image_game_notification.setImageResource(R.drawable.player1_move)
        image_game_notification.visibility=View.VISIBLE
        Handler().postDelayed({
            image_game_notification.visibility = View.INVISIBLE
        }, 700)
    }
    fun showPlayer1Wins(){
        image_game_notification.setImageResource(R.drawable.player1_wins)
        image_game_notification.visibility=View.VISIBLE
        Handler().postDelayed({
            image_game_notification.visibility = View.INVISIBLE
        }, 2000)
    }
    fun showPlayer2Move(){
        image_game_notification.setImageResource(R.drawable.player2_move)
        image_game_notification.visibility=View.VISIBLE
        Handler().postDelayed({
            image_game_notification.visibility = View.INVISIBLE
        }, 700)
    }
    fun showPlayer2Wins(){
        image_game_notification.setImageResource(R.drawable.player2_wins)
        image_game_notification.visibility=View.VISIBLE
        Handler().postDelayed({
            image_game_notification.visibility = View.INVISIBLE
        }, 2000)
    }
    fun showAiWins(){
        image_game_notification.setImageResource(R.drawable.ai_wins)
        image_game_notification.visibility=View.VISIBLE
        Handler().postDelayed({
            image_game_notification.visibility = View.INVISIBLE
        }, 2000)
    }
    fun showYouWin(){
        image_game_notification.setImageResource(R.drawable.your_move)
        image_game_notification.visibility=View.VISIBLE
        Handler().postDelayed({
            image_game_notification.visibility = View.INVISIBLE
        }, 2000)
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
