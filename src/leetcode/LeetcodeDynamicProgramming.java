package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LeetcodeDynamicProgramming {

	public static void main(String[] args) {
		
		LeetcodeDynamicProgramming run = new LeetcodeDynamicProgramming();
		System.out.println(run.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
		System.out.println(run.isInterleave("aa", "ab", "aaba"));
		System.out.println(run.numDistinct("rabbbit", "rabbit"));
		System.out.println(run.numDistinct("ABCDEE", "ACE"));
		System.out.println(run.numDistinct("daacaedaceacabbaabdccdaaeaebacddadcaeaacadbceaecddecdeedcebcdacdaebccdeebcbdeaccabcecbeeaadbccbaeccbbdaeadecabbbedceaddcdeabbcdaeadcddedddcececbeeabcbecaeadddeddccbdbcdcbceabcacddbbcedebbcaccac", "ceadbaa"));
		
		System.out.println(run.numDistinctBetter("rabbbit", "rabbit"));
		System.out.println(run.numDistinctBetter("ABCDEE", "ACE"));
		System.out.println(run.numDistinctBetter("daacaedaceacabbaabdccdaaeaebacddadcaeaacadbceaecddecdeedcebcdacdaebccdeebcbdeaccabcecbeeaadbccbaeccbbdaeadecabbbedceaddcdeabbcdaeadcddedddcececbeeabcbecaeadddeddccbdbcdcbceabcacddbbcedebbcaccac", "ceadbaa"));
		
		int[][] arr = {{2},{3,4},{6,5,7},{4,1,8,3}};
		
		List<List<Integer>> triangle = new ArrayList<>();
		triangle.add(Arrays.asList(2));
		triangle.add(Arrays.asList(3,4));
		triangle.add(Arrays.asList(6,5,7));
		triangle.add(Arrays.asList(4,1,8,3));
		System.out.println(run.minimumTotal(triangle));
		
		List<String> wordDict = new ArrayList<>();
		Set<String> set = wordDict.stream().collect(Collectors.toSet());
		Arrays.asList(1);
		
		int coins[] = {1};
		System.out.println(run.coinChange(coins, 2));
	}
	
    public int coinChange(int[] coins, int amount) {
        if (amount <= 0)
            return -1;
        
        int dp[] = new int[amount+1];
        dp[0] = 0;
        
        for (int i=0; i<coins.length; i++) {
            if (coins[i] < dp.length)
                dp[coins[i]] = 1;
        }
        
        for (int i=1; i<=amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j=0; j<coins.length; j++) {
                if (i >= coins[j]) {
                    int k =1;
                    while(i- k*coins[j] >= 0) {
                        if (dp[i-k*coins[j]] != -1)
                            dp[i] = Math.min(dp[i], k + dp[i-k*coins[j]]);
                        k++;
                    }
                }
            }
            if (dp[i] == Integer.MAX_VALUE)
                dp[i] = -1;
        }
        
        return dp[amount];
    }
	
	public int minimumTotal(List<List<Integer>> triangle) {
		
		List<Integer> buffer = new ArrayList<>();
		
        for (int i=triangle.size()-1; i>=0; i--) {
        	List<Integer> list = triangle.get(i);
        	List<Integer> bufferTemp = new ArrayList<>();
        	if (list.size() == 1) {
        		bufferTemp.add(list.get(0) + (buffer.isEmpty() ? 0 : buffer.get(0)));
        	} else {
	        	for (int j=1; j<list.size(); j++) {
	        		bufferTemp.add(Math.min(list.get(j-1) + (buffer.isEmpty() ? 0 : buffer.get(j-1)), 
	        								list.get(j) + (buffer.isEmpty() ? 0 :buffer.get(j))));
	        	}
        	}
        	buffer = bufferTemp;
        }
        
        return buffer.get(0);
    }
	
	public int numDistinctBetter(String s, String t) {
		
		int dp[][] = new int[t.length()+1][s.length()+1];
		
		for (int i=0; i<=s.length(); i++)
			dp[0][i] = 1;
		
		for (int i=0; i<t.length(); i++) {
			for (int j=0; j<s.length(); j++) {
				if (s.charAt(j) == t.charAt(i)) {
					dp[i+1][j+1] = dp[i][j] + dp[i+1][j];
				} else {
					dp[i+1][j+1] = dp[i+1][j];
				}
			}
		}
		
		return dp[t.length()][s.length()];
	}
	
	public int numDistinct(String s, String t) {
		
		if (t.isEmpty())
			return 1;
		
        if (s.isEmpty())
        	return 0;
        
        int count = 0;
        
        for (int i=0; i<s.length(); i++) {
        	if (s.charAt(i) == t.charAt(0)) {
        		count += numDistinct(s.substring(i+1), t.substring(1));
        	}
        }
        
        return count;
    }
	
    public boolean isInterleave(String s1, String s2, String s3) {
    	
       if (s1.length() + s2.length() != s3.length())
    	   return false;
        
       boolean dp[][] = new boolean[s1.length()+1][s2.length()+1];
       
       for (int i=0; i<=s1.length(); i++) {
    	   for (int j=0; j<=s2.length(); j++) {
    		   if (i==0 && j==0) {
    			   dp[i][j] = true;
    		   } else if (i==0) {
    			   dp[i][j] = dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1);
    		   } else if (j==0) {
    			   dp[i][j] = dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1);
    		   } else {
    			   dp[i][j] = (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1)) || (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1));
    		   }
    	   }
       }
       
       return dp[s1.length()][s2.length()];
        
    }

}
