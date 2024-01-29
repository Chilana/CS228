package edu.iastate.cs228.hw1;

/**
 * 
 * @author chilana amaratunga
 *	Also provide appropriate comments for this class
 *
 */
public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;
	
	
	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;
	
	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}
	
	/**
	 * Checks all neigborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 *  
	 * @param counts of all customers
	 */
	public void census(int nCensus[]) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0; 
		nCensus[EMPTY] = 0; 
		nCensus[CASUAL] = 0; 
		nCensus[OUTAGE] = 0; 
		nCensus[STREAMER] = 0; 

		// this is what checks who is in the neighborhood
		  int minRow = Math.max(0,  row - 1);
		  int maxRow = Math.min(plain.grid.length - 1, row + 1);
		  int minCol = Math.max(0, col - 1);
		  int maxCol = Math.min(plain.grid[0].length - 1, col + 1);
	    	   for(int i = minRow; i <= maxRow ; i++) {
	    		   for(int j = minCol; j <= maxCol ; j++) {
	    			   if(i == row  && j == col) {
	    				   continue;
	    			   }
	    			   if(plain.grid[i][j].who() == State.RESELLER) { 
	    				  nCensus[RESELLER] += 1;
	    			   }
	    			   else if(plain.grid[i][j].who() == State.EMPTY) { 
		    				  nCensus[EMPTY] += 1;
	    			   }
	    			   else if(plain.grid[i][j].who() == State.CASUAL) { 
		    				  nCensus[CASUAL] += 1;
	    			   }
	    			   else if(plain.grid[i][j].who() == State.OUTAGE) { 
		    				  nCensus[OUTAGE] += 1;
	    			   }
	    			   else if(plain.grid[i][j].who() == State.STREAMER) { 
		    				  nCensus[STREAMER] += 1;
	    			   }
	    		   }
	    	   }
	 }

	

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);
	
	
}
