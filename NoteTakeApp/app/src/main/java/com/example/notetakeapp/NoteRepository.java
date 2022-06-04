package com.example.notetakeapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class NoteRepository {

    NoteDao noteDao;
    LiveData<List<Note>> notes;


    public NoteRepository(Application application) {
      NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
      noteDao = noteDatabase.noteDao();
      notes = noteDao.getAllNotes();
    }

    public void insert (Note note){
        InsertNoteAsync insertNoteAsync = new InsertNoteAsync(noteDao);
        insertNoteAsync.execute(note);

    }

    public void delete (Note note){

        DeleteNoteAsync deleteNoteAsync = new DeleteNoteAsync(noteDao);
        deleteNoteAsync.execute(note);

    }

    public void update (Note note){

        UpdateNoteAsync updateNoteAsync = new UpdateNoteAsync(noteDao);
        updateNoteAsync.execute(note);

    }

    public LiveData<List<Note>> getAllNotes(){
    return notes;
    }

    private static class InsertNoteAsync extends AsyncTask <Note, Void, Void>{

        private NoteDao noteDao;
        private InsertNoteAsync(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsync extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private DeleteNoteAsync(NoteDao noteDao){
            this.noteDao = noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsync extends AsyncTask <Note, Void, Void>{

        private NoteDao noteDao;

        private UpdateNoteAsync(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }


}
