package map;

import java.util.Random;

public class SimpleHashMap<K, V> implements Map<K,V> {
	private Entry<K, V> table[];
	private static final double MAX_LOAD = 0.75;
	private static final int DEFAULT_CAP = 16;
	private int size;
	
	public SimpleHashMap(int cap) {
		table = (Entry<K,V>[])new Entry[cap];
	}
	
	public SimpleHashMap() {
		this(DEFAULT_CAP);
	}
	
	@Override
	public V get(Object arg0) {
		K key = (K)arg0;
		int index = index(key);
		Entry<K, V> element = find(index, key);
		return element != null ? element.value : null;
	}

	@Override
	public boolean isEmpty() {
		return size <= 0;
	}
	
	private void rehash2() {
		long tt = System.currentTimeMillis();
		System.out.println("Rehash activated");
		Entry<K, V> oldTable[] = table;
		table = (Entry<K,V>[])new Entry[2*table.length];
		size = 0;
		for(Entry<K, V> entry : oldTable) {
			while(entry != null) {
				put(entry.key, entry.value);
				entry = entry.next;
			}
		}
		System.out.println((System.currentTimeMillis() - tt) + " ms");
	}
	
	private void rehash() {
		long tt = System.currentTimeMillis();
		System.out.println("Rehash 2 activated");
		Entry<K, V> oldTable[] = table;
		table = (Entry<K,V>[])new Entry[2*table.length];
		for(Entry<K, V> entry : oldTable) {
			while(entry != null) {
				int index = index(entry.getKey());
				Entry<K, V> nextEntry = entry.next;
				entry.next = table[index];
				table[index] = entry;
				entry = nextEntry;
			}
		}
		System.out.println((System.currentTimeMillis() - tt) + " ms");
	}

	@Override
	public V put(K key, V value) {
		int index = index(key);
		Entry<K, V> element = find(index, key);
		if(element == null) {
			Entry<K, V> newEntry = new Entry<K, V>(key, value);
			newEntry.next = table[index];
			table[index] = newEntry;
			size++;
			if(size/((double)table.length) > 0.75)
				rehash();
		}
		return element == null ? null : element.setValue(value);
	}

	@Override
	public V remove(Object arg0) {
		K key = (K)arg0;
		int index = index(key);
		Entry<K, V> element = table[index];
		if(element == null) {
			return null;
		} else if(element.getKey().equals(key)) {
			table[index] = element.next;
			size--;
			return element.value;
		} else {
			while(element.next != null && !element.next.key.equals(key)) 
				element = element.next;
			if(element.next != null) {
				size--;
				return element.next.value;
			} else {
				return null;
			}
		}
	}
	
	private int index(K key) {
		int index = key.hashCode() % table.length;
		return index >= 0 ? index : index + table.length;
	}
	
	private Entry<K, V> find(int index, K key) {
		Entry<K,V> curr = table[index];
		while(curr != null && !curr.getKey().equals(key))
			curr = curr.next;
		return curr;
	}

	@Override
	public int size() {
		return size;
	}
	
	private static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K,V> next;
		
		private Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V old = this.value;
			this.value = value;
			return old;
		}
		
		@Override
		public String toString() {
			return key + " -> " + value;
		}
		
	}

}
