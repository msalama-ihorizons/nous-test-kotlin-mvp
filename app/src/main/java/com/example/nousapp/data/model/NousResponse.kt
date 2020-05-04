package com.example.nousapp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NousResponse (
    @SerializedName("items") val items: List<Item>?
) : Serializable

data class Item(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("imageUrl") val imageUrl: String?
) : Serializable