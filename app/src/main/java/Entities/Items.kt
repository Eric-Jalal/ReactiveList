package Entities

import com.google.gson.annotations.SerializedName

data class Items(
        @SerializedName("id")
        val id: Long = 0,
        @SerializedName("name")
        val name: String,
        @SerializedName("size")
        val size: String,
        @SerializedName("owner")
        val owner: Owner,
        @SerializedName("has_wiki")
        val hasWiki: Boolean = false
)