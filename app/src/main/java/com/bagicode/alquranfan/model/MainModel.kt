package com.bagicode.alquranfan.model


import com.google.gson.annotations.SerializedName

data class MainModel(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("code")
    var code: Int,
    @SerializedName("status")
    var status: String
)