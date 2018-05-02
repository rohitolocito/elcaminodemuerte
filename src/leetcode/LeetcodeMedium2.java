package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Codec {
    
    private static final String SPLIT = "," ;
    private static final String NULL = "X" ;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
        
    }
    
    
    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL);
            sb.append(SPLIT);
            return;
        }
        
        sb.append(root.val);
        sb.append(SPLIT);
        
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
        Queue<String> queue = new LinkedList<>();
        
        String[] arr = data.split(SPLIT);
        queue.addAll(Arrays.asList(arr));
        return deserialize(queue);
    }
    
    private TreeNode deserialize(Queue<String> queue) {
        
        String curr = null;
        
        if (queue.isEmpty() || (curr=queue.poll()).equals(NULL))
            return null;
        
        TreeNode node = new TreeNode(Integer.parseInt(curr));
        node.left = deserialize(queue);
        node.right = deserialize(queue);
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
class MapSum {
    
    private class TrieNode {
        
        char c;
        Map<Character, TrieNode> childNodes;
        int count = 0;
        
        public TrieNode(char c) {
            this.c = c;
            this.childNodes = new HashMap<>();
            this.count = 0;
        }
    }
    
    private void insert(TrieNode root, String key, int val) {
        if (key == null || key.isEmpty())
            return;
        
        for (int i=0; i<key.length(); i++) {
            char c = key.charAt(i);
            if (root.childNodes.containsKey(c)) {
                root = root.childNodes.get(c);
            } else {
                TrieNode node = new TrieNode(c);
                root.childNodes.put(c, node);
                root = node;
            }
            root.count += val;
        }
    }
    
    private int getSum(TrieNode root, String prefix) {
        if (prefix == null || prefix.isEmpty())
            return 0;
        
        for (int i=0; i<prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (root.childNodes.containsKey(c)) {
                root = root.childNodes.get(c);
            } else {
                return 0;
            }
        }
        
        return root.count;
    }
    
    private TrieNode root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode('\0');
    }
    
    public void insert(String key, int val) {
        insert(root, key, val);
    }
    
    public int sum(String prefix) {
        return getSum(root, prefix);
    }
}


public class LeetcodeMedium2 {
	
	public static void main(String[] args) {
		
		LeetcodeMedium2 run = new LeetcodeMedium2();
		
		int[] arr = {4,3,2,7,8,2,3,1};
		System.out.println(run.findDuplicates(arr));
		
		System.out.println(run.numDecodings("12"));
		
		String words[] = {"step","steps","stripe","stepple"};
		
		System.out.println(run.shortestCompletingWord("1s3 PSt", words));
		
		System.out.println(~0);
		
		List<Integer> list = new ArrayList<>();
		list.stream().toArray(size -> new Integer[size]);

		TreeNode node = new TreeNode(5);
		node.left = new TreeNode(2);
		node.right = new TreeNode(-3);
		
		System.out.println(Arrays.toString(run.findFrequentTreeSum(node)));
		
		MapSum mapSum = new MapSum();
		mapSum.insert("aa", 3);
		mapSum.insert("aa", 2);
		System.out.println(mapSum.sum("aa"));
		
		System.out.println((int)'a');
		
		System.out.println(run.minimumDeleteSum("ccbcbcaca", "babbbbbbacacac"));
		
		String s1 = "vvtyefwwizchowjjtugnnomjlbunvgadxytfgvoineovysawpcnrcxntaigvmanlmnzxhhgctupzowehebinzsngbmusaxkpzjaqqyzfoaitibyznfdolgeqgkyroxsnfevnbeofiifuzmmestvubfbubzocthawvyxfaxwwoareeathehvahtpiievrexdxcvpxczzvotmhureolvenbxhvbkoxvfeoarmhldyacnqzceeycgyvwijkcjbwqjgrtkdpvnnnjzsexikirftfppakivmspcfsngglsjotukywlffiedwghlutkysarmnmsjkpmuduyqtrdauwezpsqbhfxgasfzvucvpgxapunqnuhkeisdycphqvqusdecmzedprruyjfcryrkkofhjepchwcfvkyazqlcbfauszbjekbl";
		String s2 = "nzxveyykyiarjfzmqxtpiepnlrqpxtikhmwuycrrwktvqvofidfphmaidlpbklskibtfqgfohohhdzrvzhejrznvlozyiqbvkmcoiobjkqezeyibwziehysisayhdgpecknfkyntasksztpgyywkgvwdpqtojxlebprqgwflhaodcmwzcikipo";
		System.out.println(run.minimumDeleteSum(s1, s2));
		
		int nums[] = {10, 9, 2, 5, 3, 7, 101, 18};
		System.out.println(run.lengthOfLIS(nums));
		System.out.println(run.lengthOfLISEfficient(nums));
		
		int nums1[] = {1, 3, 5, 4, 7 };
		System.out.println(run.lengthOfLISEfficient(nums1));
		
		int nums2[] = {5,4,0,3,1,6,2};
		System.out.println(run.arrayNesting(nums2));
		
		Collections.fill(new ArrayList<>(), "");
		
		Map<Integer, List<String>> map = new HashMap<>();
		map.computeIfAbsent(1, x -> new ArrayList<>()).add("");
		
		char board[][] = {
				{'E','E','E','E','E'},
				{'E','E','M','E','E'},
				{'E','E','E','E','E'},
				{'E','E','E','E','E'}
				};
		
		
		char[][] result = run.updateBoard(board, new int[]{3, 0});
		int arr1[] = {3, 10, 5, 25, 2, 8};
		System.out.println(run.findMaximumXOR(arr1));
		
		List<String> dict = new ArrayList<>() ;
		dict.add("cat");
		dict.add("bat");
		dict.add("rat");
		String sentence = "the cattle was rattled by the battery";
		System.out.println(run.replaceWords(dict, sentence));
		
		int[] num1 = {1,0,-1,0,-2,2};
		System.out.println(run.fourSum(num1, 0));
		
		int[][] pairs = {{7,9},{4,5},{7,9},{-7,-1},{0,10},{3,10},{3,6},{2,3}};
		System.out.println(run.findLongestChain(pairs));

		System.out.println(run.integerBreak(4));
		
		List<List<Integer>> wall = new ArrayList<>();
		wall.add(new ArrayList<>());
		wall.add(new ArrayList<>());
		wall.add(new ArrayList<>());
		wall.get(0).add(1);
		wall.get(1).add(1);
		wall.get(2).add(1);
		
		//System.out.println(run.leastBricks(wall));
		
		List<List<Integer>> wall1 = new ArrayList<>();
		wall1.add(new ArrayList<>());
		wall1.add(new ArrayList<>());
		wall1.add(new ArrayList<>());
		wall1.add(new ArrayList<>());
		wall1.add(new ArrayList<>());
		wall1.add(new ArrayList<>());
		wall1.get(0).add(1);
		wall1.get(0).add(2);
		wall1.get(0).add(2);
		wall1.get(0).add(1);
		
		wall1.get(1).add(3);
		wall1.get(1).add(1);
		wall1.get(1).add(2);
		
		wall1.get(2).add(1);
		wall1.get(2).add(3);
		wall1.get(2).add(2);
		
		wall1.get(3).add(2);
		wall1.get(3).add(4);
		
		wall1.get(4).add(3);
		wall1.get(4).add(1);
		wall1.get(4).add(2);
		
		wall1.get(5).add(1);
		wall1.get(5).add(3);
		wall1.get(5).add(1);
		wall1.get(5).add(1);
		
		System.out.println(run.leastBricks(wall1));
		
		int[][] matrix = {
				{ 1, 2, 3 },
				{ 4, 5, 6 },
				{ 7, 8, 9 }
			};
		
		System.out.println(Arrays.toString(run.findDiagonalOrder(matrix)));
		int nums3[] = {1,2,3};
		Runnable r = () -> System.out.println("hey");
		
		int ar[] = {4,2,0,2,3,2,0};
		// 4 2 0 3 2 2 0
		run.nextPermutation(ar);
		System.out.println(Arrays.toString(ar));
		
		int[] prices = {1, 3, 2, 8, 4, 9};
		System.out.println(run.maxProfit(prices, 2));
		
		int array1[] = { 7, 9, 5, 6, 3, 2 };
		int array2[] = {2, 3, 10, 6, 4, 8, 1};
		int array3[] = {7,5,4,1};
		
		
		System.out.println(run.maxDiff(array1));
		System.out.println(run.maxDiff(array2));
		System.out.println(run.maxDiff(array3));
		
		System.out.println(run.maxDiffBetter(array1));
		System.out.println(run.maxDiffBetter(array2));
		System.out.println(run.maxDiffBetter(array3));
		
		System.out.println(run.nthUglyNumber(7));
		
		System.out.println(run.diffWaysToCompute("2-1-1"));
		
	}

	
    public List<Integer> diffWaysToCompute(String input) {
        
        List<Integer> result = new ArrayList<>();
        
        if (input == null || input.isEmpty())
            return result;
        
        for (int i=0; i<input.length(); i++) {
            
            char c = input.charAt(i);
            
            if (c == '+' || c == '-' || c == '*') {
                
                List<Integer> list1 = diffWaysToCompute(input.substring(0,i));
                List<Integer> list2 = diffWaysToCompute(input.substring(i+1)); 

                for (int v1 : list1) {
                    for (int v2 : list2) {
                        result.add(evaluate(v1, v2, c));
                    }
                }
                
            }

        }
        
        if (result.isEmpty()) {
        	result.add(Integer.valueOf(input));
        }
        
        return result;

    }
    
    
    private int evaluate(int v1, int v2, char op) {
        switch(op) {
            case '+' : return v1 + v2;
            case '*' : return v1 * v2;
            case '-' : return v1 - v2;
        }
        return 0;
    }
	
	public int nthUglyNumber(int n) {
        if (n <= 0)
           return -1;
        
        long x = 1;
        
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.add(x);
        
        while (n > 0) {
            x = queue.poll();
            
            while(!queue.isEmpty() && queue.peek() == x) 
	            queue.poll();
            
            n--;
            
            queue.add(x*2);
            queue.add(x*3);
            queue.add(x*5);
        }
        
        return (int) x;
        
    }
	
	 public boolean escapeGhosts(int[][] ghosts, int[] target) {
	        
	        int myDistance = distance(0, 0, target); 
	        int shortestDistanceGhost = Integer.MAX_VALUE; 
	        
	        for (int i=0; i<ghosts.length; i++) {
	            shortestDistanceGhost = Math.min(shortestDistanceGhost, distance(ghosts[i][0], ghosts[i][1], target));
	        }
	        
	        return shortestDistanceGhost > myDistance;
	    }
	    
	    private int distance(int r, int c, int[] target) {
	        int rows = Math.abs(target[0] - r);
	        int cols = Math.abs(target[1] - c) ;
	        
	        return rows + cols;
	    }
	
	   public ListNode reverseKGroup(ListNode head, int k) {
	        if (head == null || k <= 0)
	            return head;
	        
	        int count = k;
	        ListNode node = head;
	        while (count > 1 && node != null) {
	            count--;
	            node = node.next;
	        }
	        
	        if (node == null)
	            return head;
	        
	        ListNode nextGroup = node.next;
	        node.next = null;
	        nextGroup = reverseKGroup(nextGroup, k) ;
	        
	        ListNode rev = reverse(head);
	        head.next = nextGroup;
	        return rev;
	        
	    }
	    
	    private ListNode reverse(ListNode head) {
	        if (head == null || head.next == null)
	            return head;
	        
	        ListNode next = head.next;
	        ListNode newHead = reverse(next);
	        next.next = head;
	        head.next = null;
	        return newHead;
	    }
	
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n <= 0)
            return head;
        
        ListNode nthFromEnd = head;
        ListNode node = head;
        
        while (n > 0) {
            if (node == null)
                return null;
            n--;
            node = node.next;
        }
        
        ListNode prev = null;
        
        while (node != null) {
            prev = nthFromEnd;
            nthFromEnd = nthFromEnd.next;
            node = node.next;
        }
        
        if (prev == null)
            return head.next;
        
        prev.next = nthFromEnd.next;
        
        return head;
    }
	
	public ListNode removeNthFromBeginning(ListNode head, int n) {
        if (n <= 0)
            return head;
        
        ListNode prev = null;
        ListNode node = head;
        
        while (n > 1) {
            if (node == null)
                return null;
            n--;            
            prev = node;
            node = node.next;
        }
        
        if (prev == null)
            return head.next;
        
        prev.next = node == null ? null : node.next;
        return head;
    }
	
	public ListNode[] splitListToParts(ListNode root, int k) {
        
        
        ListNode[] res = new ListNode[k];
        
        if (k == 0 || root == null)
            return res;
        
        
        int len = len(root);
        
        int size = len / k;
        
        int remaining = len % k;
        
        for (int i=0; i<res.length; i++) {
            if (size == 0) {
                if (root != null) {
                    ListNode node = root;
                    res[i] = node;
                    root = node.next;
                    node.next= null;
                }
            } else {
                int s = size;
                ListNode node = root;
                while (s > 1) {
                    s--;
                    node = node.next;
                }
                if (remaining > 0) {
                    node = node.next;
                    remaining --;
                }
                res[i] = root;
                root = node.next;
                node.next = null;
            }

        } 
        
        return res;
        
    }
    
    private int len(ListNode node) {
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        
        return len;
    }
	
	public int maxDiffBetter(int[] arr) {
		
		if (arr == null || arr.length == 0)
			return 0;
		
		int min = arr[0];
		int maxDiff = Integer.MIN_VALUE;
		
		for (int i=1; i<arr.length; i++) {
			min = Math.min(min, arr[i]);
			maxDiff = Math.max(maxDiff, arr[i]-min);
		}
		
		return maxDiff;
	}
	
	public int maxDiff(int[] arr) {
		if (arr == null || arr.length == 0)
			return 0;
		
		int min = arr[0];
		int max = arr[0];
		int maxDiff = 0;
		
		for (int i=1; i<arr.length; i++) {
			if (arr[i] < min) {
				min = arr[i];
				max = arr[i];
			}
			
			if (arr[i] > max) {
				max = arr[i];
			}
			
			maxDiff = Math.max(maxDiff, max-min);
		}
		
		return maxDiff;
	}
	
	 public int maxProfit(int[] prices, int fee) {
	        
	        if (prices == null || prices.length == 0)
	            return 0;
	        
	        int hold = -prices[0];
	        int cash = 0;
	        
	        for (int i=1; i<prices.length; i++) {
	            cash = Math.max(cash, prices[i] + hold-fee);
	            hold = Math.max(hold, cash - prices[i]);
	            System.out.println(cash+": "+hold);
	        }
	        
	        return cash;
	 }
	
    public List<List<Integer>> combinationSum3(int k, int n) {
        
        return combinations(k, n, 1);
         
     }
     
     private List<List<Integer>> combinations(int k, int n, int from) {
          List<List<Integer>> result = new ArrayList<>();
         
         if (k <= 0 || n <= 0 || (k == 1 && n >9))
             return result;
         
         if (k == 1 && n >= from) {
             List<Integer> list = new ArrayList<>();
             list.add(n);
             result.add(list);
             return result;
         }
         
         for (int i=from; i<=9; i++) {
             List<List<Integer>> list = combinations(k-1, n-i, i+1);
             for(List<Integer> l : list) {
                 l.add(i);
             }
             result.addAll(list);
         }
         
         return result;
     }
	
	//4 2 2 2 3 0 0 
	//4 2 2 0 0 2 3 
	   public void nextPermutation(int[] nums) {
	        
	        if (nums ==null || nums.length ==0)
	            return;
	        
	        Integer x = null;
	        
	        Integer index = null;
	        
	        for (int i=nums.length-1; i>0; i--) {
	            for (int j=i-1; j>=0; j--) {
	                if (nums[j] < nums[i]) {
	                    if (index == null || j > index) {
	                    	index = j;
	                    	x = i;
	                    }
	                }
	            }
	        }
	        
	        if (index == null) {
	            Arrays.sort(nums);
	        } else {
	            int temp = nums[x];
	            nums[x] = nums[index];
	            nums[index] = temp;
	            Arrays.sort(nums, index+1, nums.length);   
	        }
	        
	    }
	
	public int[] findDiagonalOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[] res = new int[m*n];
        
        int r=0, c=0, d = 1;
        
        for(int i=0; i<res.length; i++) {
            
            res[i] = matrix[r][c];
            r -=d;
            c +=d;
            
            
            if (r >= m) {
                d =-d;
                r = m-1;
                c += 2;
            }
            if (c >= n) {
                d =-d;
                c = n-1;
                r += 2;
            }
            if (r < 0) {
                d =-d;
                r =0;
            } 
            if (c < 0) {
                d =-d;
                c =0;
            }
           

        }
        
        return res;
    }
	
    public int leastBricks(List<List<Integer>> wall) {
        
        if (wall.isEmpty() || wall.size()==1)
            return 0;
        
        int res = 0;
        
        for (int i=0; i<wall.size(); i++) {
            List<Integer> row = wall.get(i);
            int sum = 0;
            for (int j=0; j<row.size(); j++) {
                sum += row.get(j);
                row.set(j, sum);
            }
        }
        
        List<Integer> base = wall.get(0);
        int maxVal = base.get(base.size()-1);
        for (int i=1; i<maxVal; i++) {
            res = Math.max(res, getCount(wall, i));
        }
        
        return wall.size()-res;
    }
    
    private int getCount (List<List<Integer>> wall, int key) {
        int count = 0;
        for (int i=0; i<wall.size(); i++) {
            List<Integer> row = wall.get(i);
            if (Collections.binarySearch(row, key) >= 0) 
                count++;
        }
        return count;
    }
	
	private Map<Integer, Integer> dp = new HashMap<>();
	
	  public int integerBreak(int n) {
		  	dp.put(1, 1);
	        if (n <= 0)
	            return 0;
	        
	        if (dp.containsKey(n))
	            return dp.get(n);
	        
	        int max = 0;
	        
	        for (int i=1; i<n; i++) {
	            max = Math.max(max, Math.max(i*(n-i), i*integerBreak(n-i)));
	        }
	        
	        dp.put(n, max);
	        
	        return max;
	    }
	
	public int findLongestChain(int[][] arr) { 
        Arrays.sort(arr, (a,b) -> a[1] - b[1]);
        int currA = Integer.MIN_VALUE;
        int count = 0;
        for (int[] pair : arr) {
        	if (currA < pair[0]) {
        		currA = pair[1];
        		count++;
        	}
        }
        return count;
    }
	
//  Dynamic Programming Solution: 
    
//  private class Pair implements Comparable<Pair> {
//      int a; 
//      int b;
     
//      public Pair(int a, int b) {
//          this.a = a;
//          this.b = b;
//      }
     
//      public int compareTo(Pair p) {
//          return this.a - p.a;
//      }
//  }
 
//  public int findLongestChain(int[][] arr) {
     
//      Pair[] pairs = new Pair[arr.length];
     
//      for (int i=0; i<arr.length; i++) {
//          pairs[i] = new Pair(arr[i][0], arr[i][1]);
//      }
     
//      Arrays.sort(pairs);
     
//      int dp[] = new int[pairs.length];
     
//      for (int i=pairs.length-1; i>=0; i--) {
//          dp[i] = 1;
//          for (int j=i+1; j<pairs.length; j++) {
//              if (pairs[i].b < pairs[j].a) {
//                  dp[i] = Math.max(dp[i], 1 + dp[j]);
//              }
//          }
//      }
     
//      int max = 0;
     
//      for (int n : dp) {
//          max = Math.max(max, n);
//      }
     
//      return max;
//  }
	
	 public List<List<Integer>> fourSum(int[] nums, int target) {
	        
	        List<List<Integer>> result = new ArrayList<>();
	        
	        Arrays.sort(nums);
	        
	        for (int i=0; i<nums.length-3; i++) {
	            
	            for(int j=i+1; j<nums.length-2; j++) {
	               
	                int m = j+1;
	                int n = nums.length-1;
	                
	                while (m < n) {
	                    int sum = nums[i] + nums[j] + nums[m] + nums[n];
	                    if (sum == target) {
	                    	
	                    	List<Integer> list =new ArrayList<>();
	                        list.add(nums[i]);
	                        list.add(nums[j]);
	                        list.add(nums[m]);
	                        list.add(nums[n]);
	                        
	                        result.add(list);
	                        
	                        m++;
	                        n--;
	                        
	                        
	                    } else if (sum > target) {
	                        
	                        n--;
	                    } else {
	                        
	                        m++;
	                    }  
	                    
	                    while (n-1 > m && nums[n-1] == nums[n])
                            n--;
	                    
	                    while (m+1 < n && nums[m+1] == nums[m])
                            m++;
	                }
	            }
	        }
	        
	        return result;
	        
	    }
	
	 public String replaceWords(List<String> dict, String s) {
	        
	        Set<String> set = dict.stream().collect(Collectors.toSet());
	        
	        StringBuilder res = new StringBuilder();
	        
	        StringBuilder prefix = new StringBuilder();
	        
	        for (int i=0; i<s.length(); i++) {
	            
	            if (s.charAt(i) == ' ') {
	                prefix.setLength(0);
	                res.append(' ');
	                continue;
	            }
	            
	            prefix.append(s.charAt(i));
	            res.append(s.charAt(i));
	            
	            if (dict.contains(prefix.toString())) {
	                while (i+1 < s.length() && s.charAt(i+1) != ' ')
	                    i++;
	            }
	            
	        }
	        
	        return res.toString();
	    }
	
	public int findMaximumXOR(int[] nums) {
        
        int max = 0; 
        int mask = 0;
        
        for (int i=31; i>=0 ;i--) {
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for (int n: nums) {
                set.add(n & mask);
            }
            
            int temp = max | (1 << i);
            for (int n : set) {
                if (set.contains(n ^ temp)) {
                    max = temp;
                }
            }
        }
        
        return max;
    }
	
	public char[][] updateBoard(char[][] board, int[] click) {
        if (isMine(board, click[0], click[1])) {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        update(board, click[0], click[1]);
        return board;
    }
    
    private void update(char[][] board, int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[r].length || board[r][c] == 'B' || Character.isDigit(board[r][c]))
            return;
        
        if (r == 2 && c == 3) {
        	System.out.println("");
        }
        
        int mineCount = mineCount(board, r, c) ;
        
        if (mineCount > 0) {
            board[r][c] = Character.forDigit(mineCount, 10);
        } else if (board[r][c] != 'M') {
            board[r][c] = 'B';
        }
        update(board, r-1, c-1);
        update(board, r-1, c);
        update(board, r-1, c+1);
        update(board, r, c-1);
        update(board, r, c+1);
        update(board, r+1, c-1);
        update(board, r+1, c);
        update(board, r+1, c+1);
    }
    
    private int mineCount(char[][] board, int r, int c) {
        int count = 0;
        if (isMine(board, r-1, c-1))
            count++;
        if (isMine(board, r-1, c))
            count++;
        if (isMine(board, r-1, c+1))
            count++;
        if (isMine(board, r, c-1))
            count++;
        if (isMine(board, r, c+1) )
            count++;
        if (isMine(board, r+1, c-1))
            count++;
        if (isMine(board, r+1, c))
            count++;
        if (isMine(board, r+1, c+1))
            count++;
        
        return count;
    }
    
    private boolean isMine(char[][] board, int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[r].length)
            return false;
        
        return board[r][c] == 'M';
    }
	
	// Number of connected components in unconnected graph
    public int countComponents(int n, int[][] edges) {
        int count = 0;
        boolean visited[] = new boolean[n];
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                count++;
                dfs(i, edges, visited);
            }
        }
        
        return count;
    }
    
    private void dfs(int from, int[][] edges, boolean visited[]) {
        if (visited[from])
            return;
        
        visited[from] = true;
        for (int i=0; i<edges.length; i++) { 
            if (edges[i][0] == from)
                dfs(edges[i][1], edges, visited);
            else if(edges[i][1] == from)
                dfs(edges[i][0], edges, visited);
        }
        
    }
	
	public List<List<String>> printTree(TreeNode root) {
        
        int m = height(root);
        int n = (int) Math.pow(2, m)-1;
        
        List<List<String>> list = new ArrayList<>();
        
        for (int i=0; i<m; i++) {
            list.add(new ArrayList<>());
            for (int j=0; j<n; j++)
                list.get(i).add("");
        }
        
        populate(root, 0, 0, n-1, list);
        
        return list;
        
    }

	public TreeNode deleteNode(TreeNode root, int key) {
	    
	    if (root == null)
	        return null;
    
	    if (root.val == key) {
	        
	        if (root.left == null && root.right == null)
	            root = null;
	        
	        else if (root.left != null)  {
	             int max = findMax(root.left);
	             root.val = max;
	             root.left = deleteNode(root.left, max);
	        } else {
	             int min = findMin(root.right);
	             root.val = min;
	             root.right = deleteNode(root.right, min);
	        }
            
        
	    } else if (root.val > key) {
	        
	        root.left = deleteNode(root.left, key);
	        
	    } else {
	        
	        root.right = deleteNode(root.right, key);
	        
	    }
    
    	return root;
	}

	private int findMin(TreeNode node) {
	    while (node.left != null)
	        node = node.left;
	    
	    return node.val;
	}
	
	private int findMax(TreeNode node) {
	    while (node.right != null) 
	        node = node.right;
	    
	    return node.val;
	}
    
    private void populate(TreeNode root, int level, int low, int high, List<List<String>> list) {
        if (root == null || low > high)
            return;
        
        int mid = (low + high) >>> 1;
        
        list.get(level).set( mid, root.val + "");
        
        populate(root.left, level+1, low, mid-1, list);
        populate(root.right, level+1, mid+1, high, list);
        
    }
    
    private int height(TreeNode root) {
        if (root == null)
            return 0;
        
        return 1 + Math.max(height(root.left) , height(root.right));
    }
	
    public int arrayNesting(int[] nums) {
        
        
        Map<Integer, Integer> map = new HashMap<>();
        
        int maxLen = 0;
        
        for (int i = 0; i<nums.length; i++) {
            int index = i;

            if (!map.containsKey(index)) {
                Set<Integer> set = new HashSet<>();
                while (true) {
                    if (set.contains(index))
                        break;
                    set.add(index);
                    index = nums[index];
                }
                int size = set.size();
                for (int n : set) {
                    map.put(n, size);
                }
                maxLen = Math.max(maxLen, size);
            }
 
            
        }
        
        return maxLen;
    }
	
	public int lengthOfLISEfficient(int[] nums) {
		
		int dp[] = new int[nums.length];
		
		int len = 0;
		
		for (int n : nums) {
			int index = Arrays.binarySearch(dp, 0, len, n);
			if (index < 0) {
				index = -(index+1);
			}
			dp[index] = n;
			if (len == index)
				len++;
		}
		
		return len;
		
	}
	
    public int lengthOfLIS(int[] nums) {
    	
    	if (nums.length == 0)
            return 0;
        
        int len[] = new int[nums.length];
        int seq[] = new int[nums.length];

        Arrays.fill(seq, -1);
        int maxLen = 1;
        
        for (int i=0; i<nums.length; i++) {
            
            for (int j=0; j<i; j++) {
                
                if (nums[i] > nums[j] && 1 + len[j] > len[i]) {
                    len[i] = 1 + len[j];
                    seq[i] = j;
                    maxLen = Math.max(maxLen, len[i]);
                }
            }
            
            len[i] = Math.max(1, len[i]);
        }
        
        System.out.println(Arrays.toString(seq));
        
        return maxLen;
        
    }
	
    public int minimumDeleteSumEfficient(String s1, String s2) {
        
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        
        int ascii=0;
        
        for (int i=s1.length()-1; i>=0; i--) {
            ascii += (int) s1.charAt(i);
            dp[i][s2.length()] = ascii;
        }
        
        ascii= 0;
        
        for (int i=s2.length()-1; i>=0; i--) {
            ascii += (int) s2.charAt(i);
            dp[s1.length()][i] = ascii;
        }
        
        for (int i=s1.length()-1; i>=0; i--) {
            for (int j=s2.length()-1; j>=0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i+1][j+1];
                } else {
                    dp[i][j] = Math.min((int) s1.charAt(i) + dp[i+1][j], (int) s2.charAt(j) + dp[i][j+1]);
                }
            }
        }
        
        return dp[0][0];
        
    }
	
    private Map<String,Integer> map = new HashMap<>();
    
    public int minimumDeleteSum(String s1, String s2) {
    	
    	String s = new StringBuilder(s1).append(',').append(s2).toString();
          
        if (map.containsKey(s))
             return map.get(s);
        
        int res = 0;
        
        if (s1.isEmpty()) 
            res = asciiSum(s2);
        
        else if (s2.isEmpty())
            res = asciiSum(s1);
        
        else if (s1.charAt(0) == s2.charAt(0))
            res =  minimumDeleteSum(s1.substring(1), s2.substring(1));
        
        else {
            res = Math.min((int) s1.charAt(0) + minimumDeleteSum(s1.substring(1), s2) , 
                            (int) s2.charAt(0) + minimumDeleteSum(s1, s2.substring(1)));
        }
        
        map.put(s, res);
        
        return res;
    }
    
    private int asciiSum(String s) {
        int sum = 0;
        for (int i=0; i<s.length(); i++) {
            sum += (int) s.charAt(i);
        }
        return sum;
    }
	
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer,Integer> sumCount = new HashMap<>();
        findFrequentTreeSum(root, sumCount);
        
        List<Integer> list = new ArrayList<>();
        int count = 0;
        
        for (int key : sumCount.keySet()) {
           if (sumCount.get(key) > count) {
                list = new ArrayList<>();
                list.add(key);
                count = sumCount.get(key);
            } else if (sumCount.get(key) == count) {
               list.add(key);
           }
        }
        
       return list.stream().mapToInt(i -> i).toArray();
    }
    
    private Integer findFrequentTreeSum(TreeNode root, Map<Integer,Integer> sumCount) {
        if (root == null)
            return null;
        
        Integer left = findFrequentTreeSum(root.left, sumCount);
        Integer right = findFrequentTreeSum(root.right, sumCount);
        
        int leftSum = (left != null ? left.intValue() : 0);
        int rightSum = (right != null ? right.intValue() : 0);
        int subtreeSum = root.val + leftSum + rightSum;
        
        sumCount.put(subtreeSum, sumCount.getOrDefault(subtreeSum, 0) + 1);
        
        return subtreeSum;
        
    }
	
    public int[] singleNumber(int[] nums) {
        
        int xor = 0;
        
        for (int n: nums) {
            xor = xor ^ n;
        }
        
        // last set bit
        xor = xor & -xor;
        
        int res[] = {0, 0};
        
        for (int n: nums) {
            if ( (n & xor) == 0) {
                res[0] = res[0] ^ n;
            } else {
                res[1] = res[1] ^ n;
            }
        }
        
        return res;
        
    }
	
    public String shortestCompletingWord(String licensePlate, String[] words) {
        Arrays.sort(words , new Comparator<String>() {
            
            @Override
            public int compare(String w1, String w2) {
                return w1.length() - w2.length();
            }
        });
        
        int[] word1 = getCount(licensePlate);
        
        for (int i=0; i<words.length; i++) {
            if (completes(word1, getCount(words[i])))
                return words[i];
        }
        
        return "";
    }
    
    private boolean completes(int[] word1, int[] word2) {
        for (int i=0; i<word1.length; i++) {
            if (word1[i] != 0 && word2[i] < word1[i])
                return false;
        }
        return true;
    }
    
    private int[] getCount(String word) {
        
        int[] count = new int[256];
        
        for (int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                count[Character.toLowerCase(c)]++; 
            }
        }
        
        return count;
    }
	
    public int[] asteroidCollision(int[] asteroids) {
        
        Stack<Integer> stack = new Stack<>();
        
        for (int n : asteroids) {
            
            boolean push = true;
            
            while (!stack.isEmpty() && n < 0 && stack.peek() > 0) {
                if (stack.peek() > Math.abs(n) || stack.pop() == Math.abs(n)) {
                    push = false;
                    break;
                }
            }
            if (push)
            stack.push(n);
        }
        
        int res[] = new int[stack.size()];
        
        for (int i=res.length-1; i>=0; i--) {
            res[i] = stack.pop();
        }
        
        return res;
    }
	
    public int numDecodings(String s) {
    	if (s == null || s.isEmpty())
    		return 0;
    	
        return decodeWays(s);
    }
    
    private int decodeWays(String s) {
   
        if (s == null)
            return 0;
        
        if (s.isEmpty())
        	return 1;
        
        int count = 0;
        
        for (int i=0; i<s.length(); i++) {
        	if (isValid(s.substring(0, i+1)))
        		count += decodeWays(s.substring(i+1));
        }
        
        return count;
    }
    
    private boolean isValid(String s) {
        
        if (s == null || s.isEmpty())
            return false; 
        
        if (s.length() > 2)
        	return false;
        
        int v = Integer.parseInt(s);
        return v >= 1 && v <= 26;
    }
	
	public List<Integer> findDuplicates(int[] nums) {
        
        List<Integer> list = new ArrayList<>();
        
        for (int i=0; i<nums.length; i++) {
            int index = Math.abs(nums[i]) -1;
            if (nums[index] < 0) { //index+1 has already appeared in the array
                list.add(index+1);
            } else {
                nums[index] = -nums[index];
            }
        }
        
        return list;
    }
}
