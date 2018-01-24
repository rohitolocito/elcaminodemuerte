package CrackingTheCodingInterview6;

public class Helper {
	
	private static final Character[] EMPTY_CHARACTER_ARRAY = {};
	
	public static Character[] toObject(char[] arr) {
		
		if(arr.length == 0)
			return EMPTY_CHARACTER_ARRAY;
		Character[] objectArray = new Character[arr.length];
		for(int i=0; i<arr.length; i++)
			objectArray[i] = new Character(arr[i]);
		
		return objectArray;
	}
	
	public static char[] toPrimitive(Character[] arr) {
		
		if(arr.length == 0)
			return new char[0];
		
		char[] charArray = new char[arr.length];
		for(int i=0; i<arr.length; i++)
			charArray[i] = arr[i];
		
		return charArray;
	}
	
	public static void printMatrix(int[][] mat) {
		System.out.println("=====================");
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat[0].length; j++) {
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("=====================");
	}
	
	public static double[] getRootsQuadratic(int a, int b, int c) {
		//aX^2 + bX + c
		double determinant = Math.pow(b, 2) - 4 * a * c;
		if (determinant > 0) {
			double root1 = (-b + Math.sqrt(determinant)) / (2 * a);
			double root2 = (-b - Math.sqrt(determinant)) / (2 * a);
			double[] roots = {root1, root2};
			return roots;
		} else if (determinant == 0) {
			double root = (-b)/(2 * a);
			return new double[]{root};
		} else {
			return new double[]{};
		}
	}

}
