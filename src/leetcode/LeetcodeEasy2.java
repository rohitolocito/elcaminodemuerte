package leetcode;

import java.util.Arrays;

//303. Range Sum Query - Immutable
class NumArray {

	private int[] nums ;
	private int[] sums;

	public NumArray(int[] nums) {
		this.nums = nums;
		this.sums = new int[size()];
		segmentTree(0, 0, nums.length-1);
	}

	private int size() {
		if (nums.length == 0)
			return 0;

		int height = (int)Math.ceil(Math.log(nums.length)/Math.log(2));
		int size = 2 * (int) Math.pow(2, height) - 1;
		return size;
	}

	private void segmentTree(int node, int low, int high) {
		if (low > high)
			return;

		if (low == high) {
			sums[node] = nums[low];
		} else {
			int mid = (low + high) >>> 1;

			segmentTree (2 * node + 1, low, mid);
			segmentTree (2 * node + 2, mid + 1, high);
			sums[node] = sums[2*node + 1] + sums[2*node + 2];
		}
	} 

	public int sumRange(int i, int j) {
		return querySum(0, 0, nums.length-1, i, j);
	}

	private int querySum(int node, int low, int high, int i, int j) {
		if (high < i || low > j)
			return 0;

		if (low >= i && high <= j) {
			return sums[node];
		} else {
			int mid = (low + high) >>> 1;
			int p1 = querySum(2 * node + 1, low , mid ,i, j);
			int p2 = querySum(2 * node + 2, mid + 1 , high ,i, j);
			return p1 + p2;
		}
	}
}

public class LeetcodeEasy2 {

	public static void main(String[] args) {

		LeetcodeEasy2 run = new LeetcodeEasy2();

		int[] arr = {1,2,2,5,3,5};
		System.out.println(Arrays.binarySearch(arr, 4));

		System.out.println("rohan".indexOf(""));

		System.out.println(run.thirdMax(arr));

		System.out.println(Integer.MIN_VALUE > Long.MIN_VALUE);

		int[] arr1 = {2, 2, 3, 1};
		System.out.println(run.thirdMax(arr1));

		int[] kth = {2,1};
		System.out.println(run.findKthLargest(kth, 1));

		System.out.println("Max -> "+ 999 * 999+" Min -> " + 999 * 100);

		System.out.println(run.largestPalindrome1(5));
		System.out.println(run.largestPalindrome2(5));
		
		int[] nums = {3,4,2,3};
		
		System.out.println(run.checkPossibility(nums));


	}
	
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        
        Integer n = null; // largest small num
        
        // 3, 4, 2, 3
        
        // 4, 3, 4, 5

        for (int i=1; i<nums.length; i++) {
            
            if (nums[i] < nums[i-1]) {
                count++;  
                
                if (count > 1)
                    return false;
                
                if (i-2 >= 0) {
                    n = nums[i-2];
                }
            }
            
            if (n != null && nums[i] < n) {
                return false;
            }
        }
        
        return true;
    }

	private static final int[] sizes = {9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999 };

	// BUGGY
	public int largestPalindrome2(int n) {

		if (n <= 0)
			return 0;

		if (n == 1)
			return 9;

		int upperBound = (int)Math.pow(10, n)-1;

		int lowerBound = upperBound/10;

		long maxVal = (long)upperBound * upperBound;

		int firstHalf = (int) (maxVal / (long) Math.pow(10, n));

		long palindrome = 0;

		boolean found = false;

		while (!found) {

			long val = createPalindrome(firstHalf);

			for (long i=upperBound; i>lowerBound; i--) {
				if (val/i > maxVal || i * i < val)
					break;

				if (val % i ==0 ) {
					found = true;
					palindrome = val;
					break;
				}

			}

			firstHalf--;
		}

		return (int)palindrome % 1337;


	}

	private long createPalindrome(int num) {
		return Long.valueOf(num + new StringBuilder(num).reverse().toString());
	}

	public int largestPalindrome1(int n) {
		// if input is 1 then max is 9 
		if(n == 1){
			return 9;
		}

		// if n = 3 then upperBound = 999 and lowerBound = 99
		int upperBound = (int) Math.pow(10, n) - 1, lowerBound = upperBound / 10;
		long maxNumber = (long) upperBound * (long) upperBound;

		// represents the first half of the maximum assumed palindrom.
		// e.g. if n = 3 then maxNumber = 999 x 999 = 998001 so firstHalf = 998
		int firstHalf = (int)(maxNumber / (long) Math.pow(10, n));

		boolean palindromFound = false;
		long palindrom = 0;

		while (!palindromFound) {
			// creates maximum assumed palindrom
			// e.g. if n = 3 first time the maximum assumed palindrom will be 998 899
			palindrom = createPalindrom(firstHalf);

			// here i and palindrom/i forms the two factor of assumed palindrom
			for (long i = upperBound; upperBound > lowerBound; i--) {
				// if n= 3 none of the factor of palindrom  can be more than 999 or less than square root of assumed palindrom 
				if (palindrom / i > maxNumber || i * i < palindrom) {
					break;
				}

				// if two factors found, where both of them are n-digits,
				if (palindrom % i == 0) {
					palindromFound = true;
					break;
				}
			}

			firstHalf--;
		}

		return (int) (palindrom % 1337);
	}

	private long createPalindrom(long num) {
		String str = num + new StringBuilder().append(num).reverse().toString();
		return Long.parseLong(str);
	}

	public int largestPalindrome(int n) {
		int max = getMax(n);
		int min = getMax(n-1);
		int largest = 0;

		for (long i=max; i>min; i--) {
			for (long j=i-1; j>min; j--) {
				if (isPalindrome(i*j))
					largest = Math.max(largest, (int)(i*j));
			}
		}

		return largest% 1337;
	}

	private boolean isPalindrome(long l) {
		long rev = 0;
		long n = l;
		while (n != 0) {
			rev = rev*10 + ( n%10 );
			n = n/10;
		}

		return rev == l;
	}

	private int getMax(int n) {
		if (n <= 0)
			return 0;

		if (n > sizes.length)
			return Integer.MAX_VALUE;

		return sizes[n-1];
	}

	public int findKthLargest(int[] nums, int k) {

		int len = nums.length;

		if (k <= 0 || k > len)
			return -1;

		int low = 0, high = len-1;
		while (low <= high) {
			int index = partition(nums, low, high);
			if (index == len-k)
				return nums[index];

			if (index > len -k) {
				high = index-1;
			} else {
				low = index+1;
			}
		}

		return -1;

	}

	private int partition(int nums[], int low, int high) {
		int pivot = nums[high];
		int i =low, j = high-1;

		while (i <= j) {
			if (nums[i] >= pivot) {
				swap(nums, i,j);
				j--;
			} else {
				i++;
			}
		}

		swap(nums, j+1, high);
		return j+1;
	}

	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	public int thirdMax(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		if (nums.length < 3)
			return getMax(nums);

		Integer max = null;
		Integer secondMax = null;
		Integer thirdMax = null;

		for (Integer n : nums) {

			if (n.equals(max) || n.equals(secondMax) || n.equals(thirdMax))
				continue;

			if (max == null || n > max) {
				thirdMax = secondMax;
				secondMax = max;
				max = n;
			}

			else if (secondMax == null || (n > secondMax && n < max)) {
				thirdMax = secondMax;
				secondMax = n;
			}

			else if (thirdMax == null || (n > thirdMax && n < secondMax)) {
				thirdMax = n;
			}
		}

		return thirdMax == null ? max : thirdMax;

	}

	private int getMax(int nums[]) {
		if (nums.length == 1)
			return nums[0];

		return Math.max(nums[0], nums[1]);
	}

	public int strStr(String haystack, String needle) {
		if (haystack == null || needle == null)
			return -1;

		if (needle.isEmpty())
			return 0;

		for (int i=0; i+needle.length()<=haystack.length(); i++) {
			if (haystack.charAt(i) == needle.charAt(0) && match(haystack, i, needle)) {
				return i;
			}
		}

		return -1;
	}

	private boolean match(String haystack, int index, String needle) {

		for (int i=0; i<needle.length(); i++) {
			if (index >= haystack.length() || needle.charAt(i) != haystack.charAt(index))
				return false;
			index++;
		}
		return true;
	}


	public boolean canPlaceFlowers(int[] flowerbed, int n) {
		if (n < 0 || flowerbed == null || flowerbed.length == 0)
			return false;

		int possible = 0;

		for (int i=0; i<flowerbed.length; i++) {
			if (flowerbed[i] == 0 && (i == 0 || flowerbed[i-1] == 0) && (i == flowerbed.length-1 || flowerbed[i+1] == 0)) {
				flowerbed[i] = 1;
				possible ++;
			}

			if (possible == n)
				break;
		}

		return possible >= n;
	}

}
