package com.example.repositories

import com.example.domain.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import java.sql.Timestamp

object Users : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", 512)
    val surname = varchar( "surname", 512)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}

fun ResultRow.toUser() : User {
    val created_at = Timestamp(this[Users.createdAt].millis)
    val updated_at = Timestamp(this[Users.createdAt].millis)

    return User(
            name = this[Users.name],
            surname = this[Users.surname],
            createdAt = created_at,
            updatedAt = updated_at
    )
}

class UsersRepo {
    init {
        connectToDatabase()
        transaction {
            create(Users)
        }
    }

    fun create(name: String, surname: String) : Int = transaction {
        val currentTime = now()

        Users.insert {
            it[Users.name] = name
            it[Users.surname] = surname
            it[Users.createdAt] = currentTime
            it[Users.updatedAt] = currentTime
        }[Users.id]
    }

    fun get(id: Int) : User = transaction {
        Users.select({ Users.id eq id }).limit(1).map { it.toUser() }.first()
    }

    private fun now() : DateTime {
        return DateTime.now()
    }
}
