package az.vtb.mscard.exception

import az.vtb.mscard.exception.ExceptionMessages.*
import mu.KotlinLogging
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

data class ErrorResponse(var message: String)

@RestControllerAdvice
class ErrorHandler {

    companion object {
        private val log = KotlinLogging.logger("ErrorHandler")
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    fun handle(exception: Exception): ErrorResponse {
        log.error("Exception: {}", exception.stackTraceToString())
        return ErrorResponse(UNEXPECTED_ERROR.message)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    fun handle(methodNotSupportedException: HttpRequestMethodNotSupportedException): ErrorResponse {
        log.error("HttpRequestMethodNotSupportedException {}", methodNotSupportedException.stackTraceToString())
        return ErrorResponse(METHOD_NOT_ALLOWED_ERROR.message)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(BAD_REQUEST)
    fun handle(httpMessageNotReadableException: HttpMessageNotReadableException): ErrorResponse {
        log.error("HttpMessageNotReadableException {}", httpMessageNotReadableException.stackTraceToString())
        return ErrorResponse(METHOD_NOT_ALLOWED_ERROR.message)
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(NOT_FOUND)
    fun handle(notFoundException: NotFoundException): ErrorResponse {
        log.error("NotFoundException {}", notFoundException.stackTraceToString())
        return ErrorResponse(NOT_FOUND_ERROR.message)
    }

    @ExceptionHandler(CustomFeignException::class)
    fun handle(customFeignException: CustomFeignException): ResponseEntity<ErrorResponse> {
        log.error("CustomFeignException {}", customFeignException.stackTraceToString())
        return ResponseEntity
            .status(customFeignException.status)
            .body(ErrorResponse(customFeignException.message))
    }
}
