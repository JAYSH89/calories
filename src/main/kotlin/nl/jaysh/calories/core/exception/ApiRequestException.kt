package nl.jaysh.calories.core.exception

class ApiRequestException(override val message: String) : RuntimeException(message)

class NotFoundException(override val message: String) : RuntimeException(message)
