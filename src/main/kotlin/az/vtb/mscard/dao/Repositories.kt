package az.vtb.mscard.dao

import org.springframework.data.repository.CrudRepository

interface CardRepository : CrudRepository<CardEntity, Long> {
    fun findAllByUserId(userId: String): List<CardEntity>
    fun findByCardId(cardId: String): CardEntity?
    fun findByCardholderNameAndPan(cardholderName: String, pan: String): CardEntity?
}
