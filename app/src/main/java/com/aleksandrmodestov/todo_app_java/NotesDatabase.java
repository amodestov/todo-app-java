package com.aleksandrmodestov.todo_app_java;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import models.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    public static final String DB_Name = "notes.db";

    private static NotesDatabase instance = null;

    public static NotesDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            application,
                            NotesDatabase.class,
                            DB_Name)
                    .build();
        }
        return instance;
    }

    public abstract NotesDao notesDao();
}
