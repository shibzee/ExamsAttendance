package com.examsattendance.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.examsattendance.model.Admin;
import com.examsattendance.model.Course;
import com.examsattendance.model.Student;
import com.examsattendance.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/08/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME="examination_attendance.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_STUDENT="students";
    private static final String TABLE_COURSE="courses";
    private static final String TABLE_PRESENT="present";
    private static final String TABLE_ABSENT="absent";
    private static final String TABLE_ENROLL="student_enroll";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_FNAME="fname";
    private static final String COLUMN_LNAME="lname";
    private static final String COLUMN_REGNO="regno";
    private static final String COLUMN_LEVEL="level";
    private static final String COLUMN_DEPARTMENT="department";
    private static final String COLUMN_FACULTY="faculty";
    private static final String COLUMN_PICTURE = "picture";
    private static final String COLUMN_TITLE="title";
    private static final String COLUMN_CODE="course_code";
    private static final String STUDENT_ID = "stu_id";
    private static final String COURSE_ID="course_id";
    private static final String COLUMN_NAME = "name";

    private static final String TABLE_USER = "user";
    private static final String COLUMN_EMAIL ="email" ;
    private static final String TABLE_ADMIN ="admin" ;
    private static final String COLUMN_PASSWORD = "password";


    private static final String CREATE_TABLE_USERS=
            "CREATE TABLE "+TABLE_USER+"("
            +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_EMAIL+" TEXT, "
            +COLUMN_PASSWORD+" TEXT UNIQUE"
            +")";
    private static final String CREATE_TABLE_ADMIN=
            "CREATE TABLE "+TABLE_ADMIN+"("
            +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_EMAIL+" TEXT UNIQUE, "
            +COLUMN_PASSWORD+" TEXT "

            +")";



    private static final String CREATE_TABLE_STUDENTS=
            "CREATE TABLE "+TABLE_STUDENT+ "("
            //+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_REGNO+" TEXT PRIMARY KEY,"
            +COLUMN_FNAME+" TEXT,"
            +COLUMN_LNAME+" TEXT,"
            +COLUMN_LEVEL+" TEXT,"
            +COLUMN_DEPARTMENT+" TEXT,"
            +COLUMN_FACULTY+" TEXT, "
            +COLUMN_PICTURE+" TEXT"
            + ")";


    private static final String CREATE_TABLE_COURSES=
            "CREATE TABLE "+TABLE_COURSE+ "("
            +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_CODE+" TEXT, "
            +COLUMN_TITLE+" TEXT"
            + ")";
    private static final String CREATE_TABLE_PRESENT=
            "CREATE TABLE "+TABLE_PRESENT+ "("
            +COLUMN_NAME+" TEXT, "
            +COLUMN_REGNO+" TEXT, "
            +COLUMN_DEPARTMENT+" TEXT"
            + ")";

    private static final String CREATE_TABLE_ABSENT=
            "CREATE TABLE "+TABLE_ABSENT+ "("
            +COLUMN_NAME+" TEXT, "
            +COLUMN_REGNO+" TEXT, "
            +COLUMN_DEPARTMENT+" TEXT"
            +")";



    private static final String CREATE_STUDENT_ENROLL=
            "CREATE TABLE "+TABLE_ENROLL+ "("
            + COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + STUDENT_ID+ " TEXT, "
            + COURSE_ID+ " INTEGER, "
            + "FOREIGN KEY(" + STUDENT_ID + ") REFERENCES "
            + TABLE_STUDENT + "(regno), "
            +"FOREIGN KEY(" + COURSE_ID + ") REFERENCES "
            + TABLE_COURSE + "(id) "
            +")";


    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_STUDENTS);
        db.execSQL(CREATE_STUDENT_ENROLL);
        db.execSQL(CREATE_TABLE_COURSES);
        db.execSQL(CREATE_TABLE_PRESENT);
        db.execSQL(CREATE_TABLE_ABSENT);
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_ADMIN);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {

        db.setForeignKeyConstraintsEnabled(true);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRESENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENROLL);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ABSENT);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_ADMIN);
        onCreate(db);
    }



    public void insertStudent(String regno,String fname,String lname, String level,String department,String faculty,String pic_url){
        // get writable database as we want to write data

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_REGNO,regno);
        values.put(COLUMN_FNAME,fname);
        values.put(COLUMN_LNAME,lname);
        values.put(COLUMN_LEVEL,level);
        values.put(COLUMN_DEPARTMENT,department);
        values.put(COLUMN_FACULTY,faculty);
        values.put(COLUMN_PICTURE,pic_url);
        db.insert(TABLE_STUDENT,null,values);


    }

    public void insertCourse(String code, String title){
        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_CODE,code);
        values.put(COLUMN_TITLE,title);

        db.insert(TABLE_COURSE,null,values);
    }

    public List<String> getCourses(){
        List<String> courses= new ArrayList<String>();
        SQLiteDatabase db= this.getWritableDatabase();
        String query= "SELECT * FROM "+TABLE_COURSE;
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.getCount()>0){
        if(cursor.moveToFirst()){
            do{
              //  Course course= new Course();
             //   course.setCourse_id(cursor.getInt(0));
               // course.setCourse_code(cursor.getString(1));
                //course.setTitle(cursor.getString(2));
                //course.setCourse_code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)));
               courses.add(cursor.getString(1));

            }while (cursor.moveToNext());
        }
        db.close();
        return courses;
        }

        return null;
    }

    public boolean studentRegisterCourse(String regno,String courseid){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(STUDENT_ID,regno);
        values.put(COURSE_ID,courseid);

        Cursor cursor= db.query(TABLE_ENROLL,null,
                STUDENT_ID+ " = ? AND "+COURSE_ID+" = ?",
                new String[]{regno,courseid},
                null,
                null,
                null);
        if(cursor != null && cursor.moveToFirst()&& cursor.getCount()>0){
            return true;
        }
        else {

            db.insert(TABLE_ENROLL, null, values);
        }
        return false;

    }

    public void addPresent(String name, String regno,String department){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,name);
        values.put(COLUMN_REGNO,regno);
        values.put(COLUMN_DEPARTMENT,department);
        db.insert(TABLE_PRESENT,null,values);
db.close();
       // return newRow;
    }


    public Admin Authenticate(Admin user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADMIN,// Selecting Table
                null,
                // new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                COLUMN_EMAIL + "=?",
                new String[]{user.getEmail()},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            Admin user1 = new Admin(cursor.getInt(0), cursor.getString(1), cursor.getString(2));

            //Match both passwords check they are same or not
            if (user.getPassword().equalsIgnoreCase(user1.getPassword())) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER,// Selecting Table
                null,
               // new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                COLUMN_EMAIL + "=?",
                new String[]{user.getEmail()},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2));

            //Match both passwords check they are same or not
            if (user.getPassword().equalsIgnoreCase(user1.getPassword())) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }


    public void createAdmin(String email, String password){


        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_EMAIL,email);
        values.put(COLUMN_PASSWORD,password);
        db.insert(TABLE_ADMIN,null, values);
        db.close();
    }

    public void createUser(String email, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_EMAIL,email);
        values.put(COLUMN_PASSWORD,password);
        db.insert(TABLE_USER,null, values);
        db.close();
    }


    public void deletePresent(String regno){
        SQLiteDatabase db=this.getWritableDatabase();
        String [] args= {regno};
        db.delete(TABLE_PRESENT,COLUMN_REGNO + " = ? ",args);
        db.close();

    }
//    public void deleteJournal(Journal journal) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(Journal.TABLE_NAME, Journal.COLUMN_ID + " = ?",
//                new String[]{String.valueOf(journal.getId())});
//        db.close();
//    }



    public int getPresentCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PRESENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    public boolean isAdminEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADMIN,// Selecting Table
                null,
          //      new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                COLUMN_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }

    public boolean isUserEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER,// Selecting Table
                null,
               // new String[]{, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                COLUMN_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }


    public void deleteAllPresent(){
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_PRESENT);
        db.close();
    }

    public Student getStudent(String regno){
        SQLiteDatabase db= this.getReadableDatabase();
        Student student = null;
        String[] selectionArgs = {regno};
        Cursor cursor= db.query(TABLE_STUDENT,
                null,
                COLUMN_REGNO + "=?",
                selectionArgs,
                null,
                null,
                null,
                null
                );

       if( cursor.getCount()>0) {
            cursor.moveToFirst();

            //prepare student object
            student = new Student(
                    cursor.getString(cursor.getColumnIndex(COLUMN_REGNO)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_FNAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_LNAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_LEVEL)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_FACULTY)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PICTURE)));

            cursor.close();
            return student;
        }

        return null;


    }

    public long addAbsent(String name,String regno,String department){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,name);
        values.put(COLUMN_REGNO,regno);
        values.put(COLUMN_DEPARTMENT,department);

        long newRow = db.insert(TABLE_ABSENT,null,values);

        return newRow;
    }


    public boolean searchPresent(String regno){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.query(TABLE_PRESENT,
                null,
                COLUMN_REGNO+" = ?",
                new String[]{regno},
                null,
                null,
                null,
                null
                );
        if(cursor != null && cursor.moveToFirst()&& cursor.getCount()>0){
            return true;
        }


        return false;

    }

    public int getStudentByCourseCount(String id){
        SQLiteDatabase db= this.getReadableDatabase();
        String [] args={id};
        String selectQuery= "SELECT fname, lname, regno, level, department, faculty FROM students, student_enroll, courses WHERE students.regno=student_enroll.stu_id AND courses.id=student_enroll.course_id AND courses.id= ? ";
        Cursor cursor= db.rawQuery(selectQuery,args);
        int count=cursor.getCount();
        cursor.close();
        return count;

    }

    public Student searchStudentInCourse(String regno,String courseId){
        SQLiteDatabase db= this.getReadableDatabase();
        Student student=null;
        String query="SELECT regno, fname, lname, level, department, faculty, picture FROM students, student_enroll, courses WHERE students.regno=student_enroll.stu_id AND courses.id=student_enroll.course_id AND students.regno=? AND courses.id=?";
        String [] args ={regno, courseId};
        Cursor cursor= db.rawQuery(query,args);
        if(cursor != null && cursor.moveToFirst()&& cursor.getCount()>0){
            //prepare student object
            student = new Student(
                    cursor.getString(cursor.getColumnIndex(COLUMN_REGNO)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_FNAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_LNAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_LEVEL)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_FACULTY)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PICTURE)));

            cursor.close();
            return student;
        }
        return null;
    }

    public List<Student> getStudentsByCourse(String course){
        List<Student> students = new ArrayList<>();
        String selectQuery= "SELECT fname, lname, regno, level, department, faculty FROM students, student_enroll, courses WHERE students.regno=student_courses.stu_id AND courses.id=student_courses.course_id AND courses.course_code= ? ";
        SQLiteDatabase db= this.getWritableDatabase();
        String [] args= {course};
        Cursor cursor= db.rawQuery(selectQuery,args);

        if(cursor.moveToFirst()){
            do{
                Student student= new Student();
                student.setFname(cursor.getString(cursor.getColumnIndex(COLUMN_FNAME)));
                student.setLname(cursor.getString(cursor.getColumnIndex(COLUMN_LNAME)));
                student.setRegno(cursor.getString(cursor.getColumnIndex(COLUMN_REGNO)));
                student.setLevel(cursor.getString(cursor.getColumnIndex(COLUMN_LEVEL)));
                student.setDepartment(cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT)));
                student.setFaculty(cursor.getString(cursor.getColumnIndex(COLUMN_FACULTY)));

                students.add(student);
            }
            while(cursor.moveToNext());
        }

        db.close();
        return students;
      //  return student;
    }


}
