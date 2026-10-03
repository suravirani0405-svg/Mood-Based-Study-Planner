//  Planner.java
// A blueprint/template for all planners


// abstract class
public abstract class Planner {

    
    protected String studentName;   
    protected String mood;
    protected int studyDurationMinutes;

    // CONSTRUCTOR 
    public Planner(String studentName, String mood, int studyDurationMinutes) {
        this.studentName = studentName;
        this.mood = mood;
        this.studyDurationMinutes = studyDurationMinutes;
    }

    // ABSTRACT METHOD 
    public abstract void startSession();

    public abstract void showPlan();

    
    public void showWelcome() {
        System.out.println("====================================");
        System.out.println("  Welcome, " + studentName + "!");
        System.out.println("  Your Mood Today: " + mood);
        System.out.println("  Study Time: " + studyDurationMinutes + " minutes");
        System.out.println("====================================");
    }

    // GETTER METHODS 
    public String getStudentName() {
        return studentName;
    }

    public String getMood() {
        return mood;
    }

    public int getStudyDuration() {
        return studyDurationMinutes;
    }
}
