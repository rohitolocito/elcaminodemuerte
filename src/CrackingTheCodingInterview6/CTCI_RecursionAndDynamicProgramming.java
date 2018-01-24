package CrackingTheCodingInterview6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import datastructures.MyPoint;

public class CTCI_RecursionAndDynamicProgramming {
	
	private enum Color {
		BLUE,
		YELLOW,
		GREEN
	}
	
	private static class Box implements Comparable<Box> {
		int w;
		int h;
		int d;
		
		public Box(int w, int h, int d) {
			this.w = w;
			this.h = h;
			this.d = d;
		}

		@Override
		public int compareTo(Box o) {
			if (this.h < o.h)
				return 1;
			else if (this.h > o.h)
				return -1;
			return 0;
		}
	}

	public static void main(String[] args) {
		
		CTCI_RecursionAndDynamicProgramming run = new CTCI_RecursionAndDynamicProgramming();
		
		// 0, 1, 1, 2, 3, 5, 8, 13
		System.out.println("** fibonacci **");
		System.out.println(run.fibonacci(15));
		System.out.println(run.fibonacciMemoization(49));
		System.out.println(run.fibonacciBottomUp(49));
		System.out.println(run.fibonacciNoAdditionalSpace(490));
		
		System.out.println("\n** triple step **");
		System.out.println(run.tripleStep(4));
		System.out.println(run.tripleStepMemoization(4));
		
		boolean[][] mat = {{false, true, false},
						   {false, false, true},
						   {true, false, false}};
		System.out.println(run.robotPath(mat));
		
		int[] arr = {-40, -20, -1, 1, 2, 3, 5, 7, 9, 12 ,13};
		System.out.println(run.magicIndex(arr));
		
		int[] arrDups = {-10,-5, 2, 2, 2, 3, 4, 7, 9, 12, 13};
		System.out.println(run.magixIndexDupElements(arrDups));
		
		int[] arrDups1 = {-10, 1, 1, 2, 2, 3, 4, 8, 9, 10, 11};
		System.out.println(run.magixIndexDupElements(arrDups1));
		
		int[] set = {1,2,3};
		System.out.println(run.powerSet(set));
		
		System.out.println(run.powerSetFaster(set));
		
		System.out.println(run.multiply(1392, 1711));
		
		System.out.println(run.recurseMultiple(1392, 1711));
		
		Stack<Integer> stack1 = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();
		Stack<Integer> stack3 = new Stack<>();
		int n = 7;
		for(int i=n; i>0; i--)
			stack1.push(i);
		run.towersOfHanoi(n, stack1, stack2, stack3);
		while(!stack3.isEmpty())
			System.out.print(stack3.pop()+", ");
		System.out.println();
		
		System.out.println(run.permutationsWithoutDups("abcd"));
		System.out.println(run.permutationsWithDups("abbc"));
		System.out.println(run.validCombinationsParenthesis(3));
		System.out.println(run.validCombinationsParenthesisLessEfficient(3));
		
		Color[][] screen = { {Color.BLUE, Color.GREEN, Color.BLUE},
							 {Color.YELLOW, Color.YELLOW, Color.YELLOW},
							 {Color.BLUE, Color.GREEN, Color.YELLOW},
							 {Color.BLUE, Color.GREEN, Color.YELLOW}
						   };
		
		run.fillScreen(screen, Color.YELLOW, 3, 0);
		for (int i=0; i<screen.length; i++) {
			for (int j=0; j<screen[0].length; j++) {
				System.out.print(screen[i][j]+", ");
			}
			System.out.println();
		}
		
		int[] denom = {25, 10, 5, 1};
		//10, 1, 1
		//5, 5, 1, 1
		//5, 1, 1, 1, ....
		//1, 1, ....
		System.out.println(run.coins(12, denom));
		System.out.println(run.coins(25, denom));
		System.out.println(run.coins(12500, denom));
		List<Integer[]> ways = run.nQueens(8);
		System.out.println(ways.size());
		for(Integer[] w : ways) {
			System.out.println(Arrays.toString(w));
		}
		
		Box[] boxArr = {new Box(1, 8, 1), new Box(6, 6, 6), new Box(4, 4, 4), new Box(2, 2, 2)};
		
		Arrays.sort(boxArr);
		
		List<Box> boxes = Arrays.asList(boxArr);
		
		System.out.println(run.maxHeight(boxes));
		
		System.out.println(run.booleanExp("1^0|0|1", false));
	}
	
	public int booleanExp(String booleanExp, boolean val) {
		
		if (booleanExp.isEmpty())
			return 0;
		
		if (booleanExp.length() == 1) {
			boolean currVal = booleanExp.charAt(0) == '0' ? false : true;
			return currVal == val ? 1 : 0;
		}
		
		int numOfWays = 0;
		
		for(int i=1; i<booleanExp.length(); i+=2) {
			String left = booleanExp.substring(0, i);
			String right = booleanExp.substring(i+1);
			char c = booleanExp.charAt(i);
			
			if (c == '^') {
				if (val == false) {
					numOfWays += booleanExp(left, true)*booleanExp(right, true) +
							booleanExp(left, false)*booleanExp(right, false);
				} else {
					numOfWays += booleanExp(left, true)*booleanExp(right, false) +
							booleanExp(left, false)*booleanExp(right, true);
				}
			} else if (c == '|') {
				if (val == false) {
					numOfWays += booleanExp(left, false)*booleanExp(right, false);
				} else {
					numOfWays += booleanExp(left, true)*booleanExp(right, true) +
							booleanExp(left, false)*booleanExp(right, true) + 
						booleanExp(left, true)*booleanExp(right, false);
				}
			} else if (c == '&') {
				if (val == false) {
					numOfWays += booleanExp(left, false)*booleanExp(right, false) +
							booleanExp(left, false)*booleanExp(right, true) +
						booleanExp(left, true)*booleanExp(right, false);
				} else {
					numOfWays += booleanExp(left, true)*booleanExp(right, true);
				}
			}
			
		}
		
		return numOfWays;
		
	}
	
	private int evaluate(String s) {
		
		if (s.length() == 1) {
			return s.charAt(0) - '0';
		}
		
		int a = s.charAt(0) - '0';
		char op = s.charAt(1);
		int b = s.charAt(2) - '0';
		
		int res = 0;
		
		switch(op) {
		case '^' : res = a ^ b;
			break;
		case '|' : res = a | b;
			break;
		case '&' : res = a & b;
			break;
		
		}
		
		return res ;
	}
	
	public int maxHeight(List<Box> boxes) {
		int maxHeightMap[] = new int[boxes.size()];
		return maxHeightBoxStack(boxes, null, 0, 0, maxHeightMap);
	}
	
	private int maxHeightBoxStack(List<Box> boxes, Box prev, int index,  int currHeight, int maxHeightMap[]) {
		if (index >= boxes.size()) {
			return currHeight;
		}
		
		if (maxHeightMap[index] != 0)
			return maxHeightMap[index];
		
		int maxHeightWithThisBoxAsBase = 0;
		
		for(int i=index; i<boxes.size(); i++) {
			if (prev == null || isBelowBigger3Dimensions(prev, boxes.get(i))) {
				maxHeightWithThisBoxAsBase = Math.max(maxHeightWithThisBoxAsBase, maxHeightBoxStack(boxes, boxes.get(i), i+1, currHeight + boxes.get(i).h, maxHeightMap));
			}
		}
		
		maxHeightMap[index] = maxHeightWithThisBoxAsBase;
		return maxHeightWithThisBoxAsBase;
	}
	
	private boolean isBelowBigger3Dimensions(Box below, Box top) {
		return below.d > top.d && below.h > top.h && below.w > top.w;
	}
	
	
	
	public List<Integer[]> nQueens(int n) {
		Integer[] cols = new Integer[n];
		List<Integer[]> ways = new ArrayList<>();
		numOfWaysNQueens(0, cols, ways);
		return ways;
	}
	
	// cols is storing column indices
	// but i iterator will indicate the row
	private void numOfWaysNQueens(int row, Integer[] cols, List<Integer[]> ways) {
		
		if (row == cols.length) {
			ways.add(cols.clone());
			return;
		}
		
		for (int c = 0; c < cols.length; c++) {
			if(canPlace(cols, row, c)) {
				cols[row] = c;
				numOfWaysNQueens(row+1, cols, ways);
			}
		}

	}
	
	private boolean canPlace(Integer cols[], int row2, int col2) {
		
		for(int row1=0; row1<row2; row1++) {
			
			int col1 = cols[row1];
			
			if (col1 == col2)
				return false;
			
			int colDiff = Math.abs(col1 - col2);
			
			int rowDiff = row2 - row1;
			
			if (colDiff == rowDiff)
				return false;
		}
		
		return true;
	}
	
	public int coins(int n, int[] denom) {
		int[][] map = new int[n+1][denom.length];
		return numOfWaysCents(n, 0, denom, map);
	}
	
	private int numOfWaysCents(int remaining, int index, int[] denom, int[][] map) {
		if (remaining < 0)
			return 0;
		
		if (remaining == 0)
			return 1;
		
		if(map[remaining][index] != 0)
			return map[remaining][index];
		
		int numOfWays = 0;
		
		for (int i=index; i<denom.length; i++) {
			numOfWays += numOfWaysCents(remaining-denom[i], i, denom, map);
			
		}
		map[remaining][index] = numOfWays;
		return numOfWays;
	}
	
	public void fillScreen(Color[][] screen, Color with, int x, int y) {
		if (x >= screen.length || x < 0 || y >= screen[x].length || y < 0) {
			return;
		}
		
		if (screen[x][y] == with)
			return;
		
		screen[x][y] = with;
		
		fillScreen(screen, with, x+1, y);
		fillScreen(screen, with, x-1, y);
		fillScreen(screen, with, x, y+1);
		fillScreen(screen, with, x, y-1);
	}
	
	public List<String> validCombinationsParenthesisLessEfficient(int n) {
		//Set because we are generating duplicates here
		Set<String> combinations = validCombinationsParenthesisLessEfficient1(n);
		return combinations.stream().collect(Collectors.toList());
	}
	
	private Set<String> validCombinationsParenthesisLessEfficient1(int remaining) {
		Set<String> combinations = new HashSet<>();
		if(remaining == 0) {
			combinations.add("");
			return combinations;
		}
		
		Set<String> prev = validCombinationsParenthesisLessEfficient1(remaining-1);
		
		for (String s : prev) {
			for (int i=0; i<s.length(); i++) {
				if (s.charAt(i) == '(') {
					String str = insertPairParenthesis(s, i);
					combinations.add(str);
				}
			}
			combinations.add("()" + s);
		}
		return combinations;
	}
	
	private String insertPairParenthesis(String s, int index) {
		String left = s.substring(0, index+1);
		String right = s.substring(index+1);
		return left + "()" + right;
	}
	public List<String> validCombinationsParenthesis(int n) {
		List<String> list = new ArrayList<>();
		validCombinationsParenthesis(n, n, "", list);
		return list;
	}
	
	private void validCombinationsParenthesis(int open, int close, String sb, List<String> list) {
		if(open == 0 && close == 0) {
			list.add(sb);	
			return;
		}
		
		if (open > 0) {
			validCombinationsParenthesis(open-1, close, sb + '(', list);
			if (close > open) {
				validCombinationsParenthesis(open, close-1, sb + ')', list);
			}
			
		} else {
			validCombinationsParenthesis(open, close-1, sb + ')', list);
		}
	}
	
	public List<String> permutationsWithDups(String dups) {
		Map<Character, Integer> charCounts = new HashMap<>();
		for (int i=0; i<dups.length(); i++) {
			charCounts.put(dups.charAt(i), charCounts.getOrDefault(dups.charAt(i), 0)+1);
		}
		List<String> permutations = new ArrayList<>();
		permutationsWithDups(new StringBuilder(), dups.length(), charCounts, permutations);
		return permutations;
	}
	
	private void permutationsWithDups(StringBuilder permute, int remaning , Map<Character, Integer> charCounts, List<String> permutations) {
		
		if (remaning == 0) {
			permutations.add(permute.toString());
			return;
		}
		
		for(char c : charCounts.keySet()) {
			if (charCounts.get(c) > 0) {
				charCounts.put(c, charCounts.get(c)-1);
				permute.append(c);
				permutationsWithDups(permute, remaning-1, charCounts, permutations);
				permute.deleteCharAt(permute.length()-1);
				charCounts.put(c, charCounts.get(c)+1);
			}
		}
	}
	
	// all permutations of n-1 chars and set current char at each possible position to generate n! permutations
	// this algo is not going to have more than 256! permutations assuming unique string & ascii charset.
	// string is immutable
	public List<String> permutationsWithoutDups(String unique) {
		List<String> permutations = new ArrayList<>();
		if (unique == null) {
			return permutations;
		}
		
		if (unique.isEmpty()) {
			permutations.add("");
			return permutations;
		}
		
		char firstChar = unique.charAt(0);
		String remaining = unique.substring(1);
		List<String> n_1Permutes = permutationsWithoutDups(remaining);
		for (int i=0; i<n_1Permutes.size(); i++) {
			String s = n_1Permutes.get(i);
			for(int j=0; j<=s.length(); j++) {
				StringBuilder sb = new StringBuilder();
				sb.append(s.substring(0, j));
				sb.append(firstChar);
				sb.append(s.substring(j));
				permutations.add(sb.toString());
			}
		}
		return permutations;
	}
	
	public void towersOfHanoi(int n, Stack<Integer> stack1, Stack<Integer> stack2, Stack<Integer> stack3) {
		if (n <= 0)
			return;
		
		if (n == 1) {
			stack3.push(stack1.pop());
			return;
		}
		
		if (n == 2) {
			stack2.push(stack1.pop());
			stack3.push(stack1.pop());
			stack3.push(stack2.pop());
			return;
		}
		
		towersOfHanoi(n-1, stack1, stack3, stack2);
		stack3.push(stack1.pop());
		towersOfHanoi(n-1, stack2, stack1, stack3);
		
	}
	
	public int recurseMultiple(int a, int b) {
		
		if (a < b)
			return recurseMultiple(b, a);
		
		if (a == 0 || b == 0)
			return 0;
		
		if (b == 1)
			return a;
		
		int val = recurseMultiple(a, b >>> 1);
		if (b % 2 == 0) 
			val = val + val;
		else
			val = val + val + a;
		return val;
	}
	
	public int multiply(int a, int b) {
		if (a < b)
			return multiply(b, a);
		
		int sum = 0;
		
		while(b != 0) {
			sum += a;
			b--;
		}
		return sum;
	}
	
	public List<List<Integer>> powerSetFaster(int[] arr) {
		List<List<Integer>> powerSet = new ArrayList<List<Integer>>();
		int size = 1 << arr.length;
		for(int i=0; i<size; i++) {
			List<Integer> list = new ArrayList<>();
			int c = i;
			for(int j=0; j<arr.length; j++, c=c>>>1) {
				if((c&1) == 1)
					list.add(arr[j]);
			}
			powerSet.add(list);
		}
		return powerSet;
	}
	
	public List<List<Integer>> powerSet(int[] arr) {
		List<List<Integer>> powerSet = new ArrayList<List<Integer>>();
		powerSet.add(new ArrayList<>());
		
		for(int i=0; i<arr.length; i++) {
			int size = powerSet.size();
			for(int j=0; j<size; j++) {
				List<Integer> list = powerSet.get(j);
				List<Integer> clone = new ArrayList<>(list);
				clone.add(arr[i]);
				powerSet.add(clone);
			}
		}
		
		return powerSet;
	}
	
	public int magixIndexDupElements(int[] arr) {
		return binarySearchWithRepetation(arr, 0, arr.length-1);
	}
	
	private int binarySearchWithRepetation(int[] arr, int low, int high) {
		if (low > high)
			return -1;
		
		int mid = (low + high) >>> 1;
		if (arr[mid] == mid)
			return mid;
		else if(mid > arr[mid]) { // 5 > 3
			int index = -1;
			if (arr[mid] >= 0) {
				index = binarySearchWithRepetation(arr, low, arr[mid]);				
			}
			if (index != -1)
				return index;
			else
				return binarySearchWithRepetation(arr, mid+1, high);

		} else {
			int index = binarySearchWithRepetation(arr, arr[mid], high);
			if (index != -1)
				return index;
			else
				return binarySearchWithRepetation(arr, low, mid-1);
			
		}
	}
	
	public int magicIndex(int arr[]) {
		return binarySearch(arr, 0, arr.length-1);
	}
	
	private int binarySearch(int[] arr, int low, int high) {
		if (low > high)
			return -1;
		
		int mid = (low + high) >>> 1;
		if (arr[mid] == mid)
			return mid;
		else if(mid < arr[mid]) {
			return binarySearch(arr, low, mid-1);
		} else {
			return binarySearch(arr, mid+1, high);
		}
	}
	
	public Stack<MyPoint> robotPath(boolean[][] mat) {
		Stack<MyPoint> path = new Stack<>();
		return robotPathExists(mat, path, 0, 0) ? path : new Stack<>();
	}
	
	private boolean robotPathExists(boolean[][] mat, Stack<MyPoint> path, int x, int y) {
		if(x == mat.length-1 && y == mat[0].length-1) {
			path.push(new MyPoint(x, y));
			return true;
		}
		
		if (x >= mat.length || y >= mat[0].length)
			return false;
		
		if (mat[x][y])
			return false;

		
		boolean pathExists = robotPathExists(mat,path, x+1, y);
		if(pathExists) {
			path.push(new MyPoint(x, y));
			return true;
		}
		
		pathExists = robotPathExists(mat,path, x, y+1);
		if(!pathExists)
			mat[x][y] = true;// ideally we should have another data structure and not override original one
		else 
			path.push(new MyPoint(x, y));
		return pathExists;
	}
	
	public int tripleStepMemoization(int n) {
		if (n <= 0)
			return 0;
		
		int ways[] = new int[n+1];
		tripleStepMemoization(n, ways);
		return ways[n];
	}
	
	public int tripleStepMemoization(int n, int[] ways) {
		if (n < 0)
			return 0;
		
		if (n == 0)
			return 1;
		
		
		if (ways[n] != 0)
			return ways[n];
		
		return ways[n] = tripleStepMemoization(n-1,ways) + tripleStepMemoization(n-2,ways) + tripleStepMemoization(n-3,ways);
		
	}
	
	public int tripleStep(int n) {
		if (n < 0)
			return 0;
		
		if (n == 0)
			return 1;
		
		return tripleStep(n-1) + tripleStep(n-2) + tripleStep(n-3);
			
	}
	
	// O(1) space complexity
	public int fibonacciNoAdditionalSpace(int n) {
		int a = 0, b = 1;
		if (n == 1)
			return a;
		
		if (n == 2)
			return b;
		
		for(int i=3 ; i<=n ;i++) {
			int c = a + b;
			a = b;
			b = c;
		}
		
		return b;
	}
	
	public int fibonacciBottomUp(int n) {
		if (n <= 2)
			return fibonacci(n);
		
		int fib[] = new int[n+1];
		fib[1] = 0;
		fib[2] = 1;
		
		for(int i=3; i<=n; i++) {
			fib[i] = fib[i-1] + fib[i-2];
		}
		return fib[n];
	}
	
	public int fibonacciMemoization(int n) {
		
		if (n <= 2)
			return fibonacci(n);
		
		int[] fibs = new int[n+1];
		
		fibs[0] = 0;
		fibs[1] = 0;
		fibs[2] = 1;
		
		fibonacci(n, fibs);
		return fibs[n];
	}
	
	private int fibonacci(int n, int[] fibs) {
		if (n == 1 || n == 2) {
			return fibs[n];
		}
		
		if (fibs[n] != 0)
			return fibs[n];
		
		int v = fibonacci(n-1, fibs) + fibonacci(n-2, fibs);
		fibs[n] = v;
		return fibs[n];

	}
	
	public int fibonacci(int n) {
		if (n <= 0)
			return 0;
		
		if (n == 1)
			return 0;
		
		if (n == 2)
			return 1;
		
		return fibonacci(n-1) + fibonacci(n-2);
	}

}
