package com.andreamw96.moviecatalogue.views.favorites.favmovies


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andreamw96.moviecatalogue.BaseFragment
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.utils.OnRecyclerViewItemSwiped
import com.andreamw96.moviecatalogue.utils.showSnackbar
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import com.andreamw96.moviecatalogue.views.favorites.FavoritesPagedAdapter
import com.andreamw96.moviecatalogue.views.favorites.FavoritesViewModel
import com.andreamw96.moviecatalogue.views.movies.detail.DetailMovieActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favorite_movies.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMoviesFragment : BaseFragment(), OnItemClickListener, OnRecyclerViewItemSwiped {

    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var favoritesPagedAdapter: FavoritesPagedAdapter

    override fun getLayout(): Int {
        return R.layout.fragment_favorite_movies
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(FavoritesViewModel::class.java)
        favoritesViewModel.setIsMovie(true)

        val itemTouchHelperCallback = com.andreamw96.moviecatalogue.utils.ItemTouchHelper(this)
        val touchHelper = ItemTouchHelper(itemTouchHelperCallback)
        touchHelper.attachToRecyclerView(rv_favorite_movies)

        favoritesPagedAdapter = FavoritesPagedAdapter(context, this, requestManager)
        rv_favorite_movies.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = favoritesPagedAdapter
        }

        showFavoriteMovies()
    }

    private fun showFavoriteMovies() {
        showLoading()

        favoritesViewModel.favorites.removeObservers(viewLifecycleOwner)
        favoritesViewModel.favorites.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                favoritesPagedAdapter.submitList(it)
                favoritesPagedAdapter.notifyDataSetChanged()
            } else {
                showSnackbar(fragment_favorite_movies, "Gagal memuat list favorite movies", Snackbar.LENGTH_INDEFINITE,
                        View.OnClickListener { showFavoriteMovies() }, "Retry")
            }

            hideLoading()
        })

    }

    override fun onItemClicked(position: Int) {
        val goToDetail = Intent(activity, DetailMovieActivity::class.java)
        goToDetail.putExtra(DetailMovieActivity.INTENT_MOVIE, favoritesPagedAdapter.getItemById(position)?.id)
        startActivity(goToDetail)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder) {
        if (view != null) {
            val swipedPosition = viewHolder.adapterPosition
            val fav = favoritesPagedAdapter.getItemById(swipedPosition)
            if (fav != null) {
                favoritesViewModel.deleteFav(fav.id)
            }

            showSnackbar(fragment_favorite_movies, "undo delete?", Snackbar.LENGTH_INDEFINITE,
                    View.OnClickListener {
                        if (fav != null) {
                            favoritesViewModel.insertFav(fav)
                        }
                    }, "Undo")
        }
    }
}
