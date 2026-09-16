import java.io.*;
import java.util.*;

public class FileManager {

    private static final String DATA_FOLDER = "data";

    private static final String BOOK_FILE =
            DATA_FOLDER + File.separator + "books.txt";

    private static final String STUDENT_FILE =
            DATA_FOLDER + File.separator + "students.txt";

    private static final String ISSUE_FILE =
            DATA_FOLDER + File.separator + "issue_records.txt";

    private static final String FINE_FILE =
            DATA_FOLDER + File.separator + "fines.txt";

    public static void initializeFiles() {

        File folder = new File(DATA_FOLDER);

        if (!folder.exists()) {
            folder.mkdir();
        }

        createFile(BOOK_FILE);
        createFile(STUDENT_FILE);
        createFile(ISSUE_FILE);
        createFile(FINE_FILE);
    }

    private static void createFile(String fileName) {

        try {
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (IOException e) {
            System.out.println("Error creating file: " + fileName);
        }
    }

    public static ArrayList<String> readFile(String fileName) {

        ArrayList<String> records = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (!line.trim().isEmpty()) {
                    records.add(line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        return records;
    }

    public static void writeFile(String fileName,
                                 ArrayList<String> records) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(fileName))) {

            for (String record : records) {
                writer.write(record);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    public static void appendToFile(String fileName,
                                    String record) {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(fileName, true))) {

            writer.write(record);
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    public static String getBookFile() {
        return BOOK_FILE;
    }

    public static String getStudentFile() {
        return STUDENT_FILE;
    }

    public static String getIssueFile() {
        return ISSUE_FILE;
    }

    public static String getFineFile() {
        return FINE_FILE;
    }
}