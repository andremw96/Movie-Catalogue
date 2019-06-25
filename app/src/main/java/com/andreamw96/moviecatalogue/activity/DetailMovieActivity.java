package com.andreamw96.moviecatalogue.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.andreamw96.moviecatalogue.R;
import com.andreamw96.moviecatalogue.model.Movie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String INTENT_MOVIE = "intent_movie";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Movie movie = getIntent().getParcelableExtra(INTENT_MOVIE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(movie.getTitle());

        CircleImageView detail_image_movie = findViewById(R.id.detail_image_movie);
        TextView detail_title_movie = findViewById(R.id.detail_title_movie);
        TextView detail_director_movie = findViewById(R.id.detail_director_movie);
        TextView detail_description_movie = findViewById(R.id.detail_description_movie);
        TextView detail_rating_movie = findViewById(R.id.detail_rating_movie);
        TextView detail_date_movie = findViewById(R.id.detail_date_movie);

        Glide.with(this)
                .load(movie.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(detail_image_movie);

        detail_title_movie.setText(movie.getTitle());
        detail_director_movie.setText(String.format("%s%s", getString(R.string.directorString), movie.getDirector()));
        detail_description_movie.setText(movie.getDescription());
        detail_rating_movie.setText(String.format("%s%s", getString(R.string.ratingString), movie.getRating()));
        detail_date_movie.setText(String.format("%s%s", getString(R.string.releaseDateString), movie.getDate()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
