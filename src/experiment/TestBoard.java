package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoard {
	private Set<TestBoardCell> targets;
    private Set<TestBoardCell> visited;

    // Empty constructor
    public TestBoard() {
        // TODO
    }

    // Returns cell at row and col
    public TestBoardCell getCell(int row, int col) {
        // TODO implement
    	return null;
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