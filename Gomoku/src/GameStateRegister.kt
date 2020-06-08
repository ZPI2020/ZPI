class GameStateRegister(private val game: Game) {
    private val gameStates = mutableListOf<GameState>()

    interface Game {
        fun getGameBoardCopy(): Board
        fun currentTurn(): Token?
        fun isGameOver(): Boolean
        fun switchBoard(newBoard: Board)
        fun setTurn(token: Token)
    }

    fun saveState() {
        val state = getCurrentState() ?: return
        if (!isAlreadySaved(state)) gameStates.add(state)
    }

    private fun getCurrentState():GameState? {
        val board = game.getGameBoardCopy()
        val turn = game.currentTurn() ?: return null
        return GameState(board, turn)
    }

    private fun isAlreadySaved(state: GameState):Boolean =
        gameStates.isNotEmpty() && areEqual(state.board, gameStates.last().board)

    private fun areEqual(board1: Board, board2: Board): Boolean {
        if (board1.size() != board2.size()) return false
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
        if (!game.isGameOver()) {
            game.switchBoard(lastState.board)
            game.setTurn(lastState.expectedToken)
        }
    }

    private fun pullLastState():GameState? {
        return if (gameStates.isNotEmpty())
            gameStates.removeAt(gameStates.lastIndex)
        else
            null
    }
}