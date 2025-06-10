import java.io.*;
import java.util.*;

public class Library {
    private List<Book> books = new ArrayList<>();
    private final String BOOKS_FILE = "data/books.txt";
    private final String ISSUED_FILE = "data/issued_books.txt";

    public Library() {
        loadBooks();
    }

    private void loadBooks() {
        books.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Book book = new Book(parts[0], parts[1], parts[2]);
                    book.setIssued(Boolean.parseBoolean(parts[3]));
                    books.add(book);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }

    public void saveBooks() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            for (Book book : books) {
                pw.println(String.join(",", book.getId(), book.getTitle(), book.getAuthor(), String.valueOf(book.isIssued())));
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooks();
    }

    public boolean removeBook(String id) {
        Iterator<Book> it = books.iterator();
        while (it.hasNext()) {
            Book b = it.next();
            if (b.getId().equals(id)) {
                it.remove();
                saveBooks();
                return true;
            }
        }
        return false;
    }

    public boolean issueBook(String id) {
        for (Book b : books) {
            if (b.getId().equals(id) && !b.isIssued()) {
                b.setIssued(true);
                saveBooks();
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(String id) {
        for (Book b : books) {
            if (b.getId().equals(id) && b.isIssued()) {
                b.setIssued(false);
                saveBooks();
                return true;
            }
        }
        return false;
    }

    public List<Book> searchBook(String keyword) {
        List<Book> results = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                b.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(b);
            }
        }
        return results;
    }

    public List<Book> getAllBooks() {
        return books;
    }
}
