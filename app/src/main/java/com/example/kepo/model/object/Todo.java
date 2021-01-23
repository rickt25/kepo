package com.example.kepo.model.object;

import com.example.kepo.model.object.ListTodo;

import java.util.List;

public class Todo {

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ListTodo> getListTodo() {
        return listTodo;
    }

    public void setListTodo(List<ListTodo> listTodo) {
        this.listTodo = listTodo;
    }

    private String userId;
    private String name;
    private String username;
    private List<ListTodo> listTodo;
}
