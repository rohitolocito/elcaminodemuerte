package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CTCI_SortingAndSearching {
	
	
	private static class RankFromStream {
		
		private class BinaryNode {
			int val;
			int left_size = 0;
			
			BinaryNode left;
			BinaryNode right;
			
			public BinaryNode(int val) {
				this.val = val;
			}
			
			public void insert(int x) {
				if (x > val) {
					if (right == null) {
						right = new BinaryNode(x);
					} else {
						right.insert(x);
					}
				} else {
					if (left == null) {
						left = new BinaryNode(x);
					} else {
						left.insert(x);
					}
					left_size++;
				}
			}
			
			@Override
			public String toString() {
				return String.valueOf(val);
			}
		}
		
		private BinaryNode root;
		
		public void track(int x) {
			if (root == null) {
				root = new BinaryNode(x);
			} else {
				root.insert(x);
			}
		}
		
		public int getRankOfNumber(int x) {
			return getRankOfNumber(root, x);
		}
		
		private int getRankOfNumber(BinaryNode root, int x) {
			if (root == null)
				return -1;
			
			if (root.val == x) 
				return root.left_size;
			
			if (x > root.val) {
				int leftSize = root.left_size;
				leftSize += 1;
				int rank = getRankOfNumber(root.right, x);
				if (rank == -1)
					return -1;
				else
					return leftSize + rank;
			} else {
				return getRankOfNumber(root.left, x);
			}
		}
	}
	
	private static class Listy {
		
		private int[] arr;
		
		public Listy(int[] arr) {
			this.arr = arr;
		}
		
		int elementAt(int index) {
			if (index < 0 || index >= arr.length)
				return -1;
			
			return arr[index];
		}
		
	}
	
	public static void main(String[] args) {
		
		CTCI_SortingAndSearching run = new CTCI_SortingAndSearching();
		
		int A[] = {1,3,5,7,9,0,0};
		int B[] = {2,4};
		run.sortedMerge(A, B);
		System.out.println(Arrays.toString(A));
		
		String[] sarr= {"loco", "calo", "look", "cool", "cola"};
		run.groupAnagrams(sarr);
		System.out.println(Arrays.toString(sarr));
		
		int[] rotated = {15, 16, 19, 20, 25, 15, 15, 15, 15, 15, 15, 15};
		System.out.println(run.searchRotatedArr(rotated, 16));
		
		Listy listy = new Listy(A);
		System.out.println(run.findElement(listy, 9));
		
		String sparseArr[] = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
		System.out.println(run.sparseSearch(sparseArr, "at"));
		//System.out.println(run.missingInt());
		
	
		long size = (long)Integer.MAX_VALUE+1; //2^32
		
		int[] fileData = {5,3,1,4,0,2,6,7};
		
		byte[] bytes = new byte[(int)(size/8)];
		for (int i=0; i<fileData.length; i++) {
			bytes[fileData[i]/8] |= (1 << (fileData[i] % 8));
		}
		
		System.out.println(run.missingIntByteArray(bytes));
		
		int[][] mat = {{15, 20, 40, 85}, 
					   {20, 35, 80, 95}, 
					   {30, 55, 95, 105}, 
					   {40, 80, 100, 120}
					  };
		
		
		System.out.println(run.sortedMatrixSearch(mat, 100));
		
		System.out.println(run.sortedMatricSearchComplex(mat, new Coordinate(0, 0), new Coordinate(mat.length-1, mat[0].length-1), 100));
		
		RankFromStream fromStream = new RankFromStream();
		fromStream.track(5);
		fromStream.track(1);
		fromStream.track(4);
		fromStream.track(4);
		fromStream.track(5);
		fromStream.track(9);
		fromStream.track(7);
		fromStream.track(13);
		fromStream.track(3);
		
		System.out.println(fromStream.getRankOfNumber(1));
		System.out.println(fromStream.getRankOfNumber(3));
		System.out.println(fromStream.getRankOfNumber(4));
		System.out.println(fromStream.getRankOfNumber(13));
		
		int[] arr = {9, 1, 0, 4, 8, 2};
		run.peaksAndValleys(arr);
	}
	
	public void peaksAndValleys(int[] arr) {

		for (int i=1; i<arr.length; i+=2) {
			
			int temp = arr[i];
			
			if (arr[i] < arr[i-1]) {
				arr[i] = arr[i-1];
				arr[i-1] = temp;
			} else if (i+1 < arr.length && arr[i] < arr[i+1]) {
				arr[i] = arr[i+1];
				arr[i+1] = temp;
			}
			
		}
		
		System.out.println(Arrays.toString(arr));
	}
	
	private static class Coordinate implements Cloneable {
		int r;
		int c;
		public Coordinate(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public void setToAverage(Coordinate a, Coordinate b) {
			this.r = (a.r + b.r)/2;
			this.c = (a.c + b.c)/2;
		}
		
		public boolean isBefore(Coordinate a) {
			return this.r <= a.r && this.c <= a.c;
		}
		
		public boolean inbounds(int[][] mat) {
			return r >= 0 && r < mat.length && c >= 0 && c < mat[0].length;
		}
		
		@Override
		public String toString() {
			return "("+this.r+", "+this.c+")";
		}
		
		@Override
		protected Object clone() {
			return new Coordinate(r, c);
		}
	}
	
	public Coordinate sortedMatricSearchComplex(int[][]mat,Coordinate origin, Coordinate dest, int x) {
		
		if (!origin.inbounds(mat) || !dest.inbounds(mat))
			return null;
		
		if (x == mat[origin.r][origin.c])
			return origin;
		
		if (!origin.isBefore(dest))
			return null;
	
		Coordinate start = (Coordinate) origin.clone();
		Coordinate end = (Coordinate) dest.clone();
		Coordinate pivot = new Coordinate(0, 0);
		
		
		// binary search on the diagonal of the mat
		while (start.isBefore(end)) {
			pivot.setToAverage(start, end);
			
			if (x < mat[pivot.r][pivot.c]) {
				end.r = pivot.r-1;
				end.c = pivot.c-1;
			} else {
				start.r = pivot.r+1;
				start.c = pivot.c+1;
			}
		}
		
		return partitionMatrix(mat, x, origin, start, dest); // why start instead of pivot !!!!??
	}
	
	private Coordinate partitionMatrix(int[][] mat, int x, Coordinate origin, Coordinate pivot, Coordinate dest) {
		Coordinate leftBottomQuadrantOrigin = new Coordinate(pivot.r, origin.c);
		Coordinate leftBottomQuadrantDest = new Coordinate(dest.r, pivot.c-1);
		
		Coordinate found = sortedMatricSearchComplex(mat, leftBottomQuadrantOrigin, leftBottomQuadrantDest, x);
		if (found == null) {
			Coordinate topRightQuadrantOrigin = new Coordinate(origin.r, pivot.c);
			Coordinate topRightQuadrantDest = new Coordinate(pivot.r-1, dest.c);
			found = sortedMatricSearchComplex(mat, topRightQuadrantOrigin, topRightQuadrantDest, x);
		}
		return found;
	}
	
	// O (m + n)
	public boolean sortedMatrixSearch(int[][] mat, int x) {
		int r = 0, c = mat[0].length-1;
		
		while (r < mat.length && c >= 0) {
			if (mat[r][c] == x) 
				return true;
			if (mat[r][c] > x) {
				c--;
			} else {
				r++;
			}
		}
		
		return false;
	}
	
	public void printDuplicates10KBMemory(int[] array) {
		BitSet set = new BitSet(32000);
		for (int i=0; i<array.length; i++) {
			if (set.get(array[i])) {
				System.out.print(array[i] +", ");
			} else {
				set.set(array[i]);
			}
		}
		System.out.println();
	}
	
	public int missingIntByteArray(byte[] bytes) {
		
		for (int i=0; i<bytes.length; i++) {
			for(int j=0; j<8; j++) {
				if ((bytes[i] & (1 << j)) == 0) {
					return  i*8 + j;
				}
			}
		}
		
		return -1;
	}
	
	public long missingInt() {
		BitSet set = new BitSet();
		long size = (long)Integer.MAX_VALUE + 1;
		
		for (long i=1 ; i<=size; i++) {
			if(i != 777777) {
				set.set((int)i-1);
				System.out.println(i);
			}
		}
		
		int missing = 0;
		
		for (long i=0; i<set.size(); i++) {
			if (!set.get((int)i)) {
				return i+1;
			}
		}
		
		return missing;
	}
	
	// {"aaaa", "", "", "", "zzzz", "", "", "", "", "", "", "zzzzz", ""};
	// s = "ball"
	public int sparseSearch(String[] arr, String s) {
		return sparseSearch(arr, s, 0, arr.length-1);
	}
	
	private int sparseSearch(String[] arr, String s, int low, int high) {
		if (low > high)
			return -1;
		
		int mid = (low + high) >>> 1;
		
		if (arr[mid].equals(s)) 
			return mid;
		
		if (arr[mid].isEmpty()) {
			
			int left=mid-1, right=mid+1;
			int newLow = -1, newHigh = -1;
			
			// unnecessary logic that can be simplified...
			while (left >= low && right <= high) {
				if (!arr[left].isEmpty()) {
					if (arr[left].compareTo(s) >= 0) {
						newLow = low;
						newHigh = left;
					} else {
						newLow = right;
						newHigh = high;
					}
					break;
				} else if(!arr[right].isEmpty()) {
					if (arr[right].compareTo(s) <= 0) {
						newLow = right;
						newHigh = high;
					} else {
						newLow = low;
						newHigh = left;
					}
					break;
				}
				left--;
				right++;
			}
			if (newLow == -1 || newHigh == -1)
				return -1;
			
			return sparseSearch(arr, s, newLow, newHigh);
			
		} else if (arr[mid].compareTo(s) < 0) {
			return sparseSearch(arr, s, mid+1, high);
		} else {
			return sparseSearch(arr, s, low, mid-1);
		}
	}
	
	public int findElement(Listy listy, int x) {
		
		int index = 1;
		
		while (listy.elementAt(index) != -1 && listy.elementAt(index) < x) {
			index = index*2;
		}
		
		return findElement(listy, x, index/2, index);
	}
	
	private int findElement(Listy listy, int x, int low, int high) {
		if (low > high)
			return -1;
		
		int mid = (low + high) >>> 1;
		
		if (listy.elementAt(mid) == -1)
			return -1;
		
		if (listy.elementAt(mid) == x)
			return mid;
		
		if (listy.elementAt(mid) < x) {
			return findElement(listy, x, mid+1, high);
		} else {
			return findElement(listy, x, low, mid-1);
		}
		
	}
	
	public int searchRotatedArr(int[] arr, int x) {
		return searchRotatedArr(arr, x, 0, arr.length-1);
	}
	
	private int searchRotatedArr(int[] arr, int x, int low, int high) {
		if (low > high)
			return -1;
		
		int mid = (low + high) >>> 1;
		
		if (arr[mid] == x)
			return mid;
		
		
		if (arr[mid] <= arr[high]) { // right side is sorted
			
			if (x > arr[mid] && x <= arr[high]) {
				
				return searchRotatedArr(arr, x, mid+1, high); //search in sorted part
				
			} else {
				
				return searchRotatedArr(arr, x, low, mid-1); // no choice , search in unsorted part
			}
			
		} else { // left side is sorted
			
			if (x < arr[mid] && x >= arr[low]) {
				
				return searchRotatedArr(arr, x, low, mid-1);
				
			} else {
				
				return searchRotatedArr(arr, x, mid+1, high);
			}
			
		}
	}
	
	public void groupAnagrams(String[] arr) {
		
		Map<String, List<String>> sortedStringMap = new HashMap<>();
		
		for (String s : arr) {
			String sortStr = sort(s);
			List<String> list = sortedStringMap.getOrDefault(sortStr, new ArrayList<>());
			list.add(s);
			sortedStringMap.put(sortStr, list);
		}
		int index = 0;
		for (List<String> list : sortedStringMap.values()) {
			for(String s : list) 
				arr[index++] = s;
		}
		
	}
	
	private String sort(String a) {
		char c[] = a.toCharArray();
		Arrays.sort(c);
		return new String(c);
	}
	
	public void sortedMerge(int[] A, int[] B) {
		
		int lastA =0;
		for (int i=1; i<A.length; i++) {
			if (A[i] < A[i-1]) {
				lastA = i-1;
			}
		}
		int lastB = B.length-1;
		int k = A.length-1;
		while (lastB >= 0) {
			if (A[lastA] > B[lastB]) {
				A[k--] = A[lastA--];
			} else {
				A[k--] = B[lastB--];
			}
		}
	}

}
