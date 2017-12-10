package solutions;

import java.util.Random;

public class SortingAlgorithms<K> {

	private Random random = new Random();

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
