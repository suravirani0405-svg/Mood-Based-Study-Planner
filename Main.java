

public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 Starting Application...");

       
        // Converting String to Integer Object 
        String defaultTimeStr = "25";
        Integer defaultTime = Integer.valueOf(defaultTimeStr); // Wrapper Class method
        System.out.println("Default session time is set to: " + defaultTime + " minutes.");

        // Checking User-Defined Exception
        try {
            checkUser("Student");
        } catch (InvalidMoodException e) {
            System.out.println("Caught Exception: " + e.getMessage());
            return; // Stop
        }

        // AWT GUI 
        System.out.println("Opening Window...");
        
        @SuppressWarnings("unused")
        AppWindow window = new AppWindow(); 
    }

    // Method that throws  custom exception
    public static void checkUser(String name) throws InvalidMoodException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidMoodException("User Name cannot be empty!");    
        }
        System.out.println("✅ User Validated.\n");
    }
}