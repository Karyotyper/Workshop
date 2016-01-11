package app.workshop.com.listviewadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import app.workshop.com.pojo.Student;
import app.workshop.com.workshop.R;
import app.workshop.com.workshop.StudentDetails;

/**
 * Created by Arafat on 11/01/2016.
 */
public class StudentListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<Student> studentEntityList = new ArrayList<Student>();


    public StudentListViewAdapter(Context context,
                                 List<Student> studentEntityList) {
        mContext = context;
        this.studentEntityList = studentEntityList;
        inflater = LayoutInflater.from(mContext);
    }



    public class ViewHolder {
        TextView studentName;
        TextView studentRegistration;
        TextView studentCgpa;

        //ImageView flag;
    }


    @Override
    public int getCount() {
        return studentEntityList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_item_student, null);
            holder.studentName = (TextView) view.findViewById(R.id.textview_student_name);
            holder.studentRegistration = (TextView) view.findViewById(R.id.textview_reg_no);
            holder.studentCgpa = (TextView)view.findViewById(R.id.textview_cgpa);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.studentName.setText(studentEntityList.get(position).getName());
       // String fullName = studentEntityList.get(position).getFirstName()+" "+clientEntityList.get(position).getLastName();
        holder.studentRegistration.setText(String.valueOf(studentEntityList.get(position).getRegistration()));
        holder.studentCgpa.setText(String.valueOf(studentEntityList.get(position).getCgpa()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext,StudentDetails.class);
                intent.putExtra("ID",studentEntityList.get(position).getId());

                mContext.startActivity(intent);
            }
        });

        return view;
    }


}
