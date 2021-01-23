package com.example.kepo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kepo.R;
import com.example.kepo.controller.ApiClient;
import com.example.kepo.model.api.TodoResponse;
import com.example.kepo.model.recyclerview.TodoAdapter;
import com.example.kepo.model.recyclerview.UserTodoAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailActivity extends AppCompatActivity implements TodoAdapter.OnTodoClickListener {
    private ImageView btnBack;
    private TextView tvUsername, tvName, tvCount;
    RecyclerView recyclerView;
    UserTodoAdapter todoAdapter;
    TodoResponse todoResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_detail);

        Bundle extras = getIntent().getExtras();
        String userId = extras.getString("userId");

        tvUsername = findViewById(R.id.tv_username);
        tvName = findViewById(R.id.tv_name);
        tvCount = findViewById(R.id.tv_count);

        btnBack = findViewById(R.id.btn_back);
        recyclerView  = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        todoAdapter = new UserTodoAdapter();
        getMyTodos();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getMyTodos(){
        Bundle extras = getIntent().getExtras();
        String userId = extras.getString("userId");

        Call<TodoResponse> todoList = ApiClient.getTodoService().getMyTodos(userId);

        todoList.enqueue(new Callback<TodoResponse>() {
            @Override
            public void onResponse(Call<TodoResponse> call, Response<TodoResponse> response) {
                todoResponse = response.body();
                if(todoResponse.getData() != null){
                    tvUsername.setText(todoResponse.getData().getUsername());
                    tvName.setText(todoResponse.getData().getName());
                    tvCount.setText("Todos: "+ todoResponse.getData().getListTodo().size());

                    todoAdapter.setTodo(todoResponse ,UserDetailActivity.this::onTodoClick);
                    recyclerView.setAdapter(todoAdapter);
                }else{
                    Toast.makeText(UserDetailActivity.this, todoResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TodoResponse> call, Throwable t) {
                Toast.makeText(UserDetailActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onTodoClick(int position) {
        Intent intent = new Intent(this, TodoDetailActivity.class);
        intent.putExtra("todoId", todoResponse.getData().getListTodo().get(position).getTodo_id());
        intent.putExtra("userId", todoResponse.getData().getUserId());
        startActivity(intent);
    }
}