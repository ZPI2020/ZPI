class Board(val rows: Int, val columns: Int) {

    private val board: List<List<Field>> = List(rows) { List(columns) { Field() } }

    fun isFull(): Boolean {
        for (r in 0 until rows) {
            for (c in 0 until columns) {
                if (isFieldEmpty(r, c))
                    return false
            }
        }
        return true
    }

    fun isFieldEmpty(row: Int, column: Int): Boolean {
        return validValues(row, column) and board[row][column].isEmpty()
    }

    fun placeToken(token: Token, row: Int, column: Int) {
        if (validValues(row, column))
            board[row][column].putToken(token)
    }

    fun getToken(row: Int, column: Int): Token? {
        return if (validValues(row, column))
            board[row][column].token
        else
            null
    }

    private fun validValues(row: Int, column: Int): Boolean {
        return (row in 0 until rows) and (column in 0 until columns)
    }

    fun copy(): Board {
        val boardCopy = Board(rows, columns)
        for (r in 0 until rows) {
            for (c in 0 until columns) {
                if (!isFieldEmpty(r, c)) {
                    val token = board[r][c].token ?: continue
                    boardCopy.placeToken(token, r, c)
                }
            }
        }
        return boardCopy
    }
}