package application;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import phonebook.MapPhoneBook;
import phonebook.PhoneBook;

public class PhoneBookApplication extends Application{
	private PhoneBook phoneBook;
	private NameListView nameListView;

	/**
	 * The entry point for the Java program.
	 * @param args
	 */
	public static void main(String[] args) {	
		// launch() do the following:
		// - creates an instance of the Main class
		// - calls Main.init()
		// - create and start the javaFX application thread
		// - waits for the javaFX application to finish (close all windows)
		// the javaFX application thread do:
		// - calls Main.start(Stage s)
		// - runs the event handling loop
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		loadFile();
		
		// set default locale english 
		Locale.setDefault(Locale.ENGLISH);
		
		nameListView = new NameListView(phoneBook);
		BorderPane root = new BorderPane();
		root.setTop(new PhoneBookMenu(phoneBook, nameListView));
		root.setCenter(nameListView);		
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("PhoneBook");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	private void loadFile() throws IOException, FileNotFoundException, ClassNotFoundException {
		if(Dialogs.confirmDialog("S", "p", "Should we read from a file?")) {
			 FileChooser fs = new FileChooser();
			 fs.setTitle("Choose file");
			 File file = fs.showOpenDialog(null);
			 ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			 phoneBook = (MapPhoneBook)ois.readObject();
			 ois.close();
		} else {
			phoneBook = new MapPhoneBook();
		}
	}

	
	
	@Override
	public void stop() {
		if(Dialogs.confirmDialog("S", "p", "Should we save the phoneBook?")) {
			FileChooser fs = new FileChooser();
			fs.setTitle("Choose file");
			File file = fs.showSaveDialog(null);
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(phoneBook);
				oos.flush();
				oos.close();
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
			}
			
		}
	}
	
}
