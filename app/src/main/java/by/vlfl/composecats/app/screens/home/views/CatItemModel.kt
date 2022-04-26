package by.vlfl.composecats.app.screens.home.views

import android.os.Parcelable
import by.vlfl.composecats.domain.entity.CatEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatItemModel(
    val breedName: String,
    val breedTemperament: String,
    val breedOrigin: String,
    val breedDescription: String,
    val imageUrl: String
) : Parcelable

fun CatEntity.toCardItemModel() = CatItemModel(
    breedName = breedName,
    breedTemperament = breedTemperament,
    breedOrigin = breedOrigin,
    breedDescription = breedDescription,
    imageUrl = imageUrl
)