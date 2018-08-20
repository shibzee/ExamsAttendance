package com.examsattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.examsattendance.database.DatabaseHelper;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
private Spinner spinner;
private TextView mTextView;
private Button proceed;
//    List<String> semester=new LinkedList<>(Arrays.asList("Select Semester","First Semester","Second Semester"));
//    List<String> levels = new LinkedList<>(Arrays.asList("Select Level","100","200","300","400"));
//   //100 level
//    List<String> courseFOne = new LinkedList<>(Arrays.asList("Select Course","Cosc 101"));
//    List<String> courseSOne = new LinkedList<>(Arrays.asList("Select Course","Cosc 102"));

    //200 level
//    List<String> courseFTwo = new LinkedList<>(Arrays.asList("Select Course","Cosc 201","Cosc 203"));
//    List<String> courseSTwo = new LinkedList<>(Arrays.asList("Select Course","Cosc 204","Cosc 208"));

    //300 level
//    List<String> courseFThree = new LinkedList<>(Arrays.asList("Select Course","Cosc 301","Cosc 311"));
//    List<String> courseSThree = new LinkedList<>(Arrays.asList("Select Course","Cosc 302","Cosc 304"));

    //400 level
//    List<String> courseFFour = new LinkedList<>(Arrays.asList("Select Course","Cosc 401","Cosc 411"));
//    List<String> courseSFour = new LinkedList<>(Arrays.asList("Select Course","Cosc 402","Cosc 408"));

//private final String [] levels={"Select Level","100","200","300","400"};
//private final String [] courseThree=  {"Select Course","Cosc 301","Cosc 307","Cosc 311"};
//private final String [] courseFour= {"Select Course","Cosc 401","Cosc 407","Cosc 411"};
//private NiceSpinner mLevel;
//private NiceSpinner mCourses;
//private NiceSpinner mSemester;
    CardView addStudent;
    CardView addCourse;
    CardView takeAttedance;
    CardView studRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  spinner= findViewById(R.id.spinner);
        //  mTextView= findViewById(R.id.sugar);
        proceed = findViewById(R.id.btn_proceed);
        addCourse=findViewById(R.id.card_add_course);
        takeAttedance=findViewById(R.id.card_take_attedance);
        studRegister=findViewById(R.id.card_register_course);

        DatabaseHelper db=new DatabaseHelper(this);
    //    db.studentRegisterCourse("u14cs1074",1);
        studRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterCourse.class));
            }
        });
        takeAttedance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SelectCourse.class));
            }
        });

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddCourse.class));
            }
        });
        addStudent=findViewById(R.id.card_add_student);
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddStudent.class));
            }
        });
//   mSemester=(NiceSpinner)findViewById(R.id.spinner_semester);
//   mLevel=(NiceSpinner)findViewById(R.id.spinner_level);
//   mCourses=(NiceSpinner)findViewById(R.id.spinner_courses);
//
//
//   mSemester.attachDataSource(semester);
//   mLevel.attachDataSource(levels);
//   mSemester.setOnItemSelectedListener(this);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
            }
        });
    }

//
//   mLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//       @Override
//       public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//       }
//
//       @Override
//       public void onNothingSelected(AdapterView<?> parent) {
//
//       }
//   });
//   mSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//       @Override
//       public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//           switch (position){
//               case 1:
//                   if(mLevel.getSelectedIndex()==1){
//                       mCourses.attachDataSource(courseFOne);
//                   }
//                   else if(mLevel.getSelectedIndex()==2){
//                       mCourses.attachDataSource(courseFTwo);
//                   }
//                   else if(mLevel.getSelectedIndex()==3){
//                       mCourses.attachDataSource(courseFThree);
//                   }
//                   else if(mLevel.getSelectedIndex()==4){
//                        mCourses.attachDataSource(courseFFour);
//                   }
//
//                   break;
//               case 2:
//                   if(mLevel.getSelectedIndex()==1){
//                        mCourses.attachDataSource(courseSOne);
//                   }
//                   else if(mLevel.getSelectedIndex()==2){
//                        mCourses.attachDataSource(courseSTwo);
//                   }
//                   else if(mLevel.getSelectedIndex()==3){
//                        mCourses.attachDataSource(courseSThree);
//                   }
//                   else if(mLevel.getSelectedIndex()==4){
//                        mCourses.attachDataSource(courseSFour);
//                   }
//                   break;
//           }
//       }

//       @Override
//       public void onNothingSelected(AdapterView<?> parent) {
//
//       }
//   });
//
//   //     mLevel.attachDataSource(levels);
//     //   mLevel.setOnItemSelectedListener(this);
////   spinner.setOnItemSelectedListener(this);
//        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
//      //  arrayAdapter.insert();
//        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
////        spinner.setAdapter(arrayAdapter);
//
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////    mTextView.setText(dataset.get(position));
//
//    switch (position){
//        case 1:
//          //  mCourses.attachDataSource(datame);
//            break;
//    }
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}































































































































































































































































































































































