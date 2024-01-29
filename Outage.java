package edu.iastate.cs228.hw1;

public class Outage extends TownCell {

	/**
	 * @author chilana amaratunga
	 */
	public Outage(Town p, int r, int c) {
		super(p, r, c);
		
	}

	@Override
	public State who() {
		
		return State.OUTAGE;
	}

	@Override
	public TownCell next(Town tNew) {
		
		return new Empty(tNew, row, col);
	}

}
