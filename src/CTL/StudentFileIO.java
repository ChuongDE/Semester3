
package CTL;

import DTO.*;
import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PHAM NGUYEN THIEN CHUONG - DE160221
 */
public class StudentFileIO {
    private File dataFile = null;
    private FileOutputStream fileOutput;
    private OutputStreamWriter outputStream;
    private PrintWriter theWriter;
    private int IT = 0, biz = 0;
    
    
    private String[] parse_Data(String s) {
	String[] retString = new String[8];
	int startPos = 0, endPos = 0;
	for (int i = 0, j = 0; i < s.length(); i++) {
            if (endPos == 0 && s.charAt(i) == ':') {
                endPos = i;
                retString[j++] = s.substring(startPos, endPos);
            }else if(s.charAt(i) == ':') {
                startPos = endPos+1;
                endPos = i;
		retString[j++] = s.substring(startPos, endPos);
            }
        }
        return retString;
    }
    
    private File create_File() {
        File file;
        try {
            file = new File("export.txt ");
            if(file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                return file;
            } else {
                System.out.println("File already exists.");
                return file;
            }
        } catch (Exception e) {
            System.out.println("An error occurred");
        }
        return null;
    }
    
    public int input_To_File(ArrayList<Student> list) {
        try {
            File file = new File("student.txt");
            if (file.exists()) this.dataFile = file;
            else this.dataFile = create_File();
            fileOutput = new FileOutputStream(this.dataFile, true);
            outputStream = new OutputStreamWriter(fileOutput);
            theWriter = new PrintWriter(outputStream);
            for (int i = 0; i < list.size(); i++) {
                String data = list.get(i).toString();
                theWriter.append(data + "\n");
                theWriter.flush();
            }
            fileOutput.close();
            outputStream.close();
            outputStream.close();
            System.out.println("Data save success");
            return 1;
        } catch (IOException e) {
            System.out.println("Something went wrong when saving data");
        }
        return 0;
    }
    
    private File get_Data_From_File() {
        dataFile = new File("import.txt");
        if (dataFile.exists()) return this.dataFile;
        return this.dataFile;
    }
    
    public void read_Data_From_File(ArrayList<Student> studentList) {
        String data[];
        File file = get_Data_From_File();
	try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                data = parse_Data(fileReader.nextLine());
                if(data[7].compareTo("0") == 0) System.out.print("IT Student: ");
                else System.out.print("Business Student: ");
                System.out.print("Name: " + data[0]);
		System.out.print(", MSSV: " + data[1]);
		System.out.print(", Email: " + data[2]);
                System.out.print(", Address: " + data[3]);
                System.out.printf(", Java/Accounting Score: %.2f", Double.parseDouble(data[4]));
                System.out.printf(", Css/Maketing Score: %.2f", Double.parseDouble(data[5]));
                System.out.printf(", Averange Score: %.2f\n", Double.parseDouble(data[6]));
		if(reinstall_The_List(studentList, data) == 0) IT++;
                else biz++;
            }
            fileReader.close();
        } catch (NullPointerException | FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }
    
    private int reinstall_The_List(ArrayList<Student> studentList, String data[]){
        int majorID;
        Student student;
        if(data[7].compareTo("0") == 0) {
            student = new ITStudent();
            majorID = 0;
            ((ITStudent)student).setJavaScore(Double.parseDouble(data[4]));
            ((ITStudent)student).setCssScore(Double.parseDouble(data[5]));
        }else {
            student = new BizStudent();
            majorID = 1;
            ((BizStudent)student).setAccountingScore(Double.parseDouble(data[4]));
            ((BizStudent)student).setMaketingScore(Double.parseDouble(data[5]));
        }
        student.setName(data[0]);
        student.setStudentID(data[1]);
        student.setEmail(data[2]);
        student.setAddress(data[3]);
        studentList.add(student);
        return majorID;
    }    
}
