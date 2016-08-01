package gui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modell.SudokuBoard;

public class SudokuApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();
		
		TilePane boxes = new TilePane();
		boxes.setPrefColumns(9);
		boxes.setPrefRows(9);
		
		SudokuBoard board = new SudokuBoard();
		TextField[][] fields = new TextField[9][9];
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				SudokoInputField sitf = new SudokoInputField(board, row, col);
				fields[row][col] = sitf;
				boxes.getChildren().add(sitf);
			}
		}
		
		SolveButton solveButton = new SolveButton(board, fields);
		ClearButton clearButton = new ClearButton(board, fields);
		HBox buttons = new HBox();
		buttons.getChildren().add(solveButton);
		buttons.getChildren().add(clearButton);
		
		Scene scene = new Scene(root);
		root.getChildren().add(boxes);
		root.getChildren().add(buttons);
		primaryStage.setTitle("Sudoku");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
	        launch(args);
	}
}
