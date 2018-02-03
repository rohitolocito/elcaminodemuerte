package zalando;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    
    @Override
    public String toString() {
    	return String.valueOf(val);
    }
}

public class Zalando {
	
	public static void main(String[] args) {
		
		Zalando run = new Zalando();
		System.out.println(run.reverse(1234));
		System.out.println(run.reverse("1234567899"));
		System.out.println(run.pascalTriangle(5));
		
		int[] arr= {5, -1, 3, 8, 6};
		int[] arr1 = {-1, 5, 3, 6, 8};
		System.out.println(run.partitionIndex(arr));
		System.out.println(run.partitionIndex(arr1));
		System.out.println(run.countPalindromes("bab"));
		System.out.println(run.countPalindromes("barbarabar"));
		
		char board[][] = {{'X','.','.','X'},
						  {'.','.','.','X'},
						  {'.','.','.','X'}};
		
		System.out.println(run.countBattleships(board));
		
		int A[] = {60, 80, 40 };
		int B[] = {2, 3, 5};
		
		System.out.println(run.countStops(A, B, 2, 200));
	}
	
	public int countStops(int[] weights, int[] floors, int maxAllowed, int maxWeight) {
		int weight = 0;	
		int stops = 0;
		int numOfPeople = 0;
		Set<Integer> set = new HashSet<>();
		
		for (int i=0; i<weights.length; i++) {
			if (weight + weights[i] > maxWeight || numOfPeople > maxAllowed) {
				stops += set.size() + 1;
				set.clear();
				weight = 0;
				numOfPeople = 0;
			}
			numOfPeople++;
			weight += weights[i];
			set.add(floors[i]);
		}
		
		stops += set.size() == 0 ? 0 : set.size()+1;
		
		return stops;
	}
	
    public int countBattleships(char[][] board) {
        int count = 0;
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (board[i][j] == 'X') {
                    boolean isNew = true;
                    
                    if (i >= 1) {
                        isNew = isNew && board[i-1][j] != 'X';
                    }
                    
                    if (j >= 1) {
                        isNew = isNew && board[i][j-1] != 'X';
                    }
                    
                    if (isNew) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
	
	public int countPalindromes(String s) {
		boolean[][] isPalindrome = new boolean[s.length()][s.length()];
		
		for (int i=0; i<isPalindrome.length; i++) 
			isPalindrome[i][i] = true;
		
		for (int len=2; len <= isPalindrome.length; len++) {
			for (int i=0; i+len<=s.length(); i++) {
				int j = i+len-1;
				isPalindrome[i][j] = s.charAt(i) == s.charAt(j) && isPalindrome[i+1][j-1];
			}
		}
		
		Set<String> set = new HashSet<>();
		for (int i=0; i<isPalindrome.length; i++) {
			for (int j=0; j<isPalindrome.length; j++) {
				if (isPalindrome[i][j]) {
					set.add(s.substring(i, j+1));
				}
			}
		}
		System.out.println("Palindromes -> "+set);
		return set.size();
	}
	
	public int partitionIndex(int[] arr) {
		if (arr.length == 0)
			return -1;
		
		int partition = -1;
		int prevMax=-1, currMax= arr[0];
		
		for (int i=1; i<arr.length; i++) {
			
			if (arr[i] > currMax) {
				prevMax =currMax;
				currMax = arr[i];
				partition = i;
			}
			
			if (prevMax != -1) {
				if (arr[i] <= prevMax)
					return -1;
			}
		}
		
		
		return partition;
	}
	
	public List<List<Integer>> pascalTriangle(int n) {
		List<List<Integer>> res = new ArrayList<>();
		
		if (n <= 0)
			return res;
		
		List<Integer> list = new ArrayList<>();
		list.add(1);
		res.add(list);
		
		for (int i=2; i<= n; i++) {
			list = new ArrayList<>();
			list.add(1);
			
			List<Integer> prev = res.get(res.size()-1);
			for (int j=0; j+1<prev.size(); j++) {
				list.add(prev.get(j) + prev.get(j+1));
			}
			
			list.add(1);
			res.add(list);
		}
		
		return res;
	}
	
	public TreeNode getSubTree(TreeNode[] arr, TreeNode node) {
		TreeNode[] res = new TreeNode[1];
		getSubTree(arr, 0, arr.length-1, node, res);
		return res[0];
	}
	
	private TreeNode getSubTree(TreeNode[] arr, int low, int high, TreeNode node, TreeNode[] res) {
		if (low > high)
			return null;
		
		if (res[0] != null)
			return null;
		
		int mid = (low + high) >>> 1;
		TreeNode root = arr[mid];
		root.left = getSubTree(arr, low, mid-1, node, res);
		root.right = getSubTree(arr, mid+1, high, node, res);
		if (root == node) {
			res[0] = root;
		}
		return root;
		
	}
	
	
	public int reverse(int num) {
		int rev = 0;
		
		while (num != 0) {
			rev = rev*10 + num%10;
			num = num/10;
			
		}
		return rev;
	}
	
	public long reverse(String num) {
		long rev = 0;
		long prev = 0;
		for (int i=num.length()-1; i>=0; i--) {
			int n = (num.charAt(i) - '0');
			rev = rev*10 + n;
			if ((rev - n) / 10 != prev) {
				System.out.println("overflow");
				break;
			}
			prev = rev;
		}
		return rev;
	}

}
