package datastructures;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class MyHashMap<K, V> {
	
	private class Entry {
		K key;
		V value;
		int hash;
		Entry next;
		
		public Entry(K key, V value, int hash, Entry next) {
			this.key = key;
			this.value = value;
			this.hash = hash;
			this.next = next;
		}
	}
	
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	private static final int DEFAULT_CAPACITY = 16;
	
	private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;

	private Object[] table ;
	
	private int size = 0;
	
	private float loadfactor = DEFAULT_LOAD_FACTOR;
	
	public MyHashMap(int initialCapacity, float loadfactor) {
		if(initialCapacity <= 0) 
			initialCapacity = DEFAULT_CAPACITY;
		
		if (initialCapacity > MAX_CAPACITY)
			initialCapacity = MAX_CAPACITY;
		
		this.table = new Object[initialCapacity];
		if (loadfactor <= 0 || loadfactor > 1.0f) 
			loadfactor = this.loadfactor;
		
		this.loadfactor = loadfactor;
	}
	
	public MyHashMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}
	
	public MyHashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	
	public V put(K key, V value) {
		int index = 0;
		int hash = 0;
		
		if (key != null) {
			hash = key.hashCode();
			index = hash % table.length;
		}
		
		if (table[index] == null) {
			table[index] = new Entry(key, value, hash, null);
		} else {
			Entry entry = (Entry)table[index];
			while (entry != null) {
				if(entry.key == null) {
					
				} else if(entry.key.equals(key) && entry.hash == hash) {
					V old = entry.value;
					entry.value = value;
					return old;
				}
				entry = entry.next;
			}
			table[index] = new Entry(key, value, hash, entry);
		}
		size++;
		return value;
	}
	
	public V get(K key) {
		int index = 0;
		int hash = 0;
		
		if(size >= table.length * this.loadfactor) 
			rehash();
		
		if (key != null) {
			hash = key.hashCode();
			index = hash % table.length; // this will break if the hashcode returns a negative value for the key
		}
		
		Entry entry = (Entry) table[index];
		while(entry != null) {
			if(entry.key == null && key == null)
				return entry.value;
			else if(entry.key.equals(key) && entry.hash == hash)
				return entry.value;
			entry = entry.next;
		}
		return null;
	}
	
	public void rehash() {
		int length = table.length;
		int newLength = length * 2;
		if(newLength == Integer.MAX_VALUE) {
			return;
		}
		if(newLength > (Integer.MAX_VALUE-8)) {
			newLength = Integer.MAX_VALUE;
		}
		
		Object[] newTable = new Object[newLength];
		
		for(Object obj : table) {
			Entry entry = (Entry) obj;
			while(entry != null) {
				
				int hash = entry.hash;
				int index= hash % newLength;
				
				Entry next = entry.next;
				
				Entry e = (Entry) newTable[index];
				entry.next = e;
				newTable[index] = entry;
				
				entry = next;
			}
		}
		
		this.table = newTable;
		
	}
	
	public Set<K> keySet() {
		
		return new AbstractSet<K>() {

			@Override
			public Iterator<K> iterator() {
				return new KeyIterator();
			}

			@Override
			public int size() {
				return size;
			}
		};
	}
	
	private abstract class HashIterator<E> implements Iterator<E> {
		int index = 0;
		Entry next;
		
		public HashIterator() {
			if(size >0) {
				while (index < table.length && (next = (Entry) table[index++]) == null);
			}
		}

		@Override
		public boolean hasNext() {
			return next != null;
		}
		
		public Entry nextEntry() {
			
			if (next == null)
				throw new NoSuchElementException();
			
			Entry val = next;
			next = next.next;
			
			if (next == null) {
				while (index < table.length && (next = (Entry) table[index++]) == null);
			}
			return val;
			
		}
		
	}
	
	private class KeyIterator<K> extends HashIterator {

		@Override
		public K next() {
			return (K) nextEntry().key;
		}
		
	}
	
	private class ValueIterator<V> extends HashIterator {

		@Override
		public V next() {
			return (V) nextEntry().value;
		}
		
	}
	
	
}
