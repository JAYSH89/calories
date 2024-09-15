package nl.jaysh.calories.core

import nl.jaysh.calories.core.model.profile.ProfileEntity
import org.jetbrains.exposed.sql.SchemaUtils
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class SchemaInitialize : ApplicationRunner {
  override fun run(args: ApplicationArguments?) {
    SchemaUtils.create(ProfileEntity)
  }
}
