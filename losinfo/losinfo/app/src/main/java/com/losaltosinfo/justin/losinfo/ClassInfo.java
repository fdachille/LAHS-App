package com.losaltosinfo.justin.losinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import static com.losaltosinfo.justin.losinfo.R.styleable.Toolbar;

/**
 * Created by justin on 5/16/2016.
 */
public class ClassInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView courseName = (TextView) findViewById(R.id.courseNameThing);
        TextView teachers = (TextView) findViewById(R.id.Teachers);
        TextView textbooks = (TextView) findViewById(R.id.Textbooks);
        TextView description = (TextView) findViewById(R.id.Description);

        Intent intent = getIntent();

        String courseString = intent.getStringExtra("coursename");
        String teacherString = intent.getStringExtra("teachers");
        String textbooksString = intent.getStringExtra("textbooks");
        String descriptionString = intent.getStringExtra("description");

        courseName.setText(courseString);
        teachers.setText(teacherString);
        textbooks.setText(textbooksString);
        description.setText(descriptionString);
    }
}
