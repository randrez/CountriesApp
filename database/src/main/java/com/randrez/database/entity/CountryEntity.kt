package com.randrez.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val code: String,
    val name: String?,
    val officialName: String?,
    val region: String?,
    val subregion: String?,
    val population: String?,
    val capital: String?,
    val borders: List<String>,
    val latitude: Double?,
    val longitude: Double?,
    val image: String?
)