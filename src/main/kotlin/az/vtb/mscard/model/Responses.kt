package az.vtb.mscard.model

import java.math.BigDecimal
import java.time.LocalDate

data class GetUserCardsResponse (
    val cardholderName: String,
    val type: CardType,
    val expireDate: LocalDate,
    val balance: BigDecimal,
)
