package co.thyagoneves.eventnews.di

import co.thyagoneves.eventnews.repositories.EventsRepository
import co.thyagoneves.eventnews.rest.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EventsModule {
    @Provides
    @Singleton
    fun provideRetrofitService() : RetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://5f5a8f24d44d640016169133.mockapi.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }

    @Provides
    @Singleton
    fun providesEventsRepository(): EventsRepository = EventsRepository(provideRetrofitService())
}