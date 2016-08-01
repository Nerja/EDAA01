package gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modell.SudokuBoard;

public class ClearButton extends Button {

	private SudokuBoard board;
	private TextField[][] fields;
	
	public ClearButton(SudokuBoard board, TextField[][] fields) {
		super("Clear");
		this.board = board;
		this.fields = fields;
		setOnMouseClicked(e -> clear());
	}

	private void clear() {
		board.clear();
		for(int i = 0; i < fields.length; i++) 
			for(int j = 0; j < fields[0].length; j++) 
				fields[i][j].setText("");
	}

}
