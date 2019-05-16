package com.bagicode.alquranfan.model


import com.google.gson.annotations.SerializedName

data class Ayah(
    @SerializedName("hizbQuarter")
    var hizbQuarter: Int,
    @SerializedName("juz")
    var juz: Int,
    @SerializedName("manzil")
    var manzil: Int,
    @SerializedName("number")
    var number: Int,
    @SerializedName("numberInSurah")
    var numberInSurah: Int,
    @SerializedName("page")
    var page: Int,
    @SerializedName("ruku")
    var ruku: Int,
    @SerializedName("sajda")
    var sajda: Boolean,
    @SerializedName("text")
    var text: String
)