package solutions;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;

class EggDrop {
	
	private int numOfFloors;
	private int egg1Drop = 0, egg2Drop=0;
	private double[] quadratricRoots = {};
	private Random r = new Random();
	
	public EggDrop(int numOfFloors) {
		this.numOfFloors = numOfFloors;
		//x^2 + x - (2*numOfFloors) = 0;
		this.quadratricRoots = Helper.getRootsQuadratic(1,1,-2*this.numOfFloors);
		System.out.println(Arrays.toString(this.quadratricRoots));
	}
	
	public int findN() {
		if (this.quadratricRoots.length == 0)
			return -1;
		
		this.egg1Drop = (int) Math.ceil(Math.abs(this.quadratricRoots[0]));
		System.out.println("Starting eggDrop with... " + this.egg1Drop);
		return withEgg1Drop(this.egg1Drop);
	}
	
	private int withEgg1Drop(int egg1Drop) {
		
		int prevEggDrop = 0;
		int interval = egg1Drop;
		while(!didItBreak(egg1Drop) && egg1Drop <= this.numOfFloors) {
			interval--;
			prevEggDrop = egg1Drop;
			egg1Drop = egg1Drop + interval;
		}
		int egg2Drop = prevEggDrop+1;
		while(egg2Drop < egg1Drop && egg2Drop <= this.numOfFloors && !didItBreak(egg2Drop)) {
			egg2Drop ++;
		}
		return egg2Drop;
	}
	
	private boolean didItBreak(int n) {
		return r.nextBoolean();
	}
	
	
}

class GenderRatio {
	
	private int numOfFamilies = 0;
	private int boys = 0, girls = 0;
	
	public GenderRatio(int numOfFamilies) {
		this.numOfFamilies = numOfFamilies;
	}
	
	public double getRatio() {
		return (double)this.girls/this.boys;
	}
	
	public void populate() {
		for (int i=0; i<this.numOfFamilies; i++) {
			int count[] = generate();
			boys += count[0];
			girls += count[1];
		}
	}
	
	private int[] generate() {
		Random r = new Random();
		int g = 0, b = 0;
		while(g == 0) {
			if (r.nextInt() % 2 == 0)
				b ++;
			else
				g++;
		}
		
		int[] count = new int[2];
		count[0] = b;
		count[1] = g;
		return count;
	}
}

public class CTCI_MathAndLogicPuzzles {

	public static void main(String[] args) {
		
		CTCI_MathAndLogicPuzzles run = new CTCI_MathAndLogicPuzzles();
		System.out.println(run.GCD(17, 33));
		System.out.println(run.GCD(84, 64));
		System.out.println(run.LCM(4, 6));
		System.out.println(run.isPrime(19));
		System.out.println(run.isPrime(69));
		System.out.println("3 => "+run.isPrimeSieveOfEratosthenes(3));
		System.out.println("121 => "+run.isPrimeSieveOfEratosthenes(121));

		// takes a looooong time
		//System.out.println((Integer.MAX_VALUE-2) + " => " +  run.isPrimeSieveOfEratosthenes(Integer.MAX_VALUE-2));
		
		GenderRatio genderRatio = new GenderRatio(25);
		genderRatio.populate();
		System.out.println("Ratio => " + genderRatio.getRatio());
		
		EggDrop drop = new EggDrop(100);
		System.out.println(drop.findN());
		
		System.out.println(run.numOfDoorsOpen(100));
	}
	
//	public int minDaysFindPoisonedBottle(int bottles, int strips) {
//		
//	}
	
	public int numOfDoorsOpen(int numOfLockers) {
		BitSet lockers = new BitSet(numOfLockers);
		lockers.set(1, numOfLockers+1);
		
		for(int i=2; i<=numOfLockers; i++) {
			for(int j=i; j<=numOfLockers; j+=i) {
				lockers.flip(j);
			}
		}
		return lockers.cardinality();
	}
	
	
	public boolean isPrimeSieveOfEratosthenes(int n) {
		if (n <= 1)
			return false;

		BitSet isPrime = new BitSet();
		isPrime.set(0, n+1);
		
		int prime = 2;
		double sqrt = Math.sqrt(n);
		while (prime <= sqrt) {
			populateNonPrimes(isPrime, prime, n);
			prime = getNextPrime(isPrime, prime+1);
		}
		return isPrime.get(n);
	}
	
	private int getNextPrime(BitSet isPrime, int from) {
		return isPrime.nextSetBit(from);
	}
	
	private void populateNonPrimes(BitSet isPrime, int prime, int n) {
		for (int i=prime * prime; i >=0 && i<=n; i+=prime) {
			isPrime.clear(i);
		}
	}
	
	
	public boolean isPrime(int n) {
		if (n <= 1)
			return false;
		
		double sqrt = Math.sqrt(n);
		for (int i=2; i<=sqrt; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
	
	public int GCD(int a, int b) {
		if (b > a) {
			return GCD(b, a);
		}
		int temp ;
		while( (temp = a % b) != 0) {
			b = temp;
		}
		return b;
	}
	
	public int LCM(int a, int b) {
		return a*b/GCD(a,b);
	}

}
