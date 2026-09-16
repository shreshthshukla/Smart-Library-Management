# 📚 Library Management System

> A simple Java-based console application for managing books, students, borrowing records, and fines.

---

## 📌 About the Project

The **Library Management System** is a Java console application developed to manage the basic day-to-day operations of a library.

The project uses **Object-Oriented Programming (OOP)** and **file handling** to keep the system organized and maintain data between different program sessions.

---

## ✨ Features

* 📖 **Book Management**

  * Add, view, search, update, and delete books
  * Track total and available copies

* 👨‍🎓 **Student Management**

  * Register students
  * View, search, update, and delete student records

* 🔄 **Issue & Return**

  * Issue books to registered students
  * Return issued books
  * Automatically update book availability
  * Maintain borrowing history

* 💰 **Fine Management**

  * Automatically calculate fines for late returns
  * View pending fines
  * Mark fines as paid

* 📊 **Reports**

  * View books
  * View available books
  * View issued books
  * View students
  * View fine records

* 💾 **File Storage**

  * Records are stored in text files
  * Data remains available after restarting the application

---

## 🛠️ Technologies Used

| Technology       | Purpose                         |
| ---------------- | ------------------------------- |
| ☕ Java           | Core programming language       |
| 🧩 OOP           | Classes, objects, encapsulation |
| 📁 File Handling | Persistent data storage         |
| 📋 ArrayList     | Managing records                |
| 📅 LocalDate     | Issue and due dates             |
| 💻 VS Code       | Development environment         |

---

## 📂 Project Structure

```text
LibraryManagementSystem/
│
├── 📄 Main.java
├── 📄 Book.java
├── 📄 BookManager.java
├── 📄 Student.java
├── 📄 StudentManager.java
├── 📄 IssueRecord.java
├── 📄 IssueManager.java
├── 📄 Fine.java
├── 📄 FineManager.java
├── 📄 FileManager.java
│
└── 📁 data/
    ├── books.txt
    ├── students.txt
    ├── issue_records.txt
    └── fines.txt
```

---

## 🚀 How to Run

### 1. Clone the repository

```bash
git clone <your-repository-link>
```

### 2. Open the project

Open the project folder in **VS Code**.

### 3. Compile the files

Open the terminal and run:

```bash
javac *.java
```

### 4. Run the application

```bash
java Main
```

---

## 🖥️ Main Menu

```text
============== MAIN MENU ==============

1. Book Management
2. Student Management
3. Issue / Return Book
4. Fine Management
5. Reports
6. Exit
```

---

## 🧠 Concepts Demonstrated

This project demonstrates practical use of:

* Object-Oriented Programming
* Encapsulation
* Classes and Objects
* Constructors
* Methods
* ArrayList
* File Handling
* Exception Handling
* Input Validation
* Date Handling
* CRUD Operations
* Modular Programming

---

## 🎯 Project Purpose

The main purpose of this project is to apply Java programming and OOP concepts to a practical real-world problem while building a simple and functional library management system.

---

## 👩‍💻 Author

**Shreshth Shukla**

VIT Bhopal University

---
