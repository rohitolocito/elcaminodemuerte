package solutions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.stream.Collectors;

public class CTCI_Java {
	
	private static class Generics<T> {
		private static int objCount = 0;
		private T data;
		//private static T value;  
		//cannot have a static variable/method with Generic types since 
		//both String/Integer (for example) will be sharing the static object
		
		public Generics(T data) {
			this.data = data;
			this.objCount++;
		}
		
		public int getObjectCount() {
			return objCount;
		}
		
		public static void staticHi() {
			System.out.println("Hi from " + objCount);
		}
	}
	
	class PrivateConstructorClass {
		
		private PrivateConstructorClass() {
			
		}
		
		public void hi() {
			System.out.println("Hi !" + super.hashCode());
		}
	}
	
	final class FinalClass {
		
		@Override
		protected void finalize() throws Throwable {
			// TODO Auto-generated method stub
			super.finalize();
		}
	}
	
	static class Country {
		
		private String continent;
		
		private int population;
		
		public Country(String continent, int population) {
			this.continent = continent;
			this.population = population;
		}
		
		public String getContinent() {
			return this.continent;
		}
		
		public int getPopulation() {
			return this.population;
		}
	}
	
	private final int finalVarPrimitive = 0; 

	public static void main(String[] args) {
		
		CTCI_Java run = new CTCI_Java();
		run.testTryCatchFinally();
		
		PrivateConstructorClass privateConstructorClass = run.new PrivateConstructorClass();
		privateConstructorClass.hi();
		
		//System.out.println(run.finalVarPrimitive);
		
		Generics genericsString = new Generics<String>("hi");
		Generics genericsInteger = new Generics<Integer>(1);
		System.out.println(genericsString.getObjectCount());
		
		Map<String, Integer> map = new LinkedHashMap<>();
		map.put("1", 1);
		map.put("One", 1);
		map.put("ONE", 1);
		map.put("2", 2);
		map.put("Two", 2);
		System.out.println(map);
		
		Country India = new Country("Asia", Integer.MAX_VALUE);
		Country China = new Country("Asia", Integer.MAX_VALUE);
		List<Country> asianCountries = new ArrayList<>();
		asianCountries.add(India);
		asianCountries.add(China);
		
		System.out.println("pop " + run.getPopulation(asianCountries, "Asia"));
		System.out.println("pop " + run.getPopulationUsingReduce(asianCountries, "Asia"));
		
		TreeMap<Integer, String> treeMap = new TreeMap<>();
		for (int i=-9; i<10; i++) 
			treeMap.put(i, String.valueOf(i));
		
		for (Integer keys : treeMap.keySet())
			System.out.print(keys+ " ");

		System.out.println();
		SortedMap<Integer, String> subMap = treeMap.subMap(8, 11);
		for (Integer keys : subMap.keySet())
			System.out.print(keys+ " ");
		
		List<Integer> list = new ArrayList<>();
		for (int i=0; i<20; i++)
			list.add(i);
		
		System.out.println();
		System.out.println(run.getRandomSubset(list));
		System.out.println();
		run.execute("solutions.CTCI_Java$Square", 4.0, "area");
	}
	
	private class Square {
		
		double val;
		
		public Square(double val) {
			this.val = val;
		}
		
		public double area() {
			return this.val * this.val;
		}
	}
	
	public void execute(String className, double val, String methodName) {
		try {
			Class classSquare = Class.forName(className);
			
			Constructor constructor = classSquare.getConstructor(double.class);
			Square square = (Square)constructor.newInstance(val);
			Method m = classSquare.getDeclaredMethod(methodName);
			System.out.println(m.invoke(square));
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Integer> getRandomSubset(List<Integer> list) {
		Random r = new Random();
		return list.stream().filter(integer -> r.nextBoolean()).collect(Collectors.toList());
	}
	
	public long getPopulationUsingReduce(List<Country> countries, String continent) {
		LongBinaryOperator binaryOperator = (a, b) -> a + b;
		
		return countries
				.stream()
				.filter(country -> country.getContinent().equals(continent))
				.mapToLong(country -> country.getPopulation())
				.reduce(0l, binaryOperator);
	}
	
	public long getPopulation(List<Country> countries, String continent) {
		return countries
				.stream()
				.filter(country -> country.getContinent().equals(continent))
				.mapToLong(country -> country.getPopulation())
				.sum();
	}
	
	public void testTryCatchFinally() {
		
		try {
			System.out.println("try !");
			//System.exit(0); // does not execute finally
			return;
		} catch(Exception e) {
			System.out.println("Catch !");
		}
		
		finally {
			System.out.println("Finally !");
		}
	}

}
