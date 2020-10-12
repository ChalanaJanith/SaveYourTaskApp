package com.example.sqlliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditToDo extends AppCompatActivity {

    private EditText title,desc;
    private Button update;
    private DBhandler dBhandler;
    private Context context;
    private Long updateDate;
    Todo todo1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do);

        context = this;
        dBhandler = new DBhandler(context);
        todo1 = new Todo();
        title = findViewById(R.id.editTitle);
        desc = findViewById(R.id.editDescription);
        update = findViewById(R.id.buttonEdit);

        final String id = getIntent().getStringExtra("id");
        todo1 = dBhandler.getSingletodo(Integer.parseInt(id));
        title.setText(todo1.getTitle());
        desc.setText(todo1.getDesc());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titletext = title.getText().toString();
                String desctext = desc.getText().toString();
                updateDate = System.currentTimeMillis();

                Todo objtodo = new Todo(Integer.parseInt(id),titletext,desctext,updateDate,0);
                int state = dBhandler.updatesingletodo(objtodo);
                startActivity(new Intent(context,MainActivity.class));
            }
        });

    }
}