package com.example.sqlliteproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.zip.Inflater;

public class TodoAdapter extends ArrayAdapter<Todo> {
    private Context context;
    private int resource;
    List<Todo> todos;

    TodoAdapter(Context context, int resource , List<Todo> todos){
        super(context, resource, todos);

        this.context = context;
        this.resource = resource;
        this.todos = todos;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView title = row.findViewById(R.id.title1);
        TextView description = row.findViewById(R.id.description1);
        ImageView imageView = row.findViewById(R.id.imageView1);


        Todo toDo = todos.get(position);
        title.setText(toDo.getTitle());
        description.setText(toDo.getDesc());
        imageView.setVisibility(row.INVISIBLE);

        if(toDo.getFinished() > 0){
            imageView.setVisibility(View.VISIBLE);
        }

        return row;
    }
}
