package solutions;

import java.util.HashSet;
import java.util.Set;

public class CTCI_ArraysAndStrings {

	public static void main(String[] args) {
		
		CTCI_ArraysAndStrings run = new CTCI_ArraysAndStrings();
		System.out.println(run.isUnique("abcad"));
		System.out.println(run.isUniqueLessExpensiveDS("abcad"));
		System.out.println(run.isUniqueNoSpace("what up men ?"));
		System.out.println(run.arePermutations("madam", "damma"));
		System.out.println(run.arePermutationsSlow("madamcc", "cdammca"));
		System.out.println(run.URLify("Mr John Smith    ", 13));
		System.out.println(run.URLifyBetter("Mr John Smith    ", 13));
		System.out.println(run.isPermutationOfPalindrome("aTco cta"));
		System.out.println(run.isOneEditDistanceAway("pale", "ple"));
		System.out.println(run.isOneEditDistanceAway("pales", "pale"));
		System.out.println(run.isOneEditDistanceAway("pale", "bale"));
		System.out.println(run.isOneEditDistanceAway("pale", "bake"));
		System.out.println(run.isOneEditDistanceAway("bale", "ale"));
		System.out.println(run.compress("aabcccccaaa"));
		System.out.println(run.compress("abcc"));
		
		int[][] mat = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		run.rotateBy90(mat);
		Helper.printMatrix(mat);
		
		int[][] zeroMat = {{1,2,0,3}, {4,5,6,7}, {0,8,9,10}};
		run.zeroMatrixSpaceEfficient(zeroMat);
		Helper.printMatrix(zeroMat);
		System.out.println(run.isRotated("waterbottle", "erbottlewat"));
	}
	
	public boolean isRotated(String original, String rotated) {
		if(original.length() == rotated.length() && original.length() > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(rotated);
			sb.append(rotated);
			return sb.indexOf(original) >= 0;
		}
		return false;
	}
	
	public void zeroMatrixSpaceEfficient(int[][] mat) {
		boolean firstRowHasZero = false, firstColHasZero = false;
		for(int j=0; j<mat[0].length; j++)
			if(mat[0][j] == 0) {
				firstRowHasZero = true;
				break;
			}
		for(int i=0; i<mat.length; i++) 
			if(mat[i][0] == 0) {
				firstColHasZero = true;
				break;
			}
		for(int i=1; i<mat.length; i++) {
			for(int j=1; j<mat[0].length; j++) {
				if(mat[i][j] == 0) {
					mat[i][0] = 0;
					mat[0][j] = 0;
				}
			}
		}
		for(int i=1; i<mat.length; i++) {
			for(int j=1; j<mat[0].length; j++) {
				if(mat[i][0] == 0 || mat[0][j] == 0) {
					mat[i][j] = 0;
				}
			}
		}
		if(firstRowHasZero) {
			for(int j=0; j<mat[0].length; j++)
				mat[0][j] = 0;
		}
		
		if(firstColHasZero) {
			for(int i=0; i<mat.length; i++) {
				mat[i][0] = 0;
			}
		}
	}
	
	public void zeroMatrix(int[][] mat) {
		boolean rowHasZero[] = new boolean[mat.length];
		boolean colHasZero[] = new boolean[mat[0].length];
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat[0].length; j++) {
				if(mat[i][j] == 0) {
					rowHasZero[i] = true;
					colHasZero[j] = true;
				}
			}
		}
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat[0].length; j++) {
				if(rowHasZero[i] || colHasZero[j])
					mat[i][j] = 0;
			}
		}
	}
	
	
	
	public void rotateBy90(int[][] mat) {
		int n = mat.length;
		for(int layer=0 ; layer < n/2; layer++) {
			int first = layer;
			int last = n-1-layer;
			for(int i=first; i<last; i++) {
				
				int offset = i-first;
				//save top
				int top = mat[first][i];
				//left -> top
				mat[first][i] = mat[last-offset][first];
				//bottom -> left
				mat[last-offset][first] = mat[last][last-offset];
				//right -> bottom
				mat[last][last-offset] = mat[i][last];
				//top -> right
				mat[i][last] = top;
			}
		}
	}
	
	
	public String compress(String s) {
		if (s == null || s.isEmpty())
			return "";
		
		StringBuilder sb = new StringBuilder();
		int count = 0;
		char c = '\0';
		
		for(int i=0; i<s.length(); i++) {
			if(count == 0) {
				c = s.charAt(i);
				count++;
			} else {
				char curr = s.charAt(i);
				if(c == curr)
					count++;
				else  {
					sb.append(c);
					sb.append(count);
					c = curr;
					count = 1;
				}
			}
		}
		
		sb.append(c);
		sb.append(count);
		
		return sb.length() < s.length() ? sb.toString() : s;
	}
	
	public boolean isOneEditDistanceAway(String a, String b) {
		int aLen = a.length();
		int bLen = b.length();
		if (Math.abs(aLen - bLen) > 1)
			return false;
		
		if (aLen > bLen) {
			// remove each char from a and check if they are equal
			return isOneRemovalAwayEfficient(a, b);
			
		} else if (aLen < bLen) {
			// add a char from b to a and check for equality
			return isOneRemovalAwayEfficient(b, a);
		} else {
			int diff = 0;
			for(int i=0; i<a.length(); i++) {
				if(a.charAt(i) != b.charAt(i))
					diff++;
				if(diff > 1)
					return false;
			}
			return true;
		}
	}
	
	private boolean isOneRemovalAwayEfficient(String a, String b) {
		int index1 = 0;
		int index2 = 0;
		int diff = 0;
		while(index1 < a.length() && index2 < b.length()) {
			if(a.charAt(index1) == a.charAt(index2)) {
				index1++;
				index2++;
			} else {
				index1++;
				diff ++;
			}
			if (diff > 1)
				return false;
			
		}
		
		return true;
	}
	
	
	// inefficient... string comparison is not required.. 
	private boolean isOneRemovalAway(String a, String b) {
		for(int i=0; i<a.length(); i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(a, 0, i);
			sb.append(a, i+1, a.length());
			if(sb.toString().equals(b))
				return true;
		}
		return false;
	}
	
	
	
	//assuming case-insensitive palindrome
	public boolean isPermutationOfPalindrome(String s) {
		
		boolean charset[] = new boolean[256];
		for(int i=0; i<s.length(); i++) {
			char c = Character.toLowerCase(s.charAt(i));
			if (c != ' ')
				charset[c] = !charset[c];
		}
		
		int count = 0;
		for(boolean b : charset) {
			if(b)
				count++;
		}
		
		return count <= 1;
		
	}
	
	public String URLifyBetter(String s, int len) {
		
		StringBuilder stringBuilder = new StringBuilder();
		for(int i=0; i<len; i++) {
			char c = s.charAt(i);
			if (c == ' ')
				stringBuilder.append("%20");
			else
				stringBuilder.append(c);
		}
		return stringBuilder.toString();
		
	}
	
	public String URLify(String s, int len) {
		char c[] = s.toCharArray();
		
		int lastCharIndex = len-1;
		int lastSIndex = s.length() - 1;
		
		while(lastSIndex >= 0) {
			if(c[lastCharIndex] != ' ')
				c[lastSIndex--] = c[lastCharIndex--];
			else {
				lastCharIndex--;
				c[lastSIndex--] = '0';
				c[lastSIndex--] = '2';
				c[lastSIndex--] = '%';
			}
		}
		
		return new String(c);
		
	}
	
	public boolean arePermutations(String a, String b) {
		if(a.length() != b.length())
			return false;
		
		int count[] = new int[256];
		for(int i=0; i<a.length(); i++)
			count[a.charAt(i)]++;
		for(int i=0; i<b.length(); i++) {
			count[b.charAt(i)]--;
			if(count[b.charAt(i)] < 0)
				return false;
		}
		
		return true;
	}
	
	// also testing my quick sort on diff primitive types
	public boolean arePermutationsSlow(String a, String b) {
		if(a.length() != b.length())
			return false;
		
		char aSorted[] = a.toCharArray();
		char bSorted[] = b.toCharArray();
		
		Character aSortedObject[] = Helper.toObject(aSorted);
		Character bSortedObject[] = Helper.toObject(bSorted);
		
		SortingAlgorithms<Character> sort = new SortingAlgorithms<>();
		sort.QuickSort(aSortedObject);
		sort.QuickSort(bSortedObject);
		
		return (new String(Helper.toPrimitive(aSortedObject))).equals(new String(Helper.toPrimitive(bSortedObject)));
		
	}
	
	public boolean isUniqueNoSpace(String s) {
		int checker = 0;
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			int val = c - 'a';
			if((checker & (1 << val)) > 0)
				return false;
			checker |= (1 << val);
		}
		return true;
	}
	
	public boolean isUniqueLessExpensiveDS(String s) {
		//asssuming ASCII char set
		boolean charset[] = new boolean[256];
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if (charset[c])
				return false;
			
			charset[c] = true;
		}
		return true;
	}
	
	public boolean isUnique(String s) {
		Set<Character> set = new HashSet<>();
		for(int i=0; i<s.length(); i++) {
			if(set.contains(s.charAt(i))) {
				return false;
			}
			set.add(s.charAt(i));
		}
		return true;
	} 
	
	
	
	

}
