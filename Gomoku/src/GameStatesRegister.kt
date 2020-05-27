class GameStatesRegister(private val game: MetaGame) {
    private val gameStates = mutableListOf<GameState>()

    fun saveState() {
        val state = getCurrentState() ?: return
        if (!isAlreadySaved(state)) {
            gameStates.add(state)
        }
    }

    private fun getCurrentState():GameState? {
        val board = game.getBoardCopy() ?: return null
        val turn = game.expectedToken ?: return null
        return GameState(board, turn)
    }

    private fun isAlreadySaved(state: GameState): Boolean {
        return if (gameStates.isEmpty())
            false
        else
            areEqual(state.board, gameStates.last().board)
    }

    private fun areEqual(board1: Board, board2: Board): Boolean {
        for (r in 0 until board1.rows) {
            for (c in 0 until board1.columns) {
                if (board1.getToken(r, c) != board2.getToken(r, c))
                    return false
            }
        }
        return true
    }

    fun recoverLastState() {
        val lastState = pullLastState() ?: return
        if (!game.gameOver) {
            game.setNewBoard(lastState.board)
            game.setNextTurn(lastState.expectedToken)
        }
    }

    private fun pullLastState():GameState? {
        var lastState: GameState? = null
        if (gameStates.isNotEmpty()) {
            lastState = gameStates.last()
            gameStates.removeAt(gameStates.lastIndex)
        }
        return lastState
    }
}