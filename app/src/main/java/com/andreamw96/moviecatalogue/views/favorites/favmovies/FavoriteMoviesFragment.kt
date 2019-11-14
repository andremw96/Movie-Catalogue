package com.andreamw96.moviecatalogue.views.favorites.favmovies


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.moviecatalogue.BaseFragment
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.utils.showSnackbar
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import com.andreamw96.moviecatalogue.views.favorites.FavoritesAdapter
import com.andreamw96.moviecatalogue.views.favorites.FavoritesViewModel
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favorite_movies.*


/**
 * A simple [Fragment] subclass.
 */
class FavoriteMoviesFragment : BaseFragment(), OnItemClickListener {

    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun getLayout(): Int {
        return R.layout.fragment_favorite_movies
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(FavoritesViewModel::class.java)
        favoritesViewModel.setIsMovie(true)

        favoritesAdapter = FavoritesAdapter(context, this, requestManager)
        rv_favorite_movies.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = favoritesAdapter
        }

        showFavoriteMovies()
    }

    private fun showFavoriteMovies() {
        showLoading()

        favoritesViewModel.favorites.removeObservers(viewLifecycleOwner)
        favoritesViewModel.favorites.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                favoritesAdapter.bindData(it)
            } else {
                showSnackbar(fragment_favorite_movies, "Gagal memuat list favorite movies", Snackbar.LENGTH_INDEFINITE,
                        View.OnClickListener { showFavoriteMovies() }, "Retry")
            }

            hideLoading()
        })

    }

    override fun onItemClicked(position: Int) {
        val goToDetail = Intent(activity, DetailMovieActivity::class.java)
        goToDetail.putExtra(DetailMovieActivity.INTENT_MOVIE, favoritesAdapter.listFavorite[position].id)
        startActivity(goToDetail)
    }


}
