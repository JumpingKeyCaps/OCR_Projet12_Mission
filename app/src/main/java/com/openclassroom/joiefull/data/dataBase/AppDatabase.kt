package com.openclassroom.joiefull.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.openclassroom.joiefull.data.dataBase.dao.ProductDetailsDtoDao
import com.openclassroom.joiefull.data.dataBase.entity.ProductDetailsDto

/**
 * The Database of the App (with Room)
 */
@Database(entities = [ProductDetailsDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDetailsDao(): ProductDetailsDtoDao
}