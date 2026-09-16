public class Student {

    private int studentId;
    private String name;
    private String course;
    private int year;
    private String contact;

    // Constructor
    public Student(int studentId, String name, String course,
                   int year, String contact) {

        this.studentId = studentId;
        this.name = name;
        this.course = course;
        this.year = year;
        this.contact = contact;
    }

    // Getters
    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getYear() {
        return year;
    }

    public String getContact() {
        return contact;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // Convert student details into a line for the text file
    public String toFileString() {
        return studentId + "|" + name + "|" + course + "|" +
               year + "|" + contact;
    }

    // Display student details
    public void display() {
        System.out.println("----------------------------------------");
        System.out.println("Student ID : " + studentId);
        System.out.println("Name       : " + name);
        System.out.println("Course     : " + course);
        System.out.println("Year       : " + year);
        System.out.println("Contact    : " + contact);
        System.out.println("----------------------------------------");
    }
}
