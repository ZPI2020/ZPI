abstract class MetaGame {

    protected var board: Board? = null
    abstract var token1: Token
        protected set
    abstract var token2: Token
        protected set
    var expectedToken: Token? = null
        protected set
    var gameOver: Boolean = false
        protected set
    var winningToken: Token? = null
        protected set

    fun setNewBoard(newBoard: Board) {
        this.board = newBoard
    }

    fun isBoardSet(): Boolean {
        return board != null
    }

    fun getBoardCopy(): Board? {
        return board?.copy()
    }

    fun setNextTurn(token: Token) {
        expectedToken = token
    }

    fun placeToken(token: Token, row: Int, column: Int) {
        if (validValues(row, column) and isTokenExpected(token) and (board?.isFieldEmpty(row, column) == true)) {
            board?.placeToken(token, row, column)
            checkIfGameOver()
            nextTurn()
        }
    }

    private fun validValues(row: Int, column: Int): Boolean {
        val board = this.board ?: return false
        return (row in 0 until board.rows) and (column in 0 until board.columns)
    }

    private fun isTokenExpected(token: Token): Boolean {
        return token == expectedToken
    }

    protected abstract fun checkIfGameOver()

    private fun nextTurn() {
        when {
            gameOver -> expectedToken = null
            expectedToken == token1 -> expectedToken = token2
            expectedToken == token2 -> expectedToken = token1
        }
    }

    protected fun gameOver() {
        this.gameOver = true
    }

    protected fun setWinner() {
        winningToken = expectedToken
    }
}