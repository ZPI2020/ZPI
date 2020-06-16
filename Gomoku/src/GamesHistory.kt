import java.io.*
import java.lang.StringBuilder
import java.time.LocalDateTime
import java.util.*
import javax.xml.stream.events.Characters
import kotlin.math.sqrt

class GamesHistory {
    private val FILE_NAME = "history.txt"

    fun save(diffLevel: String, winner: String, board: Array<IntArray>) {
        val fw = FileWriter(FILE_NAME, true)
        val date = LocalDateTime.now()

        fw.write("date:" + date.year + "/" + date.monthValue + "/" + date.dayOfMonth + "\n")
        fw.write("difficulty:$diffLevel\n")
        fw.write("winner:$winner\n")
        fw.write("board:" + board.joinToString("") { it.joinToString("") } + "\n")

        fw.close()
    }

    fun load(): List<GameLog> {
        val fis = FileInputStream(FILE_NAME)
        val isr = InputStreamReader(fis)
        val br = BufferedReader(isr)
        val sb = StringBuilder()

        val dates = mutableListOf<String>()
        val diffLevels = mutableListOf<String>()
        val winners = mutableListOf<String>()
        val gameBoards = mutableListOf<Array<IntArray>>()

        br.forEachLine { line ->
            when {
                line.startsWith("date") -> dates.add(line.substringAfter(":"))
                line.startsWith("difficulty") -> diffLevels.add(line.substringAfter(":"))
                line.startsWith("winner") -> winners.add(line.substringAfter(":"))
                line.startsWith("board") -> gameBoards.add(parseBoardStringToArray(line.substringAfter(":")))
            }
        }
        fis.close()

        val logs = mutableListOf<GameLog>()
        for (i in dates.indices)
            logs.add(GameLog(dates[i], diffLevels[i], winners[i], gameBoards[i]))

        return logs
    }

    private fun parseBoardStringToArray(boardString: String): Array<IntArray> {
        val boardSize = sqrt(boardString.length.toDouble()).toInt()
        val stringRows = boardString.chunked(boardSize)
        val board = stringRows.map {
            it.map { Character.getNumericValue(it) }.toIntArray()
        }.toTypedArray()

        return board
    }
}