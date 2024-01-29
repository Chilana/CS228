package edu.iastate.cs228.hw2;

/**
 *  
 * @author Chilana Amaratunga
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		
		Point[] points;
		
		PointScanner[] scanners = new PointScanner[4]; 
		
		Scanner userInput = new Scanner(System.in);
		
		int trial = 1;
		
		
		System.out.println("Performances of Four Sorting Algorithms in Point Scanning");
		System.out.println();
		System.out.println("keys: 1 (random integers) 2 (file input) 3 (exit)");
		
		
	while(true) {
			
		
		System.out.print("Trial " + trial +": ");
		
		int choice = userInput.nextInt();
		
		if(choice == 1) {
			
			System.out.print("Enter number of random points: ");
			int numPts = userInput.nextInt();
			
			System.out.println();
			System.out.println();
			
			Random rand = new Random();
			
			points = generateRandomPoints(numPts, rand);
			
			scanners[0] = new PointScanner(points, Algorithm.SelectionSort);
			scanners[1] = new PointScanner(points, Algorithm.InsertionSort);
			scanners[2] = new PointScanner(points, Algorithm.MergeSort);
			scanners[3] = new PointScanner(points, Algorithm.QuickSort);
		}
		else if(choice == 2) {
			
		   try {
			   
			System.out.println("Points from a file");
			System.out.print("File Name: ");
			String file = userInput.next();
			
			System.out.println();
			System.out.println();
			
			scanners[0] = new PointScanner(file, Algorithm.SelectionSort);
			scanners[1] = new PointScanner(file, Algorithm.InsertionSort);
			scanners[2] = new PointScanner(file, Algorithm.MergeSort);
			scanners[3] = new PointScanner(file, Algorithm.QuickSort);
	
		   }
		   catch(FileNotFoundException e) {
			   userInput.close();
			   throw new FileNotFoundException();
		   }
		}
		
		 else if(choice == 3) {
			 
			 userInput.close();
			 return;
			 
		 }
		
		 
		
		
		System.out.println("algorithm        size time(ns)");
		System.out.println("----------------------------------");
		
		for(int i = 0; i < 4; i++) {
			scanners[i].scan();
			System.out.println(scanners[i].stats());
			System.out.println();
			scanners[i].writeMCPToFile();
			
		}
		
		trial++;
		
		System.out.println("----------------------------------");
		System.out.println();
	 }
		
	
		
		
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	private static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		if(numPts < 1) {
			
			throw new IllegalArgumentException();
		}
		
		Point[] points = new Point[numPts];

		for(int i = 0; i < numPts; i++) {
		
			points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
	
		return points; 
	
	}
	
}
