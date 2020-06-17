package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import com.example.myapplication.Game_Board_View.PositionClickedListener
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class Activity_game : AppCompatActivity(), PositionClickedListener, GamePresenter.GameListener {

    private var presenter: GamePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        gameBoardView.positionClickedListener=this

        val sharedPref = this.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE) ?: return
        val gb_size_mode = sharedPref.getInt("SIZE", 0)
        val fm_mode = sharedPref.getInt("FIRSTMOVE", 0)
        val game_mode = intent.extras?.getInt("GAMEMODE") ?: 0

        presenter = GamePresenter(this, fm_mode, gb_size_mode, game_mode)
    }

    override fun onStart() {
        super.onStart()
        presenter?.startGame()
    }

    override fun onPositionClicked(x: Int, y: Int) {
        Log.i("X", x.toString())
        Log.i("Y", y.toString())
        presenter?.onBoardClick(x, y)
    }

    fun undoMoveClick(view: View){
        presenter?.undoMove()
    }

    fun restartGameClick(view: View){
        presenter?.restartGame()
    }

    override fun drawWinningPositions(arr:Array<Pair<Int,Int>>){
        gameBoardView.winingPoints=arr
        gameBoardView.gameFinished =  true
        gameBoardView.invalidate()
    }

    override fun gameBoardNewGame(){
        gameBoardView.resetGame()
    }

    override fun showYourMove(){
        image_game_notification.setImageResource(R.drawable.your_move)
        image_game_notification.visibility=View.VISIBLE
        Handler().postDelayed({
            image_game_notification.visibility = View.INVISIBLE
        }, 700)
    }

    override fun showPlayer1Move(){
        image_game_notification.setImageResource(R.drawable.player1_move)
        image_game_notification.visibility=View.VISIBLE
        Handler().postDelayed({
            image_game_notification.visibility = View.INVISIBLE
        }, 700)
    }

    override fun showPlayer1Wins(){
        image_game_notification.setImageResource(R.drawable.player1_wins)
        image_game_notification.visibility=View.VISIBLE
        Handler().postDelayed({
            image_game_notification.visibility = View.INVISIBLE
        }, 2000)
    }

    override fun showPlayer2Move(){
        image_game_notification.setImageResource(R.drawable.player2_move)
        image_game_notification.visibility=View.VISIBLE
        Handler().postDelayed({
            image_game_notification.visibility = View.INVISIBLE
        }, 700)
    }

    override fun showPlayer2Wins(){
        image_game_notification.setImageResource(R.drawable.player2_wins)
        image_game_notification.visibility=View.VISIBLE
        Handler().postDelayed({
            image_game_notification.visibility = View.INVISIBLE
        }, 2000)
    }

    override fun showAiWins(){
        image_game_notification.setImageResource(R.drawable.ai_wins)
        image_game_notification.visibility=View.VISIBLE
        Handler().postDelayed({
            image_game_notification.visibility = View.INVISIBLE
        }, 2000)
    }

    override fun setBoard(board : Array<IntArray>){
        gameBoardView.gameBoardArray = board
        gameBoardView.invalidate()
    }

    override fun showYouWin(){
        image_game_notification.setImageResource(R.drawable.your_move)
        image_game_notification.visibility=View.VISIBLE
        Handler().postDelayed({
            image_game_notification.visibility = View.INVISIBLE
        }, 2000)
    }
}
