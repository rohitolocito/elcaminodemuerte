package solutions;

import java.util.ArrayList;
import java.util.List;

public class CTCI_BitManipulation {

	public static void main(String[] args) {
		
		CTCI_BitManipulation run = new  CTCI_BitManipulation();	
		System.out.println(run.updateNwithM(1024, 19, 2, 6));
		System.out.println(run.integerToBinaryString(Integer.MAX_VALUE));
		System.out.println(run.integerToBinaryString(Integer.MIN_VALUE));
		System.out.println(run.fractionalToBinaryString(0.25));
		
		System.out.println(run.longestSeqOf1sPossible(1775)+" == " + run.longestSeqOf1sPossibleSpaceEfficient(1775));
		System.out.println(run.longestSeqOf1sPossible(0) + " == " + run.longestSeqOf1sPossibleSpaceEfficient(0));
		System.out.println(run.longestSeqOf1sPossible(Integer.MAX_VALUE) + " == " + run.longestSeqOf1sPossibleSpaceEfficient(Integer.MAX_VALUE));
		
		run.printNextSmallestAndLargestSameNumOf1s(3);
		run.printNextSmallestAndLargestSameNumOf1s(13948);
		System.out.println(run.integerToBinaryString(14072));
		System.out.println(run.flipBits(29, 15));
		System.out.println(run.flipBitsFaster(29, 15));
		
		System.out.println("** pairWiseSwap **");
		System.out.println(run.integerToBinaryString(72));
		System.out.println(run.integerToBinaryString(run.pairWiseSwap(72)));
	}
	
	public void drawLine(byte[] screen, int width, int x1, int x2, int y) {
		int len = screen.length;
		int numOfRows = len / width;
		int from = width * y + x1;
		int to = width * y + x2;
		for (int i=from; i<= to; i++) {
			screen[i] = -1;
		}
		return;
	}
	
	public int pairWiseSwap(int n) {
		int evenBits = n & 0xAAAAAAAA;
		int oddBits = n & 0x55555555;
		
		// move odd bits to left to swap
		int toLeft = oddBits << 1;
		
		// move even bits to right to swap
		int toRight = evenBits >>> 1;
		
		return (toLeft | toRight);
	}
	
	public int flipBitsFaster(int a, int b) {
		int c = a ^ b;
		int count = 0;
		while (c != 0) {
			count++;
			c = c & (c-1);
		}
		return count;
	}
	
	public int flipBits(int a, int b) {
		int c = a ^ b;
		int count = 0;
		while(c != 0) {
			count += (c & 1);
			c = c >> 1;
		}
		return count;
	}
	
	public boolean isPowerOf2(int n) {
		return (n & (n-1)) == 0;
	}
	
	
	public void printNextSmallestAndLargestSameNumOf1s(int n) {
		if (n <= 0) {
			System.out.println("ERROR");
			return;
		}
		
		// next largest with same num of 1s
		// find the first occurrence of 1 from 1,2,4,8.... max.. call the point X
		// find next available 0 after the point X.. set it to 1
		
		// 3, 0011
		// 6, 0110
		
		//9, 1001
		//10, 1010
		
		int firstOccurrenceOf1 = 0;
		int i = 0;
		int val = n;
		while(val != 0) {
			if ((val & 1) == 1) {
				firstOccurrenceOf1 = i;
				break;
			}
			val = val >>> 1;
			i++;
		}
		
		//firstOccurrenceOf1 will always be >= 0 && < 31 since n > 0

		while(val != 0) {
			if ( (val & 1) == 0) {
				break;
			}
			val = val >>> 1;
			i++;
		}
		if (i == 31) {
			System.out.println("Larger not possible !");
		} else {
			int larger = updateBit(n, firstOccurrenceOf1, false);
			larger = updateBit(larger, i, true);
			System.out.println("larger than "+n+" = "+larger);
		}
		
		val = n;
		//find the zero before firstOne
		if (firstOccurrenceOf1 == 0) {
			
		} else {
			
			int smaller = updateBit(val, firstOccurrenceOf1, false);
			
		}
	}
	
	public int longestSeqOf1sPossibleSpaceEfficient(int n) {
		
		int currNumOfOnes =0 , prevNumOfOnes = 0, lastNumOfZeroes = 0, longestSoFar = 0;
		
		for(int i=0; i< Integer.BYTES*8; i++) {
			int val = n & 1;
			if (val == 1) {
				currNumOfOnes ++;
				if (lastNumOfZeroes == 1) {
					longestSoFar = Math.max(longestSoFar, prevNumOfOnes + 1 + currNumOfOnes); 
				}  else {
					longestSoFar = Math.max(longestSoFar, 1 + currNumOfOnes);
				}
			} else {
				prevNumOfOnes = currNumOfOnes;
				currNumOfOnes = 0;
				lastNumOfZeroes++;
				longestSoFar = Math.max(longestSoFar, 1);
			}
			n = n >>> 1;
		}
		
		return longestSoFar;
	}
	
	public int longestSeqOf1sPossible(int n) {
		
		List<Integer> counts = new ArrayList<>(32);
		int val =0;
		int count = 0;
		
		int i=0;
		while (i < Integer.BYTES*8) {
			int v = (n & 1);
			n = n >>> 1;
			if (v != val) {
				counts.add(count);
				count = 1;
			} else {
				count++;
			}
			val = v;
			i++;
		}

		counts.add(count);
		

		System.out.println(counts);
		
		int longestSoFar = 0, numOfZeroes = 0, numOfOnes = 0;
		
		for(i=0; i<counts.size(); i++) {
			if (i % 2 == 0) {
				numOfZeroes = counts.get(i);
				if (numOfZeroes == 1 && i + 1 < counts.size()) {
					int nextOnesCount = counts.get(i+1);
					if (numOfOnes + 1 + nextOnesCount > longestSoFar) 
						longestSoFar = numOfOnes + 1 + nextOnesCount;
				} else {
					longestSoFar = Math.max(longestSoFar, 1 + numOfOnes);
				}
			} else {
				numOfOnes = counts.get(i);
			}
			
		}
		
		return longestSoFar;
	}
	
	public String integerToBinaryString(int x) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < 32) {
			sb.append(x & 1);
			x = x >> 1;
			i++;
		}
		sb.reverse();
		return sb.toString();
	}
	
	private String fractionalToBinaryString(double x) {
		StringBuilder sb = new StringBuilder();
		sb.append(".");
		while(x > 0) {
			if (sb.length() > 31) {
				break;
			}
			
			double r = x * 2;
			
			if (r >= 1) {
				sb.append(1);
				x = r-1;
			} else {
				sb.append(0);
				x = r;
			}
		}
		
		return sb.toString();
	}
//	
//	
//	public String doubleToBinaryString(double x) {
//		
//	}
	
	public int updateNwithM(int n, int m, int i, int j) {
		while(m != 0) {
			int bit = getBit(m, 0);
			n = updateBit(n, i++, bit == 1);
			m = m >>> 1;
		}
		return n;
	}
	
	public int getBit(int n, int i) {
		return (n & (1 << i)) > 0 ? 1 : 0;
	}
	
	public int setBit(int n, int i) {
		return (n | (1 << i));
	}
	
	public int clearBit(int n, int i) {
		return (n & (~(1 << i)));
	}
	
	public int clearBitsMSBThroughI(int n, int i) {
		return (n & ((1 << i) - 1));
	}
	
	public int clearBitsIThrough0(int n, int i) {
		return (n & (-1 << (i+1)));
	}
	
	// clear bit i; set it to 0 or 1 depending on isOne
	public int updateBit(int n, int i, boolean isOne) {
		int val = isOne ? 1 : 0;
		return (n & (~(1 << i))) | (val << i);
	}

}
