package com.example.kepo.model.api;

import com.example.kepo.model.object.User;

import java.util.List;

public class SearchUserResponse {
    private String userId;
    private String message;
    private List<User> data;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
