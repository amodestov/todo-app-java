package com.aleksandrmodestov.todo_app_java;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import models.Note;

public class MainViewModel extends AndroidViewModel  {

    private NotesDao notesDao;
    public MainViewModel(@NonNull Application application) {
        super(application);
        notesDao = NotesDatabase.getInstance(application).notesDao();
    }

    public LiveData<List<Note>> getNotes() {
        return notesDao.getNotes();
    }

    public void remove(Note note) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                notesDao.remove(note.getId());
            }
        });
        thread.start();
    }
}
