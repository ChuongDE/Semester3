package CTL;

import DTO.*;
import java.util.*;
/**
 *
 * @author PHAM NGUYEN THIEN CHUONG - DE160221
 */
public class StudentManager{
    private String test;
    private Validation check = new Validation();
    private int choice, innerChoice = -1, numOfStudent, IT = 0, biz = 0;
    public static Scanner sc = new Scanner(System.in);
    private final char ESC = 'e';
    

    
    public void Add_Student_Info(ArrayList<Student> studentList) {
	System.out.print("Number of student: ");
	do {
            do {
                test = input();
            } while (check.checkNumber(test, true) == 0);
            numOfStudent = Integer.parseInt(test);
            if(numOfStudent <= 0) {
                System.err.println("Number of student cannot be less or equal 0");
		System.out.print("Please enter again: ");
            }
	} while (numOfStudent <= 0);
	//Input section
	for(int i = 0; (i < numOfStudent) && (test.charAt(0) != ESC); i++) {
            System.out.println("\t\t[0]: IT student\t\t[1]Business student");
            do {
		System.out.print("Choose student major [0/1]: ");
		do {
                    test = input();
		} while (check.checkNumber(test, true) == 0);
		innerChoice = Integer.parseInt(test);
            } while (innerChoice < 0 || innerChoice > 1);
            if(innerChoice == 0) IT++;
            else biz++;
            System.out.println("Student " + (i+1));
            inputStudentInfo(studentList, innerChoice);
            if((i >= 1 && i != numOfStudent-1) && (numOfStudent > 2)) {
                System.out.print("If you want to exit input operation, press [E]: ");
                do {
                    test = input();
                } while (check.checkNull(test) == 0);	
            }
	}
    }
    
    public void inputStudentInfo(ArrayList<Student> list, int majorID) {
        Student student;
        String Score;
        if (majorID == 0) {
            student = new ITStudent();
        } else {
            student = new BizStudent();
        }
        //Name input
        System.out.print("Enter student name: ");
        do {
            student.setName(input());
        } while (check.checkName(student.getName()) == 0);
        //StudentID input
        do {
            System.out.print("Enter StudentID: ");
            do {
                test = input();
            } while (check.checkStudentID(test) == 0);
        } while (check.similarStudentID(list, test) == 1);
        student.setStudentID(test);
        //Mail input
        System.out.print("Enter Email: ");
        do {
            test = input();
        } while (check.checkEmail(test) == 0);
        student.setEmail(test);
        //Address input
        System.out.print("Enter student address: ");
        student.setAddress(input().trim());
        //Grade input
        System.out.println("Enter student grade");
        if (majorID == 0) {
            System.out.print("Java: ");
        } else {
            System.out.print("Accounting: ");
        }
        do {
            Score = input();
        } while (check.checkGrade(Score) == 0);
        if (majorID == 0) {
            ((ITStudent) student).setJavaScore(Double.parseDouble(Score));
        } else {
            ((BizStudent) student).setAccountingScore(Double.parseDouble(Score));
        }
        if (majorID == 0) {
            System.out.print("CSS: ");
        } else {
            System.out.print("Marketing: ");
        }
        do {
            Score = input();
        } while (check.checkGrade(Score) == 0);
        if (majorID == 0) {
            ((ITStudent) student).setCssScore(Double.parseDouble(Score));
        } else {
            ((BizStudent) student).setMaketingScore(Double.parseDouble(Score));
        }
        //add to list
        list.add(student);
    }
    
    
    public void print_Data(ArrayList<Student> studentList, int noOfprint) {
        if(studentList == null || studentList.isEmpty() == true){
            System.out.println(" > Empty Student List");
            return;
        }
	printInfo(studentList, IT, biz, noOfprint);
    }
		
    private void printInfo(ArrayList<Student> list, int IT, int bIZ, int noStudent) {	
	int limit = 0;
	if(noStudent != 5) limit = list.size();
	if(noStudent == 5) {
            if(list.size() < 5) limit = list.size();
            else limit = 5;
	}
        if (IT > 0) {
            System.out.println("\t\t\t<IT STUDENT>");
            for(int i = 0; i < limit; i++) {
                if(list.get(i) instanceof ITStudent) exportInfo(list.get(i));
            }
        }
        if (bIZ > 0) {
            System.out.println("\t\t\t<BIZ STUDENT>");
            for(int i = 0; i < limit; i++) {
                if(list.get(i) instanceof BizStudent) exportInfo(list.get(i));
            }
        }
    }
    
    private void exportInfo(Student s) {
        System.out.print("Student ID: " + s.getStudentID());
        System.out.print(", Name: " + s.getName());
        System.out.print(", Email: " + s.getEmail());
        System.out.print(", Address: " + s.getAddress());
        if(s instanceof ITStudent) {
            System.out.printf(", Java Score: %.2f", ((ITStudent)s).getJavaScore());
            System.out.printf(", CSS Score: %.2f", ((ITStudent)s).getCssScore());
        }else {
            System.out.printf(", Accounting Score: %.2f", ((BizStudent)s).getAccountingScore());
            System.out.printf(", Marketing Score: %.2f", ((BizStudent)s).getMaketingScore());
        }
        System.out.printf(", Average Score: %.2f\n", s.averangeScore());
    }
    
 
    public void find_Student_by_Address(ArrayList<Student> studentList){
        if(studentList == null || studentList.isEmpty() == true){
            System.out.println(" > Empty Student List");
            return;
        }
        String find_address;
        System.out.println("---Find Student By Address---");
        System.out.print("Enter address: ");
        find_address = sc.nextLine();
        boolean checkAddress = false;
        for(int i = 0; i < studentList.size(); i++){
            if(find_address.equals(studentList.get(i).getAddress()) == true){
                checkAddress = true;
                System.out.println(studentList.get(i).toString());
            }
            if(checkAddress == false){
                System.out.println("Address Not Founded!!!");
            }
        }
    }
    
    public void delete_Student_by_ID(ArrayList<Student> studentList){
        System.out.println("---Delete Student---");
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();
        boolean checkID = false;
        try{
            for(int i = 0; i < studentList.size(); i++){
                if(id.equals(studentList.get(i).getStudentID()) == true || checkID == true){
                    checkID = true;
                    studentList.remove(i);
                    i--;
                    System.out.println("Delete Successfully!!!");
                }
                if (checkID == false) {
                    System.out.println("Student ID not founded"); 
                }
            }  
        }catch(Exception e){
            System.out.println("> Invalid ID!");
        }
    }
    
    public void edit_Student_by_ID(ArrayList<Student> studentList){
        if(studentList == null || studentList.isEmpty() == true){
            System.out.println(" > Empty Student List");
            return;
        }
        System.out.println("---Edit Student's Information---");
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();
        try{
            for(int i = 0; i < studentList.size(); i++){
                if(id.equals(studentList.get(i).getStudentID()) == true){
                    studentList.get(i).setName(inputName());
                    studentList.get(i).setAddress(inputAddress());
                    studentList.get(i).setEmail(inputEmail());
                    System.out.println("Update Successfully!!!");
                }if(id.equals(studentList.get(i).getStudentID()) == false){
                    System.out.println("Student ID not founded"); 
                }
            }
        }catch(Exception e){
            System.out.println(" > Invalid id!");
        }
    }

    public void Sort_By_Name(ArrayList<Student> studentList){
        if(studentList == null || studentList.isEmpty() == true){
            System.out.println(" > Empty Student List");
            return;
        }
        System.out.println("---Sort Student by Name---");
        studentList.sort(new SortStudentByName());
        System.out.println("Sort Student Successfully!!!");
        System.out.println("Printed Student!!!");
        for(int i = 0; i < studentList.size(); i++){
            System.out.println(studentList.get(i).toString());
        }
    }
    
    public void print_Student_by_Averange(ArrayList<Student> studentList){
        if(studentList == null || studentList.isEmpty() == true){
            System.out.println(" > Empty Student List");
            return;
        }
        System.out.println("---Sort Student By Averange Range---");
        System.out.print("Enter first Score: ");
        double a = sc.nextDouble();
        System.out.print("Enter second Score: ");
        double b = sc.nextDouble();
        boolean checkAvg = false;
        try {
            for(int i = 0; i < studentList.size(); i++){
                 if(studentList.get(i).averangeScore() > a && studentList.get(i).averangeScore() < b){
                     checkAvg = true;
                     System.out.println(studentList.get(i).toString());
                 }
                 if(checkAvg == false){
                      System.out.println("There are no students in the averange range ");
                 }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void print_Best_Student(ArrayList<Student> studentList){
        if(studentList == null || studentList.isEmpty() == true){
            System.out.println(" > Empty List");
            return;
        }
        System.out.println("---Print 5 Student With Highest Averange---");
        studentList.sort(new SortStudentByAvg());
        System.out.println("Print Successfully!!!");
        if(studentList.size() >= 4){
            for(int i = 0; i < 5; i++){
                System.out.println(studentList.get(i).toString());
            }
        }else{
                System.out.println("Not enough Student in List");
        }
    }
    
    public String input() {
		return sc.nextLine();
	}
    public String inputName() {
	System.out.print("Name: ");
	return sc.nextLine();
    }
    
    public String inputAddress() {
	System.out.print("Address: ");
	return sc.nextLine();
    }
    
    public String inputEmail(){
        System.out.print("Email: ");
        return sc.nextLine();
    }
}