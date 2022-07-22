package com.hunger.exercise.utils

import org.springframework.stereotype.Component
import java.util.concurrent.ThreadLocalRandom

@Component
class StringUtil {

    fun radnomEnglishAndNum(number: Int): String {
        var result = ""

        repeat(number){
            val char: String = randomChar()
            result += char
        }
        return result
    }

    private fun randomChar(): String {
        val randomInt = ThreadLocalRandom.current().nextInt(0, 61)
        var char = ""
         when (randomInt) {
             in 0..9 -> char = Char(randomInt + 48 ).toString()
             in 10..35 -> char = Char(randomInt + 65 - 10).toString()
             in 36..61 -> char = Char(randomInt + 97 - 36).toString()
         }
        return char
    }


}