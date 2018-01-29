/**
 * 
 */
package knight_tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import knight_for_the_win.KnightFactory;
import knight_for_the_win.KnightLogic;
import knight_for_the_win.components.Board;
import knight_for_the_win.components.Position;
import knight_for_the_win.exceptions.PositionException;

/**
 * @author Kaloyan
 *
 */
public class KnightChessTest {

	private KnightLogic logicObject;
	private KnightFactory factoryObject;
	private Position positionObject;

	private HashMap<String, Integer[]> coord;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		factoryObject = new KnightFactory();
		logicObject = factoryObject.constructKnight();
		positionObject = new Position(1, 1);
		coord = new HashMap<>();
		coord.put("default", new Integer[] {1, 2});
		coord.put("win", new Integer[] {3,  3});
		coord.put("outofboard", new Integer[] {4, 5});
		coord.put("notlegal", new Integer[] {2, 2});
	}

	@Test
	public void testKnightFactoryObjectNotNull() {
		assertNotNull(factoryObject);
	}

	@Test
	public void testBoardObjectNotNull() {
		assertNotNull(Board.getInstance());
	}

	/**
	 * Test method for {@link knight_for_the_win.KnightLogic#KnightLogic()}.
	 */
	@Test
	public void testKnightLogicObjectNotNull() {
		assertNotNull(logicObject);
	}

	@Test
	public void testPositionObjectNull() {
		assertNotNull(positionObject);
	}
	
	@Test (expected = PositionException.class)
	public void testStartGameWithPositionOutOfBoards() throws PositionException {
		logicObject.startGame(coord.get("outofboard")[0], coord.get("outofboard")[0]);
	}

	/**
	 * Test method for
	 * {@link knight_for_the_win.KnightLogic#startGame(int, int)}.
	 * 
	 * @throws PositionException
	 */
	@Test
	public void testMoveKnightCatchClauseNumberFormatException() throws PositionException {
		logicObject.startGame(coord.get("default")[0], coord.get("default")[1]);
		
		String [] wrongInfo = {"not", "number"};
		logicObject.moveKnight(wrongInfo);
		
	}
	
	@Test
	public void testMoveKnightCatchClauseArrayIndexOutOfBoundsException() throws PositionException {
		logicObject.startGame(coord.get("default")[0], coord.get("default")[1]);
		
		String[] lessInfo = {"1"};
		logicObject.moveKnight(lessInfo);
		
	}
	
	@Test
	public void testMoveKnightWinCase() throws PositionException {
		logicObject.startGame(coord.get("default")[0], coord.get("default")[1]);
		logicObject.moveKnight(new String[] {"3", "3"});
	}
	
	@Test
	public void testMoveKnightWithSendingTwiceSamePosition() throws PositionException {
		logicObject.startGame(coord.get("default")[0], coord.get("default")[1]);
		logicObject.moveKnight(new String[] {"1", "2"});
	}
	
	@Test
	public void testIsGameFinishedParameter() throws PositionException {
		logicObject.startGame(coord.get("default")[0], coord.get("default")[1]);
		logicObject.moveKnight(new String[] {"3", "3"});
		assertTrue(logicObject.isGameFinished());
	}

	@Test
	public void testIsLegalPosition() {
		// test with legal position
		assertTrue(positionObject.isLegalPosition(2, 3));
		// test with not legal position
		assertFalse(positionObject.isLegalPosition(5, 7));
	}

}
