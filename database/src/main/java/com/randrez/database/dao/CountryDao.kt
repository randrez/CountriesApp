package com.randrez.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.randrez.database.entity.CountryEntity

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list:List<CountryEntity>):List<Long>

    @Query("SELECT * FROM countries")
    suspend fun getAll():List<CountryEntity>

    @Query("SELECT * FROM countries WHERE code =:code")
    suspend fun getCountryByCode(code:String):CountryEntity?
}