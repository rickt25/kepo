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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoAdapterVH> {

    private List<ListTodo> todoList;
    private Context context;
    private OnTodoClickListener onTodoClickListener;

    public TodoAdapter() {
    }

    public void setTodo(List<ListTodo> todoList, OnTodoClickListener onTodoClickListener){
        this.todoList = todoList;
        this.onTodoClickListener = onTodoClickListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoAdapter.TodoAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new TodoAdapter.TodoAdapterVH(LayoutInflater.from(context).inflate(R.layout.todos,parent,false), onTodoClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.TodoAdapterVH holder, int position) {
        ListTodo listTodo = todoList.get(position);
        String title = listTodo.getTitle();
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
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class TodoAdapterVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, time;
        OnTodoClickListener onTodoClickListener;

        public TodoAdapterVH(@NonNull View itemView, OnTodoClickListener onTodoClickListener) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            time = itemView.findViewById(R.id.tv_time);

            this.onTodoClickListener = onTodoClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onTodoClickListener.onTodoClick(getAdapterPosition());
        }
    }

    public interface OnTodoClickListener{
        void onTodoClick(int position);
    }
}
