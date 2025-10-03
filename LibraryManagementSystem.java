import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Book class
class Book {
    int id;
    String title;
    String author;
    boolean isAvailable;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Title: " + title + " | Author: " + author + " | Available: " + isAvailable;
    }
}

// Member class
class Member {
    int id;
    String name;

    Member(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name;
    }
}

// Main LMS class
public class LibraryManagementSystem {
    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<Member> members = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by ID");
            System.out.println("4. Delete Book by ID");
            System.out.println("5. Sort Books by Title");
            System.out.println("6. Add Member");
            System.out.println("7. View All Members");
            System.out.println("8. Borrow Book");
            System.out.println("9. Return Book");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> searchBook();
                case 4 -> deleteBook();
                case 5 -> sortBooks();
                case 6 -> addMember();
                case 7 -> viewMembers();
                case 8 -> borrowBook();
                case 9 -> returnBook();
                case 10 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 10);
    }

    // Book Methods
    static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Book Author: ");
        String author = sc.nextLine();
        books.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\nAll Books:");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    static void searchBook() {
        System.out.print("Enter Book ID to search: ");
        int id = sc.nextInt();
        for (Book b : books) {
            if (b.id == id) {
                System.out.println("Book Found: " + b);
                return;
            }
        }
        System.out.println("Book not found!");
    }

    static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        int id = sc.nextInt();
        for (Book b : books) {
            if (b.id == id) {
                books.remove(b);
                System.out.println("Book deleted successfully!");
                return;
            }
        }
        System.out.println("Book not found!");
    }

    static void sortBooks() {
        Collections.sort(books, (b1, b2) -> b1.title.compareToIgnoreCase(b2.title));
        System.out.println("Books sorted by title!");
    }

    // Member Methods
    static void addMember() {
        System.out.print("Enter Member ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Member Name: ");
        String name = sc.nextLine();
        members.add(new Member(id, name));
        System.out.println("Member added successfully!");
    }

    static void viewMembers() {
        if (members.isEmpty()) {
            System.out.println("No members available.");
            return;
        }
        System.out.println("\nAll Members:");
        for (Member m : members) {
            System.out.println(m);
        }
    }

    // Borrow/Return Methods
    static void borrowBook() {
        System.out.print("Enter Book ID to borrow: ");
        int bookId = sc.nextInt();
        System.out.print("Enter Member ID: ");
        int memberId = sc.nextInt();

        Book book = null;
        for (Book b : books) if (b.id == bookId) book = b;
        Member member = null;
        for (Member m : members) if (m.id == memberId) member = m;

        if (book == null) System.out.println("Book not found!");
        else if (member == null) System.out.println("Member not found!");
        else if (!book.isAvailable) System.out.println("Book is already borrowed!");
        else {
            book.isAvailable = false;
            System.out.println("Book borrowed successfully by " + member.name);
        }
    }

    static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int bookId = sc.nextInt();

        for (Book b : books) {
            if (b.id == bookId) {
                if (b.isAvailable) {
                    System.out.println("This book was not borrowed.");
                    return;
                } else {
                    b.isAvailable = true;
                    System.out.println("Book returned successfully!");
                    return;
                }
            }
        }
        System.out.println("Book not found!");
    }
}

