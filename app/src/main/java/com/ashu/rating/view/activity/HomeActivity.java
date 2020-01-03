package com.ashu.rating.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ashu.rating.R;
import com.ashu.rating.view.fragment.RatingListFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btRate = findViewById(R.id.btRate);
        btRate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btRate:
                openRatingListScreen();
                break;
        }
    }

    private void openRatingListScreen() {
        RatingListFragment.newInstance(5).show(getSupportFragmentManager(), RatingListFragment.class.getSimpleName());
    }

}
