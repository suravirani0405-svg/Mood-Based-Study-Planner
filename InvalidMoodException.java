//InvalidMoodException.java
//A custom exception we created ourselves


// 'extends Exception' makes this class a Checked Exception
public class InvalidMoodException extends Exception {

    private static final long serialVersionUID = 1L;

    // Constructor
    public InvalidMoodException(String message) {
        super(message); // Passes the error message to the parent Exception class
    }
}
