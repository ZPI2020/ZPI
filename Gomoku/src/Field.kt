class Field {
    var token: Token? = null

    fun isEmpty(): Boolean {
        return token == null
    }
}