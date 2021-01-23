package com.example.kepo.controller;

import com.example.kepo.model.api.SearchUserRequest;
import com.example.kepo.model.api.SearchUserResponse;
import com.example.kepo.model.api.TodoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SearchUserService {

    @POST("/searchUser")
    Call<SearchUserResponse> searchUser(@Body SearchUserRequest searchUserRequest);
}
