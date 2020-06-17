package com.example.myapplication

class GameStateRegister(private val game: MetaGame) {
    private val gameStates = mutableListOf<GameState>()

    fun saveState() {
        val state = getCurrentState() ?: return
        if (!isAlreadySaved(state)) gameStates.add(state)
    }

    private fun getCurrentState(): GameState? {
        val gameBoard = game.getGameBoardCopy()
        val turn = game.currentTurn() ?: return null
        return GameState(gameBoard, turn)
    }

    private fun isAlreadySaved(state: GameState):Boolean =
        gameStates.isNotEmpty() && state.gameBoard.equals(gameStates.last().gameBoard)

    fun recoverLastState() {
        val lastState = pullLastState() ?: return
        if (!game.isGameOver()) {
            game.switchGameBoard(lastState.gameBoard)
            game.setTurn(lastState.expectedToken)
        }
    }

    private fun pullLastState(): GameState? {
        return if (gameStates.isNotEmpty()) gameStates.removeAt(gameStates.lastIndex)
        else null
    }

    fun clearRegistry() {
        gameStates.clear()
    }
}