package clueGame;

import java.util.HashSet;
import java.util.Set;

public class BoardCell {
	private int row;
	private int column;
	private boolean room;
	private boolean occupied;
	private char initial;
	private char secretPassage;
	private DoorDirection doorDirection;
	private Set<BoardCell> adjacencyList;

	// Constructor
	public BoardCell(int row, int column) {
		this.row = row;
		this.column = column;
		this.adjacencyList = new HashSet<>();
	}

	// Get initial
	public char getInitial() {
		return this.initial;
	}

	// Get secretPassage
	public char getSecretPassage() {
		return this.secretPassage;
	}

	// Get the doorDirection
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

	// Add a cell to the adjacency list
	public void addAdjacency(BoardCell cell) {
		adjacencyList.add(cell);
	}

	// Return the adjacency list
	public Set<BoardCell> getAdjList() {
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

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	// Check cell type
	public boolean isLabel() {
		// TODO: Implement
		return true;
	}

	public boolean isRoomCenter() {
		// TODO: Implement
		return true;
	}

	public boolean isDoorway() {
		// TODO: Implement
		return true;
	}



}