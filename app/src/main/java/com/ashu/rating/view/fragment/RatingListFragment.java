package com.ashu.rating.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashu.rating.DB.Rating;
import com.ashu.rating.Utils.CommonMethods;
import com.ashu.rating.R;
import com.ashu.rating.DB.RatingDatabase;
import com.ashu.rating.view.activity.RatingHistoryActivity;
import com.ashu.rating.view.adapter.RatingAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;


public class RatingListFragment extends BottomSheetDialogFragment {

    private static final String ARG_ITEM_COUNT = "rating_count";
    private List<Rating> ratingModelList = new ArrayList<>();
    private RatingDatabase ratingDatabase;
    private RecyclerView recyclerView;

    public static RatingListFragment newInstance(int itemCount) {
        final RatingListFragment fragment = new RatingListFragment();
        final Bundle args = new Bundle();
        args.putInt(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_rating, container, false);
        recyclerView = view.findViewById(R.id.rvRating);
        ratingDatabase = RatingDatabase.getInstance(getContext());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        int count = getArguments().getInt(ARG_ITEM_COUNT);
        for (int i = 0; i <= count; i++)
            ratingModelList.add(new Rating(String.valueOf(i)));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(new RatingAdapter(ratingModelList, ratingModel -> {
            Rating rating = new Rating(ratingModel.getRateScore());
            rating.setRateScore(ratingModel.getRateScore());
            rating.setTime(CommonMethods.getCurrentDateTime());
            ratingDatabase.ratingDAO().insertRating(rating);
            startActivity(new Intent(getContext(), RatingHistoryActivity.class));
            dismiss();
        }));
    }
}
