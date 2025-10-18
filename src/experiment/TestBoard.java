package experiment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestBoard {
	private TestBoardCell[][] grid;
	private Set<TestBoardCell> targets;
    private Set<TestBoardCell> visited;
    
    final static int COLS = 4;
    final static int ROWS = 4;

    // Empty constructor
    public TestBoard() {
        grid = new TestBoardCell[ROWS][COLS];
        
        // Create cells
        for (int r = 0; r < ROWS; r++) {
        	for (int c = 0; c < COLS; c++) {
        		grid[r][c] = new TestBoardCell(r, c);
        	}
        }
        
        // Set adjacencies
        for (int r = 0; r < ROWS; r++) {
        	for (int c = 0; c < COLS; c++) {
        		TestBoardCell cell = grid[r][c];
        		if (cell.getRow() == 0) {
        			cell.addAdjacency(grid[r + 1][c]);
        		} 
        		if (cell.getRow() == 1 || cell.getRow() == 2) {
        			cell.addAdjacency(grid[r + 1][c]);
        			cell.addAdjacency(grid[r - 1][c]);
        		}
        		if (cell.getRow() == 3) {
        			cell.addAdjacency(grid[r - 1][c]);
        		} 
        		if (cell.getColumn() == 0) {
        			cell.addAdjacency(grid[r][c + 1]);
        		}
        		if (cell.getColumn() == 1 || cell.getColumn() == 2) {
        			cell.addAdjacency(grid[r][c + 1]);
        			cell.addAdjacency(grid[r][c - 1]);
        		}
        		if (cell.getColumn() == 3) {
        			cell.addAdjacency(grid[r][c - 1]);
        		}
        	}
        }
        
    }
    
    



	// Returns cell at row and col
    public TestBoardCell getCell(int row, int col) {
    	return grid[row][col];
    }

    // Start target calculation
    public void calcTargets(TestBoardCell startCell, int pathlength) {
        targets = new HashSet<>();
        visited = new HashSet<>();
        visited.add(startCell);
        findAllTargets(startCell, pathlength);
    }

    // Target calculation method
    private void findAllTargets(TestBoardCell thisCell, int numSteps) {
    	// TODO implement
    }

    // Getter for targets (returns empty set if not initialized)
    public Set<TestBoardCell> getTargets() {
    	if (targets != null) {
    		return targets;
    	}
    	else {
    		return new HashSet<>();
    	}
    }
    
    
}