package gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modell.SudokuBoard;

public class SolveButton extends Button {

	private SudokuBoard board;
	private TextField[][] fields;
	
	public SolveButton(SudokuBoard board, TextField[][] fields) {
		super("Solve");
		this.board = board;
		this.fields = fields;
		this.setOnMouseClicked(e -> solve());
	}

	private void solve() {
		int[][] solution = board.solve();
		if(solution != null) 
			for(int row = 0; row < solution.length; row++) 
				for(int col = 0; col < solution[0].length; col++)
					fields[row][col].setText(solution[row][col]+"");
	}
}
