package com.andreamw96.moviecatalogue.views.favorites.favtvshows


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.model.Favorite
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.di.ViewModelProvidersFactory
import com.andreamw96.moviecatalogue.utils.runAnimation
import com.andreamw96.moviecatalogue.views.common.OnItemClickListener
import com.andreamw96.moviecatalogue.views.common.ProgressBarInterface
import com.andreamw96.moviecatalogue.views.favorites.FavoriteAdapter
import com.andreamw96.moviecatalogue.views.favorites.FavoriteViewModel
import com.andreamw96.moviecatalogue.views.tvshows.detail.DetailTvShowActivity
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_fav_tv.*
import javax.inject.Inject


class FavTvFragment : DaggerFragment(), OnItemClickListener, ProgressBarInterface {

    private lateinit var favoriteViewModel: FavoriteViewModel

    @Inject
    lateinit var providersFactory: ViewModelProvidersFactory

    private lateinit var favAdapter: FavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading()
        favoriteViewModel = ViewModelProviders.of(this, providersFactory).get(FavoriteViewModel::class.java)
        favoriteViewModel.getFavorite(false).observe(this, getFavTvs)

        favAdapter = FavoriteAdapter(context, this)

        rv_fav_tv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = favAdapter
            favAdapter.notifyDataSetChanged()
        }

    }

    private val getFavTvs = Observer<List<Favorite>> { favItems ->
        if (favItems != null) {
            favAdapter.bindData(favItems)
            runAnimation(rv_fav_tv)

            hideLoading()
        }
    }

    override fun onItemClicked(position: Int) {
        val tvShow = TvResult()
        tvShow.apply {
            id = favAdapter.listFav[position].movieId
            backdropPath = favAdapter.listFav[position].backdropPath
            name = favAdapter.listFav[position].title
            firstAirDate = favAdapter.listFav[position].releaseDate
            overview = favAdapter.listFav[position].overview
            voteAverage = favAdapter.listFav[position].voteAverage
        }

        val goToDetail = Intent(activity, DetailTvShowActivity::class.java)
        goToDetail.putExtra(DetailTvShowActivity.INTENT_TV_SHOW, tvShow)
        startActivity(goToDetail)
    }

    override fun showLoading() {
        progressBarFavTvFrag.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarFavTvFrag.visibility = View.GONE
    }
}
