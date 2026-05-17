import java.util.ArrayList;
import java.util.Scanner;

// =============================================
//              BOOK CLASS
// =============================================
class Book {
    String bookId;
    String title;
    String author;
    String genre;
    int year;
    int totalCopies;
    int availableCopies;

    public Book(String bookId, String title, String author,
                String genre, int year, int totalCopies) {
        this.bookId         = bookId;
        this.title          = title;
        this.author         = author;
        this.genre          = genre;
        this.year           = year;
        this.totalCopies    = totalCopies;
        this.availableCopies = totalCopies;
    }

    public void displayBook(int index) {
        System.out.println("\n----- Book #" + index + " -----");
        System.out.println("Book ID          : " + bookId);
        System.out.println("Title            : " + title);
        System.out.println("Author           : " + author);
        System.out.println("Genre            : " + genre);
        System.out.println("Year Published   : " + year);
        System.out.println("Total Copies     : " + totalCopies);
        System.out.println("Available Copies : " + availableCopies);
        System.out.println("Status           : " + (availableCopies > 0 ? "AVAILABLE" : "FULLY BORROWED"));
    }
}

// =============================================
//              MEMBER CLASS
// =============================================
class Member {
    String memberId;
    String name;
    String contact;
    String email;
    int borrowedCount;

    static final int MAX_BORROW_LIMIT = 3;

    public Member(String memberId, String name,
                  String contact, String email) {
        this.memberId     = memberId;
        this.name         = name;
        this.contact      = contact;
        this.email        = email;
        this.borrowedCount = 0;
    }

    public void displayMember(int index) {
        System.out.println("\n----- Member #" + index + " -----");
        System.out.println("Member ID       : " + memberId);
        System.out.println("Name            : " + name);
        System.out.println("Contact         : " + contact);
        System.out.println("Email           : " + email);
        System.out.println("Books Borrowed  : " + borrowedCount + " / " + MAX_BORROW_LIMIT);
    }
}

// =============================================
//           BORROW RECORD CLASS
// =============================================
class BorrowRecord {
    String recordId;
    String memberId;
    String memberName;
    String bookId;
    String bookTitle;
    String borrowDate;
    String returnDate;
    boolean returned;

    public BorrowRecord(String recordId, String memberId, String memberName,
                        String bookId, String bookTitle, String borrowDate) {
        this.recordId   = recordId;
        this.memberId   = memberId;
        this.memberName = memberName;
        this.bookId     = bookId;
        this.bookTitle  = bookTitle;
        this.borrowDate = borrowDate;
        this.returnDate = "N/A";
        this.returned   = false;
    }

    public void displayRecord(int index) {
        System.out.println("\n----- Record #" + index + " -----");
        System.out.println("Record ID   : " + recordId);
        System.out.println("Member ID   : " + memberId);
        System.out.println("Member Name : " + memberName);
        System.out.println("Book ID     : " + bookId);
        System.out.println("Book Title  : " + bookTitle);
        System.out.println("Borrow Date : " + borrowDate);
        System.out.println("Return Date : " + returnDate);
        System.out.println("Status      : " + (returned ? "RETURNED" : "BORROWED"));
    }
}

// =============================================
//              MAIN PROGRAM
// =============================================
public class LibraryManagementSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Book>         books   = new ArrayList<>();
    static ArrayList<Member>       members = new ArrayList<>();
    static ArrayList<BorrowRecord> records = new ArrayList<>();
    static int recordCounter = 1;

    public static void main(String[] args) {
        loadSampleData();

        int choice;
        do {
            printMainMenu();
            choice = readInt("Enter Choice: ");

            switch (choice) {
                case 1: bookMenu();     break;
                case 2: memberMenu();   break;
                case 3: borrowBook();   break;
                case 4: returnBook();   break;
                case 5: viewRecords();  break;
                case 6: searchMenu();   break;
                case 7:
                    System.out.println("\n  Thank you for using the Library Management System!");
                    System.out.println("  Goodbye!\n");
                    break;
                default:
                    System.out.println("\n  [!] Invalid choice. Please try again.");
            }
        } while (choice != 7);

        sc.close();
    }

    // ==========================================
    //              MAIN MENU
    // ==========================================
    static void printMainMenu() {
        System.out.println("\n╔═════════════════════════════════════════╗");
        System.out.println("║       LIBRARY MANAGEMENT SYSTEM         ║");
        System.out.println("╠═════════════════════════════════════════╣");
        System.out.println("║  1. Manage Books                        ║");
        System.out.println("║  2. Manage Members                      ║");
        System.out.println("║  3. Borrow a Book                       ║");
        System.out.println("║  4. Return a Book                       ║");
        System.out.println("║  5. View Borrow Records                 ║");
        System.out.println("║  6. Search                              ║");
        System.out.println("║  7. Exit                                ║");
        System.out.println("╚═════════════════════════════════════════╝");
    }

    // ==========================================
    //              BOOK MENU
    // ==========================================
    static void bookMenu() {
        int choice;
        do {
            System.out.println("\n┌─────────────────────────┐");
            System.out.println("│       BOOK MENU         │");
            System.out.println("├─────────────────────────┤");
            System.out.println("│  1. Add Book            │");
            System.out.println("│  2. View All Books      │");
            System.out.println("│  3. Update Book         │");
            System.out.println("│  4. Delete Book         │");
            System.out.println("│  5. Back to Main Menu   │");
            System.out.println("└─────────────────────────┘");
            choice = readInt("Enter Choice: ");

            switch (choice) {
                case 1: addBook();    break;
                case 2: viewBooks();  break;
                case 3: updateBook(); break;
                case 4: deleteBook(); break;
                case 5: break;
                default: System.out.println("\n  [!] Invalid choice.");
            }
        } while (choice != 5);
    }

    static void addBook() {
        System.out.println("\n  ── Add New Book ──");
        System.out.print("  Book ID        : ");
        String id = sc.nextLine().trim();

        // Duplicate ID check
        for (Book b : books) {
            if (b.bookId.equalsIgnoreCase(id)) {
                System.out.println("\n  [!] Book ID already exists.");
                return;
            }
        }

        System.out.print("  Title          : ");
        String title = sc.nextLine().trim();

        System.out.print("  Author         : ");
        String author = sc.nextLine().trim();

        System.out.print("  Genre          : ");
        String genre = sc.nextLine().trim();

        int year = readInt("  Year Published : ");
        int copies = readInt("  Total Copies   : ");
        if (copies < 1) copies = 1;

        books.add(new Book(id, title, author, genre, year, copies));
        System.out.println("\n  [✓] Book added successfully!");
    }

    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("\n  [!] No books in the library.");
            return;
        }
        System.out.println("\n═══════════════ BOOK LIST ═══════════════");
        for (int i = 0; i < books.size(); i++) {
            books.get(i).displayBook(i + 1);
        }
        System.out.println("\n  Total Books: " + books.size());
    }

    static void updateBook() {
        if (books.isEmpty()) {
            System.out.println("\n  [!] No books available.");
            return;
        }
        viewBooks();
        int idx = readInt("\n  Enter Book Number to Update: ") - 1;

        if (idx < 0 || idx >= books.size()) {
            System.out.println("\n  [!] Invalid book number.");
            return;
        }

        Book b = books.get(idx);
        System.out.println("\n  ── Updating: " + b.title + " ──");
        System.out.println("  (Press ENTER to keep current value)");

        System.out.print("  New Title   [" + b.title + "]: ");
        String input = sc.nextLine().trim();
        if (!input.isEmpty()) b.title = input;

        System.out.print("  New Author  [" + b.author + "]: ");
        input = sc.nextLine().trim();
        if (!input.isEmpty()) b.author = input;

        System.out.print("  New Genre   [" + b.genre + "]: ");
        input = sc.nextLine().trim();
        if (!input.isEmpty()) b.genre = input;

        System.out.print("  New Year    [" + b.year + "]: ");
        input = sc.nextLine().trim();
        if (!input.isEmpty()) {
            try { b.year = Integer.parseInt(input); }
            catch (NumberFormatException e) { System.out.println("  [!] Invalid year, kept unchanged."); }
        }

        System.out.print("  New Total Copies [" + b.totalCopies + "]: ");
        input = sc.nextLine().trim();
        if (!input.isEmpty()) {
            try {
                int newTotal = Integer.parseInt(input);
                int borrowed = b.totalCopies - b.availableCopies;
                if (newTotal >= borrowed) {
                    b.availableCopies = newTotal - borrowed;
                    b.totalCopies     = newTotal;
                } else {
                    System.out.println("  [!] Cannot set total copies below currently borrowed copies (" + borrowed + "). Kept unchanged.");
                }
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid number, kept unchanged.");
            }
        }

        System.out.println("\n  [✓] Book updated successfully!");
    }

    static void deleteBook() {
        if (books.isEmpty()) {
            System.out.println("\n  [!] No books available.");
            return;
        }
        viewBooks();
        int idx = readInt("\n  Enter Book Number to Delete: ") - 1;

        if (idx < 0 || idx >= books.size()) {
            System.out.println("\n  [!] Invalid book number.");
            return;
        }

        Book b = books.get(idx);
        int borrowed = b.totalCopies - b.availableCopies;
        if (borrowed > 0) {
            System.out.println("\n  [!] Cannot delete \"" + b.title + "\". It still has " + borrowed + " copy/copies borrowed.");
            return;
        }

        System.out.print("\n  Confirm delete \"" + b.title + "\"? (y/n): ");
        String confirm = sc.nextLine().trim();
        if (confirm.equalsIgnoreCase("y")) {
            books.remove(idx);
            System.out.println("\n  [✓] Book deleted successfully!");
        } else {
            System.out.println("\n  Deletion cancelled.");
        }
    }

    // ==========================================
    //              MEMBER MENU
    // ==========================================
    static void memberMenu() {
        int choice;
        do {
            System.out.println("\n┌─────────────────────────┐");
            System.out.println("│      MEMBER MENU        │");
            System.out.println("├─────────────────────────┤");
            System.out.println("│  1. Register Member     │");
            System.out.println("│  2. View All Members    │");
            System.out.println("│  3. Update Member       │");
            System.out.println("│  4. Remove Member       │");
            System.out.println("│  5. Back to Main Menu   │");
            System.out.println("└─────────────────────────┘");
            choice = readInt("Enter Choice: ");

            switch (choice) {
                case 1: addMember();    break;
                case 2: viewMembers();  break;
                case 3: updateMember(); break;
                case 4: deleteMember(); break;
                case 5: break;
                default: System.out.println("\n  [!] Invalid choice.");
            }
        } while (choice != 5);
    }

    static void addMember() {
        System.out.println("\n  ── Register New Member ──");
        System.out.print("  Member ID : ");
        String id = sc.nextLine().trim();

        for (Member m : members) {
            if (m.memberId.equalsIgnoreCase(id)) {
                System.out.println("\n  [!] Member ID already exists.");
                return;
            }
        }

        System.out.print("  Full Name : ");
        String name = sc.nextLine().trim();

        System.out.print("  Contact   : ");
        String contact = sc.nextLine().trim();

        System.out.print("  Email     : ");
        String email = sc.nextLine().trim();

        members.add(new Member(id, name, contact, email));
        System.out.println("\n  [✓] Member registered successfully!");
    }

    static void viewMembers() {
        if (members.isEmpty()) {
            System.out.println("\n  [!] No registered members.");
            return;
        }
        System.out.println("\n═══════════════ MEMBER LIST ═══════════════");
        for (int i = 0; i < members.size(); i++) {
            members.get(i).displayMember(i + 1);
        }
        System.out.println("\n  Total Members: " + members.size());
    }

    static void updateMember() {
        if (members.isEmpty()) {
            System.out.println("\n  [!] No members available.");
            return;
        }
        viewMembers();
        int idx = readInt("\n  Enter Member Number to Update: ") - 1;

        if (idx < 0 || idx >= members.size()) {
            System.out.println("\n  [!] Invalid member number.");
            return;
        }

        Member m = members.get(idx);
        System.out.println("\n  ── Updating: " + m.name + " ──");
        System.out.println("  (Press ENTER to keep current value)");

        System.out.print("  New Name    [" + m.name + "]: ");
        String input = sc.nextLine().trim();
        if (!input.isEmpty()) m.name = input;

        System.out.print("  New Contact [" + m.contact + "]: ");
        input = sc.nextLine().trim();
        if (!input.isEmpty()) m.contact = input;

        System.out.print("  New Email   [" + m.email + "]: ");
        input = sc.nextLine().trim();
        if (!input.isEmpty()) m.email = input;

        System.out.println("\n  [✓] Member updated successfully!");
    }

    static void deleteMember() {
        if (members.isEmpty()) {
            System.out.println("\n  [!] No members available.");
            return;
        }
        viewMembers();
        int idx = readInt("\n  Enter Member Number to Remove: ") - 1;

        if (idx < 0 || idx >= members.size()) {
            System.out.println("\n  [!] Invalid member number.");
            return;
        }

        Member m = members.get(idx);
        if (m.borrowedCount > 0) {
            System.out.println("\n  [!] Cannot remove \"" + m.name + "\". They still have " + m.borrowedCount + " unreturned book(s).");
            return;
        }

        System.out.print("\n  Confirm remove member \"" + m.name + "\"? (y/n): ");
        String confirm = sc.nextLine().trim();
        if (confirm.equalsIgnoreCase("y")) {
            members.remove(idx);
            System.out.println("\n  [✓] Member removed successfully!");
        } else {
            System.out.println("\n  Removal cancelled.");
        }
    }

    // ==========================================
    //              BORROW BOOK
    // ==========================================
    static void borrowBook() {
        if (books.isEmpty()) {
            System.out.println("\n  [!] No books in the library.");
            return;
        }
        if (members.isEmpty()) {
            System.out.println("\n  [!] No registered members.");
            return;
        }

        System.out.println("\n  ── Borrow a Book ──");

        // Select member
        viewMembers();
        int mIdx = readInt("\n  Enter Member Number: ") - 1;
        if (mIdx < 0 || mIdx >= members.size()) {
            System.out.println("\n  [!] Invalid member number.");
            return;
        }
        Member member = members.get(mIdx);

        if (member.borrowedCount >= Member.MAX_BORROW_LIMIT) {
            System.out.println("\n  [!] \"" + member.name + "\" has reached the borrow limit of " + Member.MAX_BORROW_LIMIT + " books.");
            return;
        }

        // Select book
        viewBooks();
        int bIdx = readInt("\n  Enter Book Number to Borrow: ") - 1;
        if (bIdx < 0 || bIdx >= books.size()) {
            System.out.println("\n  [!] Invalid book number.");
            return;
        }
        Book book = books.get(bIdx);

        if (book.availableCopies <= 0) {
            System.out.println("\n  [!] Sorry, \"" + book.title + "\" is fully borrowed. No copies available.");
            return;
        }

        // Check if member already borrowed same book
        for (BorrowRecord r : records) {
            if (r.memberId.equals(member.memberId) &&
                r.bookId.equals(book.bookId)        &&
                !r.returned) {
                System.out.println("\n  [!] \"" + member.name + "\" already has this book borrowed.");
                return;
            }
        }

        System.out.print("\n  Enter Borrow Date (e.g., 2025-01-15): ");
        String borrowDate = sc.nextLine().trim();

        // Create record
        String recordId = "REC" + String.format("%04d", recordCounter++);
        BorrowRecord newRecord = new BorrowRecord(
            recordId, member.memberId, member.name,
            book.bookId, book.title, borrowDate
        );
        records.add(newRecord);

        // Update book and member
        book.availableCopies--;
        member.borrowedCount++;

        System.out.println("\n  ╔══════════════════════════════════╗");
        System.out.println("  ║      BORROW SUCCESSFUL!          ║");
        System.out.println("  ╠══════════════════════════════════╣");
        System.out.printf ("  ║  Record ID  : %-18s ║%n", recordId);
        System.out.printf ("  ║  Member     : %-18s ║%n", member.name);
        System.out.printf ("  ║  Book       : %-18s ║%n",
            book.title.length() > 18 ? book.title.substring(0, 15) + "..." : book.title);
        System.out.printf ("  ║  Date       : %-18s ║%n", borrowDate);
        System.out.println("  ╚══════════════════════════════════╝");
    }

    // ==========================================
    //              RETURN BOOK
    // ==========================================
    static void returnBook() {
        if (records.isEmpty()) {
            System.out.println("\n  [!] No borrow records found.");
            return;
        }

        System.out.println("\n  ── Return a Book ──");
        System.out.println("\n  Active Borrows:");
        System.out.println("  ───────────────────────────────────────────────────────────");
        System.out.printf ("  %-5s %-10s %-20s %-20s %-12s%n",
                            "#", "Record ID", "Member", "Book Title", "Borrow Date");
        System.out.println("  ───────────────────────────────────────────────────────────");

        ArrayList<Integer> activeIndices = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            BorrowRecord r = records.get(i);
            if (!r.returned) {
                activeIndices.add(i);
                String displayTitle = r.bookTitle.length() > 20
                    ? r.bookTitle.substring(0, 17) + "..."
                    : r.bookTitle;
                String displayName = r.memberName.length() > 20
                    ? r.memberName.substring(0, 17) + "..."
                    : r.memberName;
                System.out.printf("  %-5d %-10s %-20s %-20s %-12s%n",
                    activeIndices.size(), r.recordId, displayName,
                    displayTitle, r.borrowDate);
            }
        }

        if (activeIndices.isEmpty()) {
            System.out.println("  (no active borrows)");
            return;
        }
        System.out.println("  ───────────────────────────────────────────────────────────");

        int choice = readInt("\n  Enter # to Return (0 to cancel): ");
        if (choice == 0) return;

        if (choice < 1 || choice > activeIndices.size()) {
            System.out.println("\n  [!] Invalid selection.");
            return;
        }

        BorrowRecord record = records.get(activeIndices.get(choice - 1));
        System.out.print("\n  Enter Return Date (e.g., 2025-02-01): ");
        String returnDate = sc.nextLine().trim();

        // Mark record as returned
        record.returned   = true;
        record.returnDate = returnDate;

        // Update book availability
        for (Book b : books) {
            if (b.bookId.equals(record.bookId)) {
                b.availableCopies++;
                break;
            }
        }

        // Update member borrow count
        for (Member m : members) {
            if (m.memberId.equals(record.memberId)) {
                m.borrowedCount--;
                break;
            }
        }

        System.out.println("\n  [✓] Book \"" + record.bookTitle + "\" returned successfully by " + record.memberName + "!");
    }

    // ==========================================
    //              VIEW RECORDS
    // ==========================================
    static void viewRecords() {
        if (records.isEmpty()) {
            System.out.println("\n  [!] No borrow records found.");
            return;
        }

        System.out.println("\n  View Records:");
        System.out.println("  1. All Records");
        System.out.println("  2. Active (Not Yet Returned)");
        System.out.println("  3. Returned Records");
        int choice = readInt("  Choice: ");

        System.out.println("\n═══════════════ BORROW RECORDS ═══════════════");
        int count = 0;
        for (int i = 0; i < records.size(); i++) {
            BorrowRecord r = records.get(i);
            boolean show = (choice == 1)
                        || (choice == 2 && !r.returned)
                        || (choice == 3 && r.returned);
            if (show) {
                r.displayRecord(i + 1);
                count++;
            }
        }

        if (count == 0) {
            System.out.println("\n  (No records match the filter.)");
        } else {
            System.out.println("\n  Total shown: " + count);
        }
    }

    // ==========================================
    //              SEARCH MENU
    // ==========================================
    static void searchMenu() {
        System.out.println("\n┌─────────────────────────┐");
        System.out.println("│       SEARCH MENU       │");
        System.out.println("├─────────────────────────┤");
        System.out.println("│  1. Search Book by Title│");
        System.out.println("│  2. Search Book by Author│");
        System.out.println("│  3. Search Book by Genre │");
        System.out.println("│  4. Search Member by Name│");
        System.out.println("│  5. Back                 │");
        System.out.println("└──────────────────────────┘");
        int choice = readInt("Enter Choice: ");

        switch (choice) {
            case 1: searchBooks("title");  break;
            case 2: searchBooks("author"); break;
            case 3: searchBooks("genre");  break;
            case 4: searchMember();        break;
            case 5: break;
            default: System.out.println("\n  [!] Invalid choice.");
        }
    }

    static void searchBooks(String field) {
        System.out.print("\n  Enter " + field + " to search: ");
        String keyword = sc.nextLine().trim().toLowerCase();

        boolean found = false;
        System.out.println("\n  ── Search Results ──");
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            boolean match = false;
            switch (field) {
                case "title":  match = b.title.toLowerCase().contains(keyword);  break;
                case "author": match = b.author.toLowerCase().contains(keyword); break;
                case "genre":  match = b.genre.toLowerCase().contains(keyword);  break;
            }
            if (match) {
                b.displayBook(i + 1);
                found = true;
            }
        }
        if (!found) System.out.println("\n  [!] No books found matching \"" + keyword + "\".");
    }

    static void searchMember() {
        System.out.print("\n  Enter member name to search: ");
        String keyword = sc.nextLine().trim().toLowerCase();

        boolean found = false;
        System.out.println("\n  ── Search Results ──");
        for (int i = 0; i < members.size(); i++) {
            Member m = members.get(i);
            if (m.name.toLowerCase().contains(keyword)) {
                m.displayMember(i + 1);
                found = true;
            }
        }
        if (!found) System.out.println("\n  [!] No members found matching \"" + keyword + "\".");
    }

    // ==========================================
    //         UTILITY: Safe Int Reader
    // ==========================================
    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("  [!] Please enter a valid number.");
            }
        }
    }

    // ==========================================
    //         SAMPLE DATA LOADER
    // ==========================================
    static void loadSampleData() {
        // Sample Books
        books.add(new Book("B001", "The Great Gatsby",     "F. Scott Fitzgerald", "Fiction",    1925, 3));
        books.add(new Book("B002", "To Kill a Mockingbird","Harper Lee",          "Fiction",    1960, 2));
        books.add(new Book("B003", "1984",                 "George Orwell",       "Dystopian",  1949, 4));
        books.add(new Book("B004", "Clean Code",           "Robert C. Martin",    "Technology", 2008, 2));
        books.add(new Book("B005", "The Alchemist",        "Paulo Coelho",        "Adventure",  1988, 3));

        // Sample Members
        members.add(new Member("M001", "Juan dela Cruz",    "09171234567", "juan@email.com"));
        members.add(new Member("M002", "Maria Santos",      "09281234567", "maria@email.com"));
        members.add(new Member("M003", "Jose Rizal",        "09391234567", "jose@email.com"));
    }
}