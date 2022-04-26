package by.vlfl.composecats.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import by.vlfl.composecats.app.screens.details.DetailsScreen
import by.vlfl.composecats.app.screens.home.HomeScreen
import by.vlfl.composecats.app.screens.home.views.CatItemModel
import by.vlfl.composecats.app.theme.ComposeCatsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCatsTheme {
                val navController = rememberNavController()
                Surface {
                    NavHost(navController, "home") {
                        composable("home") { HomeScreen(navController) }
                        composable(
                            "catDetails/{$ARG_KEY_BREED_NAME}/{$ARG_KEY_BREED_TEMPERAMENT}/{$ARG_KEY_BREED_ORIGIN}/{$ARG_KEY_BREED_DESCRIPTION}?$ARG_KEY_BREED_IMAGE_URL={$ARG_KEY_BREED_IMAGE_URL}",
                            listOf(navArgument(ARG_KEY_BREED_IMAGE_URL) { defaultValue = "" })
                        ) { backStackEntry ->
                            DetailsScreen(
                                CatItemModel(
                                    breedName = backStackEntry.arguments?.getString(ARG_KEY_BREED_NAME) ?: "",
                                    breedTemperament = backStackEntry.arguments?.getString(ARG_KEY_BREED_TEMPERAMENT) ?: "",
                                    breedOrigin = backStackEntry.arguments?.getString(ARG_KEY_BREED_ORIGIN) ?: "",
                                    breedDescription = backStackEntry.arguments?.getString(ARG_KEY_BREED_DESCRIPTION) ?: "",
                                    imageUrl = backStackEntry.arguments?.getString(ARG_KEY_BREED_IMAGE_URL) ?: ""
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

private const val ARG_KEY_BREED_NAME = "breedName"
private const val ARG_KEY_BREED_DESCRIPTION = "breedDescription"
private const val ARG_KEY_BREED_TEMPERAMENT = "breedTemperament"
private const val ARG_KEY_BREED_ORIGIN = "breedOrigin"
private const val ARG_KEY_BREED_IMAGE_URL = "imageUrl"