package az.vtb.mscard.controller

import az.vtb.mscard.dao.CardEntity
import az.vtb.mscard.model.CreateCardRequest
import az.vtb.mscard.service.abstraction.CardService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/cards")
class CardController(
    private val cardService: CardService
) {
    @GetMapping("/user/{userId}/info")
    fun findAllByUserId(@PathVariable userId: String): List<CardEntity> = cardService.getCardInfoByUserId(userId)

    @PostMapping
    @ResponseStatus(CREATED)
    fun createCard(@RequestBody createCardRequest: CreateCardRequest) =
        cardService.createCard(createCardRequest)

    @GetMapping("/user/{userId}")
    fun getCardInfoByUserId(@PathVariable userId: String) =
        cardService.getCardsByUserId(userId)

    @DeleteMapping("/{cardId}")
    fun deleteCard(@PathVariable cardId: String) =
        cardService.deleteCard(cardId)
}
