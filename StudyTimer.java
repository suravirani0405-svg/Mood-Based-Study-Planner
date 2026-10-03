// =============================================
// FILE 6: StudyTimer.java
// PURPOSE: A countdown timer that runs in background
// CONCEPT USED: MULTITHREADING (extends Thread)
// =============================================

import java.awt.Label;

// 'extends Thread' means this class IS a thread
// A Thread runs separately in the background
public class StudyTimer extends Thread {

    private int totalSeconds;       // total time to count down
    private boolean running;        // controls if timer is active
    private boolean paused;         // controls if timer is paused
    private final Object pauseLock = new Object(); // synchronization lock
    private String studentName;
    private Label uiLabel;          

    // CONSTRUCTOR
    public StudyTimer(String studentName, int minutes, Label uiLabel) {
        this.studentName = studentName;
        this.totalSeconds = minutes * 60; // convert minutes to seconds
        this.running = true;
        this.paused = false;
        this.uiLabel = uiLabel;
    }

    
    // 'run()' is the heart of a Thread
    @Override
    public void run() {
        if (uiLabel != null) {
            uiLabel.setText("Timer Started!");
        }

        int seconds = totalSeconds;

        while (running && seconds > 0) {
            // Calculate minutes and seconds
            int mins = seconds / 60;
            int secs = seconds % 60;
            
            String timeText = "[ Time Remaining: " + mins + " min " + secs + " sec ]";
            
            // Update the AWT window live!
            if (uiLabel != null) {
                uiLabel.setText(timeText);
            }

            // Sync block for pausing
            synchronized (pauseLock) {
                while (paused) {
                    if (uiLabel != null) {
                        uiLabel.setText("[ Timer Paused - " + timeText + " ]");
                    }
                    try {
                        pauseLock.wait(); // Pause thread here until notify()
                    } catch (InterruptedException e) {
                        return; // Exit thread if interrupted during wait
                    }
                }
            }

            try {
                Thread.sleep(1000); // pause for 1 second real-time
            } catch (InterruptedException e) {
                return;
            }

            seconds--;
        }

        if (seconds == 0 && uiLabel != null) {
            uiLabel.setText("[ TIME'S UP! Great study session, " + studentName + "! ]");
        }
    }

    public void stopTimer() {
        running = false;
    }

    public void pauseTimer() {
        paused = true;
    }

    public void resumeTimer() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notify(); // Wake up the sleeping thread
        }
    }

    public boolean isRunning() {
        return running;
    }
}
