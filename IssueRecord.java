public class IssueRecord {

    private int issueId;
    private int bookId;
    private int studentId;
    private String issueDate;
    private String dueDate;
    private String returnDate;
    private String status;

    public IssueRecord(int issueId, int bookId, int studentId,
                       String issueDate, String dueDate,
                       String returnDate, String status) {

        this.issueId = issueId;
        this.bookId = bookId;
        this.studentId = studentId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public int getIssueId() {
        return issueId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toFileString() {
        return issueId + "|" + bookId + "|" + studentId + "|" +
               issueDate + "|" + dueDate + "|" + returnDate + "|" + status;
    }

    public void display() {
        System.out.println("----------------------------------------");
        System.out.println("Issue ID    : " + issueId);
        System.out.println("Book ID     : " + bookId);
        System.out.println("Student ID  : " + studentId);
        System.out.println("Issue Date  : " + issueDate);
        System.out.println("Due Date    : " + dueDate);
        System.out.println("Return Date : " + returnDate);
        System.out.println("Status      : " + status);
        System.out.println("----------------------------------------");
    }
}