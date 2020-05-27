import kotlin.math.roundToInt

class AIPlayer(playerName: String = "AI", game:MetaGame, token: Token): Player(playerName, game, token) {
    fun makeMove() {
        val (row, column) = findBestMove() ?: return
        game.placeToken(token, row, column)
    }

    private fun findBestMove(): Pair<Int, Int>? {
        val board = game.getBoardCopy() ?: return null
        var row: Int
        var column: Int
        do {
            row = (Math.random() * (board.rows-1)).roundToInt()
            column = (Math.random() * (board.columns-1)).roundToInt()
        } while (!board.isFieldEmpty(row, column))

        return Pair(row, column)
    }
}