package edu.iastate.cs228.hw1;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author chilana amaratunga
 *
 */
public class Town {

	private int length, width; // Row and col (first and second indices)
	public TownCell[][] grid;

	/**
	 * Constructor to be used when user wants to generate grid randomly, with the
	 * given seed. This constructor does not populate each cell of the grid (but
	 * should assign a 2D array to it).
	 * 
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) {
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];
	}

	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of
	 * catching it. Ensure that you close any resources (like file or scanner) which
	 * is opened in this function.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {

		File file = new File(inputFileName);

		try {
			Scanner scan = new Scanner(file);
			this.length = scan.nextInt();
			this.width = scan.nextInt();

			grid = new TownCell[length][width];
			while (scan.hasNextLine()) {
				for (int x = 0; x < length; x++) {
					for (int y = 0; y < width; y++) {
						switch (scan.next()) {
						case "C":
							grid[x][y] = new Casual(this, x, y);
							break;

						case "S":
							grid[x][y] = new Streamer(this, x, y);
							break;

						case "R":
							grid[x][y] = new Reseller(this, x, y);
							break;

						case "E":
							grid[x][y] = new Empty(this, x, y);
							break;

						case "O":
							grid[x][y] = new Outage(this, x, y);
							break;
						}
					}
				}
			}

			scan.close();

		} catch (FileNotFoundException e) {
			System.out.println("Invalid file path" + e.toString());
		}

		

	}

	/**
	 * Returns width of the grid.
	 * 
	 * @return
	 */
	public int getWidth() {

		return width;
	}

	/**
	 * Returns length of the grid.
	 * 
	 * @return
	 */
	public int getLength() {

		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following
	 * class object: Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				int randNum = rand.nextInt(5);

				if (randNum == 0) {
					grid[i][j] = new Reseller(this, i, j);
				} else if (randNum == 1) {
					grid[i][j] = new Empty(this, i, j);
				} else if (randNum == 2) {
					grid[i][j] = new Casual(this, i, j);
				} else if (randNum == 3) {
					grid[i][j] = new Outage(this, i, j);
				} else if (randNum == 4) {
					grid[i][j] = new Streamer(this, i, j);
				}
			}
		}
	}

	/**
	 * Output the town grid. For each square, output the first letter of the cell
	 * type. Each letter should be separated either by a single space or a tab. And
	 * each row should be in a new line. There should not be any extra line between
	 * the rows.
	 */
	@Override
	public String toString() {
		String s = "";

		for (int i = 0; i < length; i++) {
			s = s + "\n";
			for (int j = 0; j < getWidth(); j++) {
				if (grid[i][j].who().equals(State.CASUAL)) {
					s += "C" + "  ";
				} else if (grid[i][j].who() == State.RESELLER) {
					s += "R" + "  ";
				} else if (grid[i][j].who() == State.STREAMER) {
					s += "S" + "  ";
				} else if (grid[i][j].who() == State.EMPTY) {
					s += "E" + "  ";
				} else if (grid[i][j].who() == State.OUTAGE) {
					s += "O" + "  ";
				}
			}
		}
		return s;
	}
}
