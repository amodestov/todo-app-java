package com.aleksandrmodestov.todo_app_java;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import models.Note;

public class MainViewModel extends AndroidViewModel  {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<List<Note>> notes = new MutableLiveData<>();

    private NotesDao notesDao;
    public MainViewModel(@NonNull Application application) {
        super(application);
        notesDao = NotesDatabase.getInstance(application).notesDao();
    }

    public LiveData<List<Note>> getNotes() {
        return notesDao.getNotes();
    }

    public void removeNote(Note note) {
        Disposable disposable =
                notesDao.remove(note.getId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action() {
                            @Override
                            public void run() throws Throwable {
                                Log.d("MainViewModel", "removed note " + note.getId());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.d("MainViewModel", "An error occurred while deleting the note");
                            }
                        });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
