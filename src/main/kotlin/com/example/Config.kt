package com.example

import com.natpryce.konfig.*

object database : PropertyGroup() {
    val url by stringType
    val user by stringType
    val password by stringType
}

fun config() = ConfigurationProperties.fromResource("application.properties")
