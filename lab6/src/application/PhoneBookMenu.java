package application;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import phonebook.PhoneBook;

public class PhoneBookMenu extends MenuBar {
	private PhoneBook phoneBook;
	private NameListView nameListView;
	
	/** Creates the menu for the phone book application.
	 * @param phoneBook the phone book with names and numbers
	 * @param nameListView handles the list view for the names
	 */
	public PhoneBookMenu(PhoneBook phoneBook, NameListView nameListView) {
		this.phoneBook = phoneBook;
		this.nameListView = nameListView;

		final Menu menuPhoneBook = new Menu("PhoneBook");
		final MenuItem menuQuit = new MenuItem("Quit");
		menuQuit.setOnAction(e -> Platform.exit());
		menuPhoneBook.getItems().addAll(menuQuit);
	
		final Menu menuFind = new Menu("Find");
		
		final MenuItem menuShowAll = new MenuItem("Show All");
		final MenuItem menuFindNumbers = new MenuItem("Find number(s)");
		final MenuItem menuFindNames = new MenuItem("Find names");
		final MenuItem menuFindPersons = new MenuItem("Find persons");
		
		menuShowAll.setOnAction(e -> showAll());
		menuFindNumbers.setOnAction(e -> showNumbers());
		menuFindNames.setOnAction(e -> showNames());
		menuFindPersons.setOnAction(e -> showPersons());
		
		menuFind.getItems().addAll(menuShowAll, menuFindNumbers, menuFindNames, menuFindPersons);

	    getMenus().addAll(menuPhoneBook, menuFind);
  //    setUseSystemMenuBar(true);  // if you want operating system rendered menus, uncomment this line
	}

	
	private void showPersons() {
		Optional<String> name = Dialogs.oneInputDialog("Find persons", "", "Enter name");
		if(name.isPresent()) {
			Set<String> existingNames = phoneBook.names();
			Set<String> matchingNames = new HashSet<String>();
			for(String existingName : existingNames) 
				if(existingName.toLowerCase().startsWith(name.get().toLowerCase()))
					matchingNames.add(existingName);
			if(!matchingNames.isEmpty())
				nameListView.fillList(matchingNames);
			else
				Dialogs.alert("Name search", "", "No match found");
		}
	}


	private void showNames() {
		Optional<String> number = Dialogs.oneInputDialog("Find names", "", "number");
		if(number.isPresent()) {
			Set<String> names = phoneBook.findNames(number.get());
			if(names.isEmpty()) {
				Dialogs.alert("Find names", "", "No person with that number");
			} else {
				nameListView.fillList(names);
				nameListView.select(0);
			}
		}
	}


	private void showNumbers() {
		Optional<String> name = Dialogs.oneInputDialog("Find numbers", "", "Enter name");
		if(name.isPresent()) {
			Set<String> numbers = phoneBook.findNumbers(name.get());
			if(numbers.isEmpty()) {
				Dialogs.alert("Find numbers", "", "No such name exists");
			} else {
				List<String> names = new LinkedList<String>();
				names.add(name.get());
				nameListView.fillList(names);
				nameListView.select(0);
			}
		}
	}


	private void showAll() {
		nameListView.fillList(phoneBook.names());
		nameListView.clearSelection();
	}

}
