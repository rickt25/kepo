package com.example.kepo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kepo.R;
import com.example.kepo.controller.ApiClient;
import com.example.kepo.model.api.SearchUserRequest;
import com.example.kepo.model.api.SearchUserResponse;
import com.example.kepo.model.object.User;
import com.example.kepo.model.recyclerview.UserAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.kepo.activities.LoginActivity.getCredential;

public class SearchUserActivity extends AppCompatActivity implements UserAdapter.OnUserClickListener {
    private ImageView btnBack, btnSearch;
    private TextView searchResult;
    private EditText etSearch;
    List<User> userList;
    RecyclerView recyclerView;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search_user);

        etSearch = findViewById(R.id.et_search);
        searchResult = findViewById(R.id.tv_search);
        btnBack = findViewById(R.id.btn_back);
        btnSearch = findViewById(R.id.btn_search);
        btnBack = findViewById(R.id.btn_back);
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userAdapter = new UserAdapter();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUsers();
            }
        });
    }

    public void getUsers(){
        String userId = getCredential("user_id",SearchUserActivity.this);
        String searchKey = etSearch.getText().toString();

        SearchUserRequest searchUserRequest = new SearchUserRequest();
        searchUserRequest.setUser_id(userId);
        searchUserRequest.setSearchQuery(searchKey);

        Call<SearchUserResponse> users = ApiClient.searchUserService().searchUser(searchUserRequest);
        searchResult.setText("Result for '"+ searchKey+"'");

        users.enqueue(new Callback<SearchUserResponse>() {
            @Override
            public void onResponse(Call<SearchUserResponse> call, Response<SearchUserResponse> response) {
                SearchUserResponse searchUserResponse = response.body();
                if(searchUserResponse.getData() != null){
                    userList = searchUserResponse.getData();
                    userAdapter.setUsers(userList, SearchUserActivity.this::onUserClick);
                    recyclerView.setAdapter(userAdapter);
                }else{
                    Toast.makeText(SearchUserActivity.this, searchUserResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchUserResponse> call, Throwable t) {
                Toast.makeText(SearchUserActivity.this, "failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onUserClick(int position) {
        User user = userList.get(position);
        Intent intent = new Intent(this, UserDetailActivity.class);
        intent.putExtra("userId", user.getUser_id());
        startActivity(intent);
    }
}