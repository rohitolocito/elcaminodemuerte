package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CTCI_Hard {
	
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
