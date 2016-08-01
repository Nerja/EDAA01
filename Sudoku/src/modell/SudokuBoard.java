package modell;

public class SudokuBoard {
	private int[][] board;
	
	public SudokuBoard() {
		board = new int[9][9];
	}

	private boolean checkCol(int[][] boardToCheck, int col) {
		boolean taken[] = new boolean[9];
		for (int row = 0; row < 9; row++) {
			int nbr = boardToCheck[row][col];
			if(nbr == 0)
				continue;
			else if(nbr < 0 || nbr > 9)
				return false;
			else if(taken[nbr-1])
				return false;
			else 
				taken[nbr-1]=true;
		}
		return true;
	}
	
	private boolean checkRow(int[][] boardToCheck, int row) {
		boolean taken[] = new boolean[9];
		for (int col = 0; col < 9; col++) {
			int nbr = boardToCheck[row][col];
			if(nbr == 0)
				continue;
			else if(nbr < 0 || nbr > 9)
				return false;
			else if(taken[nbr-1])
				return false;
			else 
				taken[nbr-1]=true;
		}
		return true;
	}

	private boolean checkRegion(int[][] boardToCheck, int rr, int rc) {
		boolean taken[] = new boolean[9];
		for(int row = 3*rr; row < (rr+1)*3; row++) {
			for(int col = 3*rc; col < (rc+1)*3; col++) {
				int nbr = boardToCheck[row][col];
				if(nbr != 0 && taken[nbr-1])
					return false;
				else if(nbr != 0)
					taken[nbr-1]=true;
			}
		}
		return true;
	}
	
	public int[][] solve() {
		int[][] solution = board.clone(); 
		boolean solved = solve(solution, 0, 0);
		return solved == true ? solution : null;
	}
	
	private boolean isOk(int[][] boardToCheck, int row, int col) {
		int rr = (int)(row/3.0);
		int rc = (int)(col/3.0);
		boolean colOk = checkCol(boardToCheck, col);
		boolean rowOk = checkRow(boardToCheck, row);
		boolean regionOk = checkRegion(boardToCheck, rr, rc);
		return colOk && rowOk && regionOk;
	}
	
	private boolean solve(int[][] solution, int row, int col) {
		if(row < 0 || row > 8 || col < 0 || col > 8) {
			return true;
		} else if(solution[row][col] == 0) {
			for(int nbr = 1; nbr <= 9; nbr++) {
				solution[row][col] = nbr;
				if(isOk(solution, row, col)) 
					if(solve(solution, row+(int)(col/8.0), (col+1)%9))
						return true;
			}
			solution[row][col] = 0; //Pardon me for trying, duh
			return false;
		} else if(isOk(solution, row, col)) {
			return solve(solution, row+(int)(col/8.0), (col+1)%9);
		}
		return false;
	}
	
	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	public void setValue(int row, int col, int value) {
		if(value > 9 || value < 1)
			throw new IllegalArgumentException("Only values in [1,9]");
		else if(row < 0 || row > 8 || col < 0 || col > 8)
			throw new IllegalArgumentException("Only values in [0,8]");
		board[row][col] = value;
		if(!isOk(board, row, col)) {
			board[row][col] = 0; //Undo
			throw new IllegalArgumentException("The value " + value + " will result in unsolvable sudoku board");
		}
	}
	
	public int getValue(int row, int col) {
		if(row < 0 || row > 8 || col < 0 || col > 8)
			throw new IllegalArgumentException("Only values in [0,8]");
		return board[row][col];
	}

	public void clear() {
		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 9; col++)
				board[row][col] = 0;
	}
}
