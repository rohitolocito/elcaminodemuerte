package datastructures;

public class MyPoint {
	
	// public for ease of use
	public int x;
	public int y;
	
	public MyPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "("+x+", "+y+")";
	}

}
