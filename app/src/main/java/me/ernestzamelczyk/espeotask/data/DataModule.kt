package me.ernestzamelczyk.espeotask.data

import dagger.Module
import dagger.Provides
import me.ernestzamelczyk.espeotask.data.repository.EspeoPositionRepository
import me.ernestzamelczyk.espeotask.data.service.EspeoPositionApiService
import me.ernestzamelczyk.espeotask.data.service.ExperienceService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Provides @Singleton
    fun provideEspeoPositionRepository(espeoPositionApiService: EspeoPositionApiService): EspeoPositionRepository {
        return EspeoPositionRepository(espeoPositionApiService)
    }

    @Provides @Singleton
    fun provideExperienceService(): ExperienceService = ExperienceService()

    @Provides @Singleton
    fun provideApiService(retrofit: Retrofit): EspeoPositionApiService {
        return retrofit.create(EspeoPositionApiService::class.java)
    }

    @Provides @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://pastebin.com/")
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

}