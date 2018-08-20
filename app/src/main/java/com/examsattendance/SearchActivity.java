package com.examsattendance;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.examsattendance.database.DatabaseHelper;
import com.examsattendance.model.Student;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    DatabaseHelper db;
    TextView mFullname;
    TextView mRegno;
    TextView mLevel;
    TextView mFaculty;
    TextView mDepartment;
    CheckBox mPresent;
    ImageView image_pic;
    Button mSummary;
    String courseid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        db= new DatabaseHelper(this);
        mFullname=findViewById(R.id.text_fullname);
        mLevel=findViewById(R.id.text_level);
        mRegno=findViewById(R.id.text_regno);
        mFaculty=findViewById(R.id.text_faculty);
        mDepartment=findViewById(R.id.text_department);
        mPresent=findViewById(R.id.check_present);
        image_pic=findViewById(R.id.pic_picture);
        mSummary=findViewById(R.id.btn_summary);


        courseid = getIntent().getStringExtra("course");


        mSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),SummaryActivity.class);
                i.putExtra("course",courseid);
                startActivity(i);
                finish();
            }
        });
      //  db.insertCourse("cosc301","Algorithm");
        //db.insertCourse("cosc303","Databases");
//db.insertStudent("u14cs1001","Abdullahi","Aisha","300","Zoology","science","https://www.konstantingoldenberg.com/wp-content/uploads/2017/09/EP_2017_07962-1-300x300.jpg");
    //   db.insertStudent("u14cs1074","Yusuf","Isah","100","mathematics","science","https://cameraman.ng/wp-content/uploads/2018/01/rachael-aluko-300x300.jpg");
//        db.insertStudent("u14cs1064","muhammed","Yusuf","200","statistics","science");
//        db.insertCourse("cosc401","Data Structures and Algorithm");
//        db.insertCourse("cosc402","Formal Methods");
//        db.studentRegisterCourse("u14cs1074",1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(db.searchPresent(query)){
                    Toast.makeText(SearchActivity.this, "Student has Already been cleared", Toast.LENGTH_SHORT).show();
                }


            //    final Student student =db.getStudent(query);
                final Student student=db.searchStudentInCourse(query.trim(),courseid);
                if(student!=null) {

                    RequestManager requestManager = Glide.with(getApplicationContext());
// Create request builder and load image.
                    RequestBuilder requestBuilder = requestManager.load(student.getImage_url());
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
                    requestBuilder.apply(requestOptions);

// Show image into target imageview.
                    requestBuilder.into(image_pic);
                //    Toast.makeText(SearchActivity.this, student.getImage_url(), Toast.LENGTH_SHORT).show();
                    mDepartment.setVisibility(View.VISIBLE);
                    mLevel.setVisibility(View.VISIBLE);
                    mFaculty.setVisibility(View.VISIBLE);
                    mRegno.setVisibility(View.VISIBLE);
                    mFullname.setText("Fullname: " + student.getFname() + " " + student.getLname());
                    mDepartment.setText("Department: " + student.getDepartment());
                    mLevel.setText("Level: " + student.getLevel());
                    mFaculty.setText("Faculty: " + student.getFaculty());
                    mRegno.setText("Regno: " + student.getRegno());
                    mPresent.setVisibility(View.VISIBLE);
                    mPresent.setChecked(false);
                    mPresent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked){
                              //  db.addPresent("me","u14cs1074","Mathematics");
                                db.addPresent(student.getFname() + " " + student.getLname(),student.getRegno(),student.getDepartment());

                            }

                        }
                    });

                //    mPresent.seton
                }
                else{
                   mFullname.setText("Student did not register for this course");
                   mDepartment.setVisibility(View.GONE);
                   mLevel.setVisibility(View.GONE);
                   mFaculty.setVisibility(View.GONE);
                   mPresent.setVisibility(View.GONE);
                   mRegno.setVisibility(View.GONE);
                }

             //   Toast.makeText(SearchActivity.this, "you entered the value "+query, Toast.LENGTH_SHORT).show();
            //    movieAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
           //     movieAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}
