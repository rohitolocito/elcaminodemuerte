package solutions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datastructures.MyTrie;

public class CTCI_Moderate {
	
	private static class IntegerToEnglishPhrase {
		
		static final String BILLION = "Billion";
		static final String MILLION = "Million";
		static final String HUNDRED = "Hundred";
		static final String THOUSAND = "Thousand";
		static final String SPACE = " ";
		
		String unitsTeens[] = {"zero", "one", "two", "three", "four", "five", "six","seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
		
		String tens[] = {"", "ten", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};
		
		public void integerToEnglishPhrase(int n) {
			
			DecimalFormat decimalFormat = new DecimalFormat("000000000000");
			String number = decimalFormat.format(n);
			
			String billions = number.substring(0,3);
			String millions = number.substring(3,6);
			String thousands = number.substring(6,9);
			String hundreds = number.substring(9);
			
			StringBuilder result = new StringBuilder();
			
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

	public static void main(String[] args) {
		
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
		translator.integerToEnglishPhrase(999999999);
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
