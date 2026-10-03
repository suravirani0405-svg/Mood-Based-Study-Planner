// DatabaseManager.java
//Handles saving/loading study sessions

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    // COLLECTION: List to store our session history in memory
    private List<String> sessionHistory;

    public DatabaseManager() {
        sessionHistory = new ArrayList<>(); // Initialize the ArrayList
    }

    // save session to text file
    public void saveSessionToFile(String studentName, String mood, int duration) {
        String fileName = "sessions.txt";

        // try-with-resources (automatically closes the file)
        try (FileWriter fw = new FileWriter(fileName, true); // true = append mode
                BufferedWriter bw = new BufferedWriter(fw)) {

            String record = studentName + " studied for " + duration + " mins with mood: " + mood;

            bw.write(record);
            bw.newLine(); // go to next line

            // Add to our Collection too!
            sessionHistory.add(record);
            System.out.println("[OK] Saved to Text File: " + record);

        } catch (IOException e) { // Exception Handling
            System.out.println("[ERROR] Error saving to file: " + e.getMessage());
        }
    }

    // save session to database using JDBC
    public void saveSessionToDatabase(String studentName, String mood, int duration) {
        String url = "jdbc:sqlite:study_planner.db";
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection(url)) {
                try (Statement stmt = conn.createStatement()) {
                    String createTableSQL = "CREATE TABLE IF NOT EXISTS Sessions (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "name TEXT, mood TEXT, duration INTEGER)";
                    stmt.execute(createTableSQL);
                }
                String insertSQL = "INSERT INTO Sessions(name, mood, duration) VALUES(?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setString(1, studentName);
                    pstmt.setString(2, mood);
                    pstmt.setInt(3, duration);
                    pstmt.executeUpdate();
                }
                System.out.println("[OK] Saved to Database Successfully!");
            }
        } catch (Exception e) {
            System.out.println("[INFO] Database check skipped (JDBC Driver jar not active).");
        }
    }

    // save full day timetable to database
    public void saveTimetableToDatabase(String studentName, String mood, String scheduleText) {
        String url = "jdbc:sqlite:study_planner.db";
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection(url)) {
                try (Statement stmt = conn.createStatement()) {
                    String createTableSQL = "CREATE TABLE IF NOT EXISTS Timetables (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "name TEXT, mood TEXT, schedule_text TEXT)";
                    stmt.execute(createTableSQL);
                }
                String insertSQL = "INSERT INTO Timetables(name, mood, schedule_text) VALUES(?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setString(1, studentName);
                    pstmt.setString(2, mood);
                    pstmt.setString(3, scheduleText);
                    pstmt.executeUpdate();
                }
                System.out.println("[OK] Full-Day Timetable Saved to Database!");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Database check skipped for Timetable (JDBC Driver jar not active).");
        }
    }

    // Method to show stored history using the Collection
    public void displaySessionHistory() {
        System.out.println("\n[Session History - From ArrayList Collection]:");
        if (sessionHistory.isEmpty()) {
            System.out.println("   No sessions saved yet.");
        } else {
            for (String session : sessionHistory) { // Enhanced for-loop
                System.out.println("   - " + session);
            }
        }
    }
}
