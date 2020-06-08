import java.awt.Color

fun main() {
    val token1 = Token(Color.RED)
    val token2 = Token(Color.BLUE)
    val board = Board(10,10)
    val game = Gomoku(board, token1, token2, firstTurn = token1)
    val player1 = HumanPlayer("Kuba").also { it.assign(token1); it.play(game) }
    val player2 = AIPlayer("AI").also { it.assign(token2); it.play(game) }
    val register = GameStateRegister(game)

    register.saveState()
    player1.makeMove(0,0)
    register.saveState()
    player2.makeMove()

    register.saveState()
    player1.makeMove(0,1)
    register.saveState()
    player2.makeMove()

    register.saveState()
    player1.makeMove(0,2)
    register.saveState()
    player2.makeMove()

    register.saveState()
    player1.makeMove(0,3)
    register.saveState()
    player2.makeMove()

    register.recoverLastState()

    register.saveState()
    player1.makeMove(0,4)
    register.saveState()
    player2.makeMove()

    register.saveState()
    player1.makeMove(0,5)
    register.saveState()
    player2.makeMove()

    printBoard(game.getGameBoardCopy())
}

fun printBoard(board: Board?) {
    if (board == null)
        return

    val rows = board.rows
    val columns = board.columns
    println("")
    for (r in 0 until rows) {
        for (c in 0 until columns) {
            if (!board.isFieldEmpty(r, c)) {
                val token = board.getToken(r, c)
                if (token != null) {
                    when (token.color) {
                        Color.RED -> print("R\t")
                        Color.BLUE -> print("B\t")
                    }
                }
            }
            else
                print("_\t")
        }
        println("")
    }
}
