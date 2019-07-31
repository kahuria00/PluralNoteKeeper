package com.example.pluralnotepad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int POSITION_NOT_SET = -1;
    Spinner spinner_courses;
    EditText text_note_text;
    EditText text_note_title;
    public static final String NOTE_POSITION="com.example.pluralnotepad.NOTE_POSITION";
    private NoteInfo mNote;
    private boolean isNewNote;
    private Spinner spinnerCourses;
    private EditText noteTitle;
    private EditText noteText;
    private int notePosition;
    private boolean isCancelling;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        readDisplayStateValues();
        noteText = text_note_text;
        noteText =findViewById(R.id.text_note_text);

        noteTitle = text_note_title;
        noteTitle =findViewById(R.id.text_note_title);

        spinnerCourses = spinner_courses;
        spinnerCourses =findViewById(R.id.spinner_courses);

        List<CourseInfo> courses=DataManager.getInstance().getCourses();
        ArrayAdapter<CourseInfo> adapterCourses=
                new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,courses);
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_courses.setAdapter(adapterCourses);



        if (!isNewNote)
            displayNotes(spinner_courses, text_note_title,text_note_text);
            isNewNote = mNote==null;

    }
    @Override
    protected void onPause() {
        super.onPause();
        if (isCancelling) {
            if (isNewNote) {
                DataManager.getInstance().removeNote(notePosition);
            }
        } else {
            saveNote();
        }
    }

    private void saveNote() {
        mNote.setCourse((CourseInfo) spinnerCourses.getSelectedItem());
        mNote.setTitle(noteTitle.getText().toString());
        mNote.setText(noteText.getText().toString());
    }

    private void displayNotes(Spinner spinner_courses, EditText text_note_title, EditText text_note_text) {
        List<CourseInfo> courses=DataManager.getInstance().getCourses();
        int courseIndex=courses.indexOf(mNote.getCourse());
        spinner_courses.setSelection(courseIndex);
        text_note_title.setText(mNote.getTitle());
        text_note_text.setText(mNote.getText());
    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
        int position = intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET);
        isNewNote = position == POSITION_NOT_SET;
        if (isNewNote) {
            createNewNote();
        } else {
            mNote = DataManager.getInstance().getNotes().get(position);
        }
    }

    private void createNewNote() {
        DataManager DM =DataManager.getInstance();
        notePosition = DM.createNewNote();
        DM.getNotes().get(notePosition);
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
        if (id == R.id.action_send_email) {
            sendEmail();
            return true;
        } else if (id == R.id.action_cancel) {
            isCancelling = true;
            finish();
        }
            return super.onOptionsItemSelected(item);
        }

    private void sendEmail() {
        CourseInfo course =(CourseInfo) spinnerCourses.getSelectedItem();
        String subject =noteTitle.getText().toString();
        String text="Check out what I learned in the pluralsight course\""
        + course.getTitle()+"\"\n" + noteText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc2822");
        intent.putExtra(intent.EXTRA_SUBJECT,subject);
        intent.putExtra(intent.EXTRA_TEXT,text);
        startActivity(intent);
    }
}
