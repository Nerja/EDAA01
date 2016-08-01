package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modell.SudokuBoard;

public class SolverTest {
	private SudokuBoard board;
	
	@Before
	public void setUp(){
		board = new SudokuBoard();
	}
	
	@Test
	public void testOneCase() {
		int[][] boardToSolve = {{0, 0, 8, 0, 0, 9, 0, 6, 2},{0, 0, 0, 0, 0, 0, 0, 0, 5},{1, 0, 2, 5, 0, 0, 0, 0, 0}, {0, 0, 0, 2, 1, 0, 0, 9, 0},{0, 5, 0, 0, 0, 0, 6, 0, 0},{6, 0, 0, 0, 0, 0, 0, 2, 8}, {4, 1, 0, 6, 0, 8, 0, 0, 0},{8, 6, 0, 0, 3, 0, 1, 0, 0},{0, 0, 0, 0, 0, 0, 4, 0, 0}};
		int[][] expectedSolution = {{5, 4, 8, 1, 7, 9, 3, 6, 2},{3, 7, 6, 8, 2, 4, 9, 1, 5},{1, 9, 2, 5, 6, 3, 8, 7, 4}, {7, 8, 4, 2, 1, 6, 5, 9, 3},{2, 5, 9, 3, 8, 7, 6, 4, 1},{6, 3, 1, 9, 4, 5, 7, 2, 8}, {4, 1, 5, 6, 9, 8, 2, 3, 7},{8, 6, 7, 4, 3, 2, 1, 5, 9},{9, 2, 3, 7, 5, 1, 4, 8, 6}};
		board.setBoard(boardToSolve);
		int[][] result = board.solve();
		if(result == null)
			fail("There exists a solution!");
		else
			assertArrayEquals("Wrong solution", expectedSolution, result);
	}
}
