package com.example.tryoutpas_23_35;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView rvTeams;
    private TeamAdapter teamAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvTeams = view.findViewById(R.id.rv_teams);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);

        rvTeams.setLayoutManager(new LinearLayoutManager(getContext()));

        // Setup listener refresh
        swipeRefreshLayout.setOnRefreshListener(() -> {
            fetchTeams();
        });

        // Load data pertama kali
        swipeRefreshLayout.setRefreshing(true); // munculkan spinner saat pertama kali
        fetchTeams();

        return view;
    }

    private void fetchTeams() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<TeamResponse> call = apiService.getTeams();

        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                swipeRefreshLayout.setRefreshing(false); // stop spinner
                if (response.isSuccessful() && response.body() != null) {
                    List<Team> teams = response.body().getTeams();
                    teamAdapter = new TeamAdapter(teams);
                    rvTeams.setAdapter(teamAdapter);
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Log.e("API_ERROR", "Gagal ambil data: " + t.getMessage());
            }
        });
    }
}

