// MediaPlanner.java
// Adds media/music support on top of StudyPlanner


// MediaPlanner → extends → StudyPlanner → extends → Planner
// MULTILEVEL INHERITANCE (3 level)
public class MediaPlanner extends StudyPlanner {

    // media 
    private String mediaType;       // e.g., "Music", "Video", "Podcast"
    private boolean mediaPlaying;   

    // CONSTRUCTOR
    public MediaPlanner(String studentName, String mood, int studyDurationMinutes, String mediaType) {
        super(studentName, mood, studyDurationMinutes); // calls StudyPlanner constructor
        this.mediaType = mediaType;
        this.mediaPlaying = false;
    }

    // OVERRIDING startSession from StudyPlanner (Polymorphism!)
    
    @Override
    public void startSession() {
        super.startSession(); // first runs StudyPlanner's startSession
        playMedia();          
    }


    public void playMedia() {
        mediaPlaying = true;
        System.out.println("\n🎵 Media Started!");
        System.out.println("   Type: " + mediaType);
        System.out.println("   " + Mood.getMusicSuggestion(mood));
    }

    public void stopMedia() {
        mediaPlaying = false;
        System.out.println("\n⏹️ Media Stopped.");
    }

  
    public String getMediaType() {
        return mediaType;
    }

    public boolean isMediaPlaying() {
        return mediaPlaying;
    }

    public String getMediaStatus() {
        return mediaPlaying ? "▶ Playing" : "⏹ Stopped";
    }
}
