package az.vtb.mscard.service.abstraction

import az.vtb.mscard.dao.CardEntity
import az.vtb.mscard.model.CreateCardRequest
import az.vtb.mscard.model.GetUserCardsResponse

interface CardService {
    fun getCardInfoByUserId(userId: String): List<CardEntity>
    fun createCard(createCardRequest: CreateCardRequest)
    fun getCardsByUserId(userId: String): List<GetUserCardsResponse>
    fun deleteCard(cardId: String)
}
