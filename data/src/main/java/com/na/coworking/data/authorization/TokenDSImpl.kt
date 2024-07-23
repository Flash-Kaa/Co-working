package com.na.coworking.data.authorization

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.na.coworking.domain.entities.Token
import com.na.coworking.domain.interfaces.authorization.TokenDataSource

internal class TokenDSImpl(context: Context) : TokenDataSource {
    private val storage = EncryptedSharedPreferences.create(
        "prefs_data",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override suspend fun getToken(): Token {
        val value = storage.getString(StorageKeys.TokenValue.toString(), "") ?: ""
        return Token(value)
    }

    override suspend fun updateToken(token: Token) {
        storage.edit().putString(StorageKeys.TokenValue.toString(), token.value).apply()
    }

    private enum class StorageKeys {
        TokenValue
    }
}