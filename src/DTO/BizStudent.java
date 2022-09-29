package DTO;

/**
 *
 * @author PHAM NGUYEN THIEN CHUONG - DE160221
 */
public class BizStudent extends Student{
    private double MaketingScore;
    private double AccountingScore;

    public BizStudent(){}
    public BizStudent(String name, String address, String email, String studentID, double MaketingScore, double AccountingScore) {
        super(name, address, email, studentID);
        this.MaketingScore = MaketingScore;
        this.AccountingScore = AccountingScore;
    }
        

    public double getMaketingScore() {
        return MaketingScore;
    }

    public void setMaketingScore(double MaketingScore) {
        this.MaketingScore = MaketingScore;
    }

    public double getAccountingScore() {
        return AccountingScore;
    }

    public void setAccountingScore(double AccountingScore) {
        this.AccountingScore = AccountingScore;
    }

    @Override
    public double averangeScore() {
        return (AccountingScore * 2 + MaketingScore) / 3;
    }
    
    @Override
    public String toString() {
        return "\n\t\t\t<Biz Student>\n" + super.toString() + "Score of Biz Student\n" + "Maketing Score: " + MaketingScore + "\nAccounting Score: " + AccountingScore + "\nAverange Score: " + averangeScore() + "\n:1:";
    }
    
    
}
