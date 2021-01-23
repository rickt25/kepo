package com.example.kepo.model.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kepo.R;
import com.example.kepo.model.object.ListTodo;
import com.example.kepo.model.object.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterVH> {
    private List<User> users;
    private Context context;
    private UserAdapter.OnUserClickListener onUserClickListener;

    public void setUsers(List<User> users, OnUserClickListener onUserClickListener){
        this.users = users;
        this.onUserClickListener = onUserClickListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapter.UserAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new UserAdapter.UserAdapterVH(LayoutInflater.from(context).inflate(R.layout.users,parent,false), onUserClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserAdapterVH holder, int position) {
        User userList = users.get(position);
        String username = userList.getUsername();
        String name = userList.getName();

        holder.username.setText(username);
        holder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserAdapterVH extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView username, name;
        OnUserClickListener onUserClickListener;

        public UserAdapterVH(@NonNull View itemView, OnUserClickListener onUserClickListener) {
            super(itemView);

            username = itemView.findViewById(R.id.tv_username);
            name = itemView.findViewById(R.id.tv_name);

            this.onUserClickListener = onUserClickListener;
            itemView.setOnClickListener(this);
        }

        public void onClick(View v) {
            onUserClickListener.onUserClick(getAdapterPosition());
        }
    }

    public interface OnUserClickListener{
        void onUserClick(int position);
    }
}
