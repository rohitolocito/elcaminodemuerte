package solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import datastructures.MyLRUCache;
import datastructures.MyTrie;

class Grid {
	
	private Ant ant;
	
	private boolean[][] grid;
	
	public Grid() {
		this.ant = new Ant();
		grid = new boolean[1][1];
	}
	
	private void move() {
		ant.turn(grid[ant.position.row][ant.position.column]);
		flip(ant.position);
		ant.move();
		ensureFit(ant.position);
	}
	
	private void flip(Position position) {
		grid[position.row][position.column] = !grid[position.row][position.column];
	}
	
	private void shift(boolean[][] newGrid, boolean[][] oldGrid, int shiftRow, int shiftCol) {
		for (int i=0; i<oldGrid.length; i++) {
			for (int j=0; j<oldGrid[0].length; j++) {
				newGrid[i + shiftRow][j + shiftCol] = oldGrid[i][j];
			}
		}
	}
	
	private void ensureFit(Position position) {
		int shiftRow = 0;
		int shiftCol = 0;
		
		int numRows = grid.length;
		
		if (position.row < 0) {
			shiftRow = numRows;
			numRows = numRows * 2;
		} else if (position.row >= numRows) {
			numRows = numRows*2;
		}
		
		int numCols = grid[0].length;
		
		if (position.column < 0) {
			shiftCol = numCols;
			numCols = numCols * 2;
		} else if (position.column >= numCols) {
			numCols = numCols*2;
		}
		
		if (numRows != grid.length || numCols != grid[0].length) {
			boolean[][] newGrid = new boolean[numRows][numCols];
			shift(newGrid, grid, shiftRow, shiftCol);
			ant.adjust(shiftRow, shiftCol);
			grid = newGrid;
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<grid.length; i++) {
			for (int j=0; j<grid[0].length; j++) {
				if (ant.position.row == i && ant.position.column == j) {
					sb.append(ant.orientation.toString().toUpperCase().charAt(0)+" ");
				} else if (grid[i][j]) {
					sb.append("X ");
				} else {
					sb.append("0 ");
				}
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public void printKMoves(int k) {
		while (k > 0) {
			move();
			k--;
		}
		System.out.println(toString());
	}
	
	
}

class Ant {
	
	Orientation orientation;
	
	Position position;
	
	public Ant() {
		this.position = new Position(0, 0);
		this.orientation = Orientation.right;
	}
	
	public void turn(boolean clockwise) {
		this.orientation = orientation.rotate(clockwise);
	}
	
	public void adjust(int shiftRow, int shiftCol) {
		this.position.row += shiftRow;
		this.position.column += shiftCol;
	}
	
	public void move() {
		if (orientation == Orientation.left) {
			position.column--;
		} else if (orientation == Orientation.right) {
			position.column++;
		} else if (orientation == Orientation.up) {
			position.row--;
		} else if (orientation == Orientation.down) {
			position.row++;
		}
	}
}

enum Orientation {
	left,
	right,
	up,
	down;
	
	public Orientation rotate(boolean clockwise) {
		if (this == left) {
			return clockwise ? up : down;
		} else if (this == right){
			return clockwise ? down : up;
		} else if (this == up) {
			return clockwise ? right : left;
		} else {
			return clockwise ? left : right;
		}
	}
}

class Position {
	
	int row;
	int column;
	
	public Position(int x, int y) {
		this.row = x;
		this.column = y;
	}
}

class T9DictPrecomputation {
	
	private Map<Character,Character> letterToDigit;
	
	private Map<String, List<String>> t9ToList ;
	
	public T9DictPrecomputation(String fileName) throws IOException {
		
		letterToDigit = letterToDigitMap();
		System.out.println(letterToDigit);
		t9ToList = new HashMap<>();
		
		File dictionary = new File(fileName);
		FileReader fileReader = new FileReader(dictionary);
		BufferedReader br = new BufferedReader(fileReader);
		String word = null;
		while((word=br.readLine()) != null) {
			String t9 = convertWordToT9(word);
			List<String> list = t9ToList.getOrDefault(t9, new ArrayList<>());
			list.add(word);
			t9ToList.put(t9, list);
		}
		br.close();
	}
	
	public List<String> getWords(String num) {
		return t9ToList.getOrDefault(num, new ArrayList<>());
	}
	
	private Map<Character, Character> letterToDigitMap() {
		Map<Character, Character> map = new HashMap<>();
		char digit = '1';
		for (char c = 'a'; c <= 'r'; c++) {
			if ((c-'a') % 3 == 0)
				digit++;
			map.put(c, digit);
		}
		
		map.put('s', digit);
		map.put('t', ++digit);
		map.put('u', digit);
		map.put('v', digit);
		map.put('w', ++digit);
		map.put('x', digit);
		map.put('y', digit);
		map.put('z', digit);
		
		return map;
	}
	
	private String convertWordToT9(String word) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<word.length(); i++) {
			if (letterToDigit.containsKey(word.charAt(i))) {
				sb.append(letterToDigit.get(word.charAt(i)));
			}
		}
		return sb.toString();
	}
}

class T9Dict {
	
	private MyTrie root ;
	private Map<Character, String> map;
	
	public T9Dict(String fileName) throws IOException {
		this.root = new MyTrie();
		
		File dictionary = new File(fileName);
		FileReader fileReader = new FileReader(dictionary);
		BufferedReader br = new BufferedReader(fileReader);
		String word = null;
		while((word=br.readLine()) != null) {
			this.root.add(word);
		}
		br.close();
		
		map = new HashMap<>();
		map.put('0', "");
		map.put('1', "");
		map.put('2', "abc");
		map.put('3', "def");
		map.put('4', "ghi");
		map.put('5', "jkl");
		map.put('6', "mno");
		map.put('7', "pqrs");
		map.put('8', "tuv");
		map.put('9', "xyz");
	}
	
	//8733
	public List<String> getWords(String num) {
		List<String> words =new ArrayList<>();
		getWords(num, "", words);
		return words;
	}
	
	private void getWords(String num, String s, List<String> words) {
		if (num.isEmpty()) {
			if (root.isWord(s)) {
				words.add(s);
			}
			return;
		}
		
		String chars = map.getOrDefault(num.charAt(0), "");
		for (int i=0; i<chars.length(); i++) {
			getWords(num.substring(1), s + chars.charAt(i) , words);
		}
		
	}
	
	
}

public class CTCI_Moderate {
	
	private static class Operations {
		
		static int abs(int x) {
			if (x < 0) {
				return negate(x);
			} 
			return x;
		}
		
		static int negate(int x) {
			
			int neg = (x < 0) ? 1 : -1;
			int neg_x = 0;
			while (x != 0) {
				x += neg;
				neg_x += neg;
			}
			return neg_x;
		}
		
		static int negateFaster(int x) {
			int sign = (x < 0) ? 1 : -1;
			
			int neg_incr = sign;
			int neg_x = 0;
			
			while (x != 0) {
				if (sign == 1) { // x < 0
					if (x + neg_incr > 0) {
						neg_incr = sign;
					}
				} else {
					if (x + neg_incr < 0) {
						neg_incr = sign;
					}
				}
				x += neg_incr;
				neg_x += neg_incr;
				neg_incr += neg_incr;
			}
			
			return neg_x;
		}
		
		static int multiply(int a, int b) {
			if (a < 0 || b < 0) {
				if (a < 0 && b < 0)
					return multiply(abs(a), abs(b));
				else
					return negate(multiply(a < 0 ? abs(a) : a, b < 0 ? abs(b) : b));
			}
			
			if (a < b) 
				return multiply(b, a);
			
			if (b == 0)
				return 0;
			
			if (b == 1)
				return a;

			int prod = multiply(a, b/2);
			
			int val = prod + prod;
			if (b % 2 == 1) {
				val += a;
			}
			
			return val;
		}
		
		static int subtraction(int a, int b) {
			return a + negate(b);
		}
		
		static int divide(int a, int b) {
			if (b == 0)
				throw new ArithmeticException("Divide by Zero");
			
			if (a < 0 || b < 0) {
				if (a < 0 && b < 0)
					return divide(-a, -b);
				else
					return -divide(a < 0 ? -a : a, b < 0 ? -b : b);
			}
			
			int q = 0;
			int divisor = b;
			while (divisor <= a) {
				q++;
				divisor += b;
			}
			
			return q;
		}
	}
	
	private static class IntegerToEnglishPhrase {
		
		static final String BILLION = "Billion";
		static final String MILLION = "Million";
		static final String HUNDRED = "Hundred";
		static final String THOUSAND = "Thousand";
		static final String SPACE = " ";
		
		String unitsTeens[] = {"zero", "one", "two", "three", "four", "five", "six","seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
		
		String tens[] = {"", "ten", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};
		
		public void integerToEnglishPhrase(int n) {
			
			
			StringBuilder result = new StringBuilder();
			
			if (n < 0) {
				result.append("Negative");
				result.append(SPACE);
				n = -n;
			}
			
			DecimalFormat decimalFormat = new DecimalFormat("000000000000");
			String number = decimalFormat.format(n);
			
			String billions = number.substring(0,3);
			String millions = number.substring(3,6);
			String thousands = number.substring(6,9);
			String hundreds = number.substring(9);

			
			for (int i=0; i<number.length(); i += 3) {
				switch(i) {
					case 0 : {
						int num = Integer.parseInt(number.substring(0, 3));
						if (num != 0) {
							result.append(processHundreds(number.substring(0, 3)));
							result.append(BILLION);
							result.append(SPACE);
						}
						break;
					}
					case 3: {
						int num = Integer.parseInt(number.substring(3, 6));
						if (num != 0) {
							result.append(processHundreds(number.substring(3, 6)));
							result.append(MILLION);
							result.append(SPACE);
						}
						break;
					}
					case 6 : {
						int num = Integer.parseInt(number.substring(6, 9));
						if (num != 0) {
							result.append(processHundreds(number.substring(6, 9)));
							result.append(THOUSAND);
							result.append(SPACE);
						}
						break;
					}
					case 9 :{ 
						int num = Integer.parseInt(number.substring(9));
						if (num != 0) {
							result.append(processHundreds(number.substring(9)));
						}
						break;
					}
					
				}
			}
			
			String phrase = result.length() == 0 ? "Zero" : result.toString();
			System.out.println(phrase);
			
		}
		
		private String processHundreds(String num) {
			int value = Integer.valueOf(num);
			StringBuilder sb = new StringBuilder();
			if (value / 100 > 0) {
				int v = value/100;
				value = value % 100;
				sb.append(unitsTeens[v]);
				sb.append(SPACE);
				sb.append(HUNDRED);
				sb.append(SPACE);
				if (value == 0) {
					return sb.toString();
				}
			}
			
			if (value < 20) {
				sb.append(unitsTeens[value]);
				sb.append(SPACE);
				return sb.toString();
			}
			
			if (value % 10 == 0) {
				sb.append(tens[value/10]);
				sb.append(SPACE);
				return sb.toString();
			}
			
			sb.append(tens[value/10]);
			sb.append(SPACE);
			sb.append(unitsTeens[value%10]);
			sb.append(SPACE);
			return sb.toString();
		}
		
	}
	
	private static class WordFrequencies {
		
		private Map<String, Integer> wordCount;
		
		public WordFrequencies() {
			this.wordCount = new HashMap<>();
		}
		
		public void populate(String[] book) {
			for(String word : book) {
				word = word.toLowerCase();
				if (word.trim() != "") {
					wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
				}
			}
		}
		
		public int getFrequency(String word) {
			return this.wordCount.getOrDefault(word.toLowerCase(), 0);
		}
	}
	
	private static class Line {
		
		Point start;
		Point end;
		double slope;
		double y_intercept;
		
		public Line(Point start, Point end) {
			this.start = start;
			this.end = end;
			this.slope = (end.y - start.y)/(end.x - start.x);
			this.y_intercept = start.y - this.slope*start.x;
		}
		
		public double slope() {
			return this.slope;
		}
		
		public double y_intercept() {
			return this.y_intercept;
		}
	}
	
	private static class Point {
		
		double x;
		double y;
		
		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		public void setLocation(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException {
		
		CTCI_Moderate run = new CTCI_Moderate();
		System.out.println(Arrays.toString(run.swapInPlace(5, Integer.MAX_VALUE)));
		
		String book[] = {"what", "are", "you", "doing", "in", "life", "You", "are", "a", "lifeless", "soul"};
		
		WordFrequencies frequencies = new WordFrequencies();
		frequencies.populate(book);
		System.out.println(frequencies.getFrequency("you"));
		
		MyTrie myTrie = new MyTrie();
		for (String s : book) {
			myTrie.add(s);
		}
		
		System.out.println(myTrie.getCount("life"));
		run.generatePossibleBoardSums(3, null);
		System.out.println(run.numFactorialZeroes(25));
		System.out.println(run.numFactorialZeroes(30));
		
		int[] A = {1, 3, 15, 11, 2};
		int[] B = {23, 127, 235, 19, 8};
		System.out.println(run.smallestDiff(A, B));
		System.out.println("min => "+run.min(10, 20)+", " + "max => " + run.max(10, 20));
		
		IntegerToEnglishPhrase translator = new IntegerToEnglishPhrase();
		translator.integerToEnglishPhrase(Integer.MIN_VALUE + 3);
		System.out.println(Operations.multiply(Integer.MAX_VALUE/2,-2));
		System.out.println(Operations.divide(1,-2));
		System.out.println(Operations.abs(38383));
		
		System.out.println(Operations.negateFaster(5));
		
		Person[] people = {new Person(12, 15), new Person(20, 89), new Person(10, 98), new Person(01, 72),
				new Person(10, 98), new Person(24, 82), new Person(13, 98), new Person(90, 98), 
				new Person(83, 99), new Person(75, 94)};
		
		System.out.println(run.yearWithMostPeopleAlive(people));
		System.out.println(run.yearWithMostPeopleAliveWithoutSorting(people, 0, 100));
		System.out.println(run.divingBoard(20, 2, 11));
		Set<Integer> lengths = new HashSet<>();
		run.divingBoardRecur(20, 2, 11, new int[1], lengths);
		System.out.println(lengths);
		
		Set<Integer> set = new HashSet<>();
		run.divingBoardRecurMemoization(20, 2, 11, 0, set, new HashSet<>());
		System.out.println(set);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		
		Element element = doc.createElement("family");
		
		element.setAttribute("lastName", "McDowell");
		element.setAttribute("state", "CA");
		
		Element person = doc.createElement("person");
		person.setAttribute("firstName", "Gayle");
		person.setTextContent("Some Message");
		
		element.appendChild(person);
		
		System.out.println(run.getStringFromDocument(doc));
		Map<String, Integer> codes = new HashMap<>();
		codes.put("family", 1);
		codes.put("person", 2);
		codes.put("firstName", 3);
		codes.put("lastName", 4);
		codes.put("state", 5);
		
		StringBuilder encodedString = new StringBuilder();
		run.encodeXML(element, codes, encodedString);
		System.out.println(encodedString.toString());
		
		System.out.println(Arrays.toString(run.masterMind("BGYB", "GBBY")));
		System.out.println(Arrays.toString(run.masterMind("GGRR", "RGBY")));
		
		int[] arr = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
		System.out.println(Arrays.toString(run.subSort(arr)));
		
		int[] maxContig = {-2, -8, -3, -2, -4, 0};
		System.out.println(run.maxContiguousSum(maxContig));
		
		int[] maxContig1 = {2, 3, -8, -1, 2, 4, -2, 3};
		System.out.println(run.maxContiguousSum(maxContig1));
		
		System.out.println(run.patternMatching("aabab", "catcatgocatgo"));
		
		int[][] mat = {{0, 2, 1, 0}, {0, 1, 0, 1}, {1, 1, 0, 1}, {0, 1, 0, 1}};
		System.out.println(run.sizesOfAllPonds(mat));
		
		T9Dict dict = new T9Dict("/Users/rdoshi/Documents/workspace/CrackingTheCodingInterview6/src/dictionary");
		System.out.println(dict.getWords("8733"));
		System.out.println(dict.getWords("7373328"));
		System.out.println(dict.getWords("867"));
		
		T9DictPrecomputation dictPrecomputation = new T9DictPrecomputation("/Users/rdoshi/Documents/workspace/CrackingTheCodingInterview6/src/dictionary");
		System.out.println(dictPrecomputation.getWords("8733"));
		
		int Arr[] = {4, 1, 2, 1 ,1, 2};
		int Brr[] = {3, 6, 3, 3};
		System.out.println(Arrays.toString(run.sumSwap(Arr, Brr)));
		
		Grid grid = new Grid();
		grid.printKMoves(10);
		
		int[] pairs = {9, 1, 5, 2, 6, 3, 4, 1, 2};
		System.out.println(run.pairsWithSum(pairs, 4));
		System.out.println(run.uniquePairsWithSum(pairs, 4));
		System.out.println(run.uniquePairsWithSumUsingSort(pairs, 4));
		
		MyLRUCache<Integer, String> lruCache = new MyLRUCache<>(5);
		lruCache.add(1, "One");
		lruCache.add(2, "Two");
		lruCache.add(3, "Three");
		lruCache.add(4, "Four");
		lruCache.add(5, "Five");
		lruCache.add(6, "Six");
		lruCache.add(2, "TWO");
		System.out.println(lruCache);
		
		System.out.println(run.calculator("2*3+5/6*3+15"));
	}
	
	private static class OperatorPrecedence {
		
		public static int precdence(char c) {
			switch(c) {
				case '+' : 
				case '-' : return 0;
				case '*' : 
				case '/' : return 1;
				default : return -1;
			}
		}
	}
	
	private double calculator(String exp) {
		Stack<Double> nums = new Stack<>();
		Stack<Character> operators = new Stack<>();
		
 		for (int i=0; i<exp.length(); i++) {

 			if (exp.charAt(i) >= '0' && exp.charAt(i) <= '9') {
 				double num = 0;
				while (i < exp.length() && exp.charAt(i) >= '0' && exp.charAt(i) <= '9') {
					char c = exp.charAt(i);
					num = num*10 + (c - '0');
					i++;
				};
				nums.add(num);
				i--;
 			} else {
 				char c = exp.charAt(i);
 				while (!operators.isEmpty() && OperatorPrecedence.precdence(operators.peek()) >= OperatorPrecedence.precdence(c)) {
 					double b = nums.pop();
 					double a = nums.pop();
 					double result = process(a, b, operators.pop());
 					nums.push(result);
 				}
 				operators.push(c);
 			}
		}
 		
 		while (!operators.isEmpty()) {
 			double b = nums.pop();
			double a = nums.pop();
			double result = process(a, b, operators.pop());
			nums.push(result);
 		}
 		
 		return nums.pop();
	}
	
	private double process(double a, double b, char op) {
		if (op == '+') {
			return a + b;
		} else if (op == '/') {
			return a / b;
		} else if (op == '*') {
			return a * b;
		} else if (op == '-') {
			return a - b;
		} else {
			return 0.0d;
		}
	}
	
	private static class Pair {
		
		int a, b;
		
		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		@Override
		public String toString() {
			return "("+ a+", "+b+")";
		}
	}
	
	public List<Pair> uniquePairsWithSumUsingSort(int[] arr, int sum) {
		Arrays.sort(arr);
		List<Pair> list = new ArrayList<>();
		int start = 0, end = arr.length-1;
		
		while (start < end) {
			int s = arr[start] + arr[end];
			if (s == sum) {
				list.add(new Pair(arr[start], arr[end]));
				start++;
				end--;
			} else if (s > sum) {
				end--;
			} else {
				start++;
			}
		}
		return list;
	}
	
	public List<Pair> uniquePairsWithSum(int[] arr, int sum) {
		Map<Integer, Integer> mapCount = new HashMap<>();
		List<Pair> list = new ArrayList<>();
		
		for (int a : arr) {
			int complement = sum - a;
			if (mapCount.getOrDefault(complement, 0) > 0) {
				list.add(new Pair(a, complement));
				mapCount.put(complement, mapCount.get(complement)-1);
			} else {
				mapCount.put(a, mapCount.getOrDefault(a, 0)+1);
			}
		}
		
		return list;
	}
	
	public List<Pair> pairsWithSum(int[] arr, int sum) {
		Map<Integer, Integer> map = new HashMap<>();
		List<Pair> list = new ArrayList<>();
		for (int i=0; i<arr.length; i++)
			map.put(arr[i], i);
		
		for (int i=0; i<arr.length; i++) {
			if (map.containsKey(sum - arr[i]) && map.get(sum - arr[i]) > i) {
				list.add(new Pair(arr[i], sum-arr[i]));
			}
		}
		return list;
	}
	
	public int rand7Alternative() {
		while (true) {
			int r1 = 2*rand5(); // [0 .. 8]
			int r2 = rand5(); //[0 .. 4]
			if (r2 != 4) {
				int v = r2 % 2 ; // [0 .. 1]
				r1 += v;
				if (r1 < 7)
					return r1;
			}
		}
	}
	
	public int rand7() {
		int r = 7;
		int sum = 0;
		while (r > 0) {
			sum += rand5();
			r--;
		}
		sum /= 4;
		return sum % 7;
	}
	
	public int rand5() {
		Random r = new Random();
		return r.nextInt(5);
	}
	
	
	// 1, 1, 1, 2, 2, 4
	// 3, 3, 3, 6
	public int[] sumSwapSorted(int A[], int[] B) {
		int sumA = sumOfArray(A);
		int sumB = sumOfArray(B);
		
		int diff = Math.abs(sumA - sumB);
		
		int a=0, b=0;
		while (a < A.length && b < B.length) {
			if (A[a] + B[b] == diff) {
				int values[] = {A[a], B[b]};
				return values;
			} else if (A[a] + B[b] < diff) {
				a++;
			} else {
				b++;
			}
		}
		
		return null;
	}
	
	//4, 1, 2, 1, 1, 2 = 11
	//3, 6, 3, 3 = 15
	
	public int[] sumSwap(int A[], int B[]) {
		int sumA = sumOfArray(A);
		int sumB = sumOfArray(B);
		
		int diff = Math.abs(sumA - sumB);
		
		Set<Integer> setA = new HashSet<>();
		for (int i=0; i<A.length; i++) {
			setA.add(A[i]);
		}
		
		for (int i=0; i<B.length; i++) {
			int elem = diff - B[i];
			if (setA.contains(elem)) {
				int[] res = new int[2];
				res[0] = elem;
				res[1] = B[i];
				return res;
			}
		}
		
		return null;
	}
	
	private int sumOfArray(int[] arr) {
		int sum = 0;
		for (int i=0; i<arr.length; i++) 
			sum += arr[i];
		return sum;
	}
	
	public List<Integer> sizesOfAllPonds(int[][] mat) {
		boolean[][] visited = new boolean[mat.length][mat[0].length];
		List<Integer> sizes = new ArrayList<>();
		for (int i=0; i<mat.length; i++) {
			for (int j=0; j<mat[0].length; j++) {
				if (!visited[i][j] && mat[i][j] == 0) {
					sizes.add(sizeOfPond(mat, i, j, visited));
				}
			}
		}
		return sizes;
	}
	
	private int sizeOfPond(int[][] mat, int r, int c, boolean visited[][]) {
		if (r < 0 || r >= mat.length || c < 0 || c >= mat[0].length || mat[r][c] != 0) {
			return 0;
		}
		
		if (visited[r][c])
			return 0;
		
		visited[r][c]  = true;
		
		return 1 + 
				sizeOfPond(mat, r+1, c, visited) + 
					sizeOfPond(mat, r, c+1, visited) + 
						sizeOfPond(mat, r+1, c+1, visited) + 
							sizeOfPond(mat, r+1, c-1, visited);
	}
	
	// catcatgocatgo
	// aabab
	
	public boolean patternMatching(String pattern, String value) {
		int vLen = value.length();
		int pLen = pattern.length();
		
		char firstChar = pattern.charAt(0);
		
		int firstCharCount = getCount(pattern, firstChar);
		int secondCharCount = pLen - firstCharCount;
		
		int maxLenFirstChar = (vLen - secondCharCount)/firstCharCount;
		
		for (int i=1; i<=maxLenFirstChar; i++) {
			int firstCharLen = i;
			int secondCharLen = (vLen - firstCharCount*firstCharLen)/secondCharCount;
			String patternValue = constructPatternValue(value, pattern, firstCharLen, secondCharLen);
			if (patternValue != null && patternValue.equals(value)) {
				return true;
			}
		}
		
		return false;
	}
	
	private String constructPatternValue(String value, String pattern, int firstCharLen, int secondCharLen) {
		
		char firstChar = pattern.charAt(0);
		
		StringBuilder sb = new StringBuilder();
		int index = 0;
		
		String firstCharPatternVal = null;
		String secondCharPatternVal = null;
		
		for (int i=0; i<pattern.length(); i++) {
			char c = pattern.charAt(i);
			
			if (c == firstChar) {
				String patVal = value.substring(index, index+firstCharLen);
				if (firstCharPatternVal == null) {
					firstCharPatternVal = patVal;
				} else if(!firstCharPatternVal.equals(patVal)) {
					return null;
				}
				sb.append(firstCharPatternVal);
				index += firstCharLen;
			} else {
				String patVal = value.substring(index, index+secondCharLen);
				if (secondCharPatternVal == null) {
					secondCharPatternVal = patVal;
				} else if(!secondCharPatternVal.equals(patVal)) {
					return null;
				}
				sb.append(secondCharPatternVal);
				index += secondCharLen;
			}
		}
		
		return sb.toString();
	}
	
	private int getCount(String s, char c) {
		int count = 0;
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i) == c)
				count++;
		}
		return count;
	}

	public int maxContiguousSum(int[] arr) {
		int sum = 0;
		int maxSum = 0;
		int maxNeg = Integer.MIN_VALUE;
		
		for (int i=0; i<arr.length; i++) {
			if (arr[i] + sum > 0) {
				sum += arr[i];
			} else {
				sum = 0;
				if (arr[i] > maxNeg)
					maxNeg = arr[i];
			}
			
			if (sum > maxSum) {
				maxSum = sum;
			}
		}
		
		return maxSum != 0 ? maxSum : maxNeg;
	}
	// 1 2 4 7 10 11 7 12 6 7 16 18 19	
	// 1 2 4 10 12 6 7 8 9 10 11
	
	public int[] subSort(int[] arr) {
		int small = -1, large = -1;
		
		for (int i=arr.length-1; i>0; i--) {
			if (arr[i] < arr[i-1]) {
				small = i;
				large = i-1;
				break;
			}
		}
		
		if (small == -1)
			return new int[0];
		
		int n =-1, m=-1;
		
		for (int i=0; i<arr.length; i++) {
			if (arr[i] > arr[small]) {
				n = i;
				break;
			}
		}
		
		for (int i=arr.length-1; i>large; i--) {
			if (arr[i] < arr[large]) {
				m = i;
				break;
			}
		}
		
		int result[] = new int[2];
		result[0] = n;
		result[1] = m;
		return result;
	}
	
	public int[] masterMind(String guess, String solution) {
		Map<Character, Integer> mapCount = new HashMap<>();

		int hits =0, psuedoHits = 0;
		
		for (int i=0; i<solution.length(); i++) {
			if (guess.charAt(i) == solution.charAt(i)) {
				hits++;
			} else {
				mapCount.put(solution.charAt(i), mapCount.getOrDefault(solution.charAt(i), 0)+1);
			}
		}
		
		for (int i=0; i<guess.length(); i++) {
			if (mapCount.getOrDefault(guess.charAt(i), 0) > 0) {
				psuedoHits++;
				mapCount.put(guess.charAt(i), mapCount.get(guess.charAt(i)) -1);
			}
		}
		
		int[] result = new int[2];
		result[0] = hits;
		result[1] = psuedoHits;
		return result;
	}
	
	public void encodeXML(Node element, Map<String, Integer> codes, StringBuilder encodedString) {
		if (element == null)
			return;
		
		if (element.getNodeName().equals("#text")) {
			encodedString.append(element.getNodeValue()+ " ");
			return;
		} else {
			encodedString.append(codes.get(element.getNodeName()) + " ");	
		}
		NamedNodeMap map = element.getAttributes();
		
		int index = 0;
		
		while (map != null && map.item(index) != null) {
			Node node = map.item(index++);
			encodedString.append(codes.get(node.getNodeName())+" ");
			encodedString.append(node.getNodeValue()+" ");
		}
		
		encodedString.append(0);
		encodedString.append(" ");
		
		
		NodeList list = element.getChildNodes();
		index = 0;
		while (list != null && list.item(index) != null) {
			encodeXML(list.item(index++), codes, encodedString);
		}
		
		if (list != null && list.item(0) != null) {
			encodedString.append(0);
			encodedString.append(" ");
		}

	}
	
	public String getStringFromDocument(Document doc)
	{
	    try
	    {
	       DOMSource domSource = new DOMSource(doc);
	       StringWriter writer = new StringWriter();
	       StreamResult result = new StreamResult(writer);
	       TransformerFactory tf = TransformerFactory.newInstance();
	       Transformer transformer = tf.newTransformer();
	       transformer.transform(domSource, result);
	       return writer.toString();
	    }
	    catch(TransformerException ex)
	    {
	       ex.printStackTrace();
	       return null;
	    }
	}
	
	public Set<Integer> divingBoard(int k, int shorter ,int longer) {
		Set<Integer> lengths = new HashSet<>();
		for (int i=0 ; i <= k; i++) {
			lengths.add(i * shorter + (k-i) * longer);
		}
		return lengths;
	}
	
	public void divingBoardRecurMemoization(int k, int shorter, int longer, int length, Set<Integer> set, Set<String> visited) {
		if (k == 0) {
			set.add(length);
			return;
		}
		
		String key = k + " " + length;
		
		if (visited.contains(key))
			return;
		
		divingBoardRecurMemoization(k-1, shorter, longer, length + shorter, set, visited);
		divingBoardRecurMemoization(k-1, shorter, longer, length + longer, set, visited);
		
		visited.add(key);
	}
	
	public void divingBoardRecur(int k, int shorter, int longer, int len[], Set<Integer> set) {
		if (k == 0) {
			set.add(len[0]);
			return;
		}
		
		len[0] += shorter;
		divingBoardRecur(k-1, shorter, longer, len, set);
		len[0] -= shorter;
		
		len[0] += longer;
		divingBoardRecur(k-1, shorter, longer, len, set);
		len[0] -= longer;
	}
	
	public int yearWithMostPeopleAliveWithoutSorting(Person[] people, int start, int end) {
		int years[] = new int[end-start+2];
		
		for (int i=0; i<people.length; i++) {
			years[people[i].birth - start]++;
			years[people[i].death - start + 1]--;
		}
		
		int currAlive = 0;
		int maxAlive = 0, maxYear = 0;
		for (int i=0; i<years.length; i++) {
			currAlive += years[i];
			if (currAlive > maxAlive) {
				maxAlive = currAlive;
				maxYear = start + i;
			}
		}
		
		return maxYear;
	}
	
	public int yearWithMostPeopleAlive(Person[] people) {
		int maxAlive = 0, maxYear = 0;
		
		int j=0;

		int currAlive = 0;
		
		Arrays.sort(people);
		System.out.println(Arrays.toString(people));
		
		for (int i=0; i<people.length; i++) {
			if (people[i].birth <= people[j].death) {
				currAlive++;
			} else {
				if (currAlive > maxAlive) {
					maxAlive = currAlive;
					maxYear = people[j].death;
				}
				
				j++;
				currAlive = 1;
			}
		}
		System.out.println(maxAlive);
		return maxYear;
		
	}
	
	private static class Person implements Comparable<Person> {
		int birth;
		int death;
		public Person(int birth, int death) {
			this.birth = birth;
			this.death = death;
		}
		
		@Override
		public int compareTo(Person p) {
			if (this.birth <= p.birth)
				return -1;
			else
				return 1;
		}
		
		@Override
		public String toString() {
			return "("+this.birth+", "+this.death+")";
		}
	}
	
	public int max(int a, int b) {
		int sign_a = sign(a);
		int sign_b = sign(b);
		int sign_c = sign(a-b);
		
		int diff_sign = sign_a ^ sign_b;
		int use_sign_c = flip(diff_sign);
		
		// if diff_sign use sign of 'a'
		// if same sign use sign of 'c'
		
		int k = diff_sign*sign_a + use_sign_c*sign_c;
		int q = flip(k);
		return q * a + k * b;
		
	}
	
	public int min(int a, int b) {
		int sign_a = sign(a);
		int sign_b = sign(b);
		int sign_c = sign(a-b);
		
		int diff_sign = sign_a ^ sign_b;
		int use_sign_c = flip(diff_sign);
		
		// if diff_sign use sign of 'a'
		// if same sign use sign of 'c'
		
		int k = diff_sign*sign_a + use_sign_c*sign_c;
		int q = flip(k);
		return k * a + q * b;
		
	}
	
	private int flip(int bit) {
		return 1 ^ bit;
	}
	
	private int sign(int a) {
		return (a >> 31) & 1;
	}
	
	public int smallestDiff(int[] A, int[] B) {
		Arrays.sort(A);
		Arrays.sort(B);
		
		// 1, 2, 3, 11, 15
		// 8, 19, 23, 127, 235
		
		int i=0, j=0;
		int minDiff = Integer.MAX_VALUE;
		
		while (i < A.length && j < B.length) {
			int diff = Math.abs(A[i] - B[j]);
			if (diff == 0)
				return diff;
			
			if (diff < minDiff)
				minDiff = diff;
			
			if (A[i] < B[j]) {
				i++;
			} else {
				j++;
			}
		}
		
		return minDiff;
	}
	
	public int numFactorialZeroes(int n) {
		int fact = 5;
		int zeroes = 0;
		while (n/fact != 0) {
			zeroes += (n/fact);
			fact *= 5;
		}
		return zeroes;
	}
	
	private class Check {
		int r, c, rowIncr, colIncr;
		
		public Check(int r, int c, int rowIncr, int colIncr) {
			this.r = r;
			this.c = c;
			this.rowIncr = rowIncr;
			this.colIncr = colIncr;
		}

		public boolean inBounds(int size) {
			return r >= 0 && c >= 0 && r < size && c < size;
		}
		
		public void increment() {
			this.r += this.rowIncr;
			this.c += this.colIncr;
		}
	}
	
	
	public Piece hasWon(Piece[][] board) {
		
		int size = board.length;
		
		List<Check> instructions = new ArrayList<>();
		
		for (int i=0; i<size; i++) {
			instructions.add(new Check(i, 0, 0, 1));
			instructions.add(new Check(0, i, 1, 0));
		}
		instructions.add(new Check(0, 0, 1, 1));
		instructions.add(new Check(size-1, 0, -1, 1));
		
		for (Check check : instructions) {
			Piece p = hasWon(board, check);
			if (p != null)
				return p;
		}
		
		return null;
	}
	
	private Piece hasWon(Piece[][] board, Check check) {
		Piece p = board[check.r][check.c];
		while(check.inBounds(board.length)) {
			if (!board[check.r][check.c].equals(p))
				return null;
			check.increment();
		}
		return p;
	}
	
	// to determine the winner for each sum is pretty difficult and i can't think of a solution
	public void generatePossibleBoardSums(int n, Map<Integer, Piece> scoreWinnerMap) {
		int cells = n*n;
		int totalCombinations = (int) Math.pow(3, cells);
		List<Integer> list = new ArrayList<>();
		
		for (int i=0; i<totalCombinations; i++) {
			int c = i;
			int sum = 0;
			int factor = 1;
			for (int j=0; j<cells; j++) {
				for (int k=0; k<3; k++, c = c >>> 1) {
					if ( (c&1) == 1)
						sum += factor * k;
				}
				factor = factor*3;
			}
			list.add(sum);
		}
		
		System.out.println(list.size());
	}
	
	public int convertBoardToInt(Piece[][] board) {
		int sum = 0;
		int factor = 1;
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[0].length; j++) {
				int v = board[i][j].ordinal();
				sum += (factor * v);
				factor = factor * 3;
			}
		}
		return sum;
	}
	
	private enum Piece {
		EMPTY,
		O,
		X
	}
	
	public boolean hasWonTicTacToe(Piece board[][], int row, int col) {
		if (row < 0 || col < 0 || row >= board.length || col >= board[0].length)
			return false;
		
		Piece p = board[row][col];
		if (p == null || p.equals(Piece.EMPTY))
			return false;
		
		if (checkCol(board, col) || checkRow(board, row))
			return true;
		
		if (row == col && checkDiagonal(board, 1))
			return true;
		
		if (col == board.length-row-1 && checkDiagonal(board, -1))
			return true;
		else
			return false;
		
	}
	
	private boolean checkCol(Piece board[][] , int col) {
		
		if (col < 0 || col >= board.length)
			return false;
		
		int row = 0;
		Piece p = board[row][col];
		while (row < board.length) {
			if (!board[row][col].equals(p))
				return false;
			row ++;
		}
		return true;
	}
	
	private boolean checkRow(Piece board[][] , int row) {
		
		if (row < 0 || row >= board.length)
			return false;
		
		int col = 0;
		Piece p = board[row][col];
		while (col < board.length) {
			if (!board[row][col].equals(p))
				return false;
			col++;
		}
		return true;
	}
	
	private boolean checkDiagonal(Piece board[][] , int direction) {
		int row = direction == 1 ? 0 : board.length-1;
		int col = 0;
		
		Piece p = board[row][col];
		
		while (col < board.length) {
			if (!board[row][col].equals(p))
				return false;
			
			row += direction;
			col++;
		}
		
		return true;
	}
	
	public Point intersection(Point start1, Point end1, Point start2, Point end2) {
		if (start1.x > end1.x)
			swap(start1, end1);
		if (start2.x > end2.x)
			swap(start2, end2);
		if (start1.x > start2.x) {
			swap(start1, start2);
			swap(end1, end2);
		}
		
		Line l1 = new Line(start1, end1);
		Line l2 = new Line(start2, end2);
		
		if (l1.slope() == l2.slope()) {
			if (l1.y_intercept() == l2.y_intercept() && isBetween(start1, start2, end1)) {
				return start2;
			} else {
				return null;
			}
		}
		
		// x intercept meaning y in both lines is 0
		double x = (l2.y_intercept() - l1.y_intercept())/(l1.slope() - l2.slope());
		double y = l1.slope()*x + l1.y_intercept();
		
		Point intersection = new Point(x, y);
		if (isBetween(start1, intersection, end1) && isBetween(start2, intersection, end2))
			return intersection;
		
		return null;
		
	}
	
	private boolean isBetween(Point start, Point p, Point end) {
		return start.x <= p.x && p.x <= end.x &&
				start.y <= p.y && p.y <= end.y;
	}
	
	private void swap(Point p1, Point p2) {
		double x = p1.x;
		p1.x = p2.x;
		p2.x = x;
		
		double y = p1.y;
		p1.y = p2.y;
		p2.y = y;
	}
	
	public int[] swapInPlace(int a, int b) {
		int[] arr = new int[2];
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		arr[0] = a;
		arr[1] = b;
		return arr;
	}

}
