package db;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:analysis.db";

    // Initialize the database: create table if it doesn't exist
    public static void initializeDatabase() {
        try {
            // Explicitly load SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found.");
            e.printStackTrace();
            return;
        }

        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS analysis_results (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                filename TEXT NOT NULL,
                analyzed_at TEXT NOT NULL,
                line_count INTEGER,
                word_count INTEGER,
                char_count INTEGER
            );
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute(createTableSQL);

        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    // Save analysis results into the database
    public static void saveAnalysis(String filename, int lineCount, int wordCount, int charCount) {
        String insertSQL = """
            INSERT INTO analysis_results (filename, analyzed_at, line_count, word_count, char_count)
            VALUES (?, ?, ?, ?, ?);
        """;

        // Get current timestamp
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, filename);
            pstmt.setString(2, now);
            pstmt.setInt(3, lineCount);
            pstmt.setInt(4, wordCount);
            pstmt.setInt(5, charCount);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error saving analysis to database: " + e.getMessage());
        }
    }
}
