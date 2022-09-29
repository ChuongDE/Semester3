package DTO;


/**
 *
 * @author PHAM NGUYEN THIEN CHUONG - DE160221
 */
public class ITStudent extends Student{
    private double JavaScore;
    private double CssScore;
    
    public ITStudent(){}
    public ITStudent(String name, String address, String email, String studentID, double JavaScore, double CssScore) {
        super(name, address, email, studentID);
        this.JavaScore = JavaScore;
        this.CssScore = CssScore;
    }

    public double getJavaScore() {
        return JavaScore;
    }

    public void setJavaScore(double JavaScore) {
        this.JavaScore = JavaScore;
    }

    public double getCssScore() {
        return CssScore;
    }

    public void setCssScore(double CssScore) {
        this.CssScore = CssScore;
    }
    
    @Override
    public double averangeScore() {
        return (JavaScore * 3 + CssScore) / 4;
    }
    
    @Override
    public String toString() {
        return "\n\t\t\t<IT Student>\n" + super.toString() + "Score of IT Student\n" + "Java Score: " + JavaScore + "\nCss Score: " + CssScore + "\nAverange Score: " + averangeScore() + "\n:0:";
    }

    
    
    
}
