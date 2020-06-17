package com.example.myapplication

import android.content.Context

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Game_Board_View.PositionClickedListener
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*
import java.util.concurrent.TimeUnit


class Activity_game : AppCompatActivity(), PositionClickedListener, GamePresenter.GameListener {

    private var presenter: GamePresenter? = null
    var sec_timer=0
    var timer = object: CountDownTimer(3600000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            sec_timer+=1000

            val time_string=String.format("%02d:%02d", (sec_timer/1000) / 60, (sec_timer/1000) % 60)
            tv_time.text="TIME:\n"+time_string
        }

        override fun onFinish() {
            tv_time.text="TIME:\nETERNITY"
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        gameBoardView.positionClickedListener=this

        val sharedPref = this.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE) ?: return
        val gb_size_mode = sharedPref.getInt("SIZE", 0)
        val fm_mode = sharedPref.getInt("FIRSTMOVE", 0)
        val game_mode = intent.extras?.getInt("GAMEMODE") ?: 0

        tv_lvl.text="MODE:\nMEDIUM"
        tv_moves.text="MOVES:\n0"
        tv_time.text="TIME:\n0:00"

        presenter = GamePresenter(this, fm_mode, gb_size_mode, game_mode)
        presenter?.startGame()
    }
    override fun resetTimer() {
        sec_timer = 0
        timer.cancel()
        tv_time.text="TIME:\n0:00"
        timer.start()
    }

    override fun stopTimer() {
        timer.cancel()
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

    override fun getContext(): Context = this.applicationContext

    override fun setModeInfo(modeInfo: String) {
        tv_lvl.text = "MODE:\n$modeInfo"
    }

    override fun updateMovesCounter(moves: Int) {
        tv_moves.text = "MOVES:\n${moves.toString()}"
    }

    override fun gameBoardNewGame(){
        resetTimer()
        gameBoardView.resetGame()
    }

    override fun showYourMove(){
        image_game_notification.setImageResource(R.drawable.your_move)
        image_game_notification.visibility=View.VISIBLE
//        Handler().postDelayed({
//            image_game_notification.visibility = View.INVISIBLE
//        }, 700)
    }

    override fun showDraw() {
        image_game_notification.setImageResource(R.drawable.text_draw)
        image_game_notification.visibility=View.VISIBLE
//        Handler().postDelayed({
//            image_game_notification.visibility = View.INVISIBLE
//        }, 2000)
    }

    override fun showPlayer1Move(){
        image_game_notification.setImageResource(R.drawable.player1_move)
        image_game_notification.visibility=View.VISIBLE
//        Handler().postDelayed({
//            image_game_notification.visibility = View.INVISIBLE
//        }, 700)
    }

    override fun showPlayer1Wins(){
        image_game_notification.setImageResource(R.drawable.player1_wins)
        image_game_notification.visibility=View.VISIBLE
//        Handler().postDelayed({
//            image_game_notification.visibility = View.INVISIBLE
//        }, 2000)
    }

    override fun showPlayer2Move(){
        image_game_notification.setImageResource(R.drawable.player2_move)
        image_game_notification.visibility=View.VISIBLE
//        Handler().postDelayed({
//            image_game_notification.visibility = View.INVISIBLE
//        }, 700)
    }

    override fun showPlayer2Wins(){
        image_game_notification.setImageResource(R.drawable.player2_wins)
        image_game_notification.visibility=View.VISIBLE
//        Handler().postDelayed({
//            image_game_notification.visibility = View.INVISIBLE
//        }, 2000)
    }

    override fun showAiWins(){
        image_game_notification.setImageResource(R.drawable.ai_wins)
        image_game_notification.visibility=View.VISIBLE
//        Handler().postDelayed({
//            image_game_notification.visibility = View.INVISIBLE
//        }, 2000)
    }

    override fun setBoard(board : Array<IntArray>){
        gameBoardView.gameBoardArray = board
        gameBoardView.invalidate()
    }

    override fun showYouWin(){
        image_game_notification.setImageResource(R.drawable.you_win)
        image_game_notification.visibility=View.VISIBLE
//        Handler().postDelayed({
//            image_game_notification.visibility = View.INVISIBLE
//        }, 2000)
    }
}
