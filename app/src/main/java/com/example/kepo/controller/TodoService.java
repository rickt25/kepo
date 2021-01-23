package com.example.kepo.controller;

import com.example.kepo.model.api.TodoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TodoService {

    @GET("/user/{user_id}/todo/")
    Call<TodoResponse> getMyTodos(@Path("user_id") String userId);
}
