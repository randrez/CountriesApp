package com.randrez.network

import com.randrez.network.ConstantsApi.all
import com.randrez.network.ConstantsApi.version
import com.randrez.network.response.CountryDTO
import retrofit2.http.GET

interface ApiService {

    @GET("$version$all")
    suspend fun getAllCountries():List<CountryDTO>
}