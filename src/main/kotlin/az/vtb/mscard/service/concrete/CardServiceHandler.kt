package az.vtb.mscard.service.concrete

import az.vtb.mscard.annotation.Log
import az.vtb.mscard.client.AccountClient
import az.vtb.mscard.dao.CardEntity
import az.vtb.mscard.dao.CardRepository
import az.vtb.mscard.exception.ConflictException
import az.vtb.mscard.exception.ExceptionMessages
import az.vtb.mscard.exception.ExceptionMessages.ALREADY_EXISTS_ERROR
import az.vtb.mscard.exception.NotFoundException
import az.vtb.mscard.mapper.toAccountRequest
import az.vtb.mscard.mapper.toEntity
import az.vtb.mscard.model.CreateCardRequest
import az.vtb.mscard.model.GetUserCardsResponse
import az.vtb.mscard.service.abstraction.CardService
import az.vtb.mscard.util.CardUtil
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Log
@Service
class CardServiceHandler(
    private val cardRepository: CardRepository,
    private val accountClient: AccountClient
) : CardService {

    override fun getCardInfoByUserId(userId: String): List<CardEntity> = cardRepository.findAllByUserId(userId)

    override fun createCard(createCardRequest: CreateCardRequest) {

        cardRepository.findByCardholderNameAndPan(
            createCardRequest.cardholderName,
            createCardRequest.cardNumber
        ) ?.let {
            throw ConflictException(ALREADY_EXISTS_ERROR.message)
        } ?: run {
            val cardId = CardUtil.CARD_UTIL.generateUniqueTenDigitNumber().toString()
            val accountResponse = accountClient.createAccount(createCardRequest.toAccountRequest(cardId = cardId))
            val card = createCardRequest.toEntity(accountNumber = accountResponse.accountNumber, cardId = cardId)

            cardRepository.save(card)
        }
    }

    override fun getCardsByUserId(userId: String): List<GetUserCardsResponse> {
        val cards = cardRepository.findAllByUserId(userId)

        return runBlocking {
            coroutineScope {
                cards.map {
                    async {
                        val account = accountClient.getAccountByCardId(it.cardId)
                        GetUserCardsResponse(
                            cardholderName = it.cardholderName,
                            type = it.type,
                            expireDate = it.expireDate,
                            balance = account.balance
                        )
                    }
                }
            }.awaitAll()
        }
    }

    override fun deleteCard(cardId: String) {
        val card = cardRepository.findByCardId(cardId)
        card?.let {
            accountClient.deleteAccount(card.accountNumber)
            cardRepository.delete(it)
        } ?: throw NotFoundException(ExceptionMessages.NOT_FOUND_ERROR.message)
    }

}
