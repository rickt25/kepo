package com.example.kepo.model.api;

import com.example.kepo.model.object.TodoDetail;

public class TodoDetailResponse {
    private int status;
    private String message;
    private TodoDetail data;

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

    public TodoDetail getData() {
        return data;
    }

    public void setData(TodoDetail data) {
        this.data = data;
    }
}
