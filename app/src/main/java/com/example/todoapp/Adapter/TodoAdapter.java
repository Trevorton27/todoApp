package com.example.todoapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.MainActivity;
import com.example.todoapp.Model.TodoModel;
import com.example.todoapp.R;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
private List<TodoModel> todoList;
private final MainActivity activity;

public TodoAdapter(MainActivity activity) {
    this.activity = activity;
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.todo_layout, parent, false);
    return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
    TodoModel item = todoList.get(position);
    holder.task.setText(item.getTask());
    holder.task.setChecked(toBoolean(item.getStatus()));
    }

    public int getItemCount() {
        return todoList.size();
    }

    private boolean toBoolean(int number) {
    return number != 0;
    }

    public void setTasks(List<TodoModel> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }
}

