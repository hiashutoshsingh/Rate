package com.ashu.rating.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Rating.class}, version = 1, exportSchema = false)
public abstract class RatingDatabase extends RoomDatabase {
    private static final String DB_NAME = "UserDb";
    private static RatingDatabase instance;
    public abstract RatingDAO ratingDAO();

    public synchronized static RatingDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, RatingDatabase.class, DB_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }

}
