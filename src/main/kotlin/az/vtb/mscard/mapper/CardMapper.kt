package az.vtb.mscard.mapper

import az.vtb.mscard.dao.CardEntity
import az.vtb.mscard.model.AccountType.CARD
import az.vtb.mscard.model.CardType.MASTERCARD
import az.vtb.mscard.model.CardType.VISA
import az.vtb.mscard.model.CreateAccountRequestDto
import az.vtb.mscard.model.CreateCardRequest
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

fun CreateCardRequest.toEntity(accountNumber: String, cardId: String) =
    CardEntity(
        cardId = cardId,
        userId = userId,
        cardholderName = cardholderName,
        expireDate = LocalDate.now().plusYears(3),
        cvv = cvv,
        pan = cardNumber,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        type = if (cardNumber.first().toString() == "4") VISA else MASTERCARD,
        accountNumber = accountNumber
    )

fun CreateCardRequest.toAccountRequest(cardId: String) = CreateAccountRequestDto(
    userId = userId,
    cardId = cardId,
    balance = BigDecimal.ZERO,
    accountType = CARD,

    )
