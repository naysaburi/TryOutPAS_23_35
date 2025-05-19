package com.example.tryoutpas_23_35;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("search_all_teams.php?l=English%20Premier%20League")
    Call<TeamResponse> getTeams();
}

