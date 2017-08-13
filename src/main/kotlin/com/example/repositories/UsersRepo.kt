package com.example.repositories

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object Users : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", 512)
    val surname = varchar( "surname", 512)
}
class UsersRepo() {
    init {
        connectToDatabase()
        transaction {
            create(Users)
        }
    }

    fun create(name: String, surname: String) = transaction {
        Users.insert {
            it[Users.name] = name
            it[Users.surname] = surname
        }[Users.id]
    }
}
