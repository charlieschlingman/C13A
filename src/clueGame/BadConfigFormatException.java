package clueGame;

public class BadConfigFormatException extends Exception {
	// Default
    public BadConfigFormatException() {
        super("Configuration file format is invalid.");
    }
	// Constructor that accepts a message
    public BadConfigFormatException(String message) {
        super(message);
    }
}