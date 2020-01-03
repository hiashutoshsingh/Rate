package com.ashu.rating.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ashu.rating.DB.Rating;
import com.ashu.rating.Utils.CommonMethods;
import com.ashu.rating.R;

import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.ViewHolder> {

    private final OnItemClickListener listener;
    private List<Rating> ratingModelList;

    public RatingAdapter(List<Rating> ratingModels, OnItemClickListener listener) {
        this.listener = listener;
        ratingModelList = ratingModels;
    }

    @Override
    public RatingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RatingAdapter.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(RatingAdapter.ViewHolder holder, int position) {
        Rating ratingModel = ratingModelList.get(position);
        holder.bind(ratingModel, listener);
    }

    @Override
    public int getItemCount() {
        return ratingModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text, txtRatingTime;


        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.layout_item_rating, parent, false));
            text = itemView.findViewById(R.id.text);
            txtRatingTime = itemView.findViewById(R.id.txtRatingTime);
        }

        public void bind(final Rating ratingModel, final OnItemClickListener listener) {
            text.setText(ratingModel.getRateScore());
            if (ratingModel.getTime() != null) {
                txtRatingTime.setVisibility(View.VISIBLE);
                txtRatingTime.setText(CommonMethods.getFormattedDateString(ratingModel.getTime()));
            }
            itemView.setOnClickListener(v -> listener.onItemClick(ratingModel));
        }

    }

    public interface OnItemClickListener {
        void onItemClick(Rating ratingModel);
    }

}