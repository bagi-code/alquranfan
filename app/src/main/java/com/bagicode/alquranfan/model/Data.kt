package com.bagicode.alquranfan.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("ayahs")
    var ayahs: List<Ayah>,
    @SerializedName("edition")
    var edition: Edition,
    @SerializedName("englishName")
    var englishName: String,
    @SerializedName("englishNameTranslation")
    var englishNameTranslation: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("number")
    var number: Int,
    @SerializedName("numberOfAyahs")
    var numberOfAyahs: Int,
    @SerializedName("revelationType")
    var revelationType: String
)