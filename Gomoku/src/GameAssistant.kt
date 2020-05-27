abstract class GameAssistant {
    protected abstract val game: MetaGame
    protected var player1: Player? = null
    protected var player2: Player? = null

    fun createNewBoard(rows: Int, columns: Int) {
        if (!game.isBoardSet())
            game.setNewBoard(Board(rows, columns))
    }

    fun getBoardCopy(): Board? {
       return game.getBoardCopy()
    }

    fun addHumanPlayer(playerName: String):HumanPlayer? {
        var player: HumanPlayer? = null
        when {
            player1 == null -> { player = HumanPlayer(playerName, game, game.token1); player1 = player }
            player2 == null -> { player = HumanPlayer(playerName, game, game.token2); player2 = player }
        }
        return player
    }

    fun addAIPlayer(playerName: String): AIPlayer? {
        var player: AIPlayer? = null
        when {
            player1 == null -> { player = AIPlayer(playerName, game, game.token1); player1 = player }
            player2 == null -> { player = AIPlayer(playerName, game, game.token2); player2 = player }
        }
        return player
    }

    fun startGame(firstTurn: Turn) {
        if (!gameAlreadyStarted() and setUpCompleted()) {
            when (firstTurn) {
                Turn.PLAYER1_TURN -> game.setNextTurn(game.token1)
                Turn.PLAYER2_TURN -> game.setNextTurn(game.token2)
                Turn.NOT_SET -> {}
            }
        }
    }

    private fun gameAlreadyStarted(): Boolean {
        return game.expectedToken != null
    }

    private fun setUpCompleted(): Boolean {
        return game.isBoardSet() and playersSet()
    }

    private fun playersSet(): Boolean {
        return (player1 != null) and (player2 != null)
    }

    fun currentTurn(): Turn {
        return when (game.expectedToken) {
            game.token1 -> Turn.PLAYER1_TURN
            game.token2 -> Turn.PLAYER2_TURN
            else -> Turn.NOT_SET
        }
    }

    fun isGameOver(): Boolean {
        return game.gameOver
    }

    fun getWinner(): Player? {
        return when (game.winningToken) {
            game.token1 -> player1
            game.token2 -> player2
            else -> null
        }
    }

    fun setUpNewGameRegister(): GameStatesRegister {
        return GameStatesRegister(game)
    }
}