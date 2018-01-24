package google;

import java.util.Arrays;
import java.util.LinkedList;

public class Google {

	public static void main(String[] args) {
		
		Google run = new Google();
		
		int arr[] = {2,3,1,2,4,3};
		System.out.println(Arrays.toString(run.subArrayWithGivenSum(arr, 7)));
		
		
		int arr1[] = {34, 8, 10, 3, 2, 80, 30, 33, 1};
		int arr2[] = {9, 2, 3, 4, 5, 6, 7, 8, 18, 0};
		int arr3[] = {1, 2, 3, 4, 5, 6};
		int arr4[] = {6, 1, 4, 3, 2, 1};
		int arr5[] = {12, 15, 10, 30, 11, 7};
		
		System.out.println(run.maxJ_I(arr) + " " + run.maxJ_ICorrect(arr));
		System.out.println(run.maxJ_I(arr1)+ " " + run.maxJ_ICorrect(arr1));
		System.out.println(run.maxJ_I(arr2)+ " " + run.maxJ_ICorrect(arr2));
		System.out.println(run.maxJ_I(arr3)+ " " + run.maxJ_ICorrect(arr3));
		System.out.println(run.maxJ_I(arr4)+ " " + run.maxJ_ICorrect(arr4));
		System.out.println(run.maxJ_I(arr5)+ " " + run.maxJ_ICorrect(arr5));
	}
	
	public int maxJ_ICorrect(int[] arr) {
		
		int leftMin[] = new int[arr.length];
		int rightMax[] = new int[arr.length];
		
		leftMin[0] = arr[0];
		for (int i=1; i<arr.length; i++) {
			leftMin[i] = Math.min(leftMin[i-1], arr[i]);
		}
		
		rightMax[arr.length-1] = arr[arr.length-1];
		for (int i=arr.length-2; i>=0; i--) {
			rightMax[i] = Math.max(rightMax[i+1], arr[i]);
		}
		int max = 0;
		
		int i=0, j=0; 
		while (i < arr.length && j < arr.length) {
			if (leftMin[i] < rightMax[j]) {
				max = Math.max(max, j-i);
				j++;
			} else {
				i++;
			}
		}
		
		return max;
	}
	
	
//	  Input: {34, 8, 10, 3, 2, 80, 30, 33, 1}
//	  Output: 6  (j = 7, i = 1)
//
//	  Input: {9, 2, 3, 4, 5, 6, 7, 8, 18, 0}
//	  Output: 8 ( j = 8, i = 0)
//
//	  Input:  {1, 2, 3, 4, 5, 6}
//	  Output: 5  (j = 5, i = 0)
//
//	  Input:  {6, 5, 4, 3, 2, 1}
//	  Output: -1 
//
// INCORRECT SOLUTION
	public int maxJ_I(int[] arr) {
		int i = 0, j = arr.length-1;
	
		while (i < j) {
			if (arr[j] > arr[i]) {
				return j-i;
			}
			
			if (j-1 > i && arr[j-1] > arr[i]) 
				j--;
			else
				i++;
		}
		
		return -1;
	}
	
	// smallest subarray
	// {0,9,9,18}
	// sum = 18
	public int[] subArrayWithGivenSum(int[] arr, int sum) {
		
		int from = 0, to = Integer.MAX_VALUE;
		int currSum = 0;
		int currFrom = -1, currTo = -1;
		
		for (int i=0; i<arr.length; i++) {
			
			currSum += arr[i];
			
			if (currFrom == -1) {
				currFrom = i;
			}
			
			currTo = i;

			while (currSum > sum) {
				currSum -= arr[currFrom];
				currFrom++;
			}
			
			
			if (currSum == sum) {
				if (currTo-currFrom < to-from) {
					from = currFrom;
					to = currTo;
				}
			}
			
		}
		
		int[] range = new int[2];
		if (to != Integer.MAX_VALUE) {
			range[0] = from;
			range[1] = to;
			return range;
		} else {
			return null;
		}
		
	}

}
