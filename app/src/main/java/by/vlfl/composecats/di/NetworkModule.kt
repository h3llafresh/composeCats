package by.vlfl.composecats.di

import android.util.Log
import by.vlfl.composecats.data.remote.api.CatsApiService
import by.vlfl.composecats.utils.Constants.BASE_API_URL
import by.vlfl.composecats.utils.Constants.CLIENT_TIME_OUT
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor { message -> Log.d("OkHttp: ", message) }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(httpLoggingInterceptor)
        connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        writeTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
        retryOnConnectionFailure(true)
    }.build()


    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideCatsApiService(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): CatsApiService {
        val retrofit = Retrofit.Builder().apply {
            addConverterFactory(MoshiConverterFactory.create(moshi))
            client(okHttpClient)
            baseUrl(BASE_API_URL)
        }.build()

        return retrofit.create(CatsApiService::class.java)
    }
}