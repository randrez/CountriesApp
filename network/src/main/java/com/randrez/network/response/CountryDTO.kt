package com.randrez.network.response

import com.google.gson.annotations.SerializedName

data class CountryDTO(
    val borders: List<String>?,
    val capital: List<String>?,
    @SerializedName("cca3")
    val code: String,
    @SerializedName("flags")
    val flagDTO: FlagDTO,
    @SerializedName("latlng")
    val latitudeLongitude: List<Double>,
    @SerializedName("name")
    val namesDTO: NamesDTO,
    val population: Int?,
    val region: String?,
    val subregion: String?
)