package com.example.sqlliteproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private Button ADD;
   private ListView listView;
   private TextView count;
   Context context;

   private DBhandler dBhandler;
   // create list view Object for get data from db handler class
   private List<Todo> toDos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ADD = findViewById(R.id.add);
        listView = findViewById(R.id.todoList);
        count = findViewById(R.id.todoCount);
        context = this;
        dBhandler = new DBhandler(context);
        toDos = new ArrayList<>();

        toDos = dBhandler.getAllTodos();


        TodoAdapter adapter = new TodoAdapter(context,R.layout.single_todo,toDos);
        listView.setAdapter(adapter);
        //get todo counts from the table
        int countTodo = dBhandler.countTodo();
        count.setText(" "+"You have to Complete"+" "+countTodo+" "+"Tasks");

        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddTodo.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                final Todo todo = toDos.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(todo.getTitle());
                builder.setMessage(todo.getDesc());


                builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        todo.setFinished(System.currentTimeMillis());
                        dBhandler.updatesingletodo(todo);
                        startActivity(new Intent(context,MainActivity.class));
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dBhandler.deleteTask(todo.getId());
                        startActivity(new Intent(context,MainActivity.class));
                        Toast.makeText(getApplicationContext(),"SuccessFully deleted your "+todo.getTitle(),Toast.LENGTH_LONG).show();

                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(getApplicationContext(),EditToDo.class);
                        intent.putExtra("id",String.valueOf(todo.getId()));
                        startActivity(intent);

                    }
                });

                builder.show();
            }
        });
    }
}