package DTO;

import java.util.Comparator;

/**
 *
 * @author PHAM NGUYEN THIEN CHUONG - DE160221
 */
public class SortStudentByName implements Comparator<Student> {

    @Override
    public int compare(Student st1, Student st2) {
        return st2.getName().compareTo(st1.getName());
    }
    
}
