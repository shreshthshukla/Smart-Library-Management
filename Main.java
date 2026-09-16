import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        FileManager.initializeFiles();

        BookManager bookManager =
                new BookManager();

        StudentManager studentManager =
                new StudentManager();

        IssueManager issueManager =
                new IssueManager(
                        bookManager,
                        studentManager
                );

        FineManager fineManager =
                new FineManager();

        boolean running = true;

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "       LIBRARY MANAGEMENT SYSTEM"
        );

        System.out.println(
                "=============================================="
        );

        while (running) {

            System.out.println(
                    "\n============== MAIN MENU =============="
            );

            System.out.println(
                    "1. Book Management"
            );

            System.out.println(
                    "2. Student Management"
            );

            System.out.println(
                    "3. Issue / Return Book"
            );

            System.out.println(
                    "4. Fine Management"
            );

            System.out.println(
                    "5. Reports"
            );

            System.out.println(
                    "6. Exit"
            );

            System.out.println(
                    "========================================"
            );

            System.out.print(
                    "Enter your choice: "
            );

            String input = sc.nextLine();

            int choice;

            try {

                choice = Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid choice. Please enter a number."
                );

                continue;
            }

            switch (choice) {

                case 1:
                    bookMenu(sc, bookManager);
                    break;

                case 2:
                    studentMenu(sc, studentManager);
                    break;

                case 3:
                    issueMenu(sc, issueManager);
                    break;

                case 4:
                    fineMenu(sc, fineManager);
                    break;

                case 5:
                    reportMenu(
                            sc,
                            bookManager,
                            studentManager,
                            issueManager,
                            fineManager
                    );
                    break;

                case 6:

                    System.out.println(
                            "\nThank you for using "
                            + "Library Management System."
                    );

                    running = false;
                    break;

                default:

                    System.out.println(
                            "Invalid choice. Enter 1 to 6."
                    );
            }
        }

        sc.close();
    }

    private static void bookMenu(
            Scanner sc,
            BookManager manager) {

        boolean back = false;

        while (!back) {

            System.out.println(
                    "\n========== BOOK MANAGEMENT =========="
            );

            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Back");

            System.out.print("Enter choice: ");

            String input = sc.nextLine();

            switch (input) {

                case "1":
                    manager.addBook(sc);
                    break;

                case "2":
                    manager.viewBooks();
                    break;

                case "3":
                    manager.searchBook(sc);
                    break;

                case "4":
                    manager.updateBook(sc);
                    break;

                case "5":
                    manager.deleteBook(sc);
                    break;

                case "6":
                    back = true;
                    break;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }

    private static void studentMenu(
            Scanner sc,
            StudentManager manager) {

        boolean back = false;

        while (!back) {

            System.out.println(
                    "\n========= STUDENT MANAGEMENT ========="
            );

            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Back");

            System.out.print("Enter choice: ");

            String input = sc.nextLine();

            switch (input) {

                case "1":
                    manager.addStudent(sc);
                    break;

                case "2":
                    manager.viewStudents();
                    break;

                case "3":
                    manager.searchStudent(sc);
                    break;

                case "4":
                    manager.updateStudent(sc);
                    break;

                case "5":
                    manager.deleteStudent(sc);
                    break;

                case "6":
                    back = true;
                    break;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }

    private static void issueMenu(
            Scanner sc,
            IssueManager manager) {

        boolean back = false;

        while (!back) {

            System.out.println(
                    "\n========== ISSUE / RETURN =========="
            );

            System.out.println("1. Issue Book");
            System.out.println("2. Return Book");
            System.out.println("3. View Issue Records");
            System.out.println("4. Student Borrowing History");
            System.out.println("5. Back");

            System.out.print("Enter choice: ");

            String input = sc.nextLine();

            switch (input) {

                case "1":
                    manager.issueBook(sc);
                    break;

                case "2":
                    manager.returnBook(sc);
                    break;

                case "3":
                    manager.viewIssuedBooks();
                    break;

                case "4":
                    manager.studentHistory(sc);
                    break;

                case "5":
                    back = true;
                    break;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }

    private static void fineMenu(
            Scanner sc,
            FineManager manager) {

        boolean back = false;

        while (!back) {

            System.out.println(
                    "\n========== FINE MANAGEMENT =========="
            );

            System.out.println("1. View All Fines");
            System.out.println("2. View Pending Fines");
            System.out.println("3. Pay Fine");
            System.out.println("4. Back");

            System.out.print("Enter choice: ");

            String input = sc.nextLine();

            switch (input) {

                case "1":
                    manager.viewFines();
                    break;

                case "2":
                    manager.viewPendingFines();
                    break;

                case "3":
                    manager.payFine(sc);
                    break;

                case "4":
                    back = true;
                    break;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }

    private static void reportMenu(
            Scanner sc,
            BookManager bookManager,
            StudentManager studentManager,
            IssueManager issueManager,
            FineManager fineManager) {

        boolean back = false;

        while (!back) {

            System.out.println(
                    "\n============== REPORTS =============="
            );

            System.out.println("1. All Books");
            System.out.println("2. Available Books");
            System.out.println("3. Issued Books");
            System.out.println("4. Students");
            System.out.println("5. Fine Report");
            System.out.println("6. Back");

            System.out.print("Enter choice: ");

            String input = sc.nextLine();

            switch (input) {

                case "1":
                    bookManager.viewBooks();
                    break;

                case "2":
                    showAvailableBooks(bookManager);
                    break;

                case "3":
                    issueManager.viewIssuedBooks();
                    break;

                case "4":
                    studentManager.viewStudents();
                    break;

                case "5":
                    fineManager.viewFines();
                    break;

                case "6":
                    back = true;
                    break;

                default:
                    System.out.println(
                            "Invalid choice."
                    );
            }
        }
    }

    private static void showAvailableBooks(
            BookManager manager) {

        System.out.println(
                "\n========= AVAILABLE BOOKS ========="
        );

        for (int id = 1; id <= 10000; id++) {

            Book book = manager.findBook(id);

            if (book != null &&
                    book.getAvailableCopies() > 0) {

                book.display();
            }
        }
    }
}