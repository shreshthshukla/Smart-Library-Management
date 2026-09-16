public class Fine {

    private int fineId;
    private int issueId;
    private int studentId;
    private double amount;
    private String status;

    public Fine(int fineId, int issueId, int studentId,
                double amount, String status) {

        this.fineId = fineId;
        this.issueId = issueId;
        this.studentId = studentId;
        this.amount = amount;
        this.status = status;
    }

    public int getFineId() {
        return fineId;
    }

    public int getIssueId() {
        return issueId;
    }

    public int getStudentId() {
        return studentId;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toFileString() {
        return fineId + "|" + issueId + "|" + studentId +
               "|" + amount + "|" + status;
    }

    public void display() {
        System.out.println("----------------------------------------");
        System.out.println("Fine ID    : " + fineId);
        System.out.println("Issue ID   : " + issueId);
        System.out.println("Student ID : " + studentId);
        System.out.println("Amount     : Rs. " + amount);
        System.out.println("Status     : " + status);
        System.out.println("----------------------------------------");
    }
}