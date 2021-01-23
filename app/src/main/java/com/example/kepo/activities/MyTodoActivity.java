package com.example.kepo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kepo.R;
import com.example.kepo.controller.ApiClient;
import com.example.kepo.model.recyclerview.TodoAdapter;
import com.example.kepo.model.object.ListTodo;
import com.example.kepo.model.api.TodoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.kepo.activities.LoginActivity.getCredential;

public class MyTodoActivity extends AppCompatActivity implements TodoAdapter.OnTodoClickListener {
    private ImageView btnBack;
    private Button btnCreate;
    RecyclerView recyclerView;
    TodoAdapter todoAdapter;
    List<ListTodo> listTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_my_todo);

        btnBack = findViewById(R.id.btn_back);
        btnCreate = findViewById(R.id.btn_create);
        recyclerView  = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        todoAdapter = new TodoAdapter();
        getMyTodos();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyTodoActivity.this, CreateTodoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void getMyTodos(){
        String userId = getCredential("user_id",MyTodoActivity.this);

        Call<TodoResponse> todoList = ApiClient.getTodoService().getMyTodos(userId);

        todoList.enqueue(new Callback<TodoResponse>() {
            @Override
            public void onResponse(Call<TodoResponse> call, Response<TodoResponse> response) {
                TodoResponse todoResponse = response.body();
                if(todoResponse.getData() != null){
                    listTodo = response.body().getData().getListTodo();
                    todoAdapter.setTodo(listTodo, MyTodoActivity.this::onTodoClick);
                    recyclerView.setAdapter(todoAdapter);
                }else{
                    Toast.makeText(MyTodoActivity.this, todoResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TodoResponse> call, Throwable t) {
                Toast.makeText(MyTodoActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onTodoClick(int position) {
        ListTodo todo = listTodo.get(position);
        Intent intent = new Intent(this, TodoDetailActivity.class);
        intent.putExtra("todoId", todo.getTodo_id());
        intent.putExtra("userId", getCredential("user_id",MyTodoActivity.this));
        startActivity(intent);
    }

}