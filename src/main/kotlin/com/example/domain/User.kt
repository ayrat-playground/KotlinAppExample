package com.example.domain

import org.joda.time.DateTime
import java.sql.Timestamp

data class User(
        val name: String,
        val surname: String,
        val createdAt: Timestamp,
        val updatedAt: Timestamp
)
