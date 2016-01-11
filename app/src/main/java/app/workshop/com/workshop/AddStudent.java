package app.workshop.com.workshop;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.workshop.com.directory.DatabaseHelper;
import app.workshop.com.pojo.Student;


public class AddStudent extends ActionBarActivity {

    EditText editTextname, editTextRegistration, editTextCgpa;
    Button create;
    String name,reg,cgpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        editTextname = (EditText)findViewById(R.id.textview_student_name);
        editTextRegistration = (EditText)findViewById(R.id.textview_reg_no);
        editTextCgpa = (EditText)findViewById(R.id.textview_cgpa);

        create = (Button)findViewById(R.id.button_create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillWithData();
            }
        });

    }

    public void fillWithData(){
       DatabaseHelper db = new DatabaseHelper(this);
       name = editTextname.getText().toString();
       reg = editTextRegistration.getText().toString();
       cgpa = editTextCgpa.getText().toString();
       Student student = new Student(name,Integer.parseInt(reg),Double.parseDouble(cgpa));


        if(name.equals("")||reg.equals("")||cgpa.equals("")){
          Toast.makeText(this, "Sorry, It's important that you fill in all the fields", Toast.LENGTH_LONG).show();
        }
        else{
             db.addStudent(student);
             Toast.makeText(this, "Successfully entered a student", Toast.LENGTH_LONG).show();
             //startActivity(new Intent(AddStudent.this,MainActivity.class));
             finish();

        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_student, menu);
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
