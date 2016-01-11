package app.workshop.com.directory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import app.workshop.com.pojo.Student;

/**
 * Created by Arafat on 11/01/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "studentManager";

    // Contacts table name
    private static final String TABLE_STUDENT = "student";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "student_name";
    private static final String KEY_REG_NO = "reg_no";
    private  static final String KEY_CGPA = "cgpa";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_REG_NO + " INT,"
                + KEY_CGPA + " REAL"+ ")";
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);

        // Create tables again
        onCreate(db);
    }

    //Adding New Student
    public void addStudent(Student student){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName()); // Contact Name
        values.put(KEY_REG_NO, student.getRegistration()); // Contact Phone Number
        values.put(KEY_CGPA,student.getCgpa());

        // Inserting Row
        db.insert(TABLE_STUDENT, null, values);
        db.close(); // Closing database connection

    }

    //Getting a single student

    public Student getStudent(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENT, new String[] { KEY_ID,
                        KEY_NAME, KEY_REG_NO,KEY_CGPA }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Student student = new Student(cursor.getInt(0),
                cursor.getString(1), cursor.getInt(2),cursor.getDouble(3));
        // return contact
        return student;
      }

    public List<Student> getAllStudent(){
        List<Student> studentList = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setRegistration(cursor.getInt(2));
                student.setCgpa(cursor.getDouble(3));
                // Adding student to list
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        // return contact list
        return studentList;
    }

    //update single Student
    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName()); // Contact Name
        values.put(KEY_REG_NO, student.getRegistration()); // Contact Phone Number
        values.put(KEY_CGPA,student.getCgpa());


        // updating row
        return db.update(TABLE_STUDENT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(student.getId()) });
    }

    // Deleting single student
    public void deleteContact(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, KEY_ID + " = ?",
                new String[] { String.valueOf(student.getId()) });
        db.close();
    }

   }



