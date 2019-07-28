package com.example.pluralnotepad;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spinner_courses;
    EditText text_note_text;
    EditText text_note_title;
    public static final String NOTE_INFO="com.example.pluralnotepad.NOTE_INFO";
    private NoteInfo mNote;
    private boolean isNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinner_courses=findViewById(R.id.spinner_courses);
        text_note_text=findViewById(R.id.text_note_text);
        text_note_title=findViewById(R.id.text_note_title);

        List<CourseInfo> courses=DataManager.getInstance().getCourses();
        ArrayAdapter<CourseInfo> adapterCourses=
                new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,courses);
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_courses.setAdapter(adapterCourses);


        readDisplayStateValues();
        if (!isNewNote)
            displayNotes(spinner_courses, text_note_title,text_note_text);
            isNewNote = mNote==null;


    }

    private void displayNotes(Spinner spinner_courses, EditText text_note_title, EditText text_note_text) {
        List<CourseInfo> courses=DataManager.getInstance().getCourses();
        int courseIndex=courses.indexOf(mNote.getCourse());
        spinner_courses.setSelection(courseIndex);
        text_note_title.setText(mNote.getTitle());
        text_note_text.setText(mNote.getText());
    }

    private void readDisplayStateValues() {
        Intent intent=getIntent();
        mNote = intent.getParcelableExtra(NOTE_INFO);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
