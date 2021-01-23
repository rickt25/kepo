package com.example.kepo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kepo.R;
import com.example.kepo.controller.ApiClient;
import com.example.kepo.model.api.AddTodoRequest;
import com.example.kepo.model.object.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class CreateTodoActivity extends AppCompatActivity {
    private EditText etTitle, etDescription;
    private Button  btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_todo);

        etTitle = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    addTodo();
                }else{
                    Toast.makeText(CreateTodoActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean validate(){
        if(etTitle.getText().toString().matches("") || etDescription.getText().toString().matches(""))
            return false;
        return true;
    }

    public void addTodo(){
        AddTodoRequest addTodoRequest = new AddTodoRequest();
        addTodoRequest.setTitle(etTitle.getText().toString());
        addTodoRequest.setDescription(etDescription.getText().toString());

        String userId = LoginActivity.getCredential("user_id",CreateTodoActivity.this);
        Call<Response> responseCall = ApiClient.createTodoService().addTodo(userId,addTodoRequest);
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response res = response.body();
                Toast.makeText(CreateTodoActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                if(res.getStatus() == 200){
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("flag", true);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(CreateTodoActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}