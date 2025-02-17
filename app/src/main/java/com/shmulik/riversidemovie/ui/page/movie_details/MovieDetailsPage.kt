package com.shmulik.riversidemovie.ui.page.movie_details

import android.view.LayoutInflater
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.shmulik.riversidemovie.R
import com.shmulik.riversidemovie.databinding.MovieLayoutBinding
import com.shmulik.riversidemovie.util.loadImage


@Composable
fun MovieDetailsPage(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val movieDetailUiState by viewModel.movieDetailUiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {
            MovieDetailsScreen(movieDetailUiState){
                viewModel.onFavoriteClicked()
            }
        }
    }
}

@Composable
fun MovieDetailsScreen(
    movieDetailUiState: MovieDetailUiState,
    onFavoriteClick: () -> Unit){
    val movieEntity = movieDetailUiState.movieEntity ?: return

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            val binding = MovieLayoutBinding.inflate(LayoutInflater.from(context))
            binding.root
        },
        update = { view ->
            movieEntity.let {
                val binding = MovieLayoutBinding.bind(view)

                binding.movieTitle.text = it.title
                binding.movieYear.text = it.year
                binding.movieReleased.text = it.released
                binding.movieLanguage.text = it.language
                binding.movieCountry.text = it.country
                if(it.ratings.isNotEmpty()){
                    binding.movieDescription.text = it.ratings.size.toString()
                }
                val isFavorite = movieDetailUiState.isFavorite
                binding.favoriteButton.setImageResource(
                    if (isFavorite) R.drawable.ic_favorite_fill_white_48 else R.drawable.ic_favorite_border_white_48
                )

                binding.favoriteButton.setOnClickListener {
                    onFavoriteClick()
                }

                binding.moviePoster.loadImage(it.poster)
            }
        }
    )
}




