package com.shmulik.data.di.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.shmulik.data.BuildConfig
import com.shmulik.data.interceptor.ApiKeyInterceptor
import com.shmulik.data.interceptor.offline.OfflineInterceptor
import com.shmulik.data.interceptor.online.OnlineInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext


@Module
@InstallIn(SingletonComponent::class)
class RetrofitProviderModule {


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson,@ApplicationContext context: Context): Retrofit {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        var cache = Cache(context.cacheDir, cacheSize)


        val logInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val apiKeyInterceptor = ApiKeyInterceptor(BuildConfig.API_KEY_omdbapi)

        val offlineInterceptor = OfflineInterceptor()

        val onlineInterceptor = OnlineInterceptor()


        val client = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(offlineInterceptor)
            .addNetworkInterceptor(onlineInterceptor)
            .cache(cache)
            .build()

        return Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }
}
