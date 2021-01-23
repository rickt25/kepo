package com.example.kepo.controller;

import com.example.kepo.model.api.SearchTodoRequest;
import com.example.kepo.model.api.SearchTodoResponse;
import com.example.kepo.model.api.SearchUserRequest;
import com.example.kepo.model.api.SearchUserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SearchTodoService {

    @POST("/searchTodos")
    Call<SearchTodoResponse> searchUser(@Body SearchTodoRequest searchTodoRequest);
}
