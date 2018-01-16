package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import datastructures.MyGraph;
import datastructures.MyTrie;

public class CTCI_Hard {
	
	private static class BiNode<K> {
		
		private BiNode<K> left, right;
		private K data;
		
		public BiNode(K data) {
			this.data = data;
		}
		
		@Override
		public String toString() {
			return String.valueOf(data);
		}
	}
	
	private static class MissingNumber {
		
		private int arr[] ;
		
		public MissingNumber(int[] arr) {
			this.arr = arr;
		}
		
		public int jthBit(int i, int j) {
			if (i < 0 || i >= arr.length)
				return -1;
			
			return (arr[i] >>> j) & 1;
		}
	}
	
	private Random random = new Random();
	
	public static void main(String[] args) {
		
		CTCI_Hard run = new CTCI_Hard();
		
		System.out.println(run.add(759, 674) + "==" + (759 + 674));
		
		int[] cards = new int[52];
		for(int i=0; i<cards.length; i++)
			cards[i] = i+1;
		
		run.shuffle(cards);
		System.out.println(Arrays.toString(cards));
		
		int arr[] = {0,1,3,4,5};
		System.out.println(Arrays.toString(run.randomSet(arr, 3)));
		//System.out.println(Arrays.toString(run.randomSetCorrect(arr, 4)));
		MissingNumber missingNumber = new MissingNumber(arr);
		System.out.println(run.missingNumber(missingNumber, 5));
		System.out.println(1 >>> 2 & 2);
		
		List<BitInteger> list = new ArrayList<>();
		list.add(new BitInteger(1));
		list.add(new BitInteger(2));
		list.add(new BitInteger(0));
		list.add(new BitInteger(4));
		list.add(new BitInteger(3));
		System.out.println(run.missingNumber(list, 0));
		
		int[] subarr = {0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0};
		System.out.println(Arrays.toString(run.longestSubarraySame1s0s(subarr)));
		System.out.println(Arrays.toString(subarr));
		System.out.println(Arrays.toString(run.longestSubarraySame1s0sLinearTime(subarr)));
		System.out.println(run.numberOf2s(1000));
		
		Synonyms syn1 = new Synonyms("John", "Jon");
		Synonyms syn2 = new Synonyms("Johnny", "John");
		Synonyms syn3 = new Synonyms("Chris", "Kris");
		Synonyms syn4 = new Synonyms("Chris", "Christopher");
		
		List<Synonyms> synonyms = new ArrayList<>();
		synonyms.add(syn1);
		synonyms.add(syn2);
		synonyms.add(syn3);
		synonyms.add(syn4);
		
		Map<String, Integer> freq = new HashMap<>();
		freq.put("John", 15);
		freq.put("Jon", 12);
		freq.put("Chris", 13);
		freq.put("Kris", 4);
		freq.put("Christopher", 19);
		
		System.out.println(run.babyNamesActualFreq(freq, synonyms));
		
		Person[] circusMen = {new Person(65, 100), new Person(70, 150), new Person(56, 90), 
							  new Person(75, 190), new Person(60, 95), new Person(68, 110)};
		
		System.out.println(run.circusTower(circusMen));
		
		//1, 3, 5, 7, 9, 15, 21, 25, 35, 49
		System.out.println(run.kthValue357(19));
		System.out.println(run.getKthValue357(19));
		int majority[] = {5, 2, 5, 5, 5, 5, 9, 4, 9};
		System.out.println(run.majorityElement(majority));
		String book[] = {"de", "cuando", "voy", "por", "la", "calle", "y", "me", "acuerdo", "de", "ti"};
		System.out.println(run.closestDistance(book, "por", "de"));
		System.out.println(run.closestDistanceWithoutPreprocessing(book, "por", "de"));
		
		
		BiNode<Integer> node = new BiNode<Integer>(6);
		node.left = new BiNode<Integer>(4);
		node.left.left = new BiNode<Integer>(2);
		node.left.right = new BiNode<Integer>(5);
		
//		node.right.left = new BiNode<Integer>(5);
//		node.right.left.right = new BiNode<Integer>(7);
//		node.left.left = new BiNode<Integer>(1);
//		node.left.right = new BiNode<Integer>(3);
//		node.left.right.left = new BiNode<Integer>(3);
//		node.left.left.left = new BiNode<Integer>(0);
//		node.right.right = new BiNode<Integer>(6);
		
//		BiNode<Integer> head[] = new BiNode[1];
//		run.convertToLinkedList(node, head, false);
//		BiNode<Integer> h = head[0];
//		while (h != null) {
//			System.out.print(h +"~>");
//			h = h.right;
//		}
		
		BiNode<Integer> head = run.convertToLinkedListEfficient(node);
		head.left.right = null;
		head.left = null;
		while (head != null) {
			System.out.print(head +"~>");
			head = head.right;
		}
		System.out.println();
		
		Set<String> dict = new HashSet<>();
		dict.add("like");
		dict.add("looked");
		dict.add("her");
		dict.add("just");
		dict.add("mother");
		dict.add("brother");
		dict.add("look");
		System.out.println(run.getSpacedDocument("jesslookedjustliketimherbrother", 0,dict));
		
		int[] arr1 = new int[20];
		
		int index =0;
		Random r = new Random();
		while (index < arr1.length) {
			arr1[index++] = r.nextInt(50);
		}
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(run.smallestKSelectionSort(arr1, 7)));
		
		String[] words = {"dog", "nana", "banana", "walker", "walk", "dogwalker", "cat"};
		System.out.println(run.longestWordMadeOfOtherWords(words));
		
		int mins[] = {30, 15, 60, 75, 45, 150, 15, 45};
		System.out.println(run.massuese(mins));
		System.out.println(run.massueseConstantSpace(mins));
		System.out.println(run.massueseConstantSpace1(mins));
		String T[] = {"is", "ppi", "hi", "sis", "i", "ssippi"};
		String b = "mississippi";
		run.multiSearch(b, T);
	}
	
	public void multiSearch(String b, String[] T) {
		MyTrie suffixTree = buildSuffixTree(b);
		for (String s : T) {
			if (suffixTree.contains(s)) {
				System.out.print(s+", ");
			}
		}
		System.out.println();
	}
	
	private MyTrie buildSuffixTree(String document) {
		MyTrie trie = new MyTrie();
		for (int i=0; i<document.length(); i++) {
			for (int j=document.length(); j>i; j--) {
				String suffix = document.substring(i, j);
				trie.add(suffix);
			}
		}
		return trie;
	}
	
	public int massueseConstantSpace1(int[] mins) {
		int oneBefore = 0;
		int twoBefore = 0;
		
		for (int i=0; i<mins.length; i++) {
			int bestWith = mins[i] + twoBefore;
			int bestWithout = oneBefore;
			int current = Math.max(bestWith, bestWithout);
			twoBefore = oneBefore;
			oneBefore = current;
		}
		
		return oneBefore;
	}
	
	public int massueseConstantSpace(int[] mins) {
		int oneAway = 0;
		int twoAway = 0;
		
		for (int i=mins.length-1; i>=0; i--) {
			int bestWith = mins[i] + twoAway;
			int bestWithout = oneAway;
			int current = Math.max(bestWith, bestWithout);
			twoAway = oneAway;
			oneAway = current;
		}
		
		return oneAway;
	}
	
	public int massuese(int[] mins) {
		
		int dp[] = new int[mins.length];
		dp[0] = mins[0];
		dp[1] = mins[1];
		
		for (int i=2; i<mins.length; i++) {
			dp[i] = Math.max(dp[i-1], mins[i] + dp[i-2]);
			if (i >= 3) {
				dp[i] = Math.max(dp[i], mins[i] + dp[i-3]);
			}
		}
		
		return Math.max(dp[mins.length-1], dp[mins.length-2]);
	}
	
	private class LengthComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			if (o1.length() >= o2.length())
				return -1;
			else
				return 1;
		}
		
	}
	
	public String longestWordMadeOfOtherWords(String[] words) {
		Map<String, Boolean> map = new HashMap<>();
		for(String s : words) {
			map.put(s, true);
		}
		
		Arrays.sort(words, new LengthComparator());
		
		for (String longest: words) {
			if (isMadeOfOtherWords(longest, true, map))
				return longest;
		}
		
		return "";
	}
	
	private boolean isMadeOfOtherWords(String word, boolean isOriginalWord, Map<String, Boolean> map) {
		if (map.containsKey(word) && !isOriginalWord) {
			return map.get(word);
		}
		
		for (int i=1; i<word.length(); i++) {
			String left = word.substring(0, i);
			String right = word.substring(i);
			if (map.getOrDefault(left, false) && isMadeOfOtherWords(right, false, map))
				return true;
		}
		
		map.put(word, false);
		return false;
	}
	
	public int[] smallestKSelectionSort(int[] arr, int k) {
		
		if (k > arr.length || k < 0)
			return null;
		
		if (k == arr.length)
			return arr;
		
		int low = 0;
		int high = arr.length-1;
		
		for (int i=0; i<k; i++) {
			int index = randomizedPartition(arr, low, high);
			if (index == k) {
				return arr;
			}
			
			if (index < k) {
				low = index + 1;
			} else {
				high = index-1;
			}
		}
		
		return arr;
		
	}
	
	private int randomizedPartition(int[] arr, int low, int high) {
		Random r = new Random();
		int index = r.nextInt(high-low+1) + low;
		swap(arr, index, high);
		return partition(arr, low, high);
	}
	
	private int partition(int[] arr, int low, int high) {
		int pivot = high;
		int j = high-1;
		while (low <= j) {
			if (arr[low] > arr[pivot]) {
				swap(arr, low, j);
				j--;
			} else {
				low++;
			}
		}
		swap(arr, j+1, high);
		return j+1;
	}
	
	
	public int[] smallestK(int[] arr, int k) {
		if (k > arr.length || k < 0)
			return null;
		
		if (k == arr.length)
			return arr;
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 >= o2)
					return -1;
				else
					return 1;
			}
		});
		
		for (int a : arr) {
			if (maxHeap.size() < k) {
				maxHeap.add(a);
			} else {
				maxHeap.add(a);
				maxHeap.poll();
			}
		}
		
		int[] res = new int[k];
		while(!maxHeap.isEmpty()) {
			res[k-1] = maxHeap.poll();
			k--;
		}
		
		return res;
		
	}
	
	class ParseResult {
		int invalid;
		String parsed;
		public ParseResult(int invalid, String parsed) {
			this.parsed = parsed;
			this.invalid = invalid;
		}
		
		@Override
		public String toString() {
			return this.parsed;
		}
	}
	
	public ParseResult getSpacedDocument(String document, int start, Set<String> dict) {
		
		if (start >= document.length())
			return new ParseResult(0, "");
		
		int index = start;
		int minInvalid = Integer.MAX_VALUE;
		String minParsed = "";
		ParseResult parseResult;
		
		while (index < document.length()) {
			
			String partial = document.substring(start, index+1);
			int invalid = dict.contains(partial) ? 0 : partial.length();
			
			if (invalid < minInvalid) {
				parseResult = getSpacedDocument(document, index + 1, dict);
				if (invalid + parseResult.invalid < minInvalid) {
					minInvalid = invalid + parseResult.invalid;
					minParsed = partial + " " + parseResult.parsed;
					if (minInvalid == 0)
						break;
				}
			}
			
			index++;
		}
		
		return new ParseResult(minInvalid, minParsed);
	}
	
	
	public BiNode<Integer> convertToLinkedListEfficient(BiNode<Integer> node) {
		if (node == null) 
			return null;
		
		BiNode<Integer> prev = convertToLinkedListEfficient(node.left);
		BiNode<Integer> next = convertToLinkedListEfficient(node.right);
		
		if (prev == null && next == null) {
			node.left = node;
			node.right = node;
			return node;
		}
		
		BiNode<Integer> tail = next == null ? null : next.left;
		
		if (prev == null) {
			concat(next.left, node);
		} else {
			concat(prev.left, node);
		}
		
		if (next == null) {
			concat(node, prev);
		} else {
			concat(node, next);
		}
		
		if (prev != null && next != null) {
			concat(tail, prev);
		}
		
		return prev != null ? prev : node;
	}
	
	private void concat(BiNode<Integer> node1, BiNode<Integer> node2) {
		node1.right = node2;
		node2.left = node1;
	}
	
	public BiNode<Integer> convertToLinkedList(BiNode<Integer> node, BiNode<Integer> head[], boolean rightOfRoot) {
		if (node == null)
			return null;
		
		if (node.left == null && node.right == null && head[0] == null) {
			head[0] = node;
			return node;
		}
		
		if (node.data == 5) {
			System.out.print("");
		}
		
		BiNode<Integer> prev = convertToLinkedList(node.left, head, false);
		BiNode<Integer> next = convertToLinkedList(node.right, head, true);
		
		if (prev != null) {
			prev.right = node;
			node.left = prev;
		}
		
		if (next != null) {
			next.left = node;
			node.right = next;
		}
		
		if (rightOfRoot) {
			return prev != null ? head(prev) : node;
		} else {
			return next != null ? tail(next) : node;
		}
		
	}
	
	private BiNode<Integer> head(BiNode<Integer> node) {
		if (node == null)
			return null;
		
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	private BiNode<Integer> tail(BiNode<Integer> node) {
		if (node == null)
			return null;
		
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}
	
	public int closestDistanceWithoutPreprocessing(String[] book, String w1, String w2) {
		
		int index1 =-1, index2= -1;
		int min = Integer.MAX_VALUE;
		
		for(int i=0; i<book.length; i++) {
			if (book[i].equals(w1)) {
				index1 = i;
			}
			
			if (book[i].equals(w2)) {
				index2 = i;
			}
			
			if (index1 != -1 && index2 != -1) {
				int diff = Math.abs(index1-index2);
				if (diff < min) 
					min = diff;
			}
		}
		
		return min;
	}
	
	public int closestDistance(String[] book, String w1, String w2) {
		Map<String, List<Integer>> map = new HashMap<>();
		for (int i=0; i<book.length; i++) {
			if (map.containsKey(book[i])) {
				List<Integer> list = map.get(book[i]);
				list.add(i);
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(i);
				map.put(book[i], list);
			}
		}
		
		Integer[] arr1 = map.getOrDefault(w1, new ArrayList<>()).toArray(new Integer[0]);
		Integer[] arr2 = map.getOrDefault(w2, new ArrayList<>()).toArray(new Integer[0]);
		return findShortestDifference(arr1, arr2);
	}
	
	public int findShortestDifference(Integer[] arr1, Integer[] arr2) {
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		
		int a=0, b=0;
		int minDiff = Integer.MAX_VALUE;
		while (a < arr1.length && b < arr2.length) {
			int diff = Math.abs(arr1[a] - arr2[b]);
			if (diff == 0)
				return diff;
			if (diff < minDiff)
				minDiff = diff;
			if (arr1[a] > arr2[b]) {
				b++;
			} else {
				a++;
			}
		}
		return minDiff;
	}
	
	// 1 5 2 5 3 5 4 5
	// 1 2 5 9 5 9 5 5 5
	public int majorityElement(int[] arr) {
		int currMax = -1;
		int currCount = 0;
		
		for (int a : arr) {
			if (a == currMax) {
				currCount++;
			} else if (currCount != 0) {
				currCount--;
			} else {
				currMax = a;
				currCount = 1;
			}
		}
		
		if (currCount > arr.length/2)
			return currMax;
		
		currCount = 0;
		
		for (int a : arr) {
			if (a == currMax) {
				currCount++;
			}
		}
		
		return currCount > arr.length/2 ? currCount : -1;
			
	}
	
	public int getKthValue357(int k) {
		Queue<Integer> q3 = new LinkedList<>();
		Queue<Integer> q5 = new LinkedList<>();
		Queue<Integer> q7 = new LinkedList<>();
		q3.add(1);
		int min = Integer.MAX_VALUE;
		
		while (k > 0) {
			
			int min3 = !q3.isEmpty() ? q3.peek() : Integer.MAX_VALUE;
			int min5 = !q5.isEmpty() ? q5.peek() : Integer.MAX_VALUE;
			int min7 = !q7.isEmpty() ? q7.peek() : Integer.MAX_VALUE;
			
			min = Math.min(min3, Math.min(min5, min7));
			
			if (min == min3) {
				q3.add(min*3);
				q5.add(min*5);
				q7.add(min*7);
				q3.poll();
			} else if (min == min5) {
				q5.add(min*5);
				q7.add(min*7);
				q5.poll();
			} else {
				q7.add(min*7);
				q7.poll();
			}
			
			
			k--;
		}
		
		return min;
	}
	
	public int kthValue357(int k) {
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		int val = 1;
		for (int i=0; i<k; i++) {
			val = removeMin(queue);
			queue.add(val*3);
			queue.add(val*5);
			queue.add(val*7);
		}
		return val;
		
	}
	
	public int removeMin(Queue<Integer> queue) {
		int min = queue.peek();
		for (int i : queue) {
			if (min > i)
				min = i;
		}
		while(queue.contains(min)) {
			queue.remove(min);
		}
		return min;
	}
	
	public List<Person> circusTower(Person[] circusMen) {
		
		Arrays.sort(circusMen);
		Map<Person, List<Person>> memoization = new HashMap<>();
		List<Person> max = new ArrayList<>();
		
		for (int basePerson=0; basePerson < circusMen.length; basePerson++) {
			List<Person> tower = buildTower(circusMen, basePerson, memoization);
			if (tower.size() > max.size()) {
				max = tower;
			}
		}
		
		return max;
	}
	
	public List<Person> circusTowerIterative(Person[] circusMen) {
			
		Arrays.sort(circusMen);
		
		List<Person> max = new ArrayList<>();
		
		for (int basePerson=0; basePerson < circusMen.length; basePerson++) {
			List<Person> tower = buildTowerIterative(circusMen, basePerson);
			if (tower.size() > max.size()) {
				max = tower;
			}
		}
		
		return max;
	}
	
	private List<Person> buildTowerIterative(Person[] circusMen, int index) {
		
		List<Person> list = new ArrayList<>();
		
		if (index >= circusMen.length)
			return list;
		
		list.add(circusMen[index]);
		
		for (int i=index+1; i<circusMen.length; i++) {
			// to be done
		}
		
		return list;
	}
	
	// O(2^n) 
	private List<Person> buildTower(Person[] circusMen, int index, Map<Person, List<Person>> memoization) {
		
		if (index >= circusMen.length)
			return new ArrayList<>();
		
		if (memoization.containsKey(circusMen[index]))
			return memoization.get(circusMen[index]);
		
		List<Person> tower = new ArrayList<>();
		
		tower.add(circusMen[index]);
		
		List<Person> maxTower = new ArrayList<>();
		
		for (int i=index+1; i<circusMen.length; i++) {
			if (circusMen[i].w <= circusMen[index].w) {
				List<Person> towerWithBaseAsI = buildTower(circusMen, i, memoization);
				if (towerWithBaseAsI.size() > maxTower.size()) {
					maxTower = towerWithBaseAsI;
				}
			}
		}
		
		tower.addAll(maxTower);
		memoization.put(circusMen[index], tower);
		return tower;
	}
	
	private static class Person implements Comparable<Person> {
		int h;
		int w;
		
		public Person(int h, int w) {
			this.h = h;
			this.w = w;
		}

		@Override
		public int compareTo(Person o) {
			if (this.h > o.h) {
				return -1;
			} else {
				return 1;
			}
		}
		
		@Override
		public String toString() {
			return "("+this.h+", "+this.w+")";
		}
	}
	
	private static class Synonyms {
		String word1;
		String word2; 
		
		public Synonyms(String word1, String word2) {
			this.word1 = word1;
			this.word2 = word2;
		}
	}
	
	public Map<String, Integer> babyNamesActualFreq(Map<String, Integer> freq, List<Synonyms> synonyms) {
		MyGraph<String> graph = new MyGraph<>();
		for(Synonyms synonym : synonyms) {
			graph.addEdge(synonym.word1, synonym.word2, true);
		}
		Map<String, List<String>> frequencies = graph.stronglyConnectedComponents();
		Map<String, Integer> counts = new HashMap<>();
		
		for(String key : frequencies.keySet()) {
			int count = 0;
			for(String value : frequencies.get(key)) {
				count += freq.getOrDefault(value, 0);
			}
			counts.put(key, count);
		}
		return counts;
	}
	
	public int numberOf2s(int n) {
		
		int count = 0;
		for (int i=2; i<=n; i++) {
			count += count2s(i);
		}
		return count;
	}
	
	private int count2s(int n) {
		int count = 0;
		while (n != 0) {
			if (n % 10 == 2)
				count++;
			n = n/10;
		}
		return count;
	}
	
	
	public int[] longestSubarraySame1s0sLinearTime(int[] arr) {
		int zeroes = 0, ones = 0;
		int[] delta = new int[arr.length];
		
		for (int i=0; i<arr.length; i++) {

			if (arr[i] == 0) {
				zeroes++;
			} else {
				ones ++;
			}
			delta[i] = Math.abs(zeroes-ones);
		}
		
		int[] range = new int[2];
		System.out.println(Arrays.toString(delta));
		Map<Integer, Integer> map = new HashMap<>();
		for (int i=0; i<delta.length; i++) {
			if (!map.containsKey(delta[i])) {
				map.put(delta[i], i);
			} else {
				int lastOccurrence = map.get(delta[i]);
				int currentOccurrence = i;
				if (currentOccurrence - lastOccurrence > range[1] - range[0]) {
					range[0] = lastOccurrence;
					range[1] = currentOccurrence;
				}
			}
		}
		return range;
	}
	
	public int[] longestSubarraySame1s0s(int[] arr) {
		
		int start = 0, end = -1;
		for (int i=0; i<arr.length; i++) {
			int zeroes = 0, ones =0;
			for (int j=i; j<arr.length; j++) {
				if (arr[j] == 0) {
					zeroes ++;
				} else {
					ones++;
				}
				if (ones == zeroes) {
					if (ones > (end - start + 1)/2) { // damn it
						start = i;
						end = j;
					}
				}
			}
		}
		
		if (end != -1) {
			return Arrays.copyOfRange(arr, start, end+1);
		} else {
			return null;
		}
	}
	
	public int missingNumber(List<BitInteger> list, int column) {
		if (column >= Integer.BYTES*8)
			return 0;
		
		List<BitInteger> lsbZeroes = new ArrayList<>();
		List<BitInteger> lsbOnes = new ArrayList<>();
		
		for (BitInteger num : list) {
			if (num.fetchBit(column) == 0) {
				lsbZeroes.add(num);
			} else {
				lsbOnes.add(num);
			}
		}
		
		if (lsbZeroes.size() > lsbOnes.size()) { // v is an odd num
			int v = missingNumber(lsbOnes, column+1);
			return (v << 1) | 1;
		} else {
			int v = missingNumber(lsbZeroes, column+1);
			return (v << 1) | 0;
		}
	}
	
	private static class BitInteger {
		
		private int num;
		
		public BitInteger(int num) {
			this.num = num;
		}
		
		public int fetchBit(int j) {
			return (this.num >>> j) & 1;
		}
		
	}
	
	// O (n logn)
	public int missingNumber(MissingNumber missingNumber, int n) {
		boolean[] found = new boolean[n+1];
		
		int index = 0;
		while(missingNumber.jthBit(index, 0) != -1) {
			int num = 0;
			int j=0;
			while (j < 32) {
				num += (missingNumber.jthBit(index, j) << j);
				j++;
			}
			found[num] = true;
			index++;
		}
		
		for (int i=0; i<found.length; i++) {
			if (!found[i])
				return i;
		}
		
		return 0;
	}
	
	public int[] randomSetCorrect(int[] arr, int m) {
		if (m < 0 || m > arr.length) {
			return null;
		}
		
		int set[] = new int[m];
		
		for (int i=0; i<m; i++) {
			set[i] = arr[i];
		}
		
		for (int i=m; i<arr.length; i++) {
			int swapWith = random.nextInt(i);
			if (swapWith < m) {
				set[swapWith] = arr[i];
			}
		}
		
		return set;
	}
	
	// incorrect solution..
	// problem is we are shuffling the input arr
	public int[] randomSet(int arr[], int m) {
		if (m < 0 || m > arr.length) {
			return null;
		}
		
		int set[] = new int[m];
		int index = 0;
		int n = arr.length;
		while(index < m) {
			int swapWith = random.nextInt(n);
			swap(arr, swapWith, n-1);
			set[index++] = arr[n-1];
			n--;
		}
		
		return set;
	}
	
	public void shuffle(int[] cards) {
		int index = cards.length;
		while (index > 0) {
			int swapWith = random.nextInt(index);
			swap(cards, swapWith, index-1);
			index--;
		}
	}
	
	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	public int add(int a, int b) {
		
		while (b != 0) {
			int carry = (a & b) << 1;
			int sum = a ^ b;
			a = sum;
			b = carry;
		}
		return a;
	}

}
