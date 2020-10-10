package com.example.sqlliteproject;

public class Todo {

    private int id;
    private String title, desc;
    private long started, finished;

    public Todo() {
    }

    public Todo(int id, String title, String desc, long started, long finished) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.started = started;
        this.finished = finished;
    }

    public Todo(String title, String desc, long started, long finished) {
        this.title = title;
        this.desc = desc;
        this.started = started;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getFinished() {
        return finished;
    }

    public void setFinished(long finished) {
        this.finished = finished;
    }
}