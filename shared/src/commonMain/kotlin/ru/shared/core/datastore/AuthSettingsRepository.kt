package ru.shared.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import model.PlatformConfiguration
import ru.shared.core.model.TokenLoginPar
import ru.shared.core.model.response.Token

interface SharedFileSystem{
    val path: String
}

expect fun fileSystem( platformConfiguration: PlatformConfiguration): SharedFileSystem

class AuthSettingsRepository(
    private val dataStore: DataStore<Preferences>
):IAuthSettingsRepository {
    companion object {
        val LOGIN_PATH = stringPreferencesKey("login_PATH")
        val PASSWORD_PATH =  stringPreferencesKey("password_PATH")
        val TOKEN_PATH =  stringPreferencesKey("token_PATH")
    }



    override val token: Flow<Token?>
        get() = dataStore.data.map {
            Token(
                token = it.let { it[TOKEN_PATH] } ?: return@map null,
            )
        }

    override val passwordLoginPar: Flow<TokenLoginPar?>
        get() = dataStore.data.map {
            TokenLoginPar(
                email = it.let { it[LOGIN_PATH] } ?: return@map null,
                password = it.let { it[PASSWORD_PATH] } ?: return@map null,
            )
        }

    override suspend fun save(login: String, password: String, token: Token) {
        dataStore.edit {
            it[LOGIN_PATH] = login
            it[PASSWORD_PATH] = password
            it[TOKEN_PATH] = token.token
        }
    }


}

internal const val dataStoreFileName = "auth.preferences_pb"