package com.shmulik.riversidemovie.ui.navigaion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shmulik.riversidemovie.ui.page.favorite.FavoritePage
import com.shmulik.riversidemovie.ui.page.movie_details.MovieDetailsPage
import com.shmulik.riversidemovie.ui.page.search.SearchPage


@Composable
fun MainGraph(
    mainNavController: NavHostController
) {

    NavHost(
        navController = mainNavController,
        startDestination = Route.MainMovieRoute,
        route = Graph.Main::class

    ) {
        navigation<Route.MainMovieRoute>(startDestination = Route.SearchRoute) {
            composable<Route.SearchRoute> {
                SearchPage(navController = mainNavController)
            }
            composable<Route.MovieDetailsRoute> {
                MovieDetailsPage(navController = mainNavController)
            }
            composable<Route.FavoriteRoute> {
                FavoritePage(navController = mainNavController)
            }


        }
    }
}