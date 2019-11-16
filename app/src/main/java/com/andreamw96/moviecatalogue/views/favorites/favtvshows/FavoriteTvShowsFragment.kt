package com.andreamw96.moviecatalogue.views.favorites.favtvshows


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
import com.andreamw96.moviecatalogue.views.tvshows.detail.DetailTvShowActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favorite_tv_shows.*


/**
 * A simple [Fragment] subclass.
 */
class FavoriteTvShowsFragment : BaseFragment(), OnItemClickListener, OnRecyclerViewItemSwiped {


    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var favoritesPagedAdapter: FavoritesPagedAdapter

    override fun getLayout(): Int {
        return R.layout.fragment_favorite_tv_shows
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(FavoritesViewModel::class.java)
        favoritesViewModel.setIsMovie(false)

        val itemTouchHelperCallback = com.andreamw96.moviecatalogue.utils.ItemTouchHelper(this)
        val touchHelper = ItemTouchHelper(itemTouchHelperCallback)
        touchHelper.attachToRecyclerView(rv_favorite_tvshows)

        favoritesPagedAdapter = FavoritesPagedAdapter(context, this, requestManager)
        rv_favorite_tvshows.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = favoritesPagedAdapter
        }

        showFavoriteTvShows()
    }

    private fun showFavoriteTvShows() {
        showLoading()

        favoritesViewModel.favorites.removeObservers(viewLifecycleOwner)
        favoritesViewModel.favorites.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                favoritesPagedAdapter.submitList(it)
                favoritesPagedAdapter.notifyDataSetChanged()
            } else {
                showSnackbar(fragment_favorite_tvshows, "Gagal memuat list favorite tv shows", Snackbar.LENGTH_INDEFINITE,
                        View.OnClickListener { showFavoriteTvShows() }, "Retry")
            }

            hideLoading()
        })

    }

    override fun onItemClicked(position: Int) {
        val goToDetail = Intent(activity, DetailTvShowActivity::class.java)
        goToDetail.putExtra(DetailTvShowActivity.INTENT_TV_SHOW, favoritesPagedAdapter.getItemById(position)?.id)
        startActivity(goToDetail)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder) {
        if (view != null) {
            val swipedPosition = viewHolder.adapterPosition
            val fav = favoritesPagedAdapter.getItemById(swipedPosition)
            if (fav != null) {
                favoritesViewModel.deleteFav(fav.id)
            }

            showSnackbar(fragment_favorite_tvshows, "undo delete?", Snackbar.LENGTH_INDEFINITE,
                    View.OnClickListener {
                        if (fav != null) {
                            favoritesViewModel.insertFav(fav)
                        }
                    }, "Undo")
        }
    }
}
