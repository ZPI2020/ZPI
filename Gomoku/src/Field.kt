class Field {

    var token: Token? = null
        private set

    fun putToken(token: Token) {
        this.token = token
    }

    fun isEmpty(): Boolean {
        return token == null
    }
}