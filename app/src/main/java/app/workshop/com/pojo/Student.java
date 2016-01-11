package app.workshop.com.pojo;

/**
 * Created by Arafat on 11/01/2016.
 */
public class Student {

    private int id;

    private String name;
    private int registration;
    private double cgpa;

    public Student(int id,String name,int registration,double cgpa){
        this.id = id;
        this.name = name;
        this.registration = registration;
        this.cgpa = cgpa;
    }

    public Student(String name,int registration,double cgpa){
        this.name = name;
        this.registration = registration;
        this.cgpa = cgpa;
    }

    public Student(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRegistration() {
        return registration;
    }

    public void setRegistration(int registration) {
        this.registration = registration;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }
}
