package com.ashu.rating.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.ashu.rating.Utils.TimeStampConverter;

import java.util.Date;


@Entity
public class Rating {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "rateScore")
    private String rateScore;
    @ColumnInfo(name = "time")
    @TypeConverters({TimeStampConverter.class})
    public Date time;

    public Rating(String rateScore) {
        this.rateScore = rateScore;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRateScore() {
        return rateScore;
    }

    public void setRateScore(String rateScore) {
        this.rateScore = rateScore;
    }
}
