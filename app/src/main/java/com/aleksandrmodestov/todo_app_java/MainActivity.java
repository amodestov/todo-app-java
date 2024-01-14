package com.aleksandrmodestov.todo_app_java;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import models.Note;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton buttonAddNote;
    private RecyclerView recyclerViewNotes;
    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AddNoteActivity.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
        notesAdapter = new NotesAdapter();
        recyclerViewNotes.setAdapter(notesAdapter);
        notesAdapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
                DataBase.getInstance().remove(note.getId());
                showNotes();
            }
        });
    }

    private void initViews() {
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        buttonAddNote = findViewById(R.id.buttonAddNote);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showNotes();
    }

    private void showNotes() {
        notesAdapter.setNotes(DataBase.getInstance().getList());
    }
}