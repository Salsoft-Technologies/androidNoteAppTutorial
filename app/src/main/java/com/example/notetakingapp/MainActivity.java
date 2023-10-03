package com.example.notetakingapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView notesRecyclerView;
    private EditText noteEditText;
    private Button addButton;

    private List<String> notes = new ArrayList<>();
    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesRecyclerView = findViewById(R.id.notesRecyclerView);
        noteEditText = findViewById(R.id.noteEditText);
        addButton = findViewById(R.id.addButton);

        // Initialize RecyclerView and its adapter
        notesAdapter = new NotesAdapter(notes);
        notesRecyclerView.setAdapter(notesAdapter);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteText = noteEditText.getText().toString();
                if (!noteText.isEmpty()) {
                    notes.add(noteText);
                    notesAdapter.notifyDataSetChanged();
                    noteEditText.getText().clear();
                }
            }
        });

        EditText searchField = findViewById(R.id.searchField);
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Filter the notes based on the search text
                notesAdapter.getFilter().filter(charSequence);
            }


            @Override
            public void afterTextChanged(Editable editable) {
                // Do nothing
            }
        });
    }
}