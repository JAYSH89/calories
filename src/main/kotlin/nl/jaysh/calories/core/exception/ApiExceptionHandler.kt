package nl.jaysh.calories.core.exception

import com.google.firebase.auth.AuthErrorCode
import com.google.firebase.auth.FirebaseAuthException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.NoHandlerFoundException

@ControllerAdvice
class ApiExceptionHandler {

  @ExceptionHandler(value = [ApiRequestException::class])
  fun handleApiException(exception: ApiRequestException): ResponseEntity<ApiException> {
    val status = HttpStatus.BAD_REQUEST
    val apiException = ApiException(message = exception.message ?: "", statusCode = status)
    return ResponseEntity(apiException, status)
  }

  @ExceptionHandler(value = [NoHandlerFoundException::class, NotFoundException::class])
  fun handleNotFoundException(exception: NoHandlerFoundException): ResponseEntity<ApiException> {
    val status = HttpStatus.NOT_FOUND
    val apiException = ApiException(message = ErrorMessages.NOT_FOUND, statusCode = status)
    return ResponseEntity(apiException, status)
  }

  @ExceptionHandler(value = [HttpMessageNotReadableException::class])
  fun handleBadRequest(exception: HttpMessageNotReadableException): ResponseEntity<ApiException> {
    val status = HttpStatus.BAD_REQUEST
    val apiException = ApiException(message = ErrorMessages.BAD_REQUEST, statusCode = status)
    return ResponseEntity(apiException, status)
  }

  @ExceptionHandler(value = [FirebaseAuthException::class])
  fun handleFirebaseAuthException(exception: FirebaseAuthException): ResponseEntity<ApiException> {
    val emailExists = exception.authErrorCode == AuthErrorCode.EMAIL_ALREADY_EXISTS

    val statusCode = if (emailExists) HttpStatus.CONFLICT else HttpStatus.INTERNAL_SERVER_ERROR

    val message = if (emailExists) ErrorMessages.EMAIL_EXISTS else ErrorMessages.UNEXPECTED_ERROR

    val apiException = ApiException(message, statusCode)
    return ResponseEntity(apiException, statusCode)
  }

  @ExceptionHandler(value = [Exception::class])
  fun handleGeneralException(exception: Exception): ResponseEntity<ApiException> {
    val status = HttpStatus.INTERNAL_SERVER_ERROR
    val apiException = ApiException(ErrorMessages.UNEXPECTED_ERROR, status)
    return ResponseEntity(apiException, status)
  }
}
