package com.examsattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.examsattendance.database.DatabaseHelper;

import org.w3c.dom.Text;

public class SummaryActivity extends AppCompatActivity {

    private TextView textTotal;
    private TextView present;
    private TextView absent;
    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DatabaseHelper db=new DatabaseHelper(this);
        setContentView(R.layout.activity_summary);
        present=findViewById(R.id.text_present);
        absent=findViewById(R.id.text_absent);
        finish=findViewById(R.id.btn_finish);
        textTotal=findViewById(R.id.text_total);

        String courseid= getIntent().getStringExtra("course");
        textTotal.setText("Total: "+db.getStudentByCourseCount(courseid));

        present.setText("Present: "+db.getPresentCount());


        int abs= db.getStudentByCourseCount(courseid) - db.getPresentCount();

        absent.setText("Absent: "+abs);
       // absent.setText();

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteAllPresent();
                startActivity(new Intent(getApplicationContext(),SelectCourse.class));
                finish();
            }
        });

    }
}
