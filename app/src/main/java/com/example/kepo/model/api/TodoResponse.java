package com.example.kepo.model.api;

import com.example.kepo.model.object.Todo;

public class TodoResponse {

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

    public Todo getData() {
        return data;
    }

    public void setData(Todo data) {
        this.data = data;
    }

    private int status;
    private String message;
    private Todo data;
}
