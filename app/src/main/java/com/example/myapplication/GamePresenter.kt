package com.example.myapplication

import kotlin.math.roundToInt

class GamePresenter(private val game_ui: GameListener, fmMode: Int, boardSizeMode: Int, gameMode: Int) {

    private val token1 = Token(1)
    private val token2 = Token(2)
    private val board = createBoard(boardSizeMode)
    private val firstMove = getFirstMove(fmMode)
    private val game = Gomoku(board, token1, token2, firstMove)
    private val register = GameStateRegister(game)
    private val history = GamesHistory()
    private lateinit var player1: Player
    private lateinit var player2: Player

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
        fun gameBoardNewGame()
        fun drawWinningPositions(arr:Array<Pair<Int,Int>>)
    }

    private fun getFirstMove(fmMode: Int): Token {
        return when (fmMode) {
            1 -> this.token2
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
        game_ui.gameBoardNewGame()
        game_ui.setBoard(game.getGameBoardCopy().getValuesMatrix())
    }
}