package com.randrez.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.randrez.database.entity.CountryEntity

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CountryEntity>): List<Long>

    @Query("SELECT * FROM countries WHERE name LIKE '%' || :query || '%' OR officialName LIKE '%' || :query || '%'")
    suspend fun searchCountryByQuery(query: String): List<CountryEntity>

    @Query("SELECT * FROM countries")
    suspend fun getCountries():List<CountryEntity>

    @Query("SELECT COUNT(*) FROM countries")
    suspend fun count(): Int

    @Query("SELECT * FROM countries WHERE code =:code")
    suspend fun getCountryByCode(code: String): CountryEntity?

    @Query("SELECT * FROM countries WHERE code IN(:codes)")
    suspend fun getCountriesByCodes(codes:List<String>):List<CountryEntity>
}