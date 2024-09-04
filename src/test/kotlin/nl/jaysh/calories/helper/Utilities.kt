package nl.jaysh.calories.helper

fun String.minifyJson(): String {
  return this.replace(Regex("\\s+"), "")
    .replace(Regex("\\{\\s*"), "{")
    .replace(Regex("\\s*\\}"), "}")
    .replace(Regex("\\[\\s*"), "[")
    .replace(Regex("\\s*]"), "]")
    .replace(Regex("\\s*:\\s*"), ":")
    .replace(Regex("\\s*,\\s*"), ",")
}
