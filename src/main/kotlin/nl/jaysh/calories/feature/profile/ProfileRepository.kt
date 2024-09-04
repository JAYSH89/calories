package nl.jaysh.calories.feature.profile

import nl.jaysh.calories.core.model.profile.Profile
import nl.jaysh.calories.core.model.profile.ProfileEntity
import nl.jaysh.calories.core.model.profile.ProfileEntity.userId
import nl.jaysh.calories.core.model.profile.toProfile
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.upsert
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class ProfileRepository {

  fun findById(id: String): Profile? {
    return ProfileEntity.selectAll().where { userId eq id }.map(ResultRow::toProfile).singleOrNull()
  }

  fun upsert(profile: Profile): Profile {
    val result =
      ProfileEntity.upsert {
        it[userId] = profile.userId
        it[height] = profile.height
        it[weight] = profile.weight
        it[birthday] = profile.birthday
        it[sex] = profile.sex.toString()
        it[physicalActivityLevel] = profile.physicalActivityLevel.toString()
      }
    check(result.insertedCount == 1)

    val updatedProfile = findById(profile.userId)
    requireNotNull(updatedProfile)

    return updatedProfile
  }

  fun delete(id: String) {
    val rowsChanged = ProfileEntity.deleteWhere { userId eq id }
    check(rowsChanged == 1)
  }
}
