// AppWindow.java

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AppWindow extends Frame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private TextField nameInput;
    private TextArea outputArea;
    private Label timerLabel;

    private transient DatabaseManager dbManager;
    private transient StudyTimer currentTimer;

    @SuppressWarnings("this-escape")
    public AppWindow() {
        dbManager = new DatabaseManager();

        // 1. FRAME SETUP
        setTitle("Mood Study Planner - Full UI");
        setSize(600, 600);
        setLayout(new BorderLayout(5, 5)); // BorderLayout fills the full area!
        setBackground(Color.WHITE);

        // 2. NORTH PANEL: Title and Name Input
        Panel northPanel = new Panel(new GridLayout(2, 1));
        northPanel.setBackground(new Color(240, 240, 255)); // Light blue tint

        Label title = new Label("--- MOOD STUDY PLANNER ---", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.BLUE);
        northPanel.add(title);

        Panel nameRow = new Panel(new FlowLayout());
        Label nameLabel = new Label("Enter Your Name:");
        nameInput = new TextField("", 30);
        nameRow.add(nameLabel);
        nameRow.add(nameInput);
        northPanel.add(nameRow);

        add(northPanel, BorderLayout.NORTH);

        // 3. CENTER PANEL: Output Area (This will fill the full middle!)
        outputArea = new TextArea("", 20, 80, TextArea.SCROLLBARS_VERTICAL_ONLY);
        outputArea.setEditable(false);
        outputArea.setBackground(Color.BLACK); // Classic black background
        outputArea.setForeground(Color.WHITE); // White text as requested
        outputArea.setFont(new Font("Monospaced", Font.BOLD, 16)); // Larger, Bolder font
        add(outputArea, BorderLayout.CENTER);

        // 4. SOUTH PANEL: Buttons and Timer
        Panel southPanel = new Panel(new BorderLayout(5, 5));

        // Button Row
        Panel buttonRow = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Added padding
        buttonRow.add(createBtn("  Happy  ", Color.BLUE));
        buttonRow.add(createBtn(" Focused ", Color.GREEN));
        buttonRow.add(createBtn("   Sad   ", Color.RED));
        buttonRow.add(createBtn(" Anxious ", Color.ORANGE));
        
        buttonRow.add(createBtn(" Pause Timer ", Color.GRAY));
        buttonRow.add(createBtn(" Resume Timer ", Color.DARK_GRAY));
        southPanel.add(buttonRow, BorderLayout.NORTH);

        // Timer
        timerLabel = new Label("[ Timer: Click a mood to start ]", Label.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        southPanel.add(timerLabel, BorderLayout.CENTER);

        add(southPanel, BorderLayout.SOUTH);

        // REDIRECT OUTPUT
        redirectOutput();

        // WINDOW CLOSE
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
        toFront(); // Force window to pop on top of VS Code
    }

    private Button createBtn(String label, Color bg) {
        Button b = new Button(label);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.addActionListener(this);
        return b;
    }

    private void redirectOutput() {
        PrintStream windowStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                outputArea.append(String.valueOf((char) b));
            }
        });
        System.setOut(windowStream);
        System.setErr(windowStream);
        System.out.println(">> App Launched. Please enter name and click a button.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand().trim();
        
        if (cmd.equals("Pause Timer")) {
            if (currentTimer != null && currentTimer.isAlive()) {
                currentTimer.pauseTimer();
            }
            return;
        } else if (cmd.equals("Resume Timer")) {
            if (currentTimer != null && currentTimer.isAlive()) {
                currentTimer.resumeTimer();
            }
            return;
        }

        String mood = cmd;
        String name = nameInput.getText().trim();

        try {
            if (name.isEmpty())
                throw new InvalidMoodException("Name Required!");
            runSession(name, mood);
        } catch (InvalidMoodException ex) {
            outputArea.setText("Error: " + ex.getMessage());
        }
    }

    private void runSession(String name, String mood) {
        MediaPlanner planner = new MediaPlanner(name, mood, 25, "Music");
        outputArea.setText(""); // Clear
        System.out.println("--- SESSION FOR: " + name.toUpperCase() + " ---");
        planner.showWelcome();
        System.out.println("\n[PLAN]: " + Mood.getStudySuggestion(mood));
        System.out.println("\n[MUSIC]: " + Mood.getMusicSuggestion(mood));
        
        // Generate and log the Full Day Timetable
        String fullDayPlan = Mood.generateFullDayTimetable(mood);
        System.out.println(fullDayPlan);

        dbManager.saveSessionToFile(name, mood, 25);
        dbManager.saveSessionToDatabase(name, mood, 25);
        dbManager.saveTimetableToDatabase(name, mood, fullDayPlan);
        
        // Stop previous timer if running
        if (currentTimer != null && currentTimer.isAlive()) {
            currentTimer.interrupt();
        }
        
        currentTimer = new StudyTimer(name, 25, timerLabel);
        currentTimer.start();
        
        // Show history in the log
        dbManager.displaySessionHistory();
    }
}
