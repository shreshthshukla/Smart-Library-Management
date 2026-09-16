import java.util.*;

public class FineManager {

    public static void createFine(int issueId,
                                  int studentId,
                                  double amount) {

        int fineId = generateFineId();

        Fine fine =
                new Fine(
                        fineId,
                        issueId,
                        studentId,
                        amount,
                        "PENDING"
                );

        FileManager.appendToFile(
                FileManager.getFineFile(),
                fine.toFileString()
        );
    }

    public void viewFines() {

        System.out.println("\n===== FINE RECORDS =====");

        ArrayList<String> records =
                FileManager.readFile(
                        FileManager.getFineFile()
                );

        if (records.isEmpty()) {

            System.out.println(
                    "No fine records found."
            );

            return;
        }

        for (String record : records) {

            Fine fine = parseFine(record);

            if (fine != null) {
                fine.display();
            }
        }
    }

    public void viewPendingFines() {

        System.out.println(
                "\n===== PENDING FINES ====="
        );

        ArrayList<String> records =
                FileManager.readFile(
                        FileManager.getFineFile()
                );

        boolean found = false;

        for (String record : records) {

            Fine fine = parseFine(record);

            if (fine != null &&
                    fine.getStatus().equals("PENDING")) {

                fine.display();
                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No pending fines."
            );
        }
    }

    public void payFine(Scanner sc) {

        System.out.println("\n===== PAY FINE =====");

        int fineId =
                readPositiveInt(
                        sc,
                        "Enter Fine ID: "
                );

        ArrayList<String> records =
                FileManager.readFile(
                        FileManager.getFineFile()
                );

        boolean found = false;

        for (int i = 0; i < records.size(); i++) {

            Fine fine =
                    parseFine(records.get(i));

            if (fine != null &&
                    fine.getFineId() == fineId) {

                found = true;

                if (fine.getStatus()
                        .equals("PAID")) {

                    System.out.println(
                            "Fine is already paid."
                    );

                    return;
                }

                fine.setStatus("PAID");

                records.set(
                        i,
                        fine.toFileString()
                );

                FileManager.writeFile(
                        FileManager.getFineFile(),
                        records
                );

                System.out.println(
                        "Fine paid successfully."
                );

                break;
            }
        }

        if (!found) {

            System.out.println(
                    "Fine not found."
            );
        }
    }

    private static int generateFineId() {

        ArrayList<String> records =
                FileManager.readFile(
                        FileManager.getFineFile()
                );

        int max = 0;

        for (String record : records) {

            Fine fine = parseFineStatic(record);

            if (fine != null &&
                    fine.getFineId() > max) {

                max = fine.getFineId();
            }
        }

        return max + 1;
    }

    private Fine parseFine(String record) {

        return parseFineStatic(record);
    }

    private static Fine parseFineStatic(
            String record) {

        try {

            String[] data =
                    record.split("\\|");

            if (data.length != 5) {
                return null;
            }

            return new Fine(
                    Integer.parseInt(data[0]),
                    Integer.parseInt(data[1]),
                    Integer.parseInt(data[2]),
                    Double.parseDouble(data[3]),
                    data[4]
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