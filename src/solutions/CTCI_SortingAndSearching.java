package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CTCI_SortingAndSearching {
	
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
