package tests;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import clueGame.BadConfigFormatException;
import clueGame.Board;

public class ExceptionTests {
	
	// Test for a bad column
	@Test
	public void testBadColumns() throws BadConfigFormatException, FileNotFoundException {
		assertThrows(BadConfigFormatException.class, () -> {
			Board board = Board.getInstance();
			board.setConfigFiles("ClueLayoutBadColumns306.csv", "ClueSetup306.txt");
			board.loadSetupConfig();
			board.loadLayoutConfig();
		});
	}
	
	// Test for a bad room
	@Test
	public void testBadRoom() throws BadConfigFormatException, FileNotFoundException {
		assertThrows(BadConfigFormatException.class, () -> {
			Board board = Board.getInstance();
			board.setConfigFiles("ClueLayoutBadRoom306.csv", "ClueSetup306.txt");
			board.loadSetupConfig();
			board.loadLayoutConfig();
		});
	}
	
	
	// Test for bad format
	@Test
	public void testBadRoomFormat() throws BadConfigFormatException, FileNotFoundException {
		assertThrows(BadConfigFormatException.class, () -> {
			Board board = Board.getInstance();
			board.setConfigFiles("ClueLayout.csv", "ClueSetupBadFormat.txt");
			board.loadSetupConfig();
			board.loadLayoutConfig();
		});
	}
}
