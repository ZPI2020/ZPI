import java.awt.Color

class Gomoku: MetaGame() {
    override var token1 = Token(Color.RED)
    override var token2 = Token(Color.BLUE)

    override fun checkIfGameOver() {
        if (fiveConnected()) {
            setWinner()
            gameOver()
        }
        else if (board?.isFull() == true)
            gameOver()
    }

    private fun fiveConnected(): Boolean {
        val board = this.board ?: return false
        val token = expectedToken ?: return false

        for (c in 0 until board.columns-4) {
            for (r in 0 until board.rows) {
                if (board.getToken(r, c) == token &&
                    board.getToken(r, c+1) == token &&
                    board.getToken(r, c+2) == token &&
                    board.getToken(r, c+3) == token &&
                    board.getToken(r, c+4) == token)
                    return true
            }
        }
        for (c in 0 until board.columns) {
            for (r in 0 until board.rows-4) {
                if (board.getToken(r, c) == token &&
                    board.getToken(r+1, c) == token &&
                    board.getToken(r+2, c) == token &&
                    board.getToken(r+3, c) == token &&
                    board.getToken(r+4, c) == token)
                    return true
            }
        }
        for (c in 0 until board.columns-4) {
            for (r in 0 until board.rows-4) {
                if (board.getToken(r, c) == token &&
                    board.getToken(r+1, c+1) == token &&
                    board.getToken(r+2, c+2) == token &&
                    board.getToken(r+3, c+3) == token &&
                    board.getToken(r+4, c+4) == token)
                    return true
            }
        }
        for (c in 0 until board.columns-4) {
            for (r in 3 until board.rows) {
                if (board.getToken(r, c) == token &&
                    board.getToken(r-1, c+1) == token &&
                    board.getToken(r-2, c+2) == token &&
                    board.getToken(r-3, c+3) == token &&
                    board.getToken(r-4, c+4) == token)
                    return true
            }
        }
        return false
    }
}