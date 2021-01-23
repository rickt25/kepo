package com.example.kepo.model.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kepo.R;
import com.example.kepo.model.api.TodoResponse;
import com.example.kepo.model.object.ListTodo;
import com.example.kepo.model.object.SearchTodo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserTodoAdapter extends RecyclerView.Adapter<UserTodoAdapter.UserTodoAdapterVH> {
    private TodoResponse todoResponse;
    private List<ListTodo> todoList;
    private Context context;
    private UserTodoAdapter.OnUserTodoClickListener onUserTodoClickListener;

    public void setTodo(TodoResponse todoResponse , OnUserTodoClickListener onUserTodoClickListener){
        this.todoResponse = todoResponse;
        this.todoList = todoResponse.getData().getListTodo();
        this.onUserTodoClickListener = onUserTodoClickListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserTodoAdapter.UserTodoAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new UserTodoAdapter.UserTodoAdapterVH(LayoutInflater.from(context).inflate(R.layout.usertodos,parent,false), onUserTodoClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserTodoAdapter.UserTodoAdapterVH holder, int position) {
        ListTodo listTodo = todoList.get(position);
        String title = listTodo.getTitle();
        String username = todoResponse.getData().getUsername();
        String time = listTodo.getLast_edited();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date newDate = null;
        try {
            newDate = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        format = new SimpleDateFormat("dd MMM yyyy hh:mm");
        String date = format.format(newDate);

        holder.title.setText(title);
        holder.time.setText(date);
        holder.username.setText(username);
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class UserTodoAdapterVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, time, username;
        UserTodoAdapter.OnUserTodoClickListener onUserTodoClickListener;

        public UserTodoAdapterVH(@NonNull View itemView, OnUserTodoClickListener onUserTodoClickListener) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_title);
            time = itemView.findViewById(R.id.tv_time);
            username = itemView.findViewById(R.id.tv_username);

            this.onUserTodoClickListener = onUserTodoClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onUserTodoClickListener.onTodoClick(getAdapterPosition());
        }
    }

    public interface OnUserTodoClickListener{
        void onTodoClick(int position);
    }
}
