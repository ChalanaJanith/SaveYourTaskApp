package com.example.sqlliteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBhandler extends SQLiteOpenHelper {

    //Database Version,Database name,table name
    private static final int VERSION = 1;
    private static final String DB_NAME = "Tasks";
    private static final String TABLE_NAME = "Notes";

    //Column names
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";



    public DBhandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE_QUERY = "create table "+TABLE_NAME+
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +TITLE+" "+" TEXT,"
                +DESCRIPTION+" "+" TEXT,"
                +STARTED+" "+" TEXT,"
                +FINISHED+" "+" TEXT" +
                ");";

   /*    CREATE TABLE todo(id INTEGER primary key Autoincrement, title TEXT, description TEXT,
                started TEXT, finished TEXT); */

            //Run the Database
            db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE = " drop table if exists " + TABLE_NAME;
        //Drop older table if existed
        db.execSQL(DROP_TABLE);
        //create table again
        onCreate(db);
    }


    public void addToDo(Todo todo){
       //step 01
        SQLiteDatabase sqlDB = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,todo.getTitle());
        contentValues.put(DESCRIPTION,todo.getDesc());
        contentValues.put(STARTED,todo.getStarted());
        contentValues.put(FINISHED,todo.getFinished());

        //save to table
        sqlDB.insert(TABLE_NAME,null,contentValues);

        //close database
        sqlDB.close();

    }

    //Count task table records
    public int countTodo(){

        SQLiteDatabase db =getReadableDatabase();
        String query = " SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    //Get All todos into a list
    public List<Todo> getAllTodos(){

        List<Todo> todos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){

            do{

                //Create new Todo object
                Todo toDo = new Todo();

                toDo.setId(cursor.getInt(0));
                toDo.setTitle(cursor.getString(1));
                toDo.setDesc(cursor.getString(2));
                toDo.setStarted(cursor.getLong(3));
                toDo.setFinished(cursor.getLong(4));

                todos.add(toDo);
            }while(cursor.moveToNext());


        }
        return todos;
    }

    //Delete Item
    public void deleteTask(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,ID + " =?",new String[]{String.valueOf(id)});
        db.close();
    }

    public Todo getSingletodo(int ID1){

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor =  db.query(TABLE_NAME,new String[]{ID,TITLE,DESCRIPTION,STARTED,FINISHED},
                ID + "= ?",new String[]{String.valueOf(ID1)},null,null,null);

        Todo toDo;
        if(cursor != null){
            
            cursor.moveToFirst();
            toDo = new Todo(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(3),
                    cursor.getLong(4)


            );
            return toDo;

        }
        return null;
    }

    //update
    public int updatesingletodo(Todo todo){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,todo.getTitle());
        contentValues.put(DESCRIPTION,todo.getDesc());
        contentValues.put(STARTED,todo.getStarted());
        contentValues.put(FINISHED,todo.getFinished());

        int status = db.update(TABLE_NAME,contentValues,ID +" =?",new String[]{String.valueOf(todo.getId())});

        db.close();
        return status;
    }
}
