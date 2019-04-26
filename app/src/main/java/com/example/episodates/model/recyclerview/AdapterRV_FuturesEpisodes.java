package com.example.episodates.model.recyclerview;

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
import com.example.episodates.model.response.Episode;
import com.example.episodates.view.fragments.SearchedSerieFragment;

import java.util.List;

public class AdapterRV_FuturesEpisodes extends RecyclerView.Adapter<AdapterRV_FuturesEpisodes.ViewHolder> {

    private List<Episode> episodes;
    private SearchedSerieFragment fragment;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvEpisodeName, tvSeason, tvEpisode, tvSummary;
        ImageView ivImageEpisode;

        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            tvDate = v.findViewById(R.id.tvDate);
            tvEpisode = v.findViewById(R.id.tvEpisode);
            tvEpisodeName = v.findViewById(R.id.tvEpisodeName);
            tvSeason = v.findViewById(R.id.tvSeason);
            ivImageEpisode = v.findViewById(R.id.ivImageEpisode);
            tvSummary = v.findViewById(R.id.tvSummary);
        }
    }

    public AdapterRV_FuturesEpisodes(List<Episode> episodes, SearchedSerieFragment fragment) {
        this.episodes = episodes;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public AdapterRV_FuturesEpisodes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_future_episodes, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvDate.setText(episodes.get(position).getAirdate());
        holder.tvSeason.setText("Saison " + episodes.get(position).getSeason());
        holder.tvEpisodeName.setText(episodes.get(position).getName());
        holder.tvEpisode.setText("Episode " + episodes.get(position).getNumber());
        holder.tvSummary.setText(episodes.get(position).getSummary());

        if (episodes.get(position).getImageEpisode() != null) {
            Glide.with(fragment)
                    .load(episodes.get(position).getImageEpisode().getOriginal())
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.ic_launcher)
                            .fitCenter())
                    .into(holder.ivImageEpisode);
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
        return episodes.size();
    }

}