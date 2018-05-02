package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

class MyCalendar {
    
//  private class Interval {
//      int start, end;
     
//      public Interval(int start, int end) {
//          this.start = start;
//          this.end = end;
//      }
//  }
 
//  private List<Interval> list ;

//  public MyCalendar() {
//      list = new ArrayList<>();
//  }
 
//  public boolean book(int start, int end) {
     
//      for (Interval i : list) {
//          if (!(start >= i.end || end <= i.start)) {
//              return false;
//          }
//      }
     
//      list.add(new Interval(start, end));
     
//      return !conflict;
//  }
	
	
	
 
 
 private TreeMap<Integer, Integer> map;

 public MyCalendar() {
     map = new TreeMap<>();
 }
 
 public boolean book(int start, int end) {
     
     Integer prevStart = map.floorKey(start);
     Integer nextStart = map.ceilingKey(start);
     
     if ((prevStart == null || map.get(prevStart) <= start) && (nextStart == null || nextStart >= end)) {
         map.put(start, end);
         return true;
     } 
     
     return false;
 }
}

/**
* Your MyCalendar object will be instantiated and called as such:
* MyCalendar obj = new MyCalendar();
* boolean param_1 = obj.book(start,end);
*/

public class LeetcodeMedium3 {

	public static void main(String[] args) {
		
		LeetcodeMedium3 run = new LeetcodeMedium3();
		
		System.out.println(run.longestPalindromeSubseq("gphyvqruxjmwhonjjrgumxjhfyupajxbjgthzdvrdqmdouuukeaxhjumkmmhdglqrrohydrmbvtuwstgkobyzjjtdtjroqpyusfsbjlusekghtfbdctvgmqzeybnwzlhdnhwzptgkzmujfldoiejmvxnorvbiubfflygrkedyirienybosqzrkbpcfidvkkafftgzwrcitqizelhfsruwmtrgaocjcyxdkovtdennrkmxwpdsxpxuarhgusizmwakrmhdwcgvfljhzcskclgrvvbrkesojyhofwqiwhiupujmkcvlywjtmbncurxxmpdskupyvvweuhbsnanzfioirecfxvmgcpwrpmbhmkdtckhvbxnsbcifhqwjjczfokovpqyjmbywtpaqcfjowxnmtirdsfeujyogbzjnjcmqyzciwjqxxgrxblvqbutqittroqadqlsdzihngpfpjovbkpeveidjpfjktavvwurqrgqdomiibfgqxwybcyovysydxyyymmiuwovnevzsjisdwgkcbsookbarezbhnwyqthcvzyodbcwjptvigcphawzxouixhbpezzirbhvomqhxkfdbokblqmrhhioyqubpyqhjrnwhjxsrodtblqxkhezubprqftrqcyrzwywqrgockioqdmzuqjkpmsyohtlcnesbgzqhkalwixfcgyeqdzhnnlzawrdgskurcxfbekbspupbduxqxjeczpmdvssikbivjhinaopbabrmvscthvoqqbkgekcgyrelxkwoawpbrcbszelnxlyikbulgmlwyffurimlfxurjsbzgddxbgqpcdsuutfiivjbyqzhprdqhahpgenjkbiukurvdwapuewrbehczrtswubthodv"));
		System.out.println(run.longestPalindromeSubseq("aabaaba"));
		System.out.println(run.longestPalindrome("cbbd"));
		
		char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
		//System.out.println(run.leastInterval(tasks, 2));
		System.out.println(run.leastInterval1(tasks, 2));
		
		TreeMap<Integer, Integer> cal = new TreeMap<>();
		cal.floorKey(1);
		cal.ceilingKey(1);
		
		System.out.println(run.numTrees(3));
		System.out.println(run.minDistance("x", "abc"));
		
		System.out.println(run.isScramble("great", "rgeat"));
		
		String str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
		String strs[] = {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
		System.out.println(run.wordBreak(str, Arrays.asList(strs)));
		
	}
	
	private class TrieNode {
        char c; 
        TrieNode child[];
        boolean isWord;
        
        public TrieNode(char c) {
            this.c = c;
            this.child = new TrieNode[26];
        }
    }


    public boolean wordBreak(String s, List<String> wordDict) {
        
        TrieNode root = new TrieNode('\0');
        
        for (String word : wordDict) {
            insert(word, root);
        }
        
        Map<Integer, Boolean> map = new HashMap<>();
        return wordBreak(s, 0, root, map);
        
    }

    private boolean wordBreak(String s, int index, TrieNode root, Map<Integer, Boolean> map) {
        
        if (index == s.length())
            return true;
        
        if (map.containsKey(index))
        	return map.get(index);
        
        TrieNode curr = root;
        boolean result = false;
        
        for (int i=index; i<s.length(); i++) {
            char c =  s.charAt(i);
            if (curr.child[c-'a'] != null) {
                curr = curr.child[c-'a'];
                if (curr.isWord && wordBreak(s, i+1, root, map)) {
                    result = true;
                    break;
                }
            } else {
                break;
            }
        }
        
        map.put(index, result);
        return false;
    }

    private void insert(String w, TrieNode root) {
        
        TrieNode curr = root;
        
        for (int i=0; i<w.length(); i++) {
            char c = w.charAt(i);
            if (curr.child[c-'a'] == null) {
                curr.child[c-'a'] = new TrieNode(c);
            }
            curr = curr.child[c-'a']; 
        }
        
        curr.isWord = true;
    }

	
	public int numDecodings(String s) {
        
        int dp[] = new int[s.length()+1];
        dp[s.length()] = 1;
        
        for (int i=s.length()-1; i>=0; i--) {
            if (isValid(s.substring(i, i+1))) {
                dp[i] = 1 * dp[i+1];
            }
            if (i < s.length()-1) {
                if (isValid(s.substring(i, i+2))) {
                    dp[i] = dp[i] + (1 * dp[i+2]);
                }
            }
        }
        
        return dp[0];
    }
    
    private boolean isValid(String s) {
        return s.charAt(0) != '0' && 1 <= Integer.parseInt(s) && Integer.parseInt(s) <= 26;
    }
	
    public boolean isScramble(String s1, String s2) {
        return isScramble1(s1, s2);
    }
    
    private boolean isScramble1(String s1, String s2) {
        
        if(s1.equals(s2))
            return true;
        
        int[] set = new int[26];
        
        for (int i=0; i<s1.length(); i++) {
            set[s1.charAt(i) - 'a']++;
            set[s2.charAt(i) - 'a']--;
        }
        
        for (int i=0; i<26; i++) 
            if (set[i] != 0)
                return false;
        
        int len = s1.length();
        
        for (int i=1; i<=len-1; i++) {
        	if( isScramble(s1.substring(0,i), s2.substring(0,i)) && isScramble(s1.substring(i), s2.substring(i)))
                return true;
            if( isScramble(s1.substring(0,i), s2.substring(len-i)) && isScramble(s1.substring(i), s2.substring(0,len-i)))
                return true;
        }
        
        return false;
    }
	
	public int minDistance(String word1, String word2) {
 
        int dp[][] = new int[word1.length()+1][word2.length()+1];
        
        for (int i=0; i<=word1.length(); i++)
            dp[i][0] = i;
        
        for (int i=0; i<=word2.length(); i++)
            dp[0][i] = i;
        
        for (int i=1; i<=word1.length(); i++) {
        	for (int j=1; j<=word2.length(); j++) {
        		if (word1.charAt(i-1) == word2.charAt(j-1)) {
        			dp[i][j] = dp[i-1][j-1];
        		} else {
        			dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
        		}
        	}
        }
        
        return dp[word1.length()][word2.length()];
        
    }
	
	//718. Maximum Length of Repeated Subarray
    public int findLength(int[] A, int[] B) {
        
        int[][] dp = new int[A.length+1][B.length+1];
        
        int maxLen = 0;
        
        for (int i=A.length-1; i>=0; i--) {
            for (int j=B.length-1; j>=0; j--) {
                if (A[i] == B[j]) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i+1][j+1]);
                }
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        
        return maxLen;
    }
	
	public int numTrees(int n) {
        if (n < 0)
            return 0;
        
        if (n ==0 || n == 1)
            return 1;
        
        int count = 0;
        
        for (int i=1; i<=n; i++) {
            count += numTrees(i-1) * numTrees(n-i);
        }
        
        return count;
    }
	
	
	// my problem num : 300 on Leetcode :) 
	//394. Decode String
    public String decodeString(String s) {
        
        
        Stack<Integer> times = new Stack<>();
        Stack<StringBuilder> stack = new Stack<>();
        
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                int num = 0;
                while (i < s.length() && (c = s.charAt(i)) >= '0' && c <= '9') {
                    num = num*10 + (c - '0');
                    i++;
                }
                times.add(num);
                i--;
            } else if (c == '[') {
                stack.push(new StringBuilder());
            } else if (c == ']') {
                StringBuilder sb = stack.pop();
                int n = times.pop();
                String str = sb.toString();
                StringBuilder res = new StringBuilder();
                while (n > 0) {
                    res.append(str);
                    n--;
                }
                if (stack.isEmpty()) {
                    stack.push(res);
                } else {
                    stack.peek().append(res.toString());
                }
            } else {
                if (stack.isEmpty()) {
                    stack.push(new StringBuilder());
                }
                stack.peek().append(c);
            }
        }
        
        if (!stack.isEmpty()) {
            return stack.pop().toString();
        }
        
        return "";
        
    }
	
    public double myPow(double x, int n) {
        
    	// special case
        //1.00000
        //-2147483648

        if (n < 0) {
            if (n == Integer.MIN_VALUE)
                return 1/(x*myPow(x, Integer.MAX_VALUE));
            
            return 1/myPow(x, -n);
        }
            
        
        if (n == 0)
            return 1;
        
        if (n == 1)
            return x;
        
        if (x == 1.0)
            return x;
        
        double pow = myPow(x, n/2);
        if (n % 2 == 0)
            return pow * pow;
        else
            return pow * pow * x;
    }
	
    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>();
        int s = 1;
        while (list.size() != n) {
            list.add(s);
            if (s * 10 <= n) {
                s = s * 10;
            } else if (s % 10 != 9 && s + 1 <= n) {
                s ++;
            } else {
                while ((s / 10) % 10 == 9) {
                    s/= 10;
                }
                s = s / 10 + 1;
            }
        }
        
        return list;
    }
	
	
	
    public int leastInterval1(char[] tasks, int n) {
        
        int[] count = new int[26];
        for (char c : tasks) {
            count[c - 'A']++;
        }
        
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int c : count) {
            if (c > 0)
                queue.add(c);
        }
        
        int time = 0;
        
        while (!queue.isEmpty()) {
            
            int i=0;
            List<Integer> temp = new ArrayList<>();
            
            while (i <= n) {
            	
            	if (queue.isEmpty() && temp.isEmpty())
            		break;
                
                if (!queue.isEmpty()) {
                	if (queue.peek() == 1)
                		queue.poll();
                	else
                		temp.add(queue.poll()-1);
                }
                
                i++;
                time++;
            }
            
            for (int t : temp) 
                    queue.add(t);
        }
        
        return time;
    }
	
    public int leastInterval(char[] tasks, int n) {
        
        int[] count = new int[26];
        for (char c : tasks) {
            count[c - 'A']++;
        }
        
        int time = 0;
        Arrays.sort(count);
        
        while (count[25] > 0) {
            int i=0; 
            while (i <= n) {
                if (count[25] == 0)
                    break;
                
                if (i < 26 && count[25-i] > 0) {
                    count[25-i]--;
                }
                
                time++;
                i++;
            }
            Arrays.sort(count);
        }
        
        return time;
    }
	
    public List<Integer> rightSideView(TreeNode root) {
        
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        
        List<Integer> list = new ArrayList<>();
        
        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count > 0) {
                TreeNode node = queue.poll();
                count--;
                
                if (count == 0) { // right most at depth d
                    list.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                
            }
        }
        
        return list;
    }
	
    public String longestPalindrome(String s) {
        
        if (s == null || s.isEmpty())
            return "";
        
        boolean table[][] = new boolean[s.length()][s.length()];
        int from =0, to=0;
        
        for (int i=0; i<s.length(); i++) {
            table[i][i] = true;
        }
        
        for (int i=1; i<s.length(); i++) {
            if (s.charAt(i-1) == s.charAt(i)) {
                table[i-1][i] = true;
                from = i-1;
                to = i;
            } 
        }
        
        
        
        for (int len=3; len<=s.length(); len++) {
            for (int i=0; i+len<=s.length(); i++) {
                int j = i+len-1;
                table[i][j] = s.charAt(i) == s.charAt(j)  && table[i+1][j-1];
                if (table[i][j]) {
                    from = i;
                    to = j;
                }
            }
        }
        
        return s.substring(from, to+1);
    }
    
    public int longestPalindromeSubseq(String s) {
        
        if (s == null || s.isEmpty())
            return 0;
        
        int[][] dp = new int[s.length()][s.length()];
        
        for (int i=0; i<s.length(); i++) {
        	dp[i][i] = 1;
        }
        
        for (int i=s.length()-1; i>=0; i--) {
        	for (int j=i+1; j<s.length(); j++) {
        		if (s.charAt(i) == s.charAt(j)) {
        			dp[i][j] = 2 + dp[i+1][j-1];
        		} else {
        			dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
        		}
        	}
        }
        
        return dp[0][s.length()-1];
        
    }
    
    

}
