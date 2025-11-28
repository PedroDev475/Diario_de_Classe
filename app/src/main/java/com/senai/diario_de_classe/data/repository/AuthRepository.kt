package com.senai.diario_de_classe.data.repository
import com.senai.diario_de_classe.data.local.TokenStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class AuthRepository  @Inject constructor(
    @Named("authApi") private val authApi: AuthApiService,
    @Named("secureApi") private val secureApi: AuthApiService,
    private val store: TokenStore
) {

    suspend fun login(email: String, senha: String): Boolean {
        val resp = authApi.login(LoginRequest(email, senha))
        store.saveTokens(resp.accessToken, resp.refreshToken)

        val me = secureApi.me()
        store.saveMeCached(me)
        return true
    }

    suspend fun bootstrapSession(): MeResponse? {
        return if (store.isRefreshValidNow()) {
            store.getMeCached()
        } else null
    }

    suspend fun ensureFreshAccess(): Boolean {
        if (store.isAccessValidNow()) return true
        val refresh = store.getRefreshToken()
        if (refresh.isNullOrBlank()) return false
        if (!store.isRefreshValidNow()) return false
        return runCatching {
            val rt = authApi.refresh(RefreshRequest(refresh))
            store.saveTokens(rt.accessToken, rt.refreshToken ?: refresh)
            true
        }.getOrDefault(false)
    }

    suspend fun meAndCache(): MeResponse {
        val me = secureApi.me()
        store.saveMeCached(me)
        return me
    }

    suspend fun logout() = store.clearTokens()

    fun tokenFlow(): Flow<String?> = store.token

    suspend fun ensureFreshAccessLocallyOnly(): Boolean {
        return store.isRefreshValidNow()
    }
}

