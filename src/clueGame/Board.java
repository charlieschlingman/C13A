package clueGame;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import experiment.TestBoardCell;

public class Board {
	private BoardCell[][] grid;
	private int numRows;
	private int numColumns;
	private String layoutConfigFile;
	private String setupConfigFile;
	private Map<Character, Room> roomMap;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
	private int roomCount;
	private int doorCount;

	/*
	 * variable and methods used for singleton pattern
	 */
	private static Board theInstance = new Board();
	// constructor is private to ensure only one can be created
	private Board() {
		super() ;
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
		try {
			loadSetupConfig();
			loadLayoutConfig();
		} 
		catch (BadConfigFormatException e) {
			System.err.println("Configuration error: " + e.getMessage());
		}
	}

	// Sets configuration files
	public void setConfigFiles(String csv, String txt) {
		this.layoutConfigFile = "data/" + csv;
		this.setupConfigFile = "data/" + txt;
	}

	// Load configuration files
	public void loadSetupConfig() throws BadConfigFormatException {
		roomMap = new HashMap<>();

		try (Scanner in = new Scanner(new File(setupConfigFile))) {
			while (in.hasNextLine()) {
				String line = in.nextLine().trim();
				if (line.isEmpty() || line.startsWith("//")) continue;

				String[] parts = line.split(",");
				if (parts.length != 3) {
					throw new BadConfigFormatException("Invalid setup line: " + line);
				}

				String type = parts[0].trim();
				String name = parts[1].trim();
				char initial = parts[2].trim().charAt(0);

				if (!type.equals("Room") && !type.equals("Space")) {
					throw new BadConfigFormatException("Unknown room type: " + type);
				}

				roomMap.put(initial, new Room(name, null, null));
			}
		} 
		catch (FileNotFoundException e) {
			System.err.println("Setup file not found: " + setupConfigFile);
		}
	}

	public void loadLayoutConfig() throws BadConfigFormatException {
		List<String[]> lines = new ArrayList<>();

		try (Scanner in = new Scanner(new File(layoutConfigFile))) {
			while (in.hasNextLine()) {
				String line = in.nextLine().trim();
				if (line.isEmpty()) continue;
				lines.add(line.split(","));
			}
		} 
		catch (FileNotFoundException e) {
			System.err.println("Layout file not found: " + layoutConfigFile);
			return;
		}

		numRows = lines.size();
		numColumns = lines.get(0).length;

		// Validate consistent column counts
		for (String[] row : lines) {
			if (row.length != numColumns) {
				throw new BadConfigFormatException("Inconsistent column count in layout file.");
			}
		}

		grid = new BoardCell[numRows][numColumns];
		roomCount = 0;
		doorCount = 0;

		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numColumns; c++) {
				String cellCode = lines.get(r)[c].trim();
				if (cellCode.length() == 0) {
					throw new BadConfigFormatException("Empty cell at (" + r + "," + c + ")");
				}

				char initial = cellCode.charAt(0);
				if (!roomMap.containsKey(initial)) {
					throw new BadConfigFormatException("Invalid room initial: " + initial);
				}

				BoardCell cell = new BoardCell(r, c);
				cell.setRoom(initial != 'W' && initial != 'X');
				cell.setOccupied(false);
				setDoorAttributes(cell, cellCode);
				grid[r][c] = cell;

				// Count rooms and doors for testing
				if (cell.room()) roomCount++;
				if (cell.isDoorway()) doorCount++;
			}
		}
		
		calcAdjacencies();
	}

	// Assign door directions and special flags
	private void setDoorAttributes(BoardCell cell, String code) {
		cell.setDoorDirection(DoorDirection.NONE);
		if (code.length() > 1) {
			char symbol = code.charAt(1);
			switch (symbol) {
			case '^':
				cell.setDoorDirection(DoorDirection.UP);
				break;
			case 'v':
				cell.setDoorDirection(DoorDirection.DOWN);
				break;
			case '<':
				cell.setDoorDirection(DoorDirection.LEFT);
				break;
			case '>':
				cell.setDoorDirection(DoorDirection.RIGHT);
				break;
			case '*': 
				cell.setRoomCenter(true); 
				break;
	        case '#': 
	        	cell.setLabel(true); 
	        	break;
			default:
				cell.setDoorDirection(DoorDirection.NONE);
				break;
			}
		}
		if (code.length() == 2 && Character.isLetter(code.charAt(1))) {
		    cell.setSecretPassage(code.charAt(1));
		}
	}
	
	private void calcAdjacencies() {
	    for (int row = 0; row < numRows; row++) {
	        for (int col = 0; col < numColumns; col++) {
	            BoardCell cell = grid[row][col];
	            Set<BoardCell> adj = new HashSet<>();

	            // Skip non-walkway cells unless they are doorways
	            if (!isWalkway(cell) && !cell.isDoorway()) {
	                cell.getAdjList().clear();
	                continue;
	            }
	            
	            if (cell.getSecretPassage() != 0) {
	                char destInitial = cell.getSecretPassage();
	                Room destRoom = roomMap.get(destInitial);
	                if (destRoom != null && destRoom.getCenterCell() != null) {
	                    cell.addAdjacency(destRoom.getCenterCell());
	                }
	            }
	            
	            if (cell.isDoorway()) {
	                Room room = getRoom(cell);
	                if (room != null && room.getCenterCell() != null) {
	                    cell.addAdjacency(room.getCenterCell());
	                    room.getCenterCell().addAdjacency(cell);
	                }
	            }

	            // Check each of the four directions
	            addAdjacencyIfValid(adj, row - 1, col, DoorDirection.DOWN, cell);  // Up
	            addAdjacencyIfValid(adj, row + 1, col, DoorDirection.UP, cell);    // Down
	            addAdjacencyIfValid(adj, row, col - 1, DoorDirection.RIGHT, cell); // Left
	            addAdjacencyIfValid(adj, row, col + 1, DoorDirection.LEFT, cell);  // Right

	            // Store it
	            cell.getAdjList().clear();
	            cell.getAdjList().addAll(adj);
	        }
	    }
	}
	
	private boolean isWalkway(BoardCell cell) {
	    return cell.getInitial() == 'W';
	}

	private void addAdjacencyIfValid(Set<BoardCell> adj, int row, int col, DoorDirection neededDoorDir, BoardCell origin) {
	    if (row < 0 || row >= numRows || col < 0 || col >= numColumns) return;

	    BoardCell other = grid[row][col];
	    // Handle walkways
	    if (isWalkway(other)) {
	        adj.add(other);
	    }
	    // Handle doors
	    else if (other.isDoorway() && other.getDoorDirection() == neededDoorDir) {
	        adj.add(other);
	    }
	    // Handle doorway cells themselves
	    else if (origin.isDoorway() && origin.getDoorDirection() == neededDoorDir) {
	        adj.add(other);
	    }
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
		Room room = roomMap.get(roomInitial);
		if (room == null) {
			return new Room("Test", null, null);
		}
		return room;
	}

	public Room getRoom(BoardCell cell) {
		char initial = cell.getInitial(); // Assumes BoardCell has this method
		return roomMap.get(initial);
	}

	// Gets adjacency list
	public Set<BoardCell> getAdjList(int x, int y) {
	    return grid[x][y].getAdjList();
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

	// Getter for Room Count
	public int getRoomCount() {
		return roomCount;
	}

	// Getter for Door Count
	public int getDoorCount() {
		return doorCount;
	}
}