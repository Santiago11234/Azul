import java.util.*;
import java.lang.*;

public class GameBoard {
	private int score;
	private int playerID;
	private ArrayList<Integer> hand;
	private ArrayList<Integer> row1;
	private ArrayList<Integer> row2;
	private ArrayList<Integer> row3;
	private ArrayList<Integer> row4;
	private ArrayList<Integer> row5;
	private boolean[][] wall;
	private ArrayList<Integer> extraTiles;
	
	public GameBoard(int score, int playerID) {
		this.score = score;
		this.playerID = playerID;
		row1 = new ArrayList<Integer>();
		row2 = new ArrayList<Integer>();
		row3 = new ArrayList<Integer>();
		row4 = new ArrayList<Integer>();
		row5 = new ArrayList<Integer>();
		wall = new boolean[5][5];
		for (int r=0; r<5; r++) {
			for (int c=0; c<5; c++) {
				wall[r][c] = false;
			}
		}
		extraTiles = new ArrayList<Integer>();
		hand = new ArrayList<Integer>();
	}
	
	public boolean canPlaceTileInRow(int r) {

		if (getRow(r).size() > 0) {
			if (hand.get(0) != getRow(r).get(0)) return false;
		}
		return true;
	}
	
	public ArrayList<Integer> getHand() {
		return hand;
	}
	
	public int getPlayerID() {
		return playerID;
	}
	
	public int getScore() {
		return score;
	}
	
	public void addScore(int k) {
		score = score + k;
	}
	
	public boolean rowIsEmpty(int r) {
		return (this.getRow(r).size() == 0);
	}
	
	public boolean rowIsFull(int r) {
		if (getRow(r).equals(row1)) return (row1.size() == 1);
		if (getRow(r).equals(row2)) return (row2.size() == 2);
		if (getRow(r).equals(row3)) return (row3.size() == 3);
		if (getRow(r).equals(row4)) return (row4.size() == 4);
		if (getRow(r).equals(row5)) return (row5.size() == 5);
		System.out.println("rowIsFull() method");
		return true;
	}
	
	public void addTilesToRow(int row) {
		
		if (hand.contains(5)) {
			extraTiles.add(hand.remove(hand.indexOf(5)));
		}
		if(row > 5 || row < 0) {
			System.out.println("the row that is going into addTilesToRow in GameBoard class is either greater than 5 or less than 0");
			return;
		}  else if (!rowIsEmpty(row) && !rowIsFull(row) && getTile(row) != hand.get(0)) {
			System.out.println("you are adding a different colored tile to an already filled row");
			return;
		} else if (hand.size() + getRow(row).size() > row) {
			int k = hand.size();
			for (int i=0; i<k; i++) {
				if (!rowIsFull(row)) {
					getRow(row).add(hand.remove(0));
				} else {
					extraTiles.add(hand.remove(0));
				}
			}
		} else {

			int k = hand.size();
			for (int j=0; j<k; j++) {
				getRow(row).add(hand.remove(0));
			}

		}
	}
	
	/* public void score() {
		for (int i=1; i<=5; i++) {
			if (rowIsFull(i)) {
				addTileToWall(getRow(i));
			}
		}
	} */
	
	public ArrayList<Integer> getRow(int r) {
		switch(r) {
		
		case 1: return row1;
		case 2: return row2;
		case 3: return row3;
		case 4: return row4;
		case 5: return row5;
					
		}
		System.out.println("this is coming from getRow method");
		return null;
	}
	
	public int getIntRow(ArrayList<Integer> r) {
		if (r.equals(row1)) return 1;
		if (r.equals(row2)) return 2;
		if (r.equals(row3)) return 3;
		if (r.equals(row4)) return 4;
		if (r.equals(row5)) return 5;
		System.out.println("getIntRow");
		return -1000;
	}
	
	public int getTile(int r) {
		if (rowIsEmpty(r)) System.out.println("you're trying to get a tile from an empty row");
		return getRow(r).get(0);
	}



	public void addToExtraTiles(int t) {
		extraTiles.add(t);
	}

	
	public String toString() {
		return String.valueOf(playerID);
	}
	
	public ArrayList<Integer> getExtraTiles() {
		return extraTiles;
	}
	
	public boolean[][] getWall() {
		return wall;
	}
}
