package nl.jaysh.calories.core.model.profile

import java.time.LocalDate
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object ProfileEntity : Table(name = "profile") {
  val userId: Column<String> = varchar(name = "user_id", length = 100)
  val height: Column<Int?> = integer(name = "height").nullable()
  val weight: Column<Int?> = integer(name = "weight").nullable()
  val birthday: Column<LocalDate?> = date(name = "birthday").nullable()
  val sex: Column<String?> = varchar(name = "sex", length = 10).nullable()
  val physicalActivityLevel: Column<String?> =
    varchar(name = "physical_activity_level", length = 50).nullable()

  override val primaryKey: PrimaryKey = PrimaryKey(userId)
}

fun ResultRow.toProfile(): Profile {
  val level = this[ProfileEntity.physicalActivityLevel]?.let { PhysicalActivityLevel.valueOf(it) }
  val sex = this[ProfileEntity.sex]?.let { Sex.valueOf(it) }

  return Profile(
    userId = this[ProfileEntity.userId],
    height = this[ProfileEntity.height],
    weight = this[ProfileEntity.weight],
    birthday = this[ProfileEntity.birthday],
    sex = sex,
    physicalActivityLevel = level,
  )
}
