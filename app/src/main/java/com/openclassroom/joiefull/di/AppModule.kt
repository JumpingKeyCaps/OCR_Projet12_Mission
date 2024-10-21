package com.openclassroom.joiefull.di

import android.content.Context
import androidx.room.Room
import com.openclassroom.joiefull.data.dataBase.AppDatabase
import com.openclassroom.joiefull.data.dataBase.dao.ProductDetailsDtoDao
import com.openclassroom.joiefull.data.repository.ProductDetailsRepository
import com.openclassroom.joiefull.data.repository.ProductRepository
import com.openclassroom.joiefull.data.service.JoiefullApiService
import com.openclassroom.joiefull.data.service.network.interfaces.JoiefullNetworkService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Application Module to provide Moshi and retrofit instance.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    // ----- Service API modules (RETROFIT)

    /**
     * Moshi instance
     * build with KotlinJsonAdapterFactory.
     */
    private val moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    /**
     * Method to provide the Retrofit instance to use.
     *
     * @return a retrofit instance ready to use with the good URL and parser.
     */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    /**
     * Method to provide the JoiefullNetworkService instance to use.
     *
     * @param retrofit instance to use.
     * @return a JoiefullNetworkService ready to use.
     */
    @Provides
    @Singleton
    fun provideJoiefullNetworkService(retrofit: Retrofit): JoiefullNetworkService {
        return retrofit.create(JoiefullNetworkService::class.java)
    }



    // ----- Local Database modules (ROOM)


    /**
     * Method to provide a CoroutineScope instance to use (with IO dispatcher)
     * @return a CoroutineScope instance ready to use.
     */
    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    /**
     * Method to provide the AppDatabase instance to use.
     * @param context the application context.
     * @param coroutineScope the coroutine scope to use. (TO REMOVE IF NO PRE-POPULATION CALLBACK NEEDED)
     * @return a AppDatabase instance ready to use.
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context, coroutineScope: CoroutineScope): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "JoiefullDB").build()
    }

    /**
     * Method to provide the ProductDetails Dao instance to use.
     * @param appDatabase the AppDatabase instance to use.
     * @return a ProductDetailsDtoDao instance ready to use.
     */
    @Provides
    fun provideProductDetailsDao(appDatabase: AppDatabase) : ProductDetailsDtoDao {
        return appDatabase.productDetailsDao()
    }


    // ----- Repository providers modules

    /**
     * Method to provide the ProductRepository instance to use.
     *
     * @param joiefullApiService instance to use for api calls.
     * @return a ProductRepository instance ready to use.
     */
    @Provides
    @Singleton
    fun provideProductRepository(joiefullApiService: JoiefullApiService): ProductRepository {
        return ProductRepository(joiefullApiService)
    }

    /**
     * Method to provide the ProductDetailsRepository instance to use.
     * @param productDetailsDao instance to use for database calls.
     * @return a ProductDetailsRepository instance ready to use.
     */
    @Provides
    @Singleton
    fun provideProductDetailsRepository(productDetailsDao: ProductDetailsDtoDao): ProductDetailsRepository {
        return ProductDetailsRepository(productDetailsDao)
    }

    // ----- Context providers modules
    /**
     * Method to provide the Application Context instance to use.
     * @param context the application context.
     * @return a Context instance ready to use.
     */
    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }


}