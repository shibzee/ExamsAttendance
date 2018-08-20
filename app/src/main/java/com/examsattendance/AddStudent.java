package com.examsattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.examsattendance.database.DatabaseHelper;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AddStudent extends AppCompatActivity {
    private EditText mRegno;
    private EditText mFname;
    private EditText mLname;
    private EditText mPhoto;
    private NiceSpinner level;
    private NiceSpinner faculty;
    private NiceSpinner department;
    private Button addStudent;
    List<String> levels = new LinkedList<>(Arrays.asList("Select Level","100","200","300","400"));
    List<String> departments= new LinkedList<>(Arrays.asList("Select Department","Computer Science","Mathematics","Statistics","Biology"));
    List<String> faculties= new LinkedList<>(Arrays.asList("Select Faculty","Science","Arts","Law","Social Sciences"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DatabaseHelper db= new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        mRegno = findViewById(R.id.edit_reg);
        getSupportActionBar().setTitle("Add New Student");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mFname = findViewById(R.id.edit_fname);
        mLname= findViewById(R.id.edit_lname);
        mPhoto=findViewById(R.id.edit_photo_url);
        addStudent=findViewById(R.id.btn_add_student);
        level= findViewById(R.id.spinner_level);
        faculty= findViewById(R.id.spinner_faculty);
        department= findViewById(R.id.spinner_department);


        level.attachDataSource(levels);
        faculty.attachDataSource(faculties);
        department.attachDataSource(departments);


        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sLevel="";
                String sdepartment = "";
                String sfaculty="";



                switch (level.getSelectedIndex()){
                    case 1:
                        sLevel="100";
                        break;
                    case 2:
                        sLevel="200";
                        break;
                    case 3:
                        sLevel="300";
                        break;
                    case 4:
                        sLevel="400";
                        break;
                }

                switch (department.getSelectedIndex()){
                    case 1:
                        sdepartment="Computer Science";
                        break;
                    case 2:
                        sdepartment="Mathematics";
                        break;
                    case 3:
                        sdepartment="Statistics";
                        break;
                    case 4:
                        sdepartment="Biology";
                        break;
                }

                switch (faculty.getSelectedIndex()){
                    case 1:
                        sfaculty="Science";
                        break;
                    case 2:
                        sfaculty="Arts";
                        break;
                    case 3:
                        sfaculty="Law";
                        break;
                    case 4:
                        sfaculty="Social Sciences";
                        break;
                }

                db.insertStudent(
                       mRegno.getText().toString().trim(),
                       mFname.getText().toString().trim(),
                       mLname.getText().toString().trim(),
                       sLevel,
                       sdepartment,
                       sfaculty,
                        mPhoto.getText().toString().trim()
                );

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

    }
}
