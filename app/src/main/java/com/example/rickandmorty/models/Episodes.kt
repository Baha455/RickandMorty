package com.example.rickandmorty.models

import com.google.gson.annotations.SerializedName

data class EpisodesInfo(
	@SerializedName("info") val info : InfoEpisodes,
	@SerializedName("results") val results: List<Episodes>
)


data class InfoEpisodes (
	@SerializedName("count") val count : Int,
	@SerializedName("pages") val pages : Int,
	@SerializedName("next") val next : String,
	@SerializedName("prev") val prev : String
)


data class Episodes (
	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("air_date") val air_date : String,
	@SerializedName("episode") val episode : String,
	@SerializedName("characters") val characters : List<String>,
	@SerializedName("url") val url : String,
	@SerializedName("created") val created : String
)