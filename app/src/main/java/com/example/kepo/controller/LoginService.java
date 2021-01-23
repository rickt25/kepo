package com.example.kepo.controller;

import com.example.kepo.model.api.LoginRequest;
import com.example.kepo.model.api.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
}
