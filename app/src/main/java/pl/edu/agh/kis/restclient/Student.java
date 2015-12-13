package pl.edu.agh.kis.restclient;

/**
 * Created by wukat on 12.12.15.
 */
public class Student {

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(String firstName, String lastName, float mark) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mark = mark;
    }

    private String firstName;
    private String lastName;
    private float mark;

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
