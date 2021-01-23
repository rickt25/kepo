package com.example.kepo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kepo.R;
import com.example.kepo.model.object.User;

public class MenuActivity extends AppCompatActivity {
    private User data;
    private TextView tvName;
    private CardView myTodo, searchTodo, searchUser, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        data = (User) intent.getSerializableExtra("data");

        tvName = findViewById(R.id.tv_name);
        myTodo = findViewById(R.id.my_todo);
        searchTodo = findViewById(R.id.search_todo);
        searchUser = findViewById(R.id.search_user);
        profile = findViewById(R.id._profile);

        tvName.setText("Welcome, " + data.getName());

        myTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MyTodoActivity.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        searchTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SearchTodoActivity.class);
                startActivity(intent);
            }
        });

        searchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SearchUserActivity.class);
                startActivity(intent);
            }
        });
    }
}