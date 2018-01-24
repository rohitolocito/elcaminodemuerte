package CrackingTheCodingInterview6;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import datastructures.MyNode;

public class CTCI_LinkedLists {
	
	public static void main(String[] args) {
		
		CTCI_LinkedLists run = new CTCI_LinkedLists();
		
		MyNode<Integer> list = new MyNode<Integer>(2);
		list.next = new MyNode<Integer>(2);
		list.next.next = new MyNode<Integer>(2);
		list.next.next.next = new MyNode<Integer>(3);
		list.next.next.next.next = new MyNode<Integer>(1);
		list.next.next.next.next.next = new MyNode<Integer>(13);
		run.removeDuplicatesNoExtraSpace(list);
		
		System.out.println(list);
		
		MyNode<Integer> node = new MyNode<Integer>(7);
		node.next = new MyNode<Integer>(6);
		node.next.next = new MyNode<Integer>(5);
		node.next.next.next = new MyNode<Integer>(4);
		node.next.next.next.next = new MyNode<Integer>(3);
		node.next.next.next.next.next = new MyNode<Integer>(2);
		node.next.next.next.next.next.next = new MyNode<Integer>(1);
		
		System.out.println(run.kthToLast(node, 3).getVal());
		System.out.println(run.kthToLast(node, 7).getVal());
		System.out.println(run.kthToLast(node, 8));
		
		System.out.println(run.kthToLastRecursion(node, 3).getVal());
		System.out.println(run.kthToLastRecursion(node, 7).getVal());
		System.out.println(run.kthToLastRecursion(node, 8));
		
		System.out.println(run.kthToLastEfficient(node, 3).getVal());
		System.out.println(run.kthToLastEfficient(node, 7).getVal());
		System.out.println(run.kthToLastEfficient(node, 8));
		
		MyNode<Integer> list1 = new MyNode<Integer>(1);
		list1.next = new MyNode<Integer>(2);
		list1.next.next = new MyNode<Integer>(3);
		list1.next.next.next = new MyNode<Integer>(4);
		list1.next.next.next.next = new MyNode<Integer>(5);
		
		run.deleteNode(list1.next);
		System.out.println(list1);
		
		MyNode<Integer> partitionList = new MyNode<Integer>(3);
		partitionList.next = new MyNode<Integer>(5);
		partitionList.next.next = new MyNode<Integer>(8);
		partitionList.next.next.next = new MyNode<Integer>(5);
		partitionList.next.next.next.next = new MyNode<Integer>(10);
		partitionList.next.next.next.next.next = new MyNode<Integer>(2);
		partitionList.next.next.next.next.next.next = new MyNode<Integer>(1);
		partitionList.next.next.next.next.next.next.next = new MyNode<Integer>(4);
		
		MyNode<Integer> partitionRes = run.partition(partitionList, 5);
		System.out.println(partitionRes);
		
		MyNode<Integer> node1 = new MyNode<Integer>(7);
		node1.next = new MyNode<Integer>(1);
		node1.next.next = new MyNode<Integer>(6);

		MyNode<Integer> node2 = new MyNode<Integer>(5);
		node2.next = new MyNode<Integer>(9);
		node2.next.next = new MyNode<Integer>(2);
		
		MyNode<Integer> sum = run.sumReversedLists(node1, node2);
		System.out.println(sum);
		
		
		// 7 1 6 
		// 5 9 2
		MyNode<Integer> sumFor = run.sumForwardLists(node1, node2);
		System.out.println(sumFor);
		
		MyNode<Integer> rev = new MyNode<Integer>(1);
		rev.next = new MyNode<Integer>(2);
		rev.next.next = new MyNode<Integer>(3);
		rev.next.next.next = new MyNode<Integer>(4);
		rev.next.next.next.next = new MyNode<Integer>(5);
		System.out.println(rev = run.reverseIterative(rev));
		
		System.out.println(rev = run.reverseRecursive(rev));
		
		MyNode<Integer> palin = new MyNode<Integer>(1);
		palin.next = new MyNode<Integer>(2);
		palin.next.next = new MyNode<Integer>(2);
		palin.next.next.next = new MyNode<Integer>(2);
		palin.next.next.next.next = new MyNode<Integer>(1);
		
		System.out.println(run.areSameValues(palin, run.reverseAndClone(palin)));
		
		System.out.println(run.isPalindrome(palin));
		
		System.out.println(run.isPalindromeIterative(palin));
		
		MyNode<Integer> n1 = new MyNode<Integer>(3);
		n1.next = new MyNode<Integer>(1);
		n1.next.next = new MyNode<Integer>(5);
		n1.next.next.next = new MyNode<Integer>(9);
		n1.next.next.next.next = new MyNode<Integer>(7);
		n1.next.next.next.next.next = new MyNode<Integer>(2);
		n1.next.next.next.next.next.next = new MyNode<Integer>(1);
		
		MyNode<Integer> n2 = new MyNode<Integer>(4);
		n2.next = new MyNode<Integer>(6);
		n2.next.next = n1.next.next.next.next;
		
		System.out.println(run.intersection(n1, n2));
		System.out.println(run.intersectionExtraSpace(n1, n2));
		
		MyNode<Integer> loop = new MyNode<Integer>(1);
		loop.next = new MyNode<Integer>(2);
		loop.next.next = new MyNode<Integer>(3);
		loop.next.next.next = new MyNode<Integer>(4);
		loop.next.next.next.next = new MyNode<Integer>(5);
		loop.next.next.next.next.next = new MyNode<Integer>(6);
		loop.next.next.next.next.next.next = new MyNode<Integer>(7);
		loop.next.next.next.next.next.next.next = new MyNode<Integer>(8);
		loop.next.next.next.next.next.next.next.next = new MyNode<Integer>(9);
		loop.next.next.next.next.next.next.next.next.next = new MyNode<Integer>(10);
		loop.next.next.next.next.next.next.next.next.next.next = loop.next.next.next.next.next;
		
		System.out.println(run.loopNode(loop).val);
	}
	
	public MyNode<Integer> loopNode(MyNode<Integer> node) {
		if (node == null)
			return null;
		
		MyNode<Integer> slow = node, fast = node;
		int k = 0;
		while (fast != null) {
			k++;
			fast = fast.next;
			if (fast != null)
				fast = fast.next;
			slow = slow.next;
			System.out.println("[" + fast.val +", " + slow.val + "]");
			if (slow == fast)
				break;
		}
		
		if (fast == null)
			return null;
		
		// collision spot
		if (fast == slow)
			System.out.println("Yay => " + fast.val);
		
		while(node != slow) {
			node = node.next;
			slow = slow.next;
		}
		
		return node;
		
	}
	
	public MyNode<Integer> intersectionExtraSpace(MyNode<Integer> node1, MyNode<Integer> node2) {
		if (node1 == null || node2 == null)
			return null;
		
		Set<MyNode> set = new HashSet<>();
		while(node1 != null) {
			set.add(node1);
			node1 = node1.next;
		}
		
		while(node2 != null) {
			if(set.contains(node2))
				return node2;
			
			node2 = node2.next;
		}
		
		return null;
	}
	
	private class LastNodeLen {
		MyNode<Integer> lastNode;
		int len;
		public LastNodeLen(MyNode<Integer> node) {
			this.len = node.len();
			while(node != null && node.next != null)
				node = node.next;
			this.lastNode = node;
		}
	}
	
	public MyNode<Integer> intersection(MyNode<Integer> node1, MyNode<Integer> node2) {
		LastNodeLen lastNode1 = new LastNodeLen(node1);
		LastNodeLen lastNode2 = new LastNodeLen(node2);
		
		if (lastNode1.lastNode != lastNode2.lastNode)
			return null;
		
		int len1 = lastNode1.len;
		int len2 = lastNode2.len;
		
		MyNode<Integer> longer = null, shorter = null;
		
		if (len1 > len2) {
			longer = node1;
			shorter = node2;
		} else {
			longer = node2;
			shorter = node1;
		}
		
		longer = moveKSteps(longer, Math.abs(len1 - len2));
		
		while (longer != null && shorter != null) {
			if (longer == shorter)
				return longer;
			longer = longer.next;
			shorter = shorter.next;
		}
		
		return null;
	}
	
	private MyNode<Integer> moveKSteps(MyNode<Integer> node, int k) {
		if (node == null || k <= 0)
			return node;
		
		while (k > 0) {
			node = node.next;
			k--;
			if (node == null)
				return null;
		}
		
		return node;
	}
	
	public boolean isPalindromeIterative(MyNode<Integer> node) {
		Stack<MyNode> stack = new Stack<>();
		
		MyNode<Integer> fast = node;
		MyNode<Integer> slow = node;
		
		while (fast != null && fast.next != null) {
			fast = fast.next;
			fast = fast.next;
			stack.push(slow);
			slow = slow.next;
		}
		
		if (fast != null)
			slow = slow.next;
		
		while (slow != null) {
			MyNode<Integer> n = stack.pop();
			if (n.val != slow.val)
				return false;
			slow = slow.next;
		}
		
		return true;
	}
	
	
	private boolean areSameValues(MyNode<Integer> node1, MyNode<Integer> node2) {
		while(node1 != null && node2 != null) {
			if (node1.val != node2.val) 
				return false;
			node1 = node1.next;
			node2 = node2.next;
		}
		
		return (node1 == null && node2 == null) ? true : false;
	}
	
	
	private class Result {
		boolean isPalindrome; 
		MyNode<Integer> node;
		
		public Result(boolean isPalindrome, MyNode<Integer> node) {
			this.isPalindrome = isPalindrome;
			this.node = node;
		}
	}
	
	
	public boolean isPalindrome(MyNode<Integer> node) {
		if(node == null)
			return false;
		
		int len = node.len();
		
		return isPalindrome(node, len).isPalindrome;
	}
	
	private Result isPalindrome(MyNode<Integer> node, int len) {
		if(len == 1)
			return new Result(true, node.next);
		
		if(len == 2) {
			return new Result(node.val == node.next.val, node.next.next);
		}
		
		Result r = isPalindrome(node.next, len-2);
		if (r.isPalindrome) {
			return new Result(node.val == r.node.val, r.node.next);
		} else {
			return r;
		}
	}
	
	public MyNode<Integer> reverseAndClone(MyNode<Integer> node) {
		MyNode<Integer> head = null;
		
		while(node != null) {
			MyNode<Integer> rev = new MyNode<Integer>(node.val);
			rev.next = head;
			head = rev;
			node = node.next;
		}
		
		return head;
	}
	
	public MyNode<Integer> reverseRecursive(MyNode<Integer> node) {
		if (node == null || node.next == null) 
			return node;
		
		MyNode next = node.next;
		MyNode head = reverseRecursive(node.next);
		
		if(next != null)
			next.next = node;
		
		node.next = null;
		
		return head;
	}
	
	
	public MyNode<Integer> reverseIterative(MyNode<Integer> node) {
		if(node == null)
			return node;
		
		MyNode head = node;
		MyNode next = node.next;
		head.next = null;
		
		while (next != null) {
			MyNode nextNext = next.next;
			next.next = head;
			head = next;
			next = nextNext;
		}
		
		return head;
		
	}
	
	// 123
	// 123
	// 246
	public MyNode<Integer> sumForwardLists(MyNode<Integer> node1, MyNode<Integer> node2) {
		
		if (node1 == null)
			return node2;
		
		if (node2 == null)
			return node1;
		
		int len1 = node1.len();
		
		int len2 = node2.len();
		
		MyNode<Integer> node = null, res = null; 
		int carry[] = {0};
		
		if (len1 > len2) {
			
			node = padKZeroes(node2, len1-len2);
			res = sumForwardLists(node1, node, carry);
			
		} else if(len1 < len2) {
			
			node = padKZeroes(node1, len2-len1);
			res = sumForwardLists(node, node2, carry);
			
		} else {
			res = sumForwardLists(node1, node2, carry);
		}
		
		if (carry[0] == 0)
			return res;
		else {
			MyNode<Integer> n = new MyNode<Integer>(carry[0]);
			n.next = res;
			return n;
		}
		
	}
	
	private MyNode<Integer> padKZeroes(MyNode<Integer> node, int k) {
		if (k <= 0)
			return node;
		
		while(k > 0) {
			MyNode<Integer> zero = new MyNode<Integer>(0);
			zero.next = node;
			node = zero;
			k--;
		}
		return node;
	}
	
	// 1 2 3
	//     1
	// 1 2 4
	// assumes same length
	private MyNode<Integer> sumForwardLists(MyNode<Integer> node1, MyNode<Integer> node2, int[] carry) { 
		
		if (node1 == null && node2 == null)
			return null;
		
		MyNode<Integer> next = sumForwardLists(node1.next, node2.next, carry);
		
		MyNode<Integer> node = addTwoNodes(node1, node2, carry);
		
		node.next = next;
		
		return node;
	}
	
	private MyNode<Integer> addTwoNodes(MyNode<Integer> node1, MyNode<Integer> node2, int[] carry) {
		int sum = 0;
		
		sum += node1 != null ? node1.val : 0;
		sum += node2 != null ? node2.val : 0;
		
		sum += carry[0];
		MyNode<Integer> node = new MyNode<Integer>(sum % 10);
		carry[0] = sum/10;
		return node;
		
	}
	
	public MyNode<Integer> sumReversedLists(MyNode<Integer> node1, MyNode<Integer> node2) {
		if(node1 == null)
			return node2;
		
		if(node2 == null)
			return node1;
		
		MyNode<Integer> res = null, resCurr = null;
		int carry = 0;
		
		while(node1 != null || node2 != null) {
			
			int val1 = node1 == null ? 0 : node1.val;
			int val2 = node2 == null ? 0 : node2.val;
			
			int sum = val1 + val2 + carry;
			
			int val = sum % 10;
			carry = sum/10;
			
			if(res == null) {
				res = new MyNode<Integer>(val);
				resCurr = res;
			} else {
				resCurr.next = new MyNode<Integer>(val);
				resCurr = resCurr.next;
			}
			
			node1 = node1 != null ? node1.next : null;
			node2 = node2 != null ? node2.next : null;
		}
		
		if (carry != 0) {
			resCurr.next = new MyNode<Integer>(carry);
		}
		
		return res;
	}
	
	public MyNode<Integer> partitionSmart(MyNode<Integer> node, int x) {
		MyNode<Integer> head = node;
		MyNode<Integer> tail = node;
		
		while(node != null) {
			MyNode next = node.next;
			
			if(node.val < x) {
				node.next = head;
				head = node;
			} else {
				tail.next = node;
				tail = node;
			}
			node = next;
		}
		tail.next = null;
		
		return head;
	}
	
	public MyNode<Integer> partition(MyNode<Integer> node, int x) {
		if(node == null)
			return null;
		
		MyNode<Integer> less = null, lessCurr = null;
		MyNode<Integer> high = null, highCurr = null;
		
		while(node != null) {
			
			MyNode next = node.next;
			node.next = null;
			
			if(node.val < x) {
				if(less == null) {
					less = node;
					lessCurr = node;
				} else {
					lessCurr.next = node;
					lessCurr = node;
				}
			} else {
				if(high == null) {
					high = node;
					highCurr = node;
				} else {
					highCurr.next = node;
					highCurr = node;
				}
			}
			node = next;
		}
		
		MyNode<Integer> res = less == null ? high : less;
		if (lessCurr != null)
			lessCurr.next = high;
		
		return res;
	}
	
	public void deleteNode(MyNode<Integer> node) {
		if(node == null || node.next == null)
			return;
		
		MyNode next = node.next;
		node.val = (Integer) next.val;
		node.next = next.next;
	}
	
	public MyNode<Integer> kthToLastEfficient(MyNode<Integer> node, int k) {
		MyNode<Integer> kthNodeFromCurr = node;
		int diff = k;
		while(diff != 0) {
			if(node == null)
				return null;
			node = node.next;
			diff--;
		}
		while(node != null) {
			kthNodeFromCurr = kthNodeFromCurr.next;
			node = node.next;
		}
		return kthNodeFromCurr;
	}
	
	public MyNode<Integer> kthToLastRecursion(MyNode<Integer> node, int k) {
		int[] countFromLast = {k};
		return kthToLast(node, countFromLast);
	}
	
	private MyNode<Integer> kthToLast(MyNode<Integer> node, int[] countFromLast) {
		if(node == null)
			return null;
		
		MyNode last = kthToLast(node.next, countFromLast);
		if(countFromLast[0] == 1) {
			countFromLast[0]--;
			return node;
		} else {
			countFromLast[0]--;
			return last;
		}
	}
	
	public MyNode<Integer> kthToLast(MyNode<Integer> node, int k) {
		if (k <= 0)
			return null;
		
		int len = node.len();
		if (k > len)
			return null;
		int index = len - k + 1;
		while(index-- > 1)
			node = node.next;
		return node;
		
	}
	
	public void removeDuplicatesNoExtraSpace(MyNode<Integer> node) {
		while(node != null) {
			MyNode prev = node;
			MyNode next = node.next;
			while(next != null) {
				if(next.val == node.val) {
					prev.next = next.next;
				} else {
					prev = next;
				}
				next = next.next;
			}
			node = node.next;
		}
	}
	
	public void removeDuplicates(MyNode<Integer> node) {
		
		Set<Integer> set = new HashSet<>();
		MyNode prev = null;
		while(node != null) {
			if(set.contains(node.getVal())) {
				prev.setNext(node.next());
			} else {
				set.add(node.getVal());
				prev = node;	
			}
			node = node.next();
		}
	}

}
