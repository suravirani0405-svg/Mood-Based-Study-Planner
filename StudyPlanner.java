//  StudyPlanner.java
//  study planner with a schedule


public class StudyPlanner extends Planner implements Schedulable {

    private boolean isScheduled;  // checks if session is active

    // CONSTRUCTOR 
    public StudyPlanner(String studentName, String mood, int studyDurationMinutes) {
        super(studentName, mood, studyDurationMinutes); // calls Planner's constructor
        this.isScheduled = false;
    }

    // OVERRIDING abstract method from Planner

    @Override
    public void startSession() {
        System.out.println("\n📚 Study Session Started!");
        showWelcome(); // calling parent class method (inherited)
        showPlan();
        schedule();
    }

    // OVERRIDING abstract method from Planner
    @Override
    public void showPlan() {
        System.out.println("\n📋 TODAY'S STUDY PLAN:");
        System.out.println("   " + Mood.getStudySuggestion(mood));
        System.out.println("   " + Mood.getMusicSuggestion(mood));
    }

    // IMPLEMENTING interface method: schedule()
    @Override
    public void schedule() {
        isScheduled = true;
        System.out.println("\n✅ Session Scheduled for " + studyDurationMinutes + " minutes.");
    }

    // IMPLEMENTING interface method: cancel()
    @Override
    public void cancel() {
        isScheduled = false;
        System.out.println("\n❌ Session Cancelled.");
    }

    // IMPLEMENTING interface method: showStatus()
    @Override
    public void showStatus() {
        if (isScheduled) {
            System.out.println("📌 Status: Session is ACTIVE");
        } else {
            System.out.println("📌 Status: No active session");
        }
    }

    public boolean isScheduled() {
        return isScheduled;
    }
}
