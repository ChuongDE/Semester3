package CTL;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import DTO.Student;

/**
 *
 * @author PHAM NGUYEN THIEN CHUONG - DE160221
 */
public class Validation {
    static Scanner input = new Scanner(System.in);

    public Validation() {
    }

    public int checkNull(String test) {
        if (test.length() == 0) {
            System.err.println("This part cannot be empty");
            System.out.print("Please enter again: ");
            return 0;
        } else
            return 1;
    }

    public int checkNumber(String test, boolean isInteger) {
        if (checkNull(test) == 0)
            return 0;
        Pattern numberPattern;
        if (isInteger)
            numberPattern = Pattern.compile("^[+-]{0,1}[0-9]{0,}$");
        else
            numberPattern = Pattern.compile("^[+-]{0,1}[0-9]{0,}[.]{0,1}[0-9]{0,}$");
        Matcher numberMatcher = numberPattern.matcher(test);
        boolean b = numberMatcher.matches();
        if (!b) {
            System.err.println("Your input is not a number");
            System.out.print("Please enter again: ");
            return 0;
        }
        return 1;
    }

    public int checkName(String name) {
        if (checkNull(name) == 0)
            return 0;
        Pattern namePattern = Pattern.compile("^[\\pL\\ ]+$");
        Matcher namerMatcher = namePattern.matcher(name);
        boolean b = namerMatcher.matches();
        if (!b) {
            System.err.println("The input name is not valid");
            System.out.print("Please enter again: ");
            return 0;
        }
        for (int i = 0; i < name.length() - 1; i++) {
            if (name.charAt(i) == ' ' && name.charAt(i + 1) == ' ') {
                System.err.println("The input name is not valid");
                return 0;
            }
        }
        return 1;
    }

    public int similarName(String thisName, String cmpName) {
        thisName = thisName.toLowerCase();
        cmpName = cmpName.toLowerCase();
        if (thisName.compareTo(cmpName) == 0)
            return 1;
        return 0;
    }

    public int checkEmail(String email) {
        if (checkNull(email) == 0)
            return 0;
        Pattern namePattern = Pattern.compile("^[a-z]+[0-9]{0,}[.]{0,1}[a-z]+[0-9]{0,}[@]{1}[a-z.]+$");
        Matcher namerMatcher = namePattern.matcher(email);
        boolean b = namerMatcher.matches();
        if (!b) {
            System.err.println("The input email is not valid");
            System.out.print("Please enter again: ");
            return 0;
        }
        for (int i = 0; i < email.length() - 1; i++) {
            if (email.charAt(i) == '.' && (email.charAt(i + 1) == '.' || email.charAt(i - 1) == '@')) {
                System.err.println("The input email is not valid");
                System.out.print("Please enter again: ");
                return 0;
            }
        }
        return 1;
    }

    public int checkStudentID(String studentID) {
        if (checkNull(studentID) == 0)
            return 0;
        else {
            Pattern IDPattern = Pattern.compile("^[A-Za-z0]{2}[0-9]{6}$");
            Matcher IDMatcher = IDPattern.matcher(studentID);
            boolean b = IDMatcher.matches();
            // If b = false => !b = true
            if (!b) {
                System.err.println("StudentID is not valid!");
                System.out.print("Please enter again: ");
                return 0;
            }
        }
        return 1;
    }

    public int similarStudentID(ArrayList<Student> list, String cmpStuID) {
        String thisMSSV;
        cmpStuID = cmpStuID.toLowerCase();
        for (int i = 0; i < list.size(); i++) {
            thisMSSV = list.get(i).getStudentID().toLowerCase();
            if (thisMSSV.compareTo(cmpStuID) == 0) {
                System.err.println("The input StudentID is already exist!");
                System.err.println("[" + list.get(i).getStudentID() + "] " + list.get(i).getName());
                return 1;
            }
        }
        return 0;
    }

    public int checkGrade(String grade) {
        if (checkNull(grade) == 0 || checkNumber(grade, false) == 0)
            return 0;
        double resGrade = Double.parseDouble(grade);
        if (resGrade >= 0.0d && resGrade <= 10.0d)
            return 1;
        System.err.println("The grade must in range [0,10]");
        System.out.print("Please enter again: ");
        return 0;
    }
}
