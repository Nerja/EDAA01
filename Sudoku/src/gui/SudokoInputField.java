package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import modell.SudokuBoard;

public class SudokoInputField extends OneDigitTextField {
	
	private SudokuBoard board;
	private int row, col;
	
	public SudokoInputField(SudokuBoard board, int row, int col) {
		this.board = board;
		this.col = col;
		this.row = row;
		setOnKeyReleased(e -> updateBox());
		setPrefSize(40, 40);
		String color = "66b3ff";
		if((int)(row/3.0) % 2 == 0 && (int)(col/3.0) % 2 == 0 || (int)(row/3.0) == 1 && (int)(col/3.0) % 2 == 1) 
			color="ffff80;";
		setStyle("-fx-control-inner-background: #"+color+";");
	}

	private void updateBox() {
		try{
			board.setValue(row, col, Integer.parseInt(getText()));
		}catch(NumberFormatException nfe) {
			//Skip
		}catch(IllegalArgumentException err) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Bad input");
			alert.setHeaderText("Unsolvable Sudokuboard");
			alert.setContentText(err.getMessage());
			alert.showAndWait();
		}
	}
	
}
