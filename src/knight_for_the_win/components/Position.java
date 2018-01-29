package knight_for_the_win.components;

import java.util.concurrent.ThreadLocalRandom;

import knight_for_the_win.exceptions.PositionException;

/**
 * 
 * @author Kaloyan Tsenov
 * @see Position.{@link #x}
 * @see Position.{@link #y}
 */
public class Position {
	private final static int MIN_POSITION = 1;
	private final static int STUCK_POSITION = 2;
	private final static int MAX_POSITION = 3;
	private final static int WIN_POSITION = 3;
	private final static String[] LEGAL_STARTING_POSITIONS = { "1 1", "1 2", "1 3", "2 1", "2 3", "3 1", "3 2" };
	
	/**
	 * Chess piece column
	 */
	private int x;
	
	/**
	 * Chess piece row
	 */
	private int y;
	
	/**
	 * Constructor, sets the object starting coordinations
	 * @param x - chess piece column
	 * @param y - chess pice row
	 * @throws PositionException - occurs when the params x and y are not legal
	 */
	public Position(int x, int y) throws PositionException {
		if(!isPositionOnBoard(x, y) || !isLegalStartPosition(x, y)) {
			throw new PositionException("You can`t start on the final "
					+ "or stuck position: " + x + " " + y);
		}
		setPositionCoordinates(x, y);
	}
	
	public void setPositionCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	/**
	 * Generates random starting position of the knight.
	 * This Method is for testing only.
	 */
	// Generates a random position of the knight at the start of the game
	public static String generatePosition() {
		String coordinates = LEGAL_STARTING_POSITIONS[ThreadLocalRandom.current().nextInt(0,
				(LEGAL_STARTING_POSITIONS.length))];
		
		return coordinates;
	}
	
	private boolean isLegalStartPosition(int x, int y) {
		if ( (x == STUCK_POSITION && y == STUCK_POSITION) 
				|| (x == WIN_POSITION && y == WIN_POSITION)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if the user has made a position
	 * between the game rules
	 * @param x - row
	 * @param y - column
	 */
	public boolean isLegalPosition(int x, int y) {
		if(isPositionOnBoard(x, y)) {
			int deltaX = Math.abs(this.x - x);
			int deltaY = Math.abs(this.y - y);
			
			if((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isPositionOnBoard(int x, int y) {
		return ((x >= MIN_POSITION && x <= MAX_POSITION) && (y >= MIN_POSITION && y <= MAX_POSITION));
	}

	/**
	 * Checks if the coordinates of x and y are equal with the
	 * winning coordinates of the game
	 * @return true or false
	 */
	public Boolean isWinningPosition() {
		return (this.x == WIN_POSITION && this.y == WIN_POSITION);
	}
	
}
