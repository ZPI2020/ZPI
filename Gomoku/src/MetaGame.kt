abstract class MetaGame(
    protected var gameBoard: GameBoard,
    private val token1: Token,
    private val token2: Token,
    firstTurn: Token) {

    protected var expectedToken: Token? = firstTurn
    private var gameOver: Boolean = false
    private var winningToken: Token? = null

    fun switchGameBoard(newGameBoard: GameBoard) {
        this.gameBoard = newGameBoard
    }

    fun getGameBoardCopy(): GameBoard = gameBoard.copy()

    fun placeToken(token: Token, row: Int, column: Int) {
        if (validValues(row, column) && isTokenExpected(token) && (gameBoard.isFieldEmpty(row, column))) {
            gameBoard.placeToken(token, row, column)
            checkIfGameOver()
            nextTurn()
        }
    }

    private fun validValues(row: Int, column: Int): Boolean =
        (row in 0 until gameBoard.rows) && (column in 0 until gameBoard.columns)

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

    fun setTurn(token: Token) {
        this.expectedToken = token
    }

    fun currentTurn(): Token? = expectedToken

    protected fun gameOver() {
        this.gameOver = true
    }

    fun isGameOver(): Boolean = gameOver

    protected fun setWinner() {
        winningToken = expectedToken
    }

    fun getWinner(): Token? = this.winningToken
}