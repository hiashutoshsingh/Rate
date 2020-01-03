package com.ashu.rating.view.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashu.rating.DB.Rating;
import com.ashu.rating.R;
import com.ashu.rating.DB.RatingDatabase;
import com.ashu.rating.view.adapter.RatingAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RatingHistoryActivity extends AppCompatActivity {

    private static final String TAG = RatingHistoryActivity.class.getSimpleName();
    private RecyclerView rvRatingHistory;
    private RatingDatabase ratingDatabase;
    private List<Rating> ratingList = new ArrayList<>();
    private RatingAdapter ratingAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_history);
        rvRatingHistory = findViewById(R.id.rvRatingHistory);

        ratingDatabase = RatingDatabase.getInstance(this);
        readData();

        rvRatingHistory.setLayoutManager(new LinearLayoutManager(this));
        ratingAdapter = new RatingAdapter(ratingList, ratingModel -> {
        });
        rvRatingHistory.setAdapter(ratingAdapter);

    }

    private void readData() {
        ratingDatabase.ratingDAO().getAllRatings()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(ratings -> {
                    ratingList.clear();
                    ratingList.addAll(ratings);
                    ratingAdapter.notifyDataSetChanged();
                }, e -> Log.d(TAG, "error: " + e.getMessage()));
    }

}
