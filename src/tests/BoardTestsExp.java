package tests;

import experiment.TestBoard;
import experiment.TestBoardCell;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTestsExp {

	private TestBoard board;

	@BeforeEach
	public void setUp() {
		board = new TestBoard();
	}


	// Tests adjacent squares of 2, 2
	@Test
	public void testAdjacency2_2() {
		TestBoardCell cell = board.getCell(2, 2);
		Set<TestBoardCell> testList = cell.getAdjList();
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertEquals(4, testList.size());
	}


	// Tests adjacent squares of 1, 3
	@Test
	public void testAdjacency1_3() {
		TestBoardCell cell = board.getCell(1, 3);
		Set<TestBoardCell> testList = cell.getAdjList();
		assertTrue(testList.contains(board.getCell(0, 3)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertEquals(3, testList.size());
	}

	// Tests adjacent squares of 0, 0
	@Test
	public void testAdjacency0_0() {
		TestBoardCell cell = board.getCell(0, 0);
		Set<TestBoardCell> testList = cell.getAdjList();
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertEquals(2, testList.size());
	}

	// Tests adjacent squares of 2, 1
	@Test
	public void testAdjacency2_1() {
		TestBoardCell cell = board.getCell(2, 1);
		Set<TestBoardCell> testList = cell.getAdjList();
		assertTrue(testList.contains(board.getCell(2, 0)));
		assertTrue(testList.contains(board.getCell(1, 1)));
		assertTrue(testList.contains(board.getCell(2, 2)));
		assertTrue(testList.contains(board.getCell(3, 1)));
		assertEquals(4, testList.size());
	}

	//Tests adjacent squares of 3, 3
	@Test
	public void testAdjacency3_3() {
		TestBoardCell cell = board.getCell(3, 3);
		Set<TestBoardCell> testList = cell.getAdjList();
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertEquals(2, testList.size());
	}


	//Tests the targets of a player at 0, 0 and a room at 0, 1 with a roll of 2
	@Test
	public void testTargetsRoom1() {
		board.getCell(0, 1).setRoom(true);
		TestBoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 2);
		Set<TestBoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(0, 2)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(1, 1)));
	}

	//Tests the targets of a player at 1, 1 and a room at 3, 1 with a roll of 3
	@Test
	public void testTargetsRoom2() {
		board.getCell(1, 1).setRoom(true);
		TestBoardCell cell = board.getCell(3, 1);
		board.calcTargets(cell, 3);
		Set<TestBoardCell> targets = board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(2, 3)));
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(3, 1)));
		assertTrue(targets.contains(board.getCell(3, 2)));
	}

	// Test the targets of a player at 3, 3 and an occupied space at 2, 2 with a roll of 3
	@Test
	public void testTargetsOcc1() {
		board.getCell(2, 2).setOccupied(true);
		TestBoardCell cell = board.getCell(3, 3);
		board.calcTargets(cell, 3);
		Set<TestBoardCell> targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(3, 0)));

	}

	// Test the targets of a player at 2, 1 and an occupied space at 2, 2 with a roll of 4
	@Test
	public void testTargetsOcc2() {
		board.getCell(2, 2).setOccupied(true);
		TestBoardCell cell = board.getCell(2, 1);
		board.calcTargets(cell, 4);
		Set<TestBoardCell> targets = board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(2, 3)));
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(3, 2)));

	}

	// Move from center cell with pathlength 1
	@Test
	public void testTargetsNormal1() {
		TestBoardCell cell = board.getCell(1, 1);
		board.calcTargets(cell, 1);
		Set<TestBoardCell> targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(1, 2)));
	}

	// Move from corner with pathlength 1
	@Test
	public void testTargetsNormal2() {
		TestBoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 1);
		Set<TestBoardCell> targets = board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 0)));
	}

	// Move from edge with pathlength 1
	@Test
	public void testTargetsNormal3() {
		TestBoardCell cell = board.getCell(0, 2);
		board.calcTargets(cell, 1);
		Set<TestBoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 2)));
	}

	// Move from center with pathlength 2
	@Test
	public void testTargetsNormal4() {
		TestBoardCell cell = board.getCell(1, 1);
		board.calcTargets(cell, 2);
		Set<TestBoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(0, 2)));
		assertTrue(targets.contains(board.getCell(1, 3)));
		assertTrue(targets.contains(board.getCell(2, 0)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(3, 1)));
	}

	// Move from edge with pathlength 2
	@Test
	public void testTargetsNormal5() {
		TestBoardCell cell = board.getCell(2, 0);
		board.calcTargets(cell, 2);
		Set<TestBoardCell> targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(1, 1)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(3, 1)));
	}

	// Move from corner with pathlength 3
	@Test
	public void testTargetsNormal6() {
		TestBoardCell cell = board.getCell(3, 3);
		board.calcTargets(cell, 3);
		Set<TestBoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(2, 0)));
		assertTrue(targets.contains(board.getCell(1, 3)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(3, 2)));
		assertTrue(targets.contains(board.getCell(1, 2)));
	}

	// From center with pathlength 4
	@Test
	public void testTargetsNormal7() {
		TestBoardCell cell = board.getCell(1, 1);
		board.calcTargets(cell, 4);
		Set<TestBoardCell> targets = board.getTargets();
		assertEquals(12, targets.size());
		assertTrue(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(0, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(1, 3)));
		assertTrue(targets.contains(board.getCell(2, 0)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(2, 3)));
		assertTrue(targets.contains(board.getCell(3, 1)));
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(3, 2)));
	}

	// Room reachable, occupied cell blocks a path
	@Test
	public void testTargetsMixed1() {
		board.getCell(1, 2).setOccupied(true);
		board.getCell(2, 1).setRoom(true);
		TestBoardCell cell = board.getCell(1, 1);
		board.calcTargets(cell, 2);
		Set<TestBoardCell> targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(2, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
	}

	// Room farther away, must avoid occupied cell
	@Test
	public void testTargetsMixed2() {
		board.getCell(1, 1).setOccupied(true);
		board.getCell(2, 1).setRoom(true);
		TestBoardCell cell = board.getCell(0, 1);
		board.calcTargets(cell, 3);
		Set<TestBoardCell> targets = board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(0, 2)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(2, 0)));
		assertTrue(targets.contains(board.getCell(1, 2)));
	}
}
