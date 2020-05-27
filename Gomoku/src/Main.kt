import java.awt.Color

fun main() {
    val gameAssistant = GomokuAssistant()
    val player1 = gameAssistant.addAIPlayer("AI_1")
    val player2 = gameAssistant.addHumanPlayer("Kuba")
    val player2Register = gameAssistant.setUpNewGameRegister()

    gameAssistant.createNewBoard(10, 10)
    gameAssistant.startGame(Turn.PLAYER2_TURN)

    player1?.makeMove()
    player2Register.saveState()
    player2?.makeMove(0, 0)
    printBoard(gameAssistant.getBoardCopy())

    player1?.makeMove()
    player2Register.saveState()
    player2?.makeMove(0, 1)
    printBoard(gameAssistant.getBoardCopy())

    player1?.makeMove()
    player2Register.saveState()
    player2?.makeMove(0, 2)
    printBoard(gameAssistant.getBoardCopy())

    player2Register.recoverLastState()
    print("\n***")
    printBoard(gameAssistant.getBoardCopy())
    println("***")

    player1?.makeMove()
    player2?.makeMove(0, 3)
    printBoard(gameAssistant.getBoardCopy())

    player1?.makeMove()
    player2?.makeMove(0, 4)
    printBoard(gameAssistant.getBoardCopy())
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
