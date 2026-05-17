# Library Management System

A console-based **Library Management System** built in Java that handles books, members, borrowing, and returning — all through an interactive menu-driven interface.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Classes](#classes)
- [Getting Started](#getting-started)
- [Usage Guide](#usage-guide)
- [Sample Data](#sample-data)
- [Validations & Rules](#validations--rules)

---

## Overview

This program simulates a basic library system where a librarian can manage a collection of books and registered members, track borrow and return transactions, and search through records — all from the command line.

---

## Features

| Feature | Description |
|---|---|
| Book Management | Add, view, update, and delete books |
| Member Management | Register, view, update, and remove members |
| Borrow a Book | Assign a book to a member with a borrow date |
| Return a Book | Mark a borrowed book as returned |
| Borrow Records | View all, active, or returned records |
| Search | Search books by title, author, or genre; members by name |
| Validations | Duplicate IDs, borrow limits, availability checks |
| Sample Data | Preloaded books and members for instant testing |

---

## Project Structure

```
LibraryManagementSystem.java
│
├── class Book
├── class Member
├── class BorrowRecord
└── class LibraryManagementSystem (main)
    ├── main()
    ├── bookMenu()
    ├── memberMenu()
    ├── borrowBook()
    ├── returnBook()
    ├── viewRecords()
    ├── searchMenu()
    └── loadSampleData()
```

---

## Classes

### `Book`
Represents a library book.

| Field | Type | Description |
|---|---|---|
| `bookId` | String | Unique book identifier |
| `title` | String | Title of the book |
| `author` | String | Author's name |
| `genre` | String | Book genre/category |
| `year` | int | Year of publication |
| `totalCopies` | int | Total copies owned |
| `availableCopies` | int | Copies currently available |

---

### `Member`
Represents a registered library member.

| Field | Type | Description |
|---|---|---|
| `memberId` | String | Unique member identifier |
| `name` | String | Full name |
| `contact` | String | Contact number |
| `email` | String | Email address |
| `borrowedCount` | int | Number of books currently borrowed |
| `MAX_BORROW_LIMIT` | int (static) | Maximum books a member can borrow (default: **3**) |

---

### `BorrowRecord`
Tracks a single borrow transaction.

| Field | Type | Description |
|---|---|---|
| `recordId` | String | Auto-generated record ID (e.g. `REC0001`) |
| `memberId` | String | ID of the borrowing member |
| `memberName` | String | Name of the borrowing member |
| `bookId` | String | ID of the borrowed book |
| `bookTitle` | String | Title of the borrowed book |
| `borrowDate` | String | Date the book was borrowed |
| `returnDate` | String | Date returned (`N/A` if not yet returned) |
| `returned` | boolean | Whether the book has been returned |

---

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher

### Compile

```bash
javac LibraryManagementSystem.java
```

### Run

```bash
java LibraryManagementSystem
```

---

## Usage Guide

### Main Menu

```
╔═════════════════════════════════════════╗
║       LIBRARY MANAGEMENT SYSTEM         ║
╠═════════════════════════════════════════╣
║  1. Manage Books                        ║
║  2. Manage Members                      ║
║  3. Borrow a Book                       ║
║  4. Return a Book                       ║
║  5. View Borrow Records                 ║
║  6. Search                              ║
║  7. Exit                                ║
╚═════════════════════════════════════════╝
```

### 1 — Manage Books
- **Add Book** — Enter book ID, title, author, genre, year, and number of copies.
- **View All Books** — Lists all books with availability status.
- **Update Book** — Select a book by number and update any field. Press `ENTER` to keep the current value.
- **Delete Book** — Removes a book. Blocked if any copies are currently borrowed.

### 2 — Manage Members
- **Register Member** — Enter member ID, name, contact, and email.
- **View All Members** — Lists all members with current borrow count.
- **Update Member** — Edit name, contact, or email. Press `ENTER` to keep the current value.
- **Remove Member** — Removes a member. Blocked if they have unreturned books.

### 3 — Borrow a Book
1. Select a member from the member list.
2. Select a book from the book list.
3. Enter the borrow date (e.g., `2025-06-01`).
4. A borrow record is created automatically.

### 4 — Return a Book
1. Active borrowers are listed in a table.
2. Enter the row number of the record to return.
3. Enter the return date.

### 5 — View Borrow Records
Filter records by:
- `1` All Records
- `2` Active (not yet returned)
- `3` Returned

### 6 — Search
| Option | Description |
|---|---|
| Search by Title | Partial, case-insensitive match |
| Search by Author | Partial, case-insensitive match |
| Search by Genre | Partial, case-insensitive match |
| Search Member by Name | Partial, case-insensitive match |

---

## Sample Data

The program loads the following sample data on startup for immediate testing:

**Books**

| Book ID | Title | Author | Genre | Year | Copies |
|---|---|---|---|---|---|
| B001 | The Great Gatsby | F. Scott Fitzgerald | Fiction | 1925 | 3 |
| B002 | To Kill a Mockingbird | Harper Lee | Fiction | 1960 | 2 |
| B003 | 1984 | George Orwell | Dystopian | 1949 | 4 |
| B004 | Clean Code | Robert C. Martin | Technology | 2008 | 2 |
| B005 | The Alchemist | Paulo Coelho | Adventure | 1988 | 3 |

**Members** **not real person**

| Member ID | Name | Contact | Email |
|---|---|---|---|
| M001 | Juan dela Cruz | 09171234567 | juan@email.com |
| M002 | Maria Santos | 09281234567 | maria@email.com |
| M003 | Jose Rizal | 09391234567 | jose@email.com |

---

## Validations & Rules

| Rule | Details |
|---|---|
| Unique IDs | Duplicate book or member IDs are rejected on creation |
| Borrow Limit | A member can hold a maximum of **3 books** at a time |
| Availability Check | A book with no available copies cannot be borrowed |
| Duplicate Borrow | A member cannot borrow the same book twice simultaneously |
| Safe Delete (Book) | A book with borrowed copies cannot be deleted |
| Safe Delete (Member) | A member with unreturned books cannot be removed |
| Total Copies Update | Cannot set total copies lower than currently borrowed count |
