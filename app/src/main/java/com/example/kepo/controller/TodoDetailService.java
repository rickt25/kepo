package com.example.kepo.controller;

import com.example.kepo.model.api.TodoDetailResponse;
import com.example.kepo.model.api.TodoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TodoDetailService {

    @GET("user/{user_id}/todo/{todo_id}")
    Call<TodoDetailResponse> getDetailTodo(@Path("user_id") String userId, @Path("todo_id") String todoId);
}
