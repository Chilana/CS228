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
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		
		algorithm = "Insertion Sorter";
	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		int length = points.length;
		
		for(int i = 0; i < length - 1; i++) {
			
			Point min = points[i];
			
			int indexOfMin = i;
			
			for(int j = i + 1; j < length; j++) {
				
				if(pointComparator.compare(points[j], min) == -1) {
					
					min = points[j];
					
					indexOfMin = j;
				}
			}
			
			swap(i, indexOfMin);
			
		}
	}	
}
