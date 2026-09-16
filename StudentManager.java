import java.util.*;

public class StudentManager {

    // Add a new student
    public void addStudent(Scanner sc) {

        System.out.println("\n===== ADD STUDENT =====");

        int id = readPositiveInt(sc, "Enter Student ID: ");

        // Make sure the ID is not already in use
        if (findStudent(id) != null) {
            System.out.println("Student ID already exists.");
            return;
        }

        String name = readText(sc, "Enter Student Name: ");
        String course = readText(sc, "Enter Course: ");
        int year = readPositiveInt(sc, "Enter Year: ");
        String contact = readText(sc, "Enter Contact Number: ");

        Student student =
                new Student(id, name, course, year, contact);

        FileManager.appendToFile(
                FileManager.getStudentFile(),
                student.toFileString()
        );

        System.out.println("Student added successfully!");
    }

    // Display all registered students
    public void viewStudents() {

        System.out.println("\n===== ALL STUDENTS =====");

        ArrayList<String> records =
                FileManager.readFile(
                        FileManager.getStudentFile()
                );

        if (records.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (String record : records) {

            Student student = parseStudent(record);

            if (student != null) {
                student.display();
            }
        }
    }

    // Search for a student using their ID
    public void searchStudent(Scanner sc) {

        System.out.println("\n===== SEARCH STUDENT =====");

        int id = readPositiveInt(sc, "Enter Student ID: ");

        Student student = findStudent(id);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            student.display();
        }
    }

    // Update student information
    public void updateStudent(Scanner sc) {

        System.out.println("\n===== UPDATE STUDENT =====");

        int id = readPositiveInt(sc, "Enter Student ID: ");

        ArrayList<String> records =
                FileManager.readFile(
                        FileManager.getStudentFile()
                );

        boolean found = false;

        for (int i = 0; i < records.size(); i++) {

            Student student = parseStudent(records.get(i));

            if (student != null &&
                    student.getStudentId() == id) {

                found = true;

                System.out.println("\nCurrent details:");
                student.display();

                String name =
                        readText(sc, "New Name: ");

                String course =
                        readText(sc, "New Course: ");

                int year =
                        readPositiveInt(sc, "New Year: ");

                String contact =
                        readText(sc, "New Contact: ");

                student.setName(name);
                student.setCourse(course);
                student.setYear(year);
                student.setContact(contact);

                records.set(i, student.toFileString());

                FileManager.writeFile(
                        FileManager.getStudentFile(),
                        records
                );

                System.out.println(
                        "Student details updated successfully!"
                );

                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    // Delete a student
    public void deleteStudent(Scanner sc) {

        System.out.println("\n===== DELETE STUDENT =====");

        int id = readPositiveInt(sc, "Enter Student ID: ");

        ArrayList<String> records =
                FileManager.readFile(
                        FileManager.getStudentFile()
                );

        boolean found = false;

        for (int i = 0; i < records.size(); i++) {

            Student student = parseStudent(records.get(i));

            if (student != null &&
                    student.getStudentId() == id) {

                found = true;

                records.remove(i);

                FileManager.writeFile(
                        FileManager.getStudentFile(),
                        records
                );

                System.out.println(
                        "Student deleted successfully!"
                );

                break;
            }
        }

        if (!found) {
            System.out.println("Student not found.");
        }
    }

    // Find a student using their ID
    public Student findStudent(int id) {

        ArrayList<String> records =
                FileManager.readFile(
                        FileManager.getStudentFile()
                );

        for (String record : records) {

            Student student = parseStudent(record);

            if (student != null &&
                    student.getStudentId() == id) {

                return student;
            }
        }

        return null;
    }

    // Convert a saved text record back into a Student object
    private Student parseStudent(String record) {

        try {

            String[] data = record.split("\\|");

            if (data.length != 5) {
                return null;
            }

            return new Student(
                    Integer.parseInt(data[0]),
                    data[1],
                    data[2],
                    Integer.parseInt(data[3]),
                    data[4]
            );

        } catch (Exception e) {
            return null;
        }
    }

    // Read a positive number from the user
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
                        "Please enter a positive number."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid number. Try again."
                );
            }
        }
    }

    // Read text input and reject empty values
    private String readText(
            Scanner sc,
            String message) {

        while (true) {

            System.out.print(message);

            String value =
                    sc.nextLine().trim();

            if (!value.isEmpty() &&
                    !value.contains("|")) {

                return value;
            }

            System.out.println(
                    "Invalid input. Try again."
            );
        }
    }
}