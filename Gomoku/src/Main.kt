import java.awt.Color

fun main() {
    val token1 = Token(1)
    val token2 = Token(2)
    val board = GameBoard(10,10)
    val game = Gomoku(board, token1, token2, firstTurn = token1)
    val player1 = HumanPlayer("Kuba").also { it.assign(token1); it.play(game) }
    val player2 = AIPlayer("AI",1).also { it.assign(token2); it.play(game) }
    val register = GameStateRegister(game)

    register.saveState()
    player1.makeMove(0,0)
    player2.makeMove()

    register.saveState()
    player1.makeMove(0,1)
    player2.makeMove()

    register.saveState()
    player1.makeMove(0,2)
    player2.makeMove()

    register.saveState()
    player1.makeMove(0,3)
    player2.makeMove()

    register.recoverLastState()

    register.saveState()
    player1.makeMove(0,4)
    player2.makeMove()

    register.saveState()
    player1.makeMove(1,6)
    player2.makeMove()

    register.saveState()
    player1.makeMove(1,7)
    player2.makeMove()

    printBoard(game.getGameBoardCopy())
}

fun printBoard(board: GameBoard?) {
    if (board == null)
        return

    val rows = board.rows
    val columns = board.columns
    println("")
    for (r in 0 until rows) {
        for (c in 0 until columns) {
            print("${board.getToken(r,c)?.value ?: 0}\t")
        }
        println("")
    }
}
