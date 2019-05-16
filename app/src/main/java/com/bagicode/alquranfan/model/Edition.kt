package com.bagicode.alquranfan.model


import com.google.gson.annotations.SerializedName

data class Edition(
    @SerializedName("englishName")
    var englishName: String,
    @SerializedName("format")
    var format: String,
    @SerializedName("identifier")
    var identifier: String,
    @SerializedName("language")
    var language: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("type")
    var type: String
)