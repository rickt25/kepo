package com.example.kepo.controller;

import com.example.kepo.model.object.TodoDetail;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://it-division-kepo.herokuapp.com")
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static LoginService getUserService(){
        LoginService loginService = getRetrofit().create(LoginService.class);
        return loginService;
    }

    public static TodoService getTodoService(){
        TodoService todoService = getRetrofit().create(TodoService.class);
        return todoService;
    }

    public static AddTodoService createTodoService(){
        AddTodoService addTodoService = getRetrofit().create(AddTodoService.class);
        return addTodoService;
    }

    public static TodoDetailService getTodoDetailService(){
        TodoDetailService todoDetailService = getRetrofit().create(TodoDetailService.class);
        return todoDetailService;
    }

    public static SearchUserService searchUserService(){
        SearchUserService searchUserService = getRetrofit().create(SearchUserService.class);
        return searchUserService;
    }

    public static SearchTodoService searchTodoService(){
        SearchTodoService searchTodoService = getRetrofit().create(SearchTodoService.class);
        return searchTodoService;
    }
}
