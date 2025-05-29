# TextAnalyzerApp

**TextAnalyzerApp** is a simple Java Swing application that reads a text file, analyzes it, displays key statistics (line count, word count, character count, and top 10 frequent words), and stores the results in an SQLite database.

---

## 🚀 Features

- GUI built with Java Swing
- Analyzes:
  - Number of lines
  - Number of words
  - Number of characters
  - Top 10 most frequent words
- Stores each analysis in a local SQLite database
- Displays results inside the application


## ⚙️ Requirements

- Java JDK 8 or higher
- SQLite JDBC driver (`sqlite-jdbc-3.49.1.0.jar`)
- Terminal or Command Prompt

---

## 🧪 How to Compile and Run

1. **Compile:**
   ```bash
   javac -d bin src/logic/*.java src/gui/*.java src/db/*.java src/Main.java
2. **Create JAR:**
   ```bash
   jar cfm TextAnalyzerApp.jar manifest.txt -C bin/ .
3. **Run the Application:**
   ```bash
   java -cp "TextAnalyzerApp.jar;sqlite-jdbc-3.49.1.0.jar" Main
