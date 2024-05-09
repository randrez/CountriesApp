package com.randrez.countriesapp.tools

import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.net.URLEncoder

fun encodeImage(image: String): String =
    try {
        URLEncoder.encode(image, "UTF-8")
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
        ""
    }

fun decodeImage(image: String) =
    try {
        URLDecoder.decode(image, "UTF-8")
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
        ""
    }
