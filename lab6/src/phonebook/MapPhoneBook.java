package phonebook;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapPhoneBook implements PhoneBook, Serializable {

	private Map<String, Set<String>> phoneBook;

	public MapPhoneBook() {
		phoneBook = new TreeMap<String, Set<String>>();
	}

	@Override
	public boolean put(String name, String number) {
		Set<String> nbrs = phoneBook.get(name);
		if (nbrs == null) {
			nbrs = new HashSet<String>();
			phoneBook.put(name, nbrs);
		}
		return nbrs.add(number);
	}

	@Override
	public boolean remove(String name) {
		return phoneBook.remove(name) != null;
	}

	@Override
	public boolean removeNumber(String name, String number) {
		Set<String> nbrs = phoneBook.get(name);
		return nbrs != null ? nbrs.remove(number) : false;
	}

	@Override
	public Set<String> findNumbers(String name) {
		Set<String> returnNumbers = new HashSet<String>();
		Set<String> numbers = phoneBook.get(name);
		if (numbers != null)
			returnNumbers.addAll(numbers);
		return numbers;
	}

	@Override
	public Set<String> findNames(String number) {
		Set<String> names = new HashSet<String>();
		for (Map.Entry<String, Set<String>> entry : phoneBook.entrySet())
			if (entry.getValue().contains(number))
				names.add(entry.getKey());
		return names;
	}

	@Override
	public Set<String> names() {
		return new HashSet<String>(phoneBook.keySet());
	}

	@Override
	public int size() {
		return phoneBook.size();
	}

}
