package com.na.coworking.data.di

import com.na.coworking.data.network.ApiService
import com.na.coworking.data.BuildConfig
import com.na.coworking.domain.interfaces.authorization.TokenRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
class ServiceModule {
    @Provides
    @TokenScope
    fun provideService(tokenRepository: TokenRepository): ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.API_ADDRESS)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getClient(tokenRepository))
        .build()
        .create()

    private fun getClient(tokenRepository: TokenRepository) = OkHttpClient().newBuilder()
        .addInterceptor {
            val token = runBlocking { tokenRepository.getToken() }
            val requestWithHeaders = it.request()
                .newBuilder()
                .header("Content-Type", "application/json-patch+json")
                .header("accept", "text/plain")

            return@addInterceptor if (token.value.login == null) {
                it.proceed(requestWithHeaders.build())
            } else {
                it.proceed(requestWithHeaders.header("Authorization", "Bearer ${token.value.login}").build())
            }


        }
        .build()
}