package az.vtb.mscard.exception

class ConflictException(override val message: String) : RuntimeException(message)
class NotFoundException(override val message: String) : RuntimeException(message)
class CustomFeignException(override val message: String, val status: Int) : RuntimeException(message)
