package nl.jaysh.calories.exception

class ApiRequestException(override val message: String) : RuntimeException(message)
