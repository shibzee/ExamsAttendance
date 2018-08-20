package com.examsattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.examsattendance.database.DatabaseHelper;
import com.examsattendance.model.Course;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.List;

public class RegisterCourse extends AppCompatActivity {
NiceSpinner niceSpinner;
EditText regno;
Button registerCourse;
DatabaseHelper db;
Button goHome;
   // Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  DatabaseHelper db= new DatabaseHelper(this);
        setContentView(R.layout.activity_register_course);
        DatabaseHelper db= new DatabaseHelper(this);
        regno=findViewById(R.id.edit_reg_no);
        db=new DatabaseHelper(this);
        niceSpinner=findViewById(R.id.spinner_select_course);
        registerCourse=findViewById(R.id.btn_reg_course);
        goHome=findViewById(R.id.btn_go_home);

        niceSpinner.attachDataSource(db.getCourses());

        final DatabaseHelper finalDb = db;

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

        registerCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regn= regno.getText().toString().trim();
                String courseId= String.valueOf(niceSpinner.getSelectedIndex()+1);

                if(TextUtils.isEmpty(regn)){
                    Toast.makeText(RegisterCourse.this, "Please insert a registration number", Toast.LENGTH_SHORT).show();
                }
                else if(finalDb.studentRegisterCourse(regn,courseId)==true){
                    Toast.makeText(RegisterCourse.this, "Student is Already registered for this course", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(RegisterCourse.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
}
