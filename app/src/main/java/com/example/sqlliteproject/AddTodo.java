package com.example.sqlliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTodo extends AppCompatActivity {

    private EditText UTITLE , UDESC;
    private Button addButton;
    private DBhandler dBhandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        UTITLE = findViewById(R.id.AddTitle);
        UDESC = findViewById(R.id.AddDescription);
        addButton = findViewById(R.id.buttonAdd);

        context = this;
        dBhandler = new DBhandler(context);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userTitle = UTITLE.getText().toString();
                String userDescription = UDESC.getText().toString();
                long started = System.currentTimeMillis();

                Todo todo2 = new Todo(userTitle,userDescription,started,0);
                dBhandler.addToDo(todo2);

                startActivity(new Intent(context,MainActivity.class));
            }
        });
    }

}