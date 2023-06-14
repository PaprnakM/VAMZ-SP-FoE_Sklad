package com.example.foe_sklad.sklad

import android.content.Context
import android.widget.Toast
import java.io.*

/**
 * Trieda Sklad v sebe drží stav pokladnice a posledné požiadavky.
 * Má na starosti ukladanie aktualizovaného stavu do súboru.
 * @param context
 */
class Sklad(private val context: Context) {
    private val filename = "sklad.txt"
    private var poziadavky: IntArray
    private val sklad: IntArray

    init {
        val file = File(context.filesDir, filename)
        //ak súbor neexistuje, vytvorí sa nový, prázdny sklad
        if (!file.exists()) {
            val writer = BufferedWriter(FileWriter(file))
            val zeros = "0\n".repeat(DOBY_COUNT + DOBY_COUNT *5)
            writer.write(zeros)
            writer.close()
        }
        val reader = BufferedReader(FileReader(file))
        val lines = reader.readLines()

        poziadavky = lines.take(DOBY_COUNT).map { it.toInt() }.toIntArray()
        sklad = lines.drop(DOBY_COUNT).take(DOBY_COUNT *5).map { it.toInt() }.toIntArray()
        reader.close()
    }

    /**
     * @return poziadavky
     */
    fun getPoziadavky() : IntArray {
        return poziadavky
    }
    /**
     * @param values
     */
    fun setPoziadavky(values: IntArray) {
        poziadavky = values.copyOf()
    }
    /**
     * @return sklad
     */
    fun getSklad() : IntArray {
        return sklad
    }
    /**
     * Mení stav konkrétneho tovaru v sklade
     * @param index
     * @param value
     */
    fun setSklad(index: Int, value: Int) {
        sklad[index] = value
    }

    /**
     * Ukladá aktualizovaný stav do súboru.
     * Pomocou toastu informuje užívateľa o úspešnom uložení.
     * @exception IOException
     */
    fun saveToFile() {
        try {
            val file = File(context.filesDir, filename)
            val writer = BufferedWriter(FileWriter(file))

            val output = StringBuilder()
            poziadavky.forEach { value -> output.append(value).append("\n") }
            sklad.forEach { value -> output.append(value).append("\n") }

            writer.write(output.toString())
            writer.close()
            Toast.makeText(context, "Sklad bol aktualizovaný", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            println("An error occurred while writing to the file: ${e.message}")
        }
    }

    companion object {
        private const val DOBY_COUNT = 15
    }
}