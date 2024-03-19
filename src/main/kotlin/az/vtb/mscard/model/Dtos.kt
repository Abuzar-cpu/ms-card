package az.vtb.mscard.model

import az.vtb.mscard.model.AccountType.CARD
import java.math.BigDecimal
import java.math.BigDecimal.ZERO

data class CreateAccountRequestDto(
    val userId: String,
    val cardId: String?,
    val accountType: AccountType = CARD,
    val balance: BigDecimal = ZERO,
)

data class CreateAccountResponseDto(
    val accountNumber: String,
)

data class GetUserAccountResponseDto(
    val userId: String,
    val cardId: String?,
    val balance: BigDecimal,
    val accountType: AccountType,
)

enum class AccountType {
    CARD
}
