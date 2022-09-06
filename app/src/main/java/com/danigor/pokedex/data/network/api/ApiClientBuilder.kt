package com.danigor.pokedex.data.network.api


import com.danigor.pokedex.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClientBuilder {

    private const val DEFAULT_READ_TIMEOUT_SECS = 15L
    private const val DEFAULT_CONNECT_TIMEOUT_SECS = 5L

    fun <S> createService(
        serviceClass: Class<S>,
        baseUrl: String,
        vararg interceptors: Interceptor
    ): S {

        val httpClientBuilder = OkHttpClient.Builder()

        interceptors.forEach { interceptor -> httpClientBuilder.addInterceptor(interceptor) }

        if (BuildConfig.DEBUG) {
            // Critical part, LogClient must be last one if you have more interceptors
            httpClientBuilder.addInterceptor(getSimpleLogging())
        }

        val client = httpClientBuilder
            .readTimeout(DEFAULT_READ_TIMEOUT_SECS, TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_CONNECT_TIMEOUT_SECS, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(serviceClass)
    }
}

/**
 * Extension function that returns a simple logging with the level of
 *  [HttpLoggingInterceptor.Level.BODY]
 */
fun getSimpleLogging(): HttpLoggingInterceptor {

    return HttpLoggingInterceptor().apply {
        // logging: use NONE | HEADERS | BASIC | BODY to see request data
        level = HttpLoggingInterceptor.Level.BODY
    }
}