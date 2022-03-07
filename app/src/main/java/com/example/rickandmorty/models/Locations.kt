package com.example.rickandmorty.models

import android.os.Parcelable
import com.example.rickandmorty.presentation.adapter.Equatable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class LocationsInfo(
	@SerializedName("info") val info : InfoLocations,
	@SerializedName("results") val results: List<Locations>
)

data class InfoLocations (
	@SerializedName("count") val count : Int,
	@SerializedName("pages") val pages : Int,
	@SerializedName("next") val next : String,
	@SerializedName("prev") val prev : String
)

@Parcelize
data class Locations (
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("type") val type : String,
	@SerializedName("dimension") val dimension : String,
	@SerializedName("residents") val residents : List<String>,
	@SerializedName("url") val url : String,
	@SerializedName("created") val created : String
): Equatable, Parcelable