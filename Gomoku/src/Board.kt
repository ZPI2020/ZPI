class Board(val rows: Int, val columns: Int) {

    private val board: Array<Array<Field>> = Array(rows) { Array(columns) { Field() } }

    fun size(): Pair<Int, Int> = Pair(rows, columns)

    fun isFull(): Boolean {
        for (field in board.flatten()) {
            if (field.isEmpty())
                return false
        }
        return true
    }

    fun isFieldEmpty(row: Int, column: Int): Boolean = board[row][column].isEmpty()

    fun placeToken(token: Token, row: Int, column: Int) {
        board[row][column].token = token
    }

    fun getToken(row: Int, column: Int): Token? = board[row][column].token

    fun copy(): Board {
        val boardCopy = Board(rows, columns)
        for ((origin, copy) in board.flatten().zip(boardCopy.board.flatten()))
            copy.token = origin.token
        return boardCopy
    }
}