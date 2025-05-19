package com.example.tryoutpas_23_35;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {
    private List<Team> teams;

    public TeamAdapter(List<Team> teams) {
        this.teams = teams;
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView badge;

        public TeamViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.team_name);
            badge = itemView.findViewById(R.id.team_badge);
        }
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_team, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        Team team = teams.get(position);
        holder.name.setText(team.getName());
        Glide.with(holder.itemView.getContext()).load(team.getBadge()).into(holder.badge);
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }
}

