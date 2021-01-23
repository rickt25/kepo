package com.example.kepo.model.api;

import com.example.kepo.model.object.SearchTodo;

import java.util.List;

public class SearchTodoResponse {
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SearchTodo> getData() {
        return data;
    }

    public void setData(List<SearchTodo> data) {
        this.data = data;
    }

    private String message;
    private List<SearchTodo> data;
}
