package app.workshop.com.workshop;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.workshop.com.directory.DatabaseHelper;
import app.workshop.com.pojo.Student;


public class StudentDetails extends ActionBarActivity {

    Bundle extras;
    Student student;
    DatabaseHelper db;
    EditText editTextname, editTextRegistration, editTextCgpa;
    Button delete,update;
    String name,reg,cgpa;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        db= new DatabaseHelper(StudentDetails.this);
        Intent intent = getIntent();
        extras = intent.getExtras();
        id = extras.getInt("ID");
        Log.e("FOUND ID: ", Integer.toString(id));
        student = new Student();
        student = db.getStudent(id);

        update = (Button)findViewById(R.id.button_update);
        delete  = (Button)findViewById(R.id.button_delete);

        showData();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editTextname.getText().toString();
                reg = editTextRegistration.getText().toString();
                cgpa = editTextCgpa.getText().toString();

                if(fillWithData(name,reg,cgpa)) {
//                    db.updateStudent(student);
//                    Toast.makeText(StudentDetails.this, "Successfully updated a student", Toast.LENGTH_LONG).show();
//                    finish();
                    Student updateStudent = new Student(id, name, Integer.parseInt(reg), Double.parseDouble(cgpa));
                    db.updateStudent(updateStudent);
                    Toast.makeText(StudentDetails.this, "Successfully updated a student", Toast.LENGTH_LONG).show();
                    finish();
                }
                else
                {
                    Toast.makeText(StudentDetails.this, "Cannot update the student", Toast.LENGTH_LONG).show();

                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  db.deleteContact(student);
                  Toast.makeText(StudentDetails.this, "Successfully deleted a student", Toast.LENGTH_LONG).show();
                  finish();
            }
        });
    }

    private boolean fillWithData(String name, String reg,String cgpa) {


        if(name.equals("")||reg.equals("")||cgpa.equals("")){
            Toast.makeText(this, "Sorry, It's important that you fill in all the fields", Toast.LENGTH_LONG).show();
            return false;
        }

        return  true;

    }

    private void showData() {

       editTextname = (EditText)findViewById(R.id.textview_student_name);
       editTextname.setText(student.getName());

       editTextRegistration = (EditText)findViewById(R.id.textview_reg_no);
       editTextRegistration.setText(String.valueOf(student.getRegistration()));

       editTextCgpa = (EditText)findViewById(R.id.textview_cgpa);
       editTextCgpa.setText(Double.toString(student.getCgpa()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_details, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
