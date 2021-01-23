package com.example.kepo.controller;

import com.example.kepo.model.api.AddTodoRequest;
import com.example.kepo.model.object.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AddTodoService {

    @POST("/user/{user_id}/todo")
    Call<Response> addTodo(@Path("user_id") String userId, @Body AddTodoRequest addTodoRequest);
}
