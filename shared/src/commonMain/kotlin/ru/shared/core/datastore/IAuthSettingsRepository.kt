package ru.shared.core.datastore

import kotlinx.coroutines.flow.Flow
import ru.shared.core.model.TokenLoginPar
import ru.shared.core.model.response.Token

interface IAuthSettingsRepository {
    val token: Flow<Token?>
    val passwordLoginPar: Flow<TokenLoginPar?>

   suspend fun save(
        login: String,
        password: String,
        token: Token
    )
}