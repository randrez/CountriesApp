package com.randrez.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.randrez.database.dao.CountryDao
import com.randrez.database.entity.CountryEntity

@Database(entities = [CountryEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}