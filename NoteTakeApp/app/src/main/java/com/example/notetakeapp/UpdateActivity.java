package com.example.notetakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText editTextTitleUpdate;
    EditText editTextDescriptionUpdate;
    Button buttonCancelUpdate;
    Button buttonSaveUpdate;
    int noteId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Update Note");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_update);

        editTextTitleUpdate = findViewById(R.id.editTextTitleUpdate);
        editTextDescriptionUpdate = findViewById(R.id.editTextDescriptionUpdate);
        buttonCancelUpdate = findViewById(R.id.buttonCancelUpdate);
        buttonSaveUpdate =findViewById(R.id.buttonSaveUpdate);

        getData();

        buttonCancelUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UpdateActivity.this, "Nothing Updated", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        buttonSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String title = editTextTitleUpdate.getText().toString();
//                String description = editTextDescriptionUpdate.getText().toString();
//                Intent i = new Intent();
//                i.putExtra("title", title);
//                i.putExtra("description", description);
//                setResult(RESULT_OK, i);
//                finish();
                String titleUpdate = editTextTitleUpdate.getText().toString();
                String descriptionUpdate = editTextDescriptionUpdate.getText().toString();
                Intent i = new Intent();
                i.putExtra("titleUpdate", titleUpdate);
                i.putExtra("descriptionUpdate", descriptionUpdate);
                if (noteId!=-1){
                    i.putExtra("noteId", noteId);
                }
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }

    public void getData(){
        Intent i = getIntent();
        noteId = i.getIntExtra("id", -1);
        String titleUpdate = i.getStringExtra("title");
        String descriptionUpdate = i.getStringExtra("description");

        editTextTitleUpdate.setText(titleUpdate);
        editTextDescriptionUpdate.setText(descriptionUpdate);
    }
}