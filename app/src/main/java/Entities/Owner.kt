package Entities

import com.google.gson.annotations.SerializedName

data class Owner(
        @SerializedName("login")
        val name: String,
        @SerializedName("id")
        val id: Long = 0
)