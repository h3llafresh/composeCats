package by.vlfl.composecats.app.screens.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.vlfl.composecats.app.R
import by.vlfl.composecats.app.screens.home.views.CatItemModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is HomeViewState.HasCats -> CatsContent(
            cats = (uiState as HomeViewState.HasCats).cats
        ) { catItemModel ->
            with(catItemModel) {
                navController.navigate(
                    "catDetails/${breedName}/${breedTemperament}/${breedOrigin}/${breedDescription}?imageUrl=${imageUrl}"
                )
            }
        }
        is HomeViewState.NoCats -> EmptyView()
        is HomeViewState.Error -> ErrorView()
        is HomeViewState.Loading -> LoadingView()
    }
}

@Composable
fun CatsContent(
    cats: List<CatItemModel>,
    selectCat: (CatItemModel) -> Unit
) {
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(cats.size) { index ->
            CatItemModel(
                cats[index],
                selectCat
            )
            if (index < cats.lastIndex) {
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun CatItemModel(
    model: CatItemModel,
    onCatClick: (CatItemModel) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .clickable {
                onCatClick(model)
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(model.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_downloading),
            contentDescription = stringResource(id = R.string.home_screen__desc_cat_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .size(100.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray, CircleShape)
        )
        Text(
            text = model.breedName,
            fontSize = 28.sp,
            fontWeight = FontWeight.W300
        )
    }
}

@Composable
fun EmptyView() {
    Text(text = stringResource(id = R.string.home_screen__text_empty_list))
}

@Composable
fun ErrorView() {
    Text(text = stringResource(id = R.string.home_screen__text_error))
}

@Composable
fun LoadingView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}