package CrackingTheCodingInterview6;

import java.math.BigInteger;
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
import java.util.Stack;

import datastructures.MyGraph;
import datastructures.MyTrie;

public class CTCI_Hard {
	
	private static class ContinuousMedian {
		
		private PriorityQueue<Integer> minHeap;
		private PriorityQueue<Integer> maxHeap;
		
		public ContinuousMedian() {
			minHeap = new PriorityQueue<>();
			maxHeap = new PriorityQueue<>(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					if (o1.intValue() >= o2.intValue())
						return -1;
					else
						return 1;
				}
			});
		}
		
		public void add(int n) {
			
			if (maxHeap.size() == 0) {
				maxHeap.add(n);
				return;
			}
			
			if (maxHeap.size() > minHeap.size()) {
				if (n > maxHeap.peek()) {
					minHeap.add(n);
				} else {
					minHeap.add(maxHeap.poll());
					maxHeap.add(n);
				}
			} else {
				if (n > minHeap.peek()) {
					maxHeap.add(minHeap.poll());
					minHeap.add(n);
				} else {
					maxHeap.add(n);
				}
			}
		}
		
		public Integer getMedian() {
			if (size() == 0)
				return null;
			
			if (size() %2 == 0) {
				return (minHeap.peek() + maxHeap.peek())/2;
			} else {
				return maxHeap.peek();
			}
		}
		
		public int size() {
			return minHeap.size() + maxHeap.size();
		}
	}
	
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
		dict.add("damp");
		dict.add("limp");
		//dict.add("lamp");
		dict.add("lime");
		dict.add("dame");
		dict.add("lame");
		
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
		
		int[] input = {1, 5, 9};
		int[] seq = {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};
		
		System.out.println(Arrays.toString(run.shortestSupersequence(input, seq)));
		System.out.println(Arrays.toString(run.shortestSupersequence1(input, seq)));
		System.out.println(run.sum(5) + " " + run.product(5));
		
		ContinuousMedian continuousMedian = new ContinuousMedian();
		
		for (int s : seq) {
			continuousMedian.add(s);
			System.out.print(continuousMedian.getMedian()+" ");
		}
		
		int hist[] = {0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 5, 0, 1, 0, 0, 0};
		System.out.println("\nvol => " +run.volumeOfHistogram(hist));
		System.out.println("\nvol => " +run.volumeOfHistogramBetter(hist));
		
		int hist1[] = {0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 8, 0, 2, 0, 5, 2, 0, 3, 0, 0};
		System.out.println("\nvol => " +run.volumeOfHistogram(hist1));
		System.out.println("\nvol => " +run.volumeOfHistogramBetter(hist1));
		
		System.out.println(run.wordTransformer("damp", "lame", dict));
		
		
	}
	
	private class RangeSum {
		int col1;
		int col2;
		int sum;
		
		public RangeSum(int col1, int col2, int sum) {
			this.col1 = col1;
			this.col2 = col2;
			this.sum = sum;
		}
		
	}
	
	
	public SubmatrixSum submatrixWithLargestSumEfficient(int[][] mat) {
		
		SubmatrixSum best = null;
		
		for (int row1=0; row1 < mat.length; row1++) {
			int[] partialSum = new int[mat[0].length];
			for (int row2=row1; row2 < mat.length; row2++) {
				for (int col=0; col < mat[0].length; col++) {
					partialSum[col] += mat[row2][col];
				}
				RangeSum rangeSum = maxSubArrRange(partialSum);
				if (best == null || best.sum < rangeSum.sum) {
					best = new SubmatrixSum(row1, rangeSum.col1, row2, rangeSum.col2, rangeSum.sum);
				}
			}
		}
		
		return best;
	}
	
	private RangeSum maxSubArrRange(int[] arr) {
		int sum = 0;
		int maxSum = Integer.MIN_VALUE;
		int from = -1;
		
		RangeSum range = null;
		
		for (int i=0; i<arr.length; i++) {
			if (sum + arr[i] > 0) {
				sum += arr[i];
				if (from == -1) {
					from = i;
				}
			} else {
				if (sum != 0 && sum > maxSum) {
					maxSum = sum;
					range = new RangeSum(from, i, maxSum);
				}
				from = -1;
				sum = 0;
			}
		}
		
		return range;
	}
	
	private class SubmatrixSum {
		int row1, row2;
		int col1, col2;
		int sum;
		
		public SubmatrixSum(int row1, int col1, int row2, int col2, int sum) {
			this.row1 = row1;
			this.row2 = row2;
			this.col1 = col1;
			this.col2 = col2;
			this.sum = sum;
		}
	}
	
	// N^4 dynamic programming solution
	
	public SubmatrixSum submatrixWithLargestSum(int[][] mat) {
		
		int[][] precomputedSums = precomputeSums(mat);
		
		SubmatrixSum best = null;
		
		for (int row=0; row<mat.length; row++) {
			for (int col=0; col<mat[0].length; col++) {
				for (int row1 = row; row1 < mat.length; row1++) {
					for (int col1 = col; col1 < mat[0].length; col1++) {
						
						int sum = getSumSubmatrix(precomputedSums, row, col, row1, col1);
						
						if (best == null || best.sum < sum) {
							best = new SubmatrixSum(row, col, row1, col1, sum);
						}
					}
				}
			}
		}
		
		return best;
	}
	
	private int getSumSubmatrix(int[][] precomputedSums, int row1, int col1, int row2, int col2) {
		int topAndLeft = row1 > 0 && col1 > 0 ? precomputedSums[row1-1][col1-1] : 0;
		int top = row1 > 0 ? precomputedSums[row1-1][col2] : 0;
		int left = col1 > 0 ? precomputedSums[row2][col1-1] : 0;
		int full = precomputedSums[row2][col2];
		return full - top - left + topAndLeft;
				
	}
	
	private int[][] precomputeSums(int[][] mat) {
		int[][] sums = new int[mat.length][mat[0].length];
		
		for (int i=0; i<mat.length; i++) {
			for (int j=0; j<mat[0].length; j++) {
				int left = j > 0 ? sums[i][j-1] : 0;
				int top = i > 0 ? sums[i-1][j] : 0;
				int overlap = i > 0 && j > 0 ? sums[i-1][j-1] : 0;
				sums[i][j] = left + top - overlap + mat[i][j];
			}
		}
		
		return sums;
	}
	
	private class RowCol {
		int row;
		int col;
		public RowCol(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private class SubSquareLoc {
		
		RowCol rowCol1;
		RowCol rowCol2;
		RowCol rowCol3;
		RowCol rowCol4;
		
		
	}
	
	public SubSquareLoc maxSubsquare(int[][] mat) {
		for (int size = mat.length; size > 0; size --) {
			SubSquareLoc loc = maxSubsquare(mat, size);
			if (loc != null)
				return loc;
		}
		
		return null;
	}
	
	private SubSquareLoc maxSubsquare(int[][] mat, int size) {
		int count = mat.length-size+1;
		for (int row = 0; row < count; row++) {
			for (int col=0; col < count; col++) {
				if (isBlackSquare(mat, row, col, size)) {
					RowCol rowCol1 = new RowCol(row, col);
					RowCol rowCol2 = new RowCol(row+size-1, col);
					RowCol rowCol3 = new RowCol(row, col+size-1);
					RowCol rowCol4 = new RowCol(row+size-1, col+size-1);
					
					SubSquareLoc loc = new SubSquareLoc();
					loc.rowCol1 = rowCol1;
					loc.rowCol2 = rowCol2;
					loc.rowCol3 = rowCol3;
					loc.rowCol4 = rowCol4;
					return loc;
				}
			}
		}
		
		return null;
	}
	
	private boolean isBlackSquare(int[][] mat, int row, int col, int size) {
		
		// top & bottom
		for (int i=col; i<col+size; i++) {
			if (mat[row][i] != 1 && mat[row+size-1][i] != 1)
				return false;
		}
		
		for (int i=row; i<row+size; i++) {
			if (mat[i][col] != 1 && mat[i][col+size-1] != 1)
				return false;
		}
		
		return true;
	}
	
	public List<String> wordTransformer(String w1, String w2, Set<String> dict) {
		List<String> result = new ArrayList<>();
		ResultWord resultWord = wordTransformer(w1.toCharArray(), w2.toCharArray(), 0, dict, result);
		if (resultWord.isWord)
			return result;
		else
			return new ArrayList<>();
		
	}
	
	private ResultWord wordTransformer(char[] word1, char[] word2, int index, Set<String> dict, List<String> result) {
		if (index >= word1.length)
			return new ResultWord("", true);

		ResultWord res = null;
		
		if (word1[index] == word2[index]) {
			return wordTransformer(word1, word2, index+1, dict, result);
		} else {
			char temp = word1[index];
			word1[index] = word2[index];
			if (dict.contains(new String(word1))) {
				result.add(new String(word1));
				if (!(res = wordTransformer(word1, word2, index+1, dict, result)).isWord) {
					result.remove(result.size()-1);
				} else if (res.str.isEmpty()) {
					res = new ResultWord(result.get(result.size()-1), true); // last word
				}
			} else {
				word1[index] = temp;
				if ((res = wordTransformer(word1, word2, index+1, dict, result)).isWord) {
					char arr[] = res.str.toCharArray();
					arr[index] = word2[index];
					if (dict.contains(new String(arr))) {
						res = new ResultWord(new String(arr), true);
						result.add(new String(word1));
					}
				}
			}
			word1[index] = temp;
		}
		
		return res;
	}
	
	private class ResultWord {
		String str;
		boolean isWord;
		
		public ResultWord(String str, boolean isWord) {
			this.str = str;
			this.isWord = isWord;
		}
	}
	
	public int volumeOfHistogramBetter(int[] arr) {
		int maxLR[] = new int[arr.length];
		int maxRL[] = new int[arr.length];
		int delta[] = new int[arr.length];
		
		int max = arr[0];
		for (int i=0; i<arr.length; i++) {
			max = Math.max(max, arr[i]);
			maxLR[i] = max;
		}
		
		max = arr[arr.length-1];
		for (int i=arr.length-1; i>=0; i--) {
			max = Math.max(max, arr[i]);
			maxRL[i] = max;
		}
		
		for (int i=0; i<arr.length; i++) {
			delta[i] = Math.min(maxLR[i], maxRL[i]) - arr[i];
		}
		
		int sum = 0;
		
		for (int i=0; i<arr.length; i++) {
			sum += delta[i];
		}
		
		return sum;
	}
	
	public int volumeOfHistogram(int[] arr) {
		
		int vol = 0;
		Stack<Integer> stack = new Stack<>();
		
		for (int i=0; i<arr.length; i++) {
			if (arr[i] != 0) {
				if (!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
					
					while (stack.size() > 1 && arr[i] > arr[stack.peek()]) {
						vol -= arr[stack.pop()];
					}
					int prevIndex = stack.pop();
					int minVal = Math.min(arr[i], arr[prevIndex]);
					vol += minVal*(i-prevIndex-1);
				}
				stack.add(i);
			}
		}
		
		
		while(stack.size() > 1) {
			int index = stack.pop();
			int prevIndex = stack.pop();
			int minVal = Math.min(arr[index], arr[prevIndex]);
			vol += minVal * (index-prevIndex-1);
		}
		
		return vol;
	}
	
	public BigInteger product(int N) {
		BigInteger prod = new BigInteger("1");
		for (int i=2; i<=N; i++) {
			prod = prod.multiply(new BigInteger(String.valueOf(i)));
		}
		return prod;
	}
	
	public BigInteger sum(int N) {
		BigInteger sum = new BigInteger(String.valueOf(N));
		BigInteger sum1 = new BigInteger(String.valueOf(N + 1));
		return sum.multiply(sum1).divide(new BigInteger(String.valueOf(2)));
	}
	
	
	
	private class HeapNode {
		int val;
		int index;
		
		public HeapNode(int val, int index) {
			this.val = val;
			this.index = index;
		}
		
		@Override
		public String toString() {
			return String.valueOf(index);
		}
	}
	
	public int[] shortestSupersequence1(int input[], int[] seq) {
		Map<Integer, LinkedList<Integer>> map = new HashMap<>();
		for (int val : input) {
			map.put(val, new LinkedList<>());
		}
		
		for (int i=0; i<seq.length; i++) {
			if (map.containsKey(seq[i])) {
				map.get(seq[i]).add(i);
			}
		}
		
		int[] range = {-1, -1};
		
		PriorityQueue<HeapNode> queue = new PriorityQueue<>(new Comparator<HeapNode>() {

			@Override
			public int compare(HeapNode o1, HeapNode o2) {
				if (o1.index <= o2.index)
					return -1;
				else
					return 1;
			}
		});
		
		int max = 0;
		
		for (int val : map.keySet()) {
			List<Integer> list = map.get(val);
			if (list.isEmpty()) {
				return range;
			}
			int index = list.get(0);
			queue.add(new HeapNode(val, index));
			if (index > max)
				max = index;
		}
		
		int start = 0, end = seq.length-1;
		
		while (true) {
			int min = queue.peek().index;
			if (max-min < end-start) {
				start = min;
				end = max;
			}
			
			HeapNode node = queue.poll();
			LinkedList<Integer> list = map.get(node.val);
			list.removeFirst();
			if (list.isEmpty())
				break;
			
			queue.add(new HeapNode(node.val, list.get(0)));
			max = Math.max(max, list.get(0));
		}
		
		range[0] = start;
		range[1] = end;
		return range;
		
	}
	
	public int[] shortestSupersequence(int input[], int[] seq) {
		Set<Integer> inputSet = new HashSet<>();
		for (int a : input) {
			inputSet.add(a);
		}
		
		Set<Integer> curr = new HashSet<>();
		LinkedList<Integer> queue = new LinkedList<>();
		int[] res = new int[2];
		
		int min = Integer.MAX_VALUE;
		
		for (int i=0; i<seq.length; i++) {
			if (inputSet.contains(seq[i])) {
				if (!curr.contains(seq[i])) {
					curr.add(seq[i]);
					queue.add(i);
				}
				if (curr.size() == inputSet.size()) {
					int firstIndex = queue.getFirst();
					int lastIndex = queue.getLast();
					if (lastIndex - firstIndex < min) {
						min = lastIndex - firstIndex;
						res[0] = firstIndex;
						res[1] = lastIndex;
					}
					queue.removeFirst();
					curr.remove(seq[firstIndex]);
				}
			}
		}
		
		return res;
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
