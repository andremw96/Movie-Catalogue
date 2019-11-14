package com.andreamw96.moviecatalogue.views.favorites.favtvshows


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
import kotlinx.android.synthetic.main.fragment_favorite_tv_shows.*


/**
 * A simple [Fragment] subclass.
 */
class FavoriteTvShowsFragment : BaseFragment(), OnItemClickListener {

    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun getLayout(): Int {
        return R.layout.fragment_favorite_tv_shows
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(FavoritesViewModel::class.java)
        favoritesViewModel.setIsMovie(false)

        favoritesAdapter = FavoritesAdapter(context, this, requestManager)
        rv_favorite_tvshows.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = favoritesAdapter
        }

        showFavoriteTvShows()
    }

    private fun showFavoriteTvShows() {
        showLoading()

        favoritesViewModel.favorites.removeObservers(viewLifecycleOwner)
        favoritesViewModel.favorites.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                favoritesAdapter.bindData(it)
            } else {
                showSnackbar(fragment_favorite_tvshows, "Gagal memuat list favorite tv shows", Snackbar.LENGTH_INDEFINITE,
                        View.OnClickListener { showFavoriteTvShows() }, "Retry")
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
