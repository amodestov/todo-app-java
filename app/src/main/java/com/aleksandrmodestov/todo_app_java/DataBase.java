package com.aleksandrmodestov.todo_app_java;

import java.util.ArrayList;
import java.util.List;

import models.Note;

public class DataBase {

    private List<Note> notes = new ArrayList<>();
    private static DataBase dataBase;

    public static DataBase getInstance() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

    public void add(Note note) {
        notes.add(note);
    }

    public void remove(int id) {
        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            if (note.getId() == id) {
                notes.remove(note);
            }
        }
    }

    public ArrayList<Note> getList() {
        return new ArrayList<>(notes);
    }
}
