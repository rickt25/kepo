package com.example.kepo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kepo.R;
import com.example.kepo.controller.ApiClient;
import com.example.kepo.model.api.SearchTodoRequest;
import com.example.kepo.model.api.SearchTodoResponse;
import com.example.kepo.model.object.SearchTodo;
import com.example.kepo.model.recyclerview.SearchTodoAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTodoActivity extends AppCompatActivity implements SearchTodoAdapter.OnUserTodoClickListener {
    private ImageView btnBack, btnSearch;
    private TextView etSearch, searchResult;
    private CheckBox cbUser, cbTodo;
    List<SearchTodo> todoList;
    RecyclerView recyclerView;
    SearchTodoAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search_todo);

        btnBack = findViewById(R.id.btn_back);
        btnSearch = findViewById(R.id.btn_search);
        etSearch = findViewById(R.id.et_search);
        searchResult = findViewById(R.id.tv_search);
        cbUser = findViewById(R.id.cb_user);
        cbTodo = findViewById(R.id.cb_todo);
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new SearchTodoAdapter();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchData();
            }
        });
    }

    public void searchData(){
        String searchKey = etSearch.getText().toString();
        int filterUser = 0, filterTodo = 0;

        if(cbUser.isChecked()){
            filterUser = 1;
        }else if(cbTodo.isChecked()){
            filterTodo = 1;
        }

        if(!cbUser.isChecked() && !cbTodo.isChecked()){
            Toast.makeText(this, "check at least one choice", Toast.LENGTH_SHORT).show();
            return;
        }

        SearchTodoRequest searchTodoRequest = new SearchTodoRequest();
        searchTodoRequest.setSearchQuery(searchKey);
        searchTodoRequest.setFilterUser(filterUser);
        searchTodoRequest.setFilterTodo(filterTodo);

        Call<SearchTodoResponse> users = ApiClient.searchTodoService().searchUser(searchTodoRequest);
        searchResult.setText("Result for : '" + searchKey + "'");

        users.enqueue(new Callback<SearchTodoResponse>() {
            @Override
            public void onResponse(Call<SearchTodoResponse> call, Response<SearchTodoResponse> response) {
                SearchTodoResponse searchTodoResponse = response.body();
                if(searchTodoResponse.getData() != null){
                    todoList = searchTodoResponse.getData();
                    userAdapter.setTodo(todoList, SearchTodoActivity.this::onTodoClick);
                    recyclerView.setAdapter(userAdapter);
                }else{
                    Toast.makeText(SearchTodoActivity.this,  searchTodoResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchTodoResponse> call, Throwable t) {
                Toast.makeText(SearchTodoActivity.this, "failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onTodoClick(int position) {
        SearchTodo todo = todoList.get(position);
        Intent intent = new Intent(this, TodoDetailActivity.class);
        intent.putExtra("userId", todo.getUser_id());
        intent.putExtra("todoId", todo.getTodo_id());
        startActivity(intent);
    }
}