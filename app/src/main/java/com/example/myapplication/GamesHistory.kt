package com.example.myapplication

import android.content.Context
import android.content.Context.MODE_APPEND
import java.io.*
import java.lang.StringBuilder
import java.util.*
import kotlin.math.sqrt

class GamesHistory {
    private val FILE_NAME = "history.txt"

    fun save(diffLevel: String, winner: String, board: Array<IntArray>, ctx: Context) {
//        val fw = FileWriter(FILE_NAME, true)
        val fw = ctx.openFileOutput(FILE_NAME, MODE_APPEND)

        fw.write(("date:" + Calendar.YEAR + "/" + Calendar.MONTH + "/" + Calendar.DAY_OF_MONTH + "\n").toByteArray())
        fw.write("difficulty:$diffLevel\n".toByteArray())
        fw.write("winner:$winner\n".toByteArray())
        fw.write(("board:" + board.joinToString("") { it.joinToString("") } + "\n").toByteArray())

        fw.close()
    }

    fun load(ctx: Context): ArrayList<GameLog> {
        val fis = ctx.openFileInput(FILE_NAME)
        val isr = InputStreamReader(fis)
        val br = BufferedReader(isr)

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

        val logs = ArrayList<GameLog>()
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