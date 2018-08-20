package com.examsattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.examsattendance.database.DatabaseHelper;

public class AddCourse extends AppCompatActivity {

    EditText mCourseCode;
    EditText mCourseTitle;
    Button mAddCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        mCourseCode=findViewById(R.id.edit_course_code);
        mCourseTitle=findViewById(R.id.edit_course_title);
        mAddCourse=findViewById(R.id.btn_add_course);
        final DatabaseHelper db=new DatabaseHelper(this);

        mAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insertCourse(mCourseCode.getText().toString().trim(),mCourseTitle.getText().toString().trim());
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });



    }
}
