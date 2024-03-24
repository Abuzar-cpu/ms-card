package az.vtb.mscard.util

import java.util.*


// NOTE: This is the card id generator for now. It will be replaced with a more sophisticated one in the future.
enum class CardUtil {
    CARD_UTIL;

    fun generateUniqueTenDigitNumber(): Long {
        val random = Random()
        // Generate a long between 1 billion (10^9) and 10 billion (10^10) - 1
        val randomNumber = random.nextLong(1_000_000_000L, 10_000_000_000L)

        // In case the first random number is less than 10 digits, pad with zeroes
        return if (randomNumber < 100_000_000L) {
            randomNumber + 1_000_000_000L
        } else {
            randomNumber
        }
    }

}
