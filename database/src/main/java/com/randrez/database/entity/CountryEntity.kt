package com.randrez.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val code: String,
    val name: String?,
    val officialName: String?,
    val region: String?,
    val population:String?,
    val capital: String?,
    val borders: String?
)