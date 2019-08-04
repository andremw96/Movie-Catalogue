package com.andreamw96.moviecatalogue.views.favorites.favtvshows


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreamw96.moviecatalogue.BaseFragment
import com.andreamw96.moviecatalogue.R
import com.andreamw96.moviecatalogue.data.model.TvResult
import com.andreamw96.moviecatalogue.utils.RecyclerItemClickListener
import com.andreamw96.moviecatalogue.utils.runAnimation
import com.andreamw96.moviecatalogue.views.favorites.FavoriteAdapter
import com.andreamw96.moviecatalogue.views.favorites.FavoriteViewModel
import com.andreamw96.moviecatalogue.views.tvshows.detail.DetailTvShowActivity
import kotlinx.android.synthetic.main.fragment_fav_tv.*
import javax.inject.Inject


class FavTvFragment : BaseFragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel

    @Inject
    lateinit var favAdapter: FavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading()
        favoriteViewModel = ViewModelProviders.of(this, providersFactory).get(FavoriteViewModel::class.java)
        favoriteViewModel.setIsMovie(false)

        initRecyclerView()

        rv_fav_tv.addOnItemTouchListener(RecyclerItemClickListener(activity?.applicationContext, rv_fav_tv, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
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

            override fun onItemLongClick(view: View?, position: Int) {
            }
        }))

        setFavorites()
    }

    private fun setFavorites() {
        favoriteViewModel.favorites.removeObservers(viewLifecycleOwner)
        favoriteViewModel.favorites.observe(viewLifecycleOwner, Observer { favItems ->
            if (favItems != null) {
                favAdapter.bindData(favItems)
                runAnimation(rv_fav_tv)
                somethingHappened(true)
            } else {
                somethingHappened(false)
            }

            hideLoading()
        })
    }

    private fun initRecyclerView() {
        rv_fav_tv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = favAdapter
            favAdapter.notifyDataSetChanged()
        }
    }

    override fun showLoading() {
        progressBarFavTvFrag.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBarFavTvFrag.visibility = View.GONE
    }

    override fun somethingHappened(isSuccess: Boolean) {
        if(isSuccess) {
            rv_fav_tv.visibility = View.VISIBLE
            img_favtv_data_notfound.visibility = View.GONE
        } else {
            rv_fav_tv.visibility = View.GONE
            img_favtv_data_notfound.visibility = View.VISIBLE
        }
    }
}
