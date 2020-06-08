class Gomoku(board: Board, token1: Token, token2: Token, firstTurn: Token): MetaGame(board, token1, token2, firstTurn) {

    override fun checkIfGameOver() {
        if (fiveConnected()) {
            setWinner()
            gameOver()
        }
        else if (board.isFull())
            gameOver()
    }

    private fun fiveConnected(): Boolean {
        val checkToken = expectedToken ?: return false

        for (c in 0 until board.columns-4) {
            for (r in 0 until board.rows) {
                if (board.getToken(r, c) == checkToken &&
                    board.getToken(r, c+1) == checkToken &&
                    board.getToken(r, c+2) == checkToken &&
                    board.getToken(r, c+3) == checkToken &&
                    board.getToken(r, c+4) == checkToken)
                    return true
            }
        }
        for (c in 0 until board.columns) {
            for (r in 0 until board.rows-4) {
                if (board.getToken(r, c) == checkToken &&
                    board.getToken(r+1, c) == checkToken &&
                    board.getToken(r+2, c) == checkToken &&
                    board.getToken(r+3, c) == checkToken &&
                    board.getToken(r+4, c) == checkToken)
                    return true
            }
        }
        for (c in 0 until board.columns-4) {
            for (r in 0 until board.rows-4) {
                if (board.getToken(r, c) == checkToken &&
                    board.getToken(r+1, c+1) == checkToken &&
                    board.getToken(r+2, c+2) == checkToken &&
                    board.getToken(r+3, c+3) == checkToken &&
                    board.getToken(r+4, c+4) == checkToken)
                    return true
            }
        }
        for (c in 0 until board.columns-4) {
            for (r in 3 until board.rows) {
                if (board.getToken(r, c) == checkToken &&
                    board.getToken(r-1, c+1) == checkToken &&
                    board.getToken(r-2, c+2) == checkToken &&
                    board.getToken(r-3, c+3) == checkToken &&
                    board.getToken(r-4, c+4) == checkToken)
                    return true
            }
        }
        return false
    }
}