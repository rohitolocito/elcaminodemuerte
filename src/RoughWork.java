import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import solutions.SortingAlgorithms;

public class RoughWork {
	
	static class Iter implements Iterable<String> {
		
		List<String> list = new ArrayList<String>();

		@Override
		public Iterator<String> iterator() {
			return new Iterator<String>() {
				
				int index = 0;

				@Override
				public boolean hasNext() {
					return index < list.size(); 
				}

				@Override
				public String next() {
					return list.get(index++);
				}
			};
		}
		
	}
	
	private static class Node {
		int val;
		Node next;
		public Node(int val) {
			this.val = val;
		}
		@Override
		public String toString() {
			Node curr = this;
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			while(curr != null) {
				sb.append(curr.val);
				curr = curr.next;
				if(curr != null)
					sb.append(", ");
			}
			sb.append("]");
			return sb.toString();
		}
	}

	public static void main(String[] args) {

		System.out.println(sumN(5));

		BST bst = new BST(4);
		bst.left = new BST(2);
		bst.left.left = new BST(1);
		bst.left.right = new BST(3);

		bst.right = new BST(6);
		bst.right.left = new BST(5);
		bst.right.right = new BST(7);
		System.out.println(bst.sum());
		System.out.println(isPrime(33));
		System.out.println(isPrime(31));
		System.out.println(isPrime(5));
		System.out.println(sqrt(9));
		System.out.println(factorial(5));
		printPermutations("abc");
		System.out.println(fibRec(20));
		System.out.println(fib(20));
		powersOf2(5);
		RoughWork roughWork = new RoughWork();
		System.out.println(roughWork.equals(roughWork));
		printPairs(1000);
		System.out.println(positions("abbc", "cbabadcbbabbcbabaabccbabc"));
		System.out.println(permutes("abc"));
		
		int[] A = {13, 27, 35, 40, 49, 55, 59};
		int[] B = {17, 35, 39, 40, 55, 58, 60};
		intersection(A, B);
		System.out.println(convertToDecimal("FF", 16));
		System.out.println(charToInt('F'));
		System.out.println(Integer.MAX_VALUE*2);
		Iter i = new Iter();
		System.out.println(i.iterator().hasNext());
		
		SortingAlgorithms<Integer> sort = new SortingAlgorithms<>();
		Integer[] arr = {12,3,7,1,8,9,22};
		sort.QuickSort(arr);
		System.out.println(Arrays.toString(arr));
		Integer[] arr1 = {5,3,2,1};
		sort.QuickSort(arr1);
		System.out.println(Arrays.toString(arr1));
		
		Node odd = new Node(1);
		odd.next = new Node(3);
		odd.next.next = new Node(5);
		odd.next.next.next = new Node(7);
		
		Node even = new Node(2);
		even.next = new Node(4);
		even.next.next = new Node(6);
		even.next.next.next = new Node(8);
		even.next.next.next.next = new Node(10);
		
		Node numbers = interweaveLists(odd, even);
		System.out.println(numbers);
		
	}
	
	public static Node interweaveLists(Node node1, Node node2) {
		if(node1 == null)
			return node2;
		if(node2 == null)
			return node1;
		
		Node list = node1;
		
		while(node1.next != null) {
			if(node2 != null) {
				Node node = node2;
				node2 = node2.next;
				node.next = null;
				
				Node next = node1.next;
				node1.next = node;
				node.next = next;
				node1 = next;
			} else {
				return list;
			}
		}
		node1.next = node2;
		
		return list;
	}
	
	public static int convertToDecimal(String num, int base) {
		if(base < 2 || (base >10 && base != 16))
			return -1;
		
		int sum = 0;
		for(int i= num.length()-1; i>=0; i--) {
			int val = charToInt(num.charAt(i));
			int exp = num.length()-1-i;
			sum += val * Math.pow(base, exp);
		}
		return sum;
	}
	
	public static int charToInt(char c) {
		if (c >= '0' && c <= '9') {
			return (int) (c - '0');
		} else if(c >= 'a' && c <= 'f') {
			return (int) (10 + c - 'a');
		} else if (c >= 'A' && c <= 'F') {
			return (int) (10 + c - 'A');
		}
		return -1;
	}
	
	public static void intersection(int[] A, int[] B) {
		int a =0, b = 0;
		
		List<Integer> common = new ArrayList<>();
		
		while (a < A.length && b < B.length) {
			int valA = A[a];
			int valB = B[b];
			if(valA == valB) {
				common.add(valA);
				a++;
				b++;
			} else if(valA < valB) {
				a++;
			} else if(valA > valB){
				b++;
			}
		}
		
		System.out.println(common);
	}
	
	// abbc         
    // cbabadcbbabbcbabaabccbabc
	
	public static List<String> permutes(String s) {
		if (s == null || s.isEmpty())
			return new ArrayList();
		
		if(s.length() == 1) {
			List<String> list = new ArrayList();
			list.add(s);
			return list;
		}
		
		String last = s.substring(s.length()-1);
		String remaining = s.substring(0, s.length()-1);
		List<String> perms = permutes(remaining);
		System.out.println("Perms -> " + perms);
		List<String> list = new ArrayList();
		for(String p : perms) {
			for(int i=0; i<=p.length(); i++) {
				list.add( p.substring(0,i) +  last + p.substring(i));
			}
		}
		return list;
		
	}
	
	public static List<Integer> positions(String smallStr, String bigStr) {
		List<Integer> list = new ArrayList<>();
		
		if(smallStr == null || bigStr == null)
			return list;
		
		int[] smallCount = new int[256];
		int[] bigCount = new int[256];
		
		char[] small = smallStr.toCharArray();
		char[] big = bigStr.toCharArray();
		
		for(int i=0; i<small.length; i++) {
			smallCount[small[i]]++;
		}
		
		int smallLen = small.length;
		int from = 0;
		for (int i=0; i<big.length; i++) {
			bigCount[big[i]]++;
			if(i - from + 1 == smallLen) {
				if(sameCounts(smallCount, bigCount)) {
					list.add(from);
				}
				bigCount[big[from]]--;
				from++;
			}
		}
		
		return list;
	}
	
	private static boolean sameCounts(int[] a, int[] b) {
		if (a.length != b.length)
			return false;
		
		for(int i=0; i<a.length; i++) {
			if(a[i] != b[i])
				return false;
		}
		return true;
	}

	// a3 + b3 = c3 + d3
	public static void printPairs(int n) {
		if (n <= 0)
			return;

		Map<Integer, List<Pair>> map = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				int result = (int) (Math.pow(i, 3) + Math.pow(j, 3));
				List<Pair> list = map.getOrDefault(result, new ArrayList<Pair>());
				list.add(new Pair(i, j));
				map.put(result, list);
			}
		}
		for(int result : map.keySet()) {
			List<Pair> list = map.get(result);
			for(int i=0; i<list.size(); i++) {
				for(int j=i+1; j<list.size(); j++) {
					Pair p1 = list.get(i);
					Pair p2 = list.get(j);
					if(p1.x != p2.y)
					System.out.println("["+list.get(i)+", " + list.get(j)+"]");
				}
			}
		}
	}

	public static int powersOf2(int n) {
		if (n <= 0) {
			System.out.println(0);
			return 0;
		}

		if (n == 1) {
			System.out.println(1);
			return 1;
		}

		int prev = powersOf2(n / 2);
		int curr = prev * 2;
		System.out.println(curr);
		return curr;
	}

	public static int fibRec(int n) {
		if (n <= 0)
			return 0;

		if (n == 1)
			return 0;

		if (n == 2)
			return 1;

		return fibRec(n - 1) + fibRec(n - 2);
	}

	// 0 1 1 2 3 5
	public static int fib(int n) {
		if (n <= 0)
			return 0;

		int a = 0, b = 1;
		for (int i = 2; i <= n; i++) {
			int x = a + b;
			a = b;
			b = x;
		}
		return a;
	}

	public static void printPermutations(String s) {
		print(s.toCharArray(), 0);
	}

	private static void print(char a[], int index) {
		if (index >= a.length) {
			System.out.println(new String(a));
			return;
		}

		for (int i = index; i < a.length; i++) {
			swap(a, i, index);
			print(a, index + 1);
			swap(a, i, index);
		}
	}

	private static void swap(char str[], int a, int b) {
		char c = str[a];
		str[a] = str[b];
		str[b] = c;
	}

	public static int factorial(int n) {
		if (n <= 1)
			return 1;

		return n * factorial(n - 1);
	}

	public static double sqrt(double a) {
		int low = 0, high = (int) a;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (mid * mid == a) {
				return mid;
			}

			if (mid * mid < a) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}

		}
		return -1;
	}

	public static boolean isPrime(int n) {
		int sqrt = (int) Math.sqrt(n);
		boolean isPrime = true;
		for (int i = 2; i <= sqrt; i++) {
			if (n % i == 0) {
				isPrime = false;
				break;
			}
		}
		return isPrime;
	}

	public static int sumN(int n) {
		if (n <= 0)
			return 0;

		return n + sumN(n - 1);
	}

	static class BST {
		BST left;
		BST right;
		int val;

		public BST(int val) {
			this.val = val;
		}

		int sum() {
			int s = val;
			if (left != null) {
				s += left.sum();
			}

			if (right != null) {
				s += right.sum();
			}
			return s;
		}
	}

}

class Pair {
	int x;
	int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
