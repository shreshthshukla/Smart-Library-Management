import java.util.*;

public class BookManager {

    // Add a new book
    public void addBook(Scanner sc) {

        System.out.println("\n===== ADD BOOK =====");

        int id = readPositiveInt(sc, "Enter Book ID: ");

        // Check if the ID is already being used
        if (findBook(id) != null) {
            System.out.println("Book ID already exists.");
            return;
        }

        String title = readText(sc, "Enter Book Title: ");
        String author = readText(sc, "Enter Author: ");
        String category = readText(sc, "Enter Category: ");

        int copies = readPositiveInt(sc, "Enter Number of Copies: ");

        Book book = new Book(
                id,
                title,
                author,
                category,
                copies,
                copies
        );

        FileManager.appendToFile(
                FileManager.getBookFile(),
                book.toFileString()
        );

        System.out.println("Book added successfully!");
    }

    // Display all books
    public void viewBooks() {

        System.out.println("\n===== ALL BOOKS =====");

        ArrayList<String> records =
                FileManager.readFile(FileManager.getBookFile());

        if (records.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        for (String record : records) {

            Book book = parseBook(record);

            if (book != null) {
                book.display();
            }
        }
    }

    // Search for a book by ID, title or author
    public void searchBook(Scanner sc) {

        System.out.println("\n===== SEARCH BOOK =====");

        System.out.println("1. Search by ID");
        System.out.println("2. Search by Title");
        System.out.println("3. Search by Author");

        int choice = readPositiveInt(sc, "Enter choice: ");

        ArrayList<String> records =
                FileManager.readFile(FileManager.getBookFile());

        boolean found = false;

        if (choice == 1) {

            int id = readPositiveInt(sc, "Enter Book ID: ");

            for (String record : records) {

                Book book = parseBook(record);

                if (book != null && book.getBookId() == id) {
                    book.display();
                    found = true;
                }
            }

        } else if (choice == 2) {

            String title = readText(sc, "Enter title: ");

            for (String record : records) {

                Book book = parseBook(record);

                if (book != null &&
                        book.getTitle().toLowerCase()
                                .contains(title.toLowerCase())) {

                    book.display();
                    found = true;
                }
            }

        } else if (choice == 3) {

            String author = readText(sc, "Enter author: ");

            for (String record : records) {

                Book book = parseBook(record);

                if (book != null &&
                        book.getAuthor().toLowerCase()
                                .contains(author.toLowerCase())) {

                    book.display();
                    found = true;
                }
            }

        } else {
            System.out.println("Invalid choice.");
            return;
        }

        if (!found) {
            System.out.println("No matching book found.");
        }
    }

    // Update the details of an existing book
    public void updateBook(Scanner sc) {

        System.out.println("\n===== UPDATE BOOK =====");

        int id = readPositiveInt(sc, "Enter Book ID: ");

        ArrayList<String> records =
                FileManager.readFile(FileManager.getBookFile());

        boolean found = false;

        for (int i = 0; i < records.size(); i++) {

            Book book = parseBook(records.get(i));

            if (book != null && book.getBookId() == id) {

                found = true;

                System.out.println("\nCurrent details:");
                book.display();

                String title = readText(sc, "New Title: ");
                String author = readText(sc, "New Author: ");
                String category = readText(sc, "New Category: ");

                int newTotal =
                        readPositiveInt(sc, "New Total Copies: ");

                // Find how many copies are currently issued
                int issued =
                        book.getTotalCopies() -
                        book.getAvailableCopies();

                if (newTotal < issued) {

                    System.out.println(
                            "Total copies cannot be less than issued copies."
                    );

                    return;
                }

                book.setTitle(title);
                book.setAuthor(author);
                book.setCategory(category);
                book.setTotalCopies(newTotal);
                book.setAvailableCopies(newTotal - issued);

                records.set(i, book.toFileString());

                FileManager.writeFile(
                        FileManager.getBookFile(),
                        records
                );

                System.out.println("Book updated successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    // Delete a book
    public void deleteBook(Scanner sc) {

        System.out.println("\n===== DELETE BOOK =====");

        int id = readPositiveInt(sc, "Enter Book ID: ");

        ArrayList<String> records =
                FileManager.readFile(FileManager.getBookFile());

        boolean found = false;

        for (int i = 0; i < records.size(); i++) {

            Book book = parseBook(records.get(i));

            if (book != null && book.getBookId() == id) {

                found = true;

                // A book cannot be deleted while it is issued
                if (book.getAvailableCopies()
                        != book.getTotalCopies()) {

                    System.out.println(
                            "Cannot delete this book because "
                            + "some copies are currently issued."
                    );

                    return;
                }

                records.remove(i);

                FileManager.writeFile(
                        FileManager.getBookFile(),
                        records
                );

                System.out.println("Book deleted successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    // Find a book using its ID
    public Book findBook(int id) {

        ArrayList<String> records =
                FileManager.readFile(FileManager.getBookFile());

        for (String record : records) {

            Book book = parseBook(record);

            if (book != null && book.getBookId() == id) {
                return book;
            }
        }

        return null;
    }

    // Change the number of available copies
    // change = -1 when a book is issued
    // change = +1 when a book is returned
    public void updateBookAvailability(int bookId, int change) {

        ArrayList<String> records =
                FileManager.readFile(FileManager.getBookFile());

        for (int i = 0; i < records.size(); i++) {

            Book book = parseBook(records.get(i));

            if (book != null &&
                    book.getBookId() == bookId) {

                int available =
                        book.getAvailableCopies() + change;

                // Don't allow an invalid number of available copies
                if (available < 0 ||
                        available > book.getTotalCopies()) {
                    return;
                }

                book.setAvailableCopies(available);

                records.set(i, book.toFileString());

                FileManager.writeFile(
                        FileManager.getBookFile(),
                        records
                );

                return;
            }
        }
    }

    // Convert a saved text record back into a Book object
    private Book parseBook(String record) {

        try {

            String[] data = record.split("\\|");

            if (data.length != 6) {
                return null;
            }

            return new Book(
                    Integer.parseInt(data[0]),
                    data[1],
                    data[2],
                    data[3],
                    Integer.parseInt(data[4]),
                    Integer.parseInt(data[5])
            );

        } catch (Exception e) {
            return null;
        }
    }

    // Read a positive integer from the user
    private int readPositiveInt(Scanner sc, String message) {

        while (true) {

            System.out.print(message);

            try {

                int value = Integer.parseInt(sc.nextLine());

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

    // Read text input and make sure it is not empty
    private String readText(Scanner sc, String message) {

        while (true) {

            System.out.print(message);

            String value = sc.nextLine().trim();

            if (!value.isEmpty() &&
                    !value.contains("|")) {

                return value;
            }

            System.out.println(
                    "Input cannot be empty or contain |."
            );
        }
    }
}
