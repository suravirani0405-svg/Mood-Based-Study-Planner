//Mood.java
// Stores the 4 moods a student can have

public class Mood {
    // Private constructor for utility class
    private Mood() {}

    public static final String HAPPY = "Happy";
    public static final String FOCUSED = "Focused";
    public static final String SAD = "Sad";
    public static final String ANXIOUS = "Anxious";

    // ARRAY
    public static final String[] ALL_MOODS = { HAPPY, FOCUSED, SAD, ANXIOUS };

    public static String getStudySuggestion(String mood) {

        if (mood.equals(HAPPY)) {
            return "You are energetic! -> Try coding: Build a small AWT or Collection program.";
        } else if (mood.equals(FOCUSED)) {
            return "Sharp focus! -> Read deeply: JDBC, Multithreading, Exception Handling.";
        } else if (mood.equals(SAD)) {
            return "Feel better! -> Watch a video tutorial on String Handling or Wrapper Classes.";
        } else if (mood.equals(ANXIOUS)) {
            return "Stay calm! -> Do a quick revision: Glance through notes, no new topics.";
        } else {
            return "Unknown mood. Please select a valid mood.";
        }
    }

    public static String getActivityType(String mood) {
        if (mood.equals(HAPPY))
            return "Activity Type  : Hands-on Coding Practice";
        if (mood.equals(FOCUSED))
            return "Activity Type  : Deep Reading & Theory";
        if (mood.equals(SAD))
            return "Activity Type  : Watch Video Tutorial";
        if (mood.equals(ANXIOUS))
            return "Activity Type  : 30 mins Quick Notes Revision";
        return "Activity Type  : General Study";
    }

    // music recommendation based on mood
    public static String getMusicSuggestion(String mood) {

        if (mood.equals(HAPPY)) {
            return "[Music] Playing: Upbeat Lo-fi Music";
        } else if (mood.equals(FOCUSED)) {
            return "[Music] Playing: Deep Focus Instrumental";
        } else if (mood.equals(SAD)) {
            return "[Music] Playing: Soft Calm Piano";
        } else if (mood.equals(ANXIOUS)) {
            return "[Music] Playing: Nature Sounds / Rain";
        } else {
            return "[Music] No music selected";
        }
    }

    // This method generates a random full-day timetable based on mood
    public static String generateFullDayTimetable(String mood) {
        String[] subjects = {"Java OOP", "JDBC Integration", "AWT GUI Design", "Exception Handling", "Multithreading"};
        int randomIdx = (int) (Math.random() * subjects.length);
        String mainSubject = subjects[randomIdx];
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n====================================\n");
        sb.append("   FULL DAY TIMETABLE (Mood: ").append(mood).append(")\n");
        sb.append("====================================\n");
        if (mood.equals(HAPPY)) {
            sb.append("09:00 AM - 11:00 AM : Coding (").append(mainSubject).append(")!\n");
            sb.append("11:30 AM - 01:00 PM : Build a Fun Mini-Project\n");
            sb.append("02:00 PM - 04:00 PM : Brainstorm & System Design\n");
            sb.append("04:30 PM - 06:00 PM : Learn a Brand New Framework\n");
            sb.append("07:00 PM - 08:30 PM : Coding Challenges (LeetCode / HackerRank)\n");
        } else if (mood.equals(FOCUSED)) {
            sb.append("08:00 AM - 11:30 AM : Theoretical Dive (").append(mainSubject).append(")\n");
            sb.append("12:00 PM - 02:00 PM : Official Documentation & Research Papers\n");
            sb.append("03:00 PM - 05:00 PM : Solve Complex Algorithmic Puzzles\n");
            sb.append("05:30 PM - 07:00 PM : Code Refactoring\n");
            sb.append("07:30 PM - 08:30 PM : Summarize Key Takeaways in Journal\n");
        } else if (mood.equals(SAD)) {
            sb.append("10:00 AM - 11:00 AM : Read an Article on ").append(mainSubject).append("\n");
            sb.append("11:30 AM - 12:30 PM : Watch Code-Along Tutorials\n");
            sb.append("02:00 PM - 03:00 PM : Organize Folders and Format Old Code\n");
            sb.append("04:00 PM - 05:00 PM : Read Through Past Notes\n");
            sb.append("05:30 PM - 06:30 PM : Take a Walk and Rest (No Screen Time)\n");
        } else if (mood.equals(ANXIOUS)) {
            sb.append("09:00 AM - 09:15 AM : 15-Min Breathing & Planning session\n");
            sb.append("09:15 AM - 10:00 AM : Pomodoro Review: ").append(mainSubject).append("\n");
            sb.append("10:30 AM - 11:15 AM : Pomodoro: Small, Easy Code Fixes\n");
            sb.append("01:00 PM - 02:30 PM : Revisit Concepts You Already Know\n");
            sb.append("03:30 PM - 05:00 PM : Write Pseudocode Only\n");
        } else {
            sb.append("09:00 AM - 11:00 AM : General Studies\n");
            sb.append("11:30 AM - 01:00 PM : Programming Foundations\n");
            sb.append("02:00 PM - 04:00 PM : Problem Solving\n");
            sb.append("04:30 PM - 06:00 PM : Revision\n");
            sb.append("07:00 PM - 08:00 PM : Wrap up\n");
        }
        sb.append("====================================\n");

        return sb.toString();
    }
}