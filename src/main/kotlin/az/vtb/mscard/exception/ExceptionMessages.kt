package az.vtb.mscard.exception

enum class ExceptionMessages(val message: String) {
    NOT_FOUND_ERROR("No account found"),
    UNEXPECTED_ERROR("Unexpected error occurred"),
    METHOD_NOT_ALLOWED_ERROR("Method not supported"),
    UNKNOWN_ERROR("Unknown error occurred"),
    ALREADY_EXISTS_ERROR("Card already exists"),
}
