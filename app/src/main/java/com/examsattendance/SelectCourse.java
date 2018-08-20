package com.examsattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.examsattendance.database.DatabaseHelper;

import org.angmarch.views.NiceSpinner;

public class SelectCourse extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
Button mProceedSearch;
NiceSpinner selectCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);
        DatabaseHelper db=new DatabaseHelper(this);
        selectCourse=findViewById(R.id.spinner_select_course);
        selectCourse.setOnItemSelectedListener(this);
//        db.insertCourse("cosc401","Algorithm");
//        db.insertCourse("cosc404","Web Design");

        db.insertStudent("u14cs1074","Abdulazeez","Mustapha","400","Computer Science","Science","https://www.konstantingoldenberg.com/wp-content/uploads/2017/09/EP_2017_07962-1-300x300.jpg");

        if(db.getCourses()==null){

        }else{

            selectCourse.attachDataSource(db.getCourses());
        }

        mProceedSearch=findViewById(R.id.btn_search_link);
        mProceedSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String selected= String.valueOf(selectCourse.getSelectedIndex()+1);
               Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
               intent.putExtra("course",selected);
               startActivity(intent);

               // startActivity(new Intent(getApplicationContext(),SearchActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


       // Toast.makeText(getApplicationContext(),""+selectCourse.getSelectedIndex(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
