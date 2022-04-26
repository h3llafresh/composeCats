package by.vlfl.composecats.data.remote.model

import by.vlfl.composecats.domain.entity.CatEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatJson(
    @Json(name = "url") val imageUrl: String,
    @Json(name = "breeds") val breedInfo: List<CatBreedInfo>
)

@JsonClass(generateAdapter = true)
data class CatBreedInfo(
    @Json(name = "name") val breedName: String,
    @Json(name = "temperament") val breedTemperament: String,
    @Json(name = "origin") val breedOrigin: String,
    @Json(name = "description") val breedDescription: String
)

fun CatJson.toDomainModel(): CatEntity {
    val breedInfo = breedInfo.firstOrNull()
    return CatEntity(
        breedName = breedInfo?.breedName ?: "Just a cat",
        breedTemperament = breedInfo?.breedTemperament ?: "Just calm and cute",
        breedOrigin = breedInfo?.breedOrigin ?: "Moon",
        breedDescription = breedInfo?.breedDescription ?: "Fluffy, loves to eat and to sleep",
        imageUrl = imageUrl
    )
}