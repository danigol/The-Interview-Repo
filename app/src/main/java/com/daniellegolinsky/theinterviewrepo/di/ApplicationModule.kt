package com.daniellegolinsky.theinterviewrepo.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.daniellegolinsky.theinterviewrepo.api.CoolTestApi
import com.daniellegolinsky.theinterviewrepo.datastore.CoolDataStore
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    // Named Dependencies
    const val IO_DISPATCHER = "IO_DISPATCHER"
    const val COOL_TEST_API = "COOL_TEST_API" // Thank god I don't have kids, they'd be "Cool kid" and "The other cool kid"
    const val COOL_DATA_STORE = "COOL_DATA_STORE"

    @Provides
    @Singleton
    @Named(IO_DISPATCHER)
    internal fun providesIoDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    @Named(COOL_TEST_API)
    internal fun providesCoolTestApi(): CoolTestApi {
        val baseUrl = "https://gist.githubusercontent.com/"

        val gson = GsonBuilder()
            .setLenient()
            .create()
        val loggingInterceptor = HttpLoggingInterceptor() // TODO Generally only for debugging...
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY // TODO (especially that)
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(10, TimeUnit.SECONDS)

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
        return retrofit.create(CoolTestApi::class.java)
    }

    @Provides
    @Singleton
    @Named(COOL_DATA_STORE)
    fun provideCoolDataStore(
        @ApplicationContext appContext: Context,
        @Named(IO_DISPATCHER) dispatcher: CoroutineDispatcher
    ): DataStore<Preferences> {
        val dataStoreSettingsName = "cool_data_store"
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            scope = CoroutineScope(dispatcher + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(dataStoreSettingsName) }
        )
    }
}