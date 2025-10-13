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

}
