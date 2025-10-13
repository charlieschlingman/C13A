package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoardCell {
	private int row;
	private int column;
	private boolean room;
	private boolean occupied;
	private Set<TestBoardCell> adjacencyList;

	// Constructor
	public TestBoardCell(int row, int column) {
		this.row = row;
		this.column = column;
		this.adjacencyList = new HashSet<>();
	}

	// Add a cell to the adjacency list
	public void addAdjacency(TestBoardCell cell) {
		adjacencyList.add(cell);
	}

	// Return the adjacency list
	public Set<TestBoardCell> getAdjList() {
		return adjacencyList;
	}

	// Set whether the cell is part of a room or not
	public void setRoom(boolean room) {
		this.room = room;
	}

	// Get whether the cell is part of a room or not
	public boolean room() {
		return room;
	}

	// Set whether the cell is occupied or not
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	// Get whether the cell is occupied or not
	public boolean getOccupied() {
		return occupied;
	}
}