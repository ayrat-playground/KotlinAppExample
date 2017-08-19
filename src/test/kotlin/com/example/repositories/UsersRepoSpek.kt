package com.example.repositories

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import kotlin.test.assertEquals

class UsersRepoSpek: Spek({
    val usersRepo = UsersRepo()

    describe("create") {
        it("creates user") {
            val name = "John"
            val surname = "Lennon"

            val userId = usersRepo.create(name, surname)
            val result = usersRepo.get(userId)

            assertEquals(result.name, name)
            assertEquals(result.surname, surname)
        }
    }
})
