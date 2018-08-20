package com.examsattendance;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.examsattendance.database.DatabaseHelper;
import com.examsattendance.model.Admin;
import com.examsattendance.model.User;

public class AdminActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    EditText mEmail;
    EditText mPassword;
    TextView userLogin;
    Button login;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        db=new DatabaseHelper(this);
        mEmail=findViewById(R.id.aeditTextEmail);
        linearLayout=findViewById(R.id.linear_admin);
        mPassword=findViewById(R.id.aeditTextPassword);

        getSupportActionBar().setTitle("Admin Login");
        login=findViewById(R.id.abuttonLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Snackbar.make(linearLayout,"Email field is empty",Snackbar.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
                    Snackbar.make(linearLayout,"Email and Password fields are empty",Snackbar.LENGTH_SHORT).show();

                }
                else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Snackbar.make(linearLayout,"Please enter a valid email address",Snackbar.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(password)){
                    Snackbar.make(linearLayout,"Password field is empty",Snackbar.LENGTH_SHORT).show();
                }
                else{
                    //Authenticate User
                    Admin currentUser=  db.Authenticate(new Admin(email,password));
                    if(currentUser!=null){
                       startActivity(new Intent(getApplicationContext(),MainActivity.class));
                       finish();
                    }
                    else{
                        Toast.makeText(AdminActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        userLogin=findViewById(R.id.atextUserLogin);
        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });

    }
}
