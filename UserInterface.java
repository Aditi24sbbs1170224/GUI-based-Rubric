import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Library library = new Library();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addBook();
                case "2" -> removeBook();
                case "3" -> issueBook();
                case "4" -> returnBook();
                case "5" -> searchBook();
                case "6" -> showAllBooks();
                case "0" -> {
                    System.out.println("Exiting program.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void printMenu() {
        System.out.println("
--- Library Management System ---");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Issue Book");
        System.out.println("4. Return Book");
        System.out.println("5. Search Book");
        System.out.println("6. View All Books");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private void addBook() {
        System.out.print("Enter book ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        library.addBook(new Book(id, title, author));
        System.out.println("Book added successfully.");
    }

    private void removeBook() {
        System.out.print("Enter book ID to remove: ");
        String id = scanner.nextLine();
        if (library.removeBook(id)) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private void issueBook() {
        System.out.print("Enter book ID to issue: ");
        String id = scanner.nextLine();
        if (library.issueBook(id)) {
            System.out.println("Book issued.");
        } else {
            System.out.println("Book not found or already issued.");
        }
    }

    private void returnBook() {
        System.out.print("Enter book ID to return: ");
        String id = scanner.nextLine();
        if (library.returnBook(id)) {
            System.out.println("Book returned.");
        } else {
            System.out.println("Book not found or not issued.");
        }
    }

    private void searchBook() {
        System.out.print("Enter title or author to search: ");
        String keyword = scanner.nextLine();
        List<Book> results = library.searchBook(keyword);
        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book b : results) {
                System.out.println(b);
            }
        }
    }

    private void showAllBooks() {
        List<Book> books = library.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book b : books) {
                System.out.println(b);
            }
        }
    }
}
