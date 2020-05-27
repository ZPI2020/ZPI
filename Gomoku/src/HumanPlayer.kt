class HumanPlayer(playerName: String, game:MetaGame, token: Token): Player(playerName, game, token) {
    fun makeMove(row: Int, column: Int) {
        this.game.placeToken(token, row, column)
    }
}