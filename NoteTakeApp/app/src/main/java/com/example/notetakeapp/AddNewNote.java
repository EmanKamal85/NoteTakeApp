package com.example.notetakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewNote extends AppCompatActivity {

    EditText editTextTitle;
    EditText editTextDescription;
    Button buttonCancel;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add New Note");
        setContentView(R.layout.activity_add_new_note);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonSave =findViewById(R.id.buttonSave);


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddNewNote.this, "Nothing Saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String description = editTextDescription.getText().toString();
                Intent i = new Intent();
                i.putExtra("title", title);
                i.putExtra("description", description);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}