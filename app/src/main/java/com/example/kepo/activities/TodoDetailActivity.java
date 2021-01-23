package com.example.kepo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kepo.R;
import com.example.kepo.controller.ApiClient;
import com.example.kepo.model.api.TodoDetailResponse;
import com.example.kepo.model.object.TodoDetail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoDetailActivity extends AppCompatActivity {
    private TextView tvTitle, tvDescription, tvTime;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_todo_detail);

        tvTitle = findViewById(R.id.tv_title);
        tvDescription = findViewById(R.id.tv_description);
        tvTime = findViewById(R.id.tv_time);
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle extras = getIntent().getExtras();
        String todoId = extras.getString("todoId");
        String userId = extras.getString("userId");

        Call<TodoDetailResponse> todoDetail = ApiClient.getTodoDetailService().getDetailTodo(userId,todoId);

        todoDetail.enqueue(new Callback<TodoDetailResponse>() {
            @Override
            public void onResponse(Call<TodoDetailResponse> call, Response<TodoDetailResponse> response) {
                TodoDetail todo = response.body().getData();
                if(todo != null){
                    String title = todo.getTitle();
                    String description = todo.getDescription();
                    String time = todo.getLast_edited();
                    String date = format(time);

                    tvTitle.setText(title);
                    tvDescription.setText(description);
                    tvTime.setText(date);
                }
            }

            @Override
            public void onFailure(Call<TodoDetailResponse> call, Throwable t) {
                Toast.makeText(TodoDetailActivity.this, "failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String format(String time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date newDate = null;
        try {
            newDate = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        format = new SimpleDateFormat("dd MMM yyyy hh:mm");
        return format.format(newDate);
    }
}