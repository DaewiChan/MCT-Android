package com.example.mct.utilities

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

open class CommonUtil {
    fun sha256Hash(text: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(text.toByteArray(StandardCharsets.UTF_8))
        val hash = StringBuilder()

        for (byte in hashBytes) {
            hash.append(String.format("%02x", byte))
        }

        return hash.toString()
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }
}