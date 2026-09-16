public class Book {

    private int bookId;
    private String title;
    private String author;
    private String category;
    private int totalCopies;
    private int availableCopies;

    // Constructor
    public Book(int bookId, String title, String author, String category,
                int totalCopies, int availableCopies) {

        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    // Getters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    // Converts book details into a format that can be saved in the file
    public String toFileString() {
        return bookId + "|" + title + "|" + author + "|" +
               category + "|" + totalCopies + "|" + availableCopies;
    }

    // Displays the book details
    public void display() {
        System.out.println("----------------------------------------");
        System.out.println("Book ID      : " + bookId);
        System.out.println("Title        : " + title);
        System.out.println("Author       : " + author);
        System.out.println("Category     : " + category);
        System.out.println("Total Copies : " + totalCopies);
        System.out.println("Available    : " + availableCopies);
        System.out.println("----------------------------------------");
    }
}
