package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;

import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 *  
 * @author Chilana Amaratunga

 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		
		algorithm = "Mergesort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		mergeSortRec(points);
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		int length = pts.length;
		
		if(length == 1) {
			
			return;
		}
		int mid = length / 2;
		
		Point[] left = new Point[mid];
		
		Point[] right = new Point[length - mid];
		
		for(int i = 0; i < mid; i++) {
		
			left[i] = pts[i];
		}
		
		for(int i = mid; i < length; i++) {
			
			right[i - mid] = pts[i];
		}
		
		mergeSortRec(left);
		mergeSortRec(right);
		merge(pts, left, right);
		
		
	}

	private void merge(Point[] arr, Point[] left, Point[] right) {
		int leftSize = left.length;
		
		int rightSize = right.length;
		
		int i,j,k;
		i = j = k = 0;
		
		while(i < leftSize && j < rightSize) {
			
			if(pointComparator.compare(left[i], right[j]) < 0) {
				
				arr[k] = left[i];
				
				i++;
				k++;
			}
			else {
				
				arr[k] = right[j];
				
				j++;
				k++;
			}
		}
		while(i < leftSize) {
			
			arr[k] = left[i];
			
			k++;
			i++;
		}
		
		while(j < rightSize) {
			
			arr[k] = right[j];
			
			k++;
			j++;
		}
		
	}
	

}
