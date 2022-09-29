package DTO;

/**
 *
 * @author PHAM NGUYEN THIEN CHUONG - DE160221
 */
public abstract class Student {
    protected String name;
    protected String address;
    protected String email;
    public  String studentID;
    
    public Student(){}

    public Student(String name, String address, String email, String studentID) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    
    public abstract double averangeScore();

    @Override
    public String toString() {
        return "Student ID: " + studentID + "\nName: " + name + "\nAddress: " + address + "\nEmail: " + email + "\n";
    }
    
    
}
