package com.example.howareyou.model

import com.google.gson.annotations.SerializedName


data class SigninDTO(

    @SerializedName("identifier") val identifier: String,
    @SerializedName("password") val password: String

)