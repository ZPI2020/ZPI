abstract class Player(val playerName: String) {
    protected var game: Game? = null
    var token: Token? = null
        protected set

    interface Game {
        fun placeToken(token: Token, row: Int, column: Int)
        fun getGameBoardCopy(): Board
    }

    fun play(game: Game) {
        this.game = game
    }

    fun assign(token: Token) {
        this.token = token
    }
}