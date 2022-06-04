package com.example.notetakeapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Note.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new PopulateNotesAsync(instance).execute();
            super.onCreate(db);
        }
    };

    private static class PopulateNotesAsync extends AsyncTask <Void, Void, Void>{

        NoteDao noteDao;
        private  PopulateNotesAsync(NoteDatabase noteDatabase){
            noteDao = noteDatabase.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title1", "Description1"));
            noteDao.insert(new Note("Title2", "Description2"));
            noteDao.insert(new Note("Title3", "Description3"));
            noteDao.insert(new Note("Title4", "Description4"));
            noteDao.insert(new Note("Title5", "Description5"));


            return null;
        }
    }
}
