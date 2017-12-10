package solutions;

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

}
