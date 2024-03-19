package az.vtb.mscard.model

data class CreateCardRequest(
    val cardholderName: String,
    val userId: String,
    val cardNumber: String,
    val cvv: String,
)
