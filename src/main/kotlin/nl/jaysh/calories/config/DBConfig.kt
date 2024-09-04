package nl.jaysh.calories.config

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource
import nl.jaysh.database.Database
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DBConfig {

  @Bean
  fun hikariConfig(): HikariConfig {
    return HikariConfig().apply {
      jdbcUrl = "jdbc:postgresql://localhost:5432/calories"
      username = "postgres"
      password = "postgres"
      driverClassName = "org.postgresql.Driver"
      maximumPoolSize = 10
      connectionTimeout = 30_000
      idleTimeout = 600_000
      maxLifetime = 1_800_000
    }
  }

  @Bean
  fun dataSource(hikariConfig: HikariConfig): DataSource {
    return HikariDataSource(hikariConfig)
  }

  @Bean
  fun sqlDriver(dataSource: DataSource): SqlDriver {
    val driver = dataSource.asJdbcDriver()

    Database.Schema.create(driver)

    return driver
  }
}
