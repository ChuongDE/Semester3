package GUI;

import CTL.StudentManager;
import CTL.StudentFileIO;
import DTO.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 *
 * @author PHAM NGUYEN THIEN CHUONG - DE160221
 */
public class Menu {
    static ArrayList<Student> studentList = new ArrayList<>();
        
    public static int GetUserChoice(){
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.println("--------------------------STUDENT MANAGER--------------------------");
        System.out.println("|         [1] Enter Information of Student                        |");
        System.out.println("|         [2] Print out the list of Student                       |");
        System.out.println("|         [3] Search Student by address                           |");
        System.out.println("|         [4] Delete Student by ID                                |");
        System.out.println("|         [5] Update Student's Information by ID                  |");
        System.out.println("|         [6] Sort list of Students by name                       |");
        System.out.println("|         [7] Print list of student by averange score             |");
        System.out.println("|         [8] Print 5 student have the highest averange score     |");
        System.out.println("|         [9] Save Student's Information to file                  |");
        System.out.println("|         [10] Read file Student's Information                    |");
        System.out.println("------------------------------------------------------------------");
        System.out.print("Enter your Choice: ");
        choice = sc.nextInt();
        return choice;
    }
    
    public static void main(String[] args) {
        int getChoice;
        StudentManager stuM = new StudentManager();
        StudentFileIO stuIO = new StudentFileIO();
        boolean writeToFile = false;
        do{
            getChoice = GetUserChoice();
            switch(getChoice){
                case 1:
                    System.out.println("\nSuccessful Selection!");  
                    stuM.Add_Student_Info(studentList);
                    writeToFile = true;
                    break;
                case 2:
                    System.out.println("\nSuccessful Selection!");
                    stuM.print_Data(studentList, 0);
                    break;
                case 3:
                    System.out.println("\nSuccessful Selection!");
                    stuM.find_Student_by_Address(studentList);
                    break;
                case 4:
                    System.out.println("\nSuccessful Selection!");
                    stuM.delete_Student_by_ID(studentList);
                    break;
                case 5:
                    System.out.println("\nSuccessful Selection!");
                    stuM.edit_Student_by_ID(studentList);
                    break;
                case 6:
                    System.out.println("\nSuccessful Selection!");
                    stuM.Sort_By_Name(studentList);
                    break;
                case 7:
                    System.out.println("\nSuccessful Selection!");
                    stuM.print_Student_by_Averange(studentList);
                    break;
                case 8:
                    System.out.println("\nSuccessful Selection!");
                    stuM.print_Best_Student(studentList);
                    break;
                case 9:
                    System.out.println("\nSuccessful Selection!");
                    if(studentList.isEmpty() == true || writeToFile == false) System.out.println("No [DATA] to SAVE");
                    else {
                        stuIO.input_To_File(studentList);
                        writeToFile = false;
                    }
                    break;
                case 10:
                    System.out.println("\nSuccessful Selection!");
                    stuIO.read_Data_From_File(studentList);
                    break;
            }
        }while((getChoice > 0) && (getChoice < 11));  
    }
}