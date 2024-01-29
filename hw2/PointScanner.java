package edu.iastate.cs228.hw2;

import java.io.File;


/**
 * 
 * @author Chilana Amaratunga

 *
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
	protected String outputFileName; // the name of the file to be made 
	
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		if(pts == null || pts.length == 0) {
			throw new IllegalArgumentException();
		}
		
		points = pts; 
		sortingAlgorithm = algo;
		
		
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		
      try {	
		File f = new File(inputFileName);
		
		Scanner scnr = new Scanner(f);
		sortingAlgorithm = algo;
		ArrayList<Point> pointList = new ArrayList<>();
		
		
		while(scnr.hasNextInt()) {
			
			int x = scnr.nextInt();
			
			if(!scnr.hasNextInt()) {
				throw new InputMismatchException();
			}
			
			int y = scnr.nextInt();
			pointList.add(new Point(x,y));
		}
		
		
		points = new Point[pointList.size()];
		
		for(int i = 0; i < pointList.size(); i++) {
			
			points[i] = pointList.get(i);
		}
		
		
		scnr.close();
      }
      catch(FileNotFoundException e) {
    	  throw new FileNotFoundException();
      }
		
	}

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 */
	public void scan()
	{
		// TODO  
		AbstractSorter aSorter = null; 
		
		if(sortingAlgorithm == Algorithm.InsertionSort) {
			
			aSorter = new InsertionSorter(points);
		}
		else if(sortingAlgorithm == Algorithm.MergeSort) {
			
			aSorter = new MergeSorter(points);
		}
		else if(sortingAlgorithm == Algorithm.QuickSort) {
			
			aSorter = new QuickSorter(points);
		}
		else if(sortingAlgorithm == Algorithm.SelectionSort) {
	
			aSorter = new SelectionSorter(points);
		}
		
		
		long yStart = System.nanoTime();
		
		aSorter.setComparator(1);
		
		aSorter.sort();
		
		int medY = aSorter.getMedian().getY();
		
		scanTime += System.nanoTime() - yStart;
		
		long xStart = System.nanoTime();
		
		aSorter.setComparator(0);
		
		aSorter.sort();
		
		int medX = aSorter.getMedian().getX();
		
		scanTime += System.nanoTime() - xStart;
		
		medianCoordinatePoint = new Point(medX, medY);
		
	
		
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 *  
	 */
	public String stats()
	{
		String stats;
		
		stats = String.format("%-14s %6d %4d", sortingAlgorithm, points.length, scanTime);
		
		return stats;
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		String medPoint;
		
		medPoint = "MCP: (" + medianCoordinatePoint.getX() + ", " + medianCoordinatePoint.getY() + ")";
		
		return medPoint;
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		try {
			
			if (sortingAlgorithm == Algorithm.InsertionSort) {
				outputFileName = "insert.txt";
			} 
			else if (sortingAlgorithm == Algorithm.SelectionSort) {
				outputFileName = "selection.txt";
			}
			else if (sortingAlgorithm == Algorithm.MergeSort) {
				outputFileName = "merge.txt";
			} 
			else if (sortingAlgorithm == Algorithm.QuickSort) {
				outputFileName = "quick.txt";
			} 
			
			File f = new File(outputFileName);
			PrintWriter writer = new PrintWriter(f);
			writer.write(this.toString());
			writer.close();
			
		}
		catch(FileNotFoundException e){
			
			throw new FileNotFoundException();
		}
		
	}	

	

		
}
