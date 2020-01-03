package com.ashu.rating.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Flowable;


@Dao
public interface RatingDAO {

    @Query("SELECT * FROM Rating ORDER BY time desc")
    Flowable<List<Rating>> getAllRatings();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertRating(Rating rating);

}
