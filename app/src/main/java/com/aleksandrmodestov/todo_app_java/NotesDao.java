package com.aleksandrmodestov.todo_app_java;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import models.Note;

@Dao
public interface NotesDao {

    @Query("SELECT * from notes")
    LiveData<List<Note>> getNotes();

    @Insert
    Completable add(Note note);

    @Query("DELETE FROM notes WHERE id = :id")
    Completable remove(int id);
}
