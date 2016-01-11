package app.workshop.com.workshop;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.workshop.com.directory.DatabaseHelper;
import app.workshop.com.listviewadapter.StudentListViewAdapter;
import app.workshop.com.pojo.Student;


public class MainActivity extends ActionBarActivity {

    StudentListViewAdapter studentListViewAdapter;
    public static List<Student> listOfStudent;
    DatabaseHelper db;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.list);

        db = new DatabaseHelper(this);
        listOfStudent = db.getAllStudent();




        /**
         * CRUD Operations
         * */
       /* // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addStudent(new Student(1, "Sakhawat Hossain Saimon Boss", 2011331004, 3.98));
        db.addStudent(new Student(2, "Tanzira Najneen Tonu", 2011331015, 4.00));
        db.addStudent(new Student(3, "Ashzabin Wadud", 2011331064, 3.99));
        db.addStudent(new Student(4, "Ehsan A Kazi",2011331016,3.98));

        // Reading all contacts
        Log.d("Reading: ", "Reading all students..");
        List<Student> students = db.getAllStudent();

        for (Student sn : students) {
            String log = "Id: "+sn.getId()+" ,Name: " + sn.getName() + " ,Registration: " + sn.getRegistration() + " ,CGPA: " + sn.getCgpa();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }*/
    }


    @Override
    protected  void onResume(){
        super.onResume();
        listOfStudent = db.getAllStudent();
        studentListViewAdapter = new StudentListViewAdapter(this,listOfStudent);
        lv.setAdapter(studentListViewAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this,AddStudent.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
