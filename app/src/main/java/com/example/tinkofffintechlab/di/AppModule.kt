package com.example.tinkofffintechlab.di

import android.content.Context
import androidx.room.Room
import com.example.tinkofffintechlab.common.Constants
import com.example.tinkofffintechlab.data.AppDataBase
import com.example.tinkofffintechlab.data.remote.api.FilmsApi
import com.example.tinkofffintechlab.data.repository.FilmRepositoryImpl
import com.example.tinkofffintechlab.domain.dao.FilmDao
import com.example.tinkofffintechlab.domain.repository.FilmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "film-database"
        ).build()
    }

    @Singleton
    @Provides
    fun providesFilmsApi(retrofit: Retrofit): FilmsApi {
        return retrofit.create(FilmsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFilmDao(appDataBase: AppDataBase): FilmDao {
        return appDataBase.getFilmDao()
    }

    @Provides
    @Singleton
    fun providesFilmRepository(filmsApi: FilmsApi, filmDao: FilmDao): FilmRepository {
        return FilmRepositoryImpl(filmsApi, filmDao)
    }

}