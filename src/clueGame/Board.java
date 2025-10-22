package clueGame;


<<<<<<< HEAD
import java.util.HashMap;
=======
>>>>>>> 85ac79c4a6e5b254563bbd0f4723f4ca64bebf1e
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

<<<<<<< HEAD
import javax.swing.JFrame;

import experiment.TestBoardCell;

=======
>>>>>>> 85ac79c4a6e5b254563bbd0f4723f4ca64bebf1e
public class Board {
	private BoardCell[][] grid;
	private int numRows;
	private int numColumns;
	private String layoutConfigFile;
	private String setupConfigFile;
	private Map<Character, Room> roomMap;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
<<<<<<< HEAD
	private int roomCount;
	private int doorCount;
=======
>>>>>>> 85ac79c4a6e5b254563bbd0f4723f4ca64bebf1e

	final static int COLS = 24;
	final static int ROWS = 25;

	/*
     * variable and methods used for singleton pattern
     */
     private static Board theInstance = new Board();
     // constructor is private to ensure only one can be created
     private Board() {
<<<<<<< HEAD
    	 super() ;

=======
            super() ;
>>>>>>> 85ac79c4a6e5b254563bbd0f4723f4ca64bebf1e
     }
     // this method returns the only Board
     public static Board getInstance() {
            return theInstance;
     }
     /*
      * initialize the board (since we are using singleton pattern)
      */
     public void initialize()
     {
<<<<<<< HEAD
    	 // Create board
    	 grid = new BoardCell[ROWS][COLS];
    	 
         for (int r = 0; r < ROWS; r++) {
         	for (int c = 0; c < COLS; c++) {
         		grid[r][c] = new BoardCell(r, c);
         	}
         }
    	 
    	 roomMap = new HashMap<>();
    	 
=======
>>>>>>> 85ac79c4a6e5b254563bbd0f4723f4ca64bebf1e
     }
	
	// Sets configuration files
	public void setConfigFiles(String csv, String txt) {
		this.layoutConfigFile = csv;
		this.setupConfigFile = txt;
	}
	
	// Load configuration files
	public void loadLayoutConfig() {
		//TODO: Implement
	}
	
	public void loadSetupConfig() {
		//TODO: Implement
	}
	
	// Getters for numRows and numCols
	public int getNumRows() {
		return this.numRows;
	}
	
	public int getNumColumns() {
		return this.numColumns;
	}
	
	// Get the room
	public Room getRoom(char roomInitial) {
<<<<<<< HEAD
		Room room = roomMap.get(roomInitial);
		if (room == null) {
			return new Room("Test", null, null);
		}
	    return room;
=======
	    return roomMap.get(roomInitial);
>>>>>>> 85ac79c4a6e5b254563bbd0f4723f4ca64bebf1e
	}
	
	public Room getRoom(BoardCell cell) {
		char initial = cell.getInitial(); // Assumes BoardCell has this method
	    return roomMap.get(initial);
	}
	
	// Gets adjacency list
	public Set<BoardCell> getAdjList(int x, int y) {
		BoardCell cell = new BoardCell(x,y); 
		calcTargets(cell, 1);
		return getTargets();
	}


	// Returns cell at row and col
	public BoardCell getCell(int row, int col) {
		return grid[row][col];
	}

	// Start target calculation
	public void calcTargets(BoardCell startCell, int pathlength) {
		targets = new HashSet<>();
		visited = new HashSet<>();
		visited.add(startCell);
		findAllTargets(startCell, pathlength);
	}

	// Target calculation method
	private void findAllTargets(BoardCell thisCell, int numSteps) {
		for (BoardCell adjCell : thisCell.getAdjList()) {
			// Skip visited cells
			if (visited.contains(adjCell))
				continue;

			// Skip occupied cells
			if (adjCell.getOccupied())
				continue;

			visited.add(adjCell);

			if (adjCell.room()) {
				targets.add(adjCell);
			} 
			else if (numSteps == 1) {
				targets.add(adjCell);
			} 
			else {
				findAllTargets(adjCell, numSteps - 1);
			}

			visited.remove(adjCell);
		}
	}

	// Getter for targets (returns empty set if not initialized)
	public Set<BoardCell> getTargets() {
		if (targets != null) {
			return targets;
		}
		else {
			return new HashSet<>();
		}
	}
<<<<<<< HEAD
	
	// Getter for Room Count
	public int getRoomCount() {
		return roomCount;
	}
	
	// Getter for Door Count
	public int getDoorCount() {
		return doorCount;
	}
=======
>>>>>>> 85ac79c4a6e5b254563bbd0f4723f4ca64bebf1e
}