package az.vtb.mscard.dao

import az.vtb.mscard.model.CardType
import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.GenerationType.IDENTITY
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "cards")
data class CardEntity(
    @field: Id
    @field: GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,

    var cardId: String,
    var userId: String,
    var accountNumber: String,

    var cardholderName: String,
    @field: Enumerated(STRING)

    var type: CardType,
    var expireDate: LocalDate,
    var cvv: String,
    var pan: String,

    @field: CreationTimestamp
    var createdAt: LocalDateTime,

    @field: UpdateTimestamp
    var updatedAt: LocalDateTime,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CardEntity
        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
