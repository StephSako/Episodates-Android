package com.example.episodates.model.adapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.episodates.R;
import com.example.episodates.model.response.Serie;
import com.example.episodates.view.fragments.FollowedSeriesList;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterRV_FollowedSeries extends RecyclerView.Adapter<AdapterRV_FollowedSeries.ViewHolder> {

    private ArrayList<Serie> series;
    private FollowedSeriesList fragment;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvSerieName, tvFutureSeasonEpisode;
        ImageView ivImageSerie;

        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            tvDate = v.findViewById(R.id.tvDate);
            tvSerieName = v.findViewById(R.id.tvEpisodeName);
            tvFutureSeasonEpisode = v.findViewById(R.id.tvSeasonEpisode);
            ivImageSerie = v.findViewById(R.id.ivImageEpisode);
        }
    }

    public AdapterRV_FollowedSeries(ArrayList<Serie> series, FollowedSeriesList fragment) {
        this.series = series;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public AdapterRV_FollowedSeries.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_followed_series, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        DateFormat dfl = DateFormat.getDateInstance(DateFormat.FULL);
        holder.tvDate.setText(dfl.format(series.get(position).getFutureDate()));
        holder.tvFutureSeasonEpisode.setText(series.get(position).getFutureEpisode());
        holder.tvSerieName.setText(series.get(position).getName());

        if (series.get(position).getImage() != null) {
            Glide.with(fragment)
                    .load(series.get(position).getImage().getOriginal())
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.ic_launcher)
                            .fitCenter())
                    .into(holder.ivImageSerie);
        }

        /*holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, TeamActivity.class);
                intent.putExtra(CLE_DONNEES_ID_TEAM, Integer.parseInt(values.get(position).getIdTeam()));
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return series.size();
    }

}