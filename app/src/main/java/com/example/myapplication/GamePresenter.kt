package com.example.myapplication

import android.content.Context
import kotlin.math.roundToInt

class GamePresenter(private val game_ui: GameListener, fmMode: Int, boardSizeMode: Int, private val gameMode: Int): TaskCaller {

    private val token1 = Token(1)
    private val token2 = Token(2)
    private val game = Gomoku(createBoard(boardSizeMode), token1, token2, getFirstMove(fmMode))
    private val register = GameStateRegister(game)
    private val history = GamesHistory()
    private lateinit var player1: Player
    private lateinit var player2: Player
    private var moveLock = true

    init {
        when (gameMode) {
            0 -> {
                player1 = HumanPlayer("Player1").also { it.assign(token1); it.play(game) }
                player2 = HumanPlayer("Player2").also { it.assign(token2); it.play(game) }
            }
            1 -> {
                player1 = HumanPlayer("Player1").also { it.assign(token1); it.play(game) }
                player2 = AIPlayer("Player2", 1).also { it.assign(token2); it.play(game) }
            }
            2 -> {
                player1 = HumanPlayer("Player1").also { it.assign(token1); it.play(game) }
                player2 = AIPlayer("Player2", 2).also { it.assign(token2); it.play(game) }
            }
            3 -> {
                player1 = HumanPlayer("Player1").also { it.assign(token1); it.play(game) }
                player2 = AIPlayer("Player2", 3).also { it.assign(token2); it.play(game) }
            }
        }
    }

    interface GameListener{
        fun setBoard(board : Array<IntArray>)
        fun showYouWin()
        fun showAiWins()
        fun showPlayer2Wins()
        fun showPlayer2Move()
        fun showPlayer1Wins()
        fun showPlayer1Move()
        fun showYourMove()
        fun showDraw()
        fun gameBoardNewGame()
        fun drawWinningPositions(arr:Array<Pair<Int,Int>>)
        fun getContext(): Context
    }

    private fun getFirstMove(fmMode: Int): Token {
        return when (fmMode) {
            1 -> this.token1
            2 -> this.token2
            else -> if (Math.random().roundToInt() == 0) token1 else token2
        }
    }

    private fun createBoard(boardSizeMode: Int): GameBoard {
        return when (boardSizeMode) {
            1 -> GameBoard(10,10)
            2 -> GameBoard(12,12)
            else -> GameBoard(8,8)
        }
    }

    fun startGame() {
        checkForAIMove()
        game_ui.gameBoardNewGame()
        game_ui.setBoard(game.getGameBoardCopy().getValuesMatrix())
        updateMoveInfo()
    }

    fun restartGame() {
        if (game.isGameOver()) {
            game.resetGame()
            register.clearRegistry()
            checkForAIMove()
            game_ui.setBoard(game.getGameBoardCopy().getValuesMatrix())
            updateMoveInfo()
        }
    }

    private fun checkForAIMove() {
        if (player2 is AIPlayer && game.currentTurn() == player2.token) {
            moveLock = true
            CompMoveAsyncTask((player2 as AIPlayer), this).execute()
        }
        else
            moveLock = false
    }

    fun undoMove() {
        register.recoverLastState()
        game_ui.setBoard(game.getGameBoardCopy().getValuesMatrix())
    }

    fun onBoardClick(row: Int, column: Int) {
        if (!isMoveValid(row, column)) return

        if (game.currentTurn() == player1.token && player1 is HumanPlayer) {
            register.saveState()
            (player1 as HumanPlayer).makeMove(row, column)
            if (game.isGameOver()) onGameOver()
            else checkForAIMove()
        }
        else if (game.currentTurn() == player2.token && player2 is HumanPlayer) {
            register.saveState()
            (player2 as HumanPlayer).makeMove(row, column)
            if (game.isGameOver()) onGameOver()
        }
        game_ui.setBoard(game.getGameBoardCopy().getValuesMatrix())
        updateMoveInfo()
    }

    private fun isMoveValid(row: Int, column: Int): Boolean =
        !moveLock && row >= 0 && column >= 0 && game.isMoveEligible(row, column)

    private fun onGameOver() {
        moveLock = true
        saveGameToHistory()

        if (game.getWinner() == player1.token) {
            if (player2 is HumanPlayer)
                game_ui.showPlayer1Wins()
            else
                game_ui.showYouWin()
        }
        else if (game.getWinner() == player2.token) {
            if (player2 is HumanPlayer)
                game_ui.showPlayer2Wins()
            else
                game_ui.showAiWins()
        } else
            game_ui.showDraw()
    }

    private fun saveGameToHistory() {
        val winner = when {
            game.getWinner() == player1.token -> "PLAYER"
            game.getWinner() == player2.token -> "AI"
            else -> "DRAW"
        }

        when (gameMode) {
            1 -> history.save("EASY", winner, game.getGameBoardCopy().getValuesMatrix(), game_ui.getContext())
            2 -> history.save("MEDIUM", winner, game.getGameBoardCopy().getValuesMatrix(), game_ui.getContext())
            3 -> history.save("HARD", winner, game.getGameBoardCopy().getValuesMatrix(), game_ui.getContext())
        }
    }

    private fun updateMoveInfo() {
        if (game.currentTurn() == player1.token) {
            if (player2 is HumanPlayer)
                game_ui.showPlayer1Move()
            else
                game_ui.showYourMove()
        }
        else if (game.currentTurn() == player2.token && player2 is HumanPlayer) {
            game_ui.showPlayer2Move()
        }
    }

    override fun taskCompleted() {
        game_ui.setBoard(game.getGameBoardCopy().getValuesMatrix())
        if (game.isGameOver())
            onGameOver()
        else {
            moveLock = false
            updateMoveInfo()
        }
    }
}