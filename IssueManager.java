import java.time.LocalDate;
import java.util.*;

public class IssueManager {

    private BookManager bookManager;
    private StudentManager studentManager;

    public IssueManager(BookManager bookManager,
                        StudentManager studentManager) {

        this.bookManager = bookManager;
        this.studentManager = studentManager;
    }

    public void issueBook(Scanner sc) {

        System.out.println("\n===== ISSUE BOOK =====");

        int bookId =
                readPositiveInt(sc, "Enter Book ID: ");

        int studentId =
                readPositiveInt(sc, "Enter Student ID: ");

        Book book = bookManager.findBook(bookId);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        Student student =
                studentManager.findStudent(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        if (book.getAvailableCopies() <= 0) {

            System.out.println(
                    "No copies of this book are available."
            );

            return;
        }

        ArrayList<String> records =
                FileManager.readFile(
                        FileManager.getIssueFile()
                );

        for (String record : records) {

            IssueRecord issue =
                    parseIssue(record);

            if (issue != null &&
                    issue.getBookId() == bookId &&
                    issue.getStudentId() == studentId &&
                    issue.getStatus().equals("ISSUED")) {

                System.out.println(
                        "This student already has this book."
                );

                return;
            }
        }

        int issueId = generateIssueId();

        LocalDate issueDate =
                LocalDate.now();

        LocalDate dueDate =
                issueDate.plusDays(14);

        IssueRecord issue =
                new IssueRecord(
                        issueId,
                        bookId,
                        studentId,
                        issueDate.toString(),
                        dueDate.toString(),
                        "-",
                        "ISSUED"
                );

        FileManager.appendToFile(
                FileManager.getIssueFile(),
                issue.toFileString()
        );

        bookManager.updateBookAvailability(
                bookId,
                -1
        );

        System.out.println(
                "\nBook issued successfully."
        );

        System.out.println(
                "Issue ID : " + issueId
        );

        System.out.println(
                "Due Date : " + dueDate
        );
    }

    public void returnBook(Scanner sc) {

        System.out.println("\n===== RETURN BOOK =====");

        int issueId =
                readPositiveInt(sc, "Enter Issue ID: ");

        ArrayList<String> records =
                FileManager.readFile(
                        FileManager.getIssueFile()
                );

        boolean found = false;

        for (int i = 0; i < records.size(); i++) {

            IssueRecord issue =
                    parseIssue(records.get(i));

            if (issue != null &&
                    issue.getIssueId() == issueId) {

                found = true;

                if (issue.getStatus()
                        .equals("RETURNED")) {

                    System.out.println(
                            "This book has already been returned."
                    );

                    return;
                }

                LocalDate returnDate =
                        LocalDate.now();

                issue.setReturnDate(
                        returnDate.toString()
                );

                issue.setStatus("RETURNED");

                records.set(
                        i,
                        issue.toFileString()
                );

                FileManager.writeFile(
                        FileManager.getIssueFile(),
                        records
                );

                bookManager.updateBookAvailability(
                        issue.getBookId(),
                        1
                );

                long lateDays =
                        java.time.temporal.ChronoUnit.DAYS.between(
                                LocalDate.parse(issue.getDueDate()),
                                returnDate
                        );

                if (lateDays > 0) {

                    double fine =
                            lateDays * 5.0;

                    FineManager.createFine(
                            issue.getIssueId(),
                            issue.getStudentId(),
                            fine
                    );

                    System.out.println(
                            "Book returned late."
                    );

                    System.out.println(
                            "Late Days : " + lateDays
                    );

                    System.out.println(
                            "Fine      : Rs. " + fine
                    );

                } else {

                    System.out.println(
                            "Book returned successfully."
                    );
                }

                break;
            }
        }

        if (!found) {
            System.out.println("Issue record not found.");
        }
    }

    public void viewIssuedBooks() {

        System.out.println("\n===== ISSUE RECORDS =====");

        ArrayList<String> records =
                FileManager.readFile(
                        FileManager.getIssueFile()
                );

        if (records.isEmpty()) {

            System.out.println(
                    "No issue records found."
            );

            return;
        }

        for (String record : records) {

            IssueRecord issue =
                    parseIssue(record);

            if (issue != null) {
                issue.display();
            }
        }
    }

    public void studentHistory(Scanner sc) {

        System.out.println(
                "\n===== STUDENT BORROWING HISTORY ====="
        );

        int studentId =
                readPositiveInt(
                        sc,
                        "Enter Student ID: "
                );

        ArrayList<String> records =
                FileManager.readFile(
                        FileManager.getIssueFile()
                );

        boolean found = false;

        for (String record : records) {

            IssueRecord issue =
                    parseIssue(record);

            if (issue != null &&
                    issue.getStudentId() == studentId) {

                issue.display();
                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No borrowing history found."
            );
        }
    }

    private int generateIssueId() {

        ArrayList<String> records =
                FileManager.readFile(
                        FileManager.getIssueFile()
                );

        int max = 0;

        for (String record : records) {

            IssueRecord issue =
                    parseIssue(record);

            if (issue != null &&
                    issue.getIssueId() > max) {

                max = issue.getIssueId();
            }
        }

        return max + 1;
    }

    private IssueRecord parseIssue(String record) {

        try {

            String[] data =
                    record.split("\\|");

            if (data.length != 7) {
                return null;
            }

            return new IssueRecord(
                    Integer.parseInt(data[0]),
                    Integer.parseInt(data[1]),
                    Integer.parseInt(data[2]),
                    data[3],
                    data[4],
                    data[5],
                    data[6]
            );

        } catch (Exception e) {

            return null;
        }
    }

    private int readPositiveInt(
            Scanner sc,
            String message) {

        while (true) {

            System.out.print(message);

            try {

                int value =
                        Integer.parseInt(sc.nextLine());

                if (value > 0) {
                    return value;
                }

                System.out.println(
                        "Enter a positive number."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid number."
                );
            }
        }
    }
}