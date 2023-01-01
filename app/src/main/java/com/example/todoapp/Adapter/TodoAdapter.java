package com.example.todoapp.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.AddTodo;
import com.example.todoapp.MainActivity;
import com.example.todoapp.Model.TodoModel;
import com.example.todoapp.R;
import com.example.todoapp.Utilities.DatabaseHandler;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

private List<TodoModel> todoList;
private final MainActivity activity;
private DatabaseHandler db;

public TodoAdapter(DatabaseHandler db, MainActivity activity) {
    this.db = db;
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
    db.openDatabase();
    TodoModel item = todoList.get(position);
    holder.task.setText(item.getTodo());
    holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    db.updateStatus(item.getId(), 1);
                } else {
                    db.updateStatus(item.getId(), 0);
                }
            }
        });
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

    public void deleteItem(int position) {
        TodoModel item = todoList.get(position);
        db.deleteTodo(item.getId());
        todoList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position) {
        TodoModel item = todoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTodo());
        AddTodo fragment = new AddTodo();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddTodo.TAG);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }
}

