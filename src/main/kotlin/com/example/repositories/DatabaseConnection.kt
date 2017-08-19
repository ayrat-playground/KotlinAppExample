package com.example.repositories

import com.example.config
import com.example.database
import org.jetbrains.exposed.sql.Database

fun connectToDatabase(dbUrl : String = config()[database.url],
                      dbUser : String = config()[database.user],
                      dbPassword : String = config()[database.password]) : Database {
    return Database.connect(dbUrl, "com.mysql.cj.jdbc.Driver", dbUser, dbPassword)
}
