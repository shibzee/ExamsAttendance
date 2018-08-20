package com.examsattendance;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.examsattendance.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
//private FirebaseAuth mAuth;
private EditText mEmail;
private EditText mPassword;
private Button mLogin;
private LinearLayout linearLayout;
private DatabaseHelper db;
private TextView admin;
private String password;
 private String email;
private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Invigilator Login");
        setContentView(R.layout.activity_login);
        mEmail= findViewById(R.id.editTextEmail);
        mPassword=findViewById(R.id.editTextPassword);
        mLogin=findViewById(R.id.buttonLogin);
        linearLayout=findViewById(R.id.linear_login);
        admin= findViewById(R.id.textAdminLogin);
        db= new DatabaseHelper(this);

//        db.createAdmin("sherif@gmail.com","abulado");
         db.createAdmin("abdul@gmail.com","abdul");
         db.createUser("abdul@gmail.com","abdul");
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminActivity.class));
                finish();
            }
        });


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= mEmail.getText().toString().trim();
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
                    User currentUser=  db.Authenticate(new User(email,password));
                    if(currentUser!=null){
                       // Toast.makeText(LoginActivity.this, "Working", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),SelectCourse.class));
                        finish();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }



}
