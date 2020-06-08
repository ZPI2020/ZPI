abstract class MetaGame(
    protected var board: Board,
    private val token1: Token,
    private val token2: Token,
    firstTurn: Token): Player.Game, GameStateRegister.Game {

    protected var expectedToken: Token? = firstTurn
    private var gameOver: Boolean = false
    private var winningToken: Token? = null

    override fun switchBoard(newBoard: Board) {
        this.board = newBoard
    }

    override fun getGameBoardCopy(): Board = board.copy()

    override fun placeToken(token: Token, row: Int, column: Int) {
        if (validValues(row, column) && isTokenExpected(token) && (board.isFieldEmpty(row, column))) {
            board.placeToken(token, row, column)
            checkIfGameOver()
            nextTurn()
        }
    }

    private fun validValues(row: Int, column: Int): Boolean =
        (row in 0 until board.rows) && (column in 0 until board.columns)

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

    override fun setTurn(token: Token) {
        this.expectedToken = token
    }

    override fun currentTurn(): Token? = expectedToken

    protected fun gameOver() {
        this.gameOver = true
    }

    override fun isGameOver(): Boolean = gameOver

    protected fun setWinner() {
        winningToken = expectedToken
    }

    fun getWinner(): Token? = this.winningToken
}