package CrackingTheCodingInterview6;

import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithms<K> {

	private Random random = new Random();
	
	public void radixSort(Integer[] arr) {
		
		int max = max(arr);
		
		for (int exp = 1; max/exp > 0; exp = exp*10) {
			radix(arr, exp);
		}
	}
	
	private void radix(Integer[] arr, int exp) {
		
		int count[] = new int[10];
		
		for (int i=0; i<arr.length; i++) {
			count[(arr[i]/exp) % 10]++;
		}
		
		for (int i=1; i<count.length; i++) {
			count[i] = count[i] + count[i-1];
		}
		
		Integer[] output = new Integer[arr.length];
		
		// in radix sort last to first matters
		for (int i=arr.length-1; i>=0; i--) {
			output[count[(arr[i]/exp) % 10]-1] = arr[i];
			count[(arr[i]/exp) % 10]--;
		}
		
		for (int i=0; i<arr.length; i++) {
			arr[i] = output[i];
		}
	}
	
	private int max(Integer[] arr) {
		int max = arr[0];
		for (int i=0; i<arr.length; i++) {
			if (arr[i] > max)
				max = arr[i];
		}
		return max;
	}
	
	private int smallestNonPositiveInteger(Integer[] arr) {
		int min = 0;
		
		for (int i=0; i<arr.length; i++) {
			if (arr[i] < 0 && arr[i] < min)
				min = arr[i];
		}
		
		return min;
	}
	
	public void countingSortEfficient(Integer[] arr, int K) {
		int min = smallestNonPositiveInteger(arr);
		
		int add = -min;
		
		for (int i=0; i<arr.length; i++) {
			arr[i] = arr[i] + add;
		}
		
		int[] count = new int[ K+ add + 1 ];
		for (int i=0; i<arr.length; i++) {
			count[arr[i]]++;
		}
		
		for(int i=1; i<count.length; i++) {
			count[i] = count[i] + count[i-1];
		}
		
		int[] output = new int[arr.length];
		for (int i=0; i<arr.length; i++) {
			output[count[arr[i]]-1] = arr[i] - add;
			count[arr[i]]--;
		}
		
		for (int i=0; i<arr.length; i++) {
			arr[i] = output[i];
		}
	}
	
	public void countingSortNonStableVersion(Integer[] arr, int K) {
		int[] count = new int[K+1];
		for (int i=0; i<arr.length; i++) {
			count[arr[i]]++;
		}
		int j = 0;
		// not very efficient...
		// what if arr = {4000, 100, 250} and K = 10000
		// in this case we are looping through 10K times
		for(int i=0; i<count.length; i++) {
			while (count[i] > 0) {
				arr[j++] = i;
				count[i]--;
			}
		}
	}
	
	public void mergeSort(K[] arr) {
		mergeSort(arr, 0, arr.length-1);
	}
	
	private void mergeSort(K[] arr, int low, int high) {
		if (low < high) {
			int mid = (low + high) >>> 1;
			mergeSort(arr, low, mid);
			mergeSort(arr, mid+1, high);
			merge(arr, low, high);
		}
	}
	
	private void merge(K[] arr, int low, int high) {
		int mid = (low + high) >>> 1;
		
		Object left[] = new Object[mid-low+1];
		Object right[] = new Object[high-mid];
		
		for (int i=low; i<= mid; i++)
			left[i-low] = arr[i];
		
		for (int i=mid+1; i<=high; i++)
			right[i - (mid+1)] = arr[i];
		
		int i=0, j=0, k=low;
		
		while(k <= high) {
			K l = i < left.length ? (K) left[i] : null;
			K r = j < right.length ? (K) right[j] : null;
			
			if ( (l != null && r != null && ((Comparable<K>)l).compareTo(r) < 0) || r == null) {
				arr[k] = l;
				i++;
			} else {
				arr[k] = r;
				j++;
			}
			k++;
		}
		
		
	}
	
	public void bubbleSort(K[] arr) {
		for (int i=0; i<arr.length; i++) {
			for (int j=0; j+1<arr.length; j++) {
				if (((Comparable<K>)arr[j+1]).compareTo(arr[j]) < 0)
					swap(arr, j, j+1);
			}
		}
	}
	
	public void selectionSort(K[] arr) {
		int last = arr.length-1;
		while (last > 0) {
			for (int i=0; i<last; i++) {
				if ( ((Comparable<K>)arr[last]).compareTo(arr[i]) < 0) {
					swap(arr, i, last);
				}
			}
			last--;
		}
	}

	public void QuickSort(K[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	private void quickSort(K[] arr, int a, int b) {
		if (a < b) {
			int p = partition(arr, a, b);
			quickSort(arr, a, p - 1);
			quickSort(arr, p + 1, b);
		}
	}
	
	private int randomizedPartition(K[] arr, int a, int b) {
		int pivot = random(a, b);
		swap(arr, pivot, b);
		return partition(arr, a, b);
	}

	private int partition(K[] arr, int a, int b) {
		K val = arr[b];
		int i = a;
		int j = b-1;
		while (i <= j) {
			if (((Comparable<K>) arr[i]).compareTo(val) > 0) {
				swap(arr, i, j);
				j--;
			} else {
				i++;
			}
		}
		swap(arr, j+1, b);
		return j+1;

	}

	private int random(int from, int to) {
		return from + random.nextInt(to - from + 1);
	}

	private void swap(K[] arr, int a, int b) {
		K obj = arr[a];
		arr[a] = arr[b];
		arr[b] = obj;
	}
}
