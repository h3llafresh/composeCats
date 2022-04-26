package by.vlfl.composecats.app.screens.details

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.vlfl.composecats.app.R
import by.vlfl.composecats.app.screens.home.views.CatItemModel
import coil.compose.AsyncImage

@Composable
fun DetailsScreen(
    argument: CatItemModel
) {

    val isExpanded = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = argument.imageUrl,
            placeholder = painterResource(id = R.drawable.ic_downloading),
            contentDescription = stringResource(id = R.string.details_screen__desc_cat_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(300.dp)
                .padding(bottom = 4.dp)
                .clip(RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp))
        )
        BreedNameRow(
            breedName = argument.breedName,
            isExpanded = isExpanded
        ) {
            isExpanded.value = !isExpanded.value
        }
        Column(
            modifier = if (isExpanded.value) {
                Modifier
                    .wrapContentHeight()
                    .animateContentSize(TweenSpec(500))
            } else {
                Modifier
                    .height(0.dp)
                    .animateContentSize(TweenSpec(500))
            }
        ) {
            DetailRow(
                detailTitle = stringResource(id = R.string.details_screen__text_temperament_subtitle),
                detailValue = argument.breedTemperament
            )
            DetailRow(
                detailTitle = stringResource(id = R.string.details_screen__text_origin_title),
                detailValue = argument.breedOrigin
            )
            DetailRow(
                detailTitle = stringResource(id = R.string.details_screen__text_description_title),
                detailValue = argument.breedDescription
            )
        }
    }
}

@Composable
fun BreedNameRow(
    breedName: String,
    isExpanded: MutableState<Boolean>,
    onExpandClickListener: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = breedName,
            fontSize = 46.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Cursive,
            modifier = Modifier.weight(0.85F)
        )
        IconButton(
            onClick = { onExpandClickListener() },
            modifier = Modifier.weight(0.15F)
        ) {
            if (isExpanded.value) {
                Icon(
                    Icons.Filled.KeyboardArrowUp,
                    contentDescription = stringResource(id = R.string.details_screen__desc_hide_details_icon),
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            } else {
                Icon(
                    Icons.Filled.KeyboardArrowDown,
                    contentDescription = stringResource(id = R.string.details_screen__desc_expand_details_icon),
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
        }
    }
    Divider(
        color = Color.Gray,
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@Composable
fun DetailRow(
    detailTitle: String,
    detailValue: String
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.padding(top = 4.dp)
    ) {
        Text(
            text = detailTitle,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.width(140.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = detailValue)
    }
}

@Preview
@Composable
fun PreviewDetailsRow() {
    DetailRow(
        detailTitle = "Title",
        detailValue = "Value"
    )
}