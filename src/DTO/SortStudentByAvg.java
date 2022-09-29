package DTO;

import java.util.Comparator;

/**
 *
 * @author PHAM NGUYEN THIEN CHUONG - DE160221
 */
public class SortStudentByAvg implements Comparator<Student> {

    @Override
    public int compare(Student avg1, Student avg2) {
       return avg1.averangeScore() < avg2.averangeScore() ? 1 : -1;
    } 
}
