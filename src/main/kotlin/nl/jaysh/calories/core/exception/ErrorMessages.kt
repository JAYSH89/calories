package nl.jaysh.calories.core.exception

object ErrorMessages {
  const val INVALID_PASSWORD =
    "password must be at least 10 characters and contain an uppercase, lowercase and special character"
  const val INVALID_EMAIL_ADDRESS = "invalid email address"
  const val EMAIL_EXISTS = "email already exists"
  const val NOT_FOUND = "resource not found"
  const val BAD_REQUEST = "request body is missing or invalid"
  const val UNEXPECTED_ERROR = "an unexpected error occurred"
}
