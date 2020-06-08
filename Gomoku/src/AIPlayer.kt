import kotlin.math.roundToInt

class AIPlayer(playerName: String = "AI"): Player(playerName) {
    fun makeMove() {
        val checkGame = this.game ?: return
        val checkToken = this.token ?: return
        val (row, column) = findBestMove() ?: return
        checkGame.placeToken(checkToken, row, column)
    }

    private fun findBestMove(): Pair<Int, Int>? {
        val board = game?.getGameBoardCopy() ?: return null
        var row: Int
        var column: Int
        do {
            row = (Math.random() * (board.rows-1)).roundToInt()
            column = (Math.random() * (board.columns-1)).roundToInt()
        } while (!board.isFieldEmpty(row, column))

        return Pair(row, column)
    }
}