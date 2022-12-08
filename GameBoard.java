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
	private boolean isDone;
	
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
		isDone = false;
	}
	
	public boolean canPlaceTileInRow(int r) {
		int t = hand.get(0);
		if (r == 1) {
			if (t==0) {
				if (wall[0][3]) return false;
			} else if (t==1) {
				if (wall[0][0]) return false;
			} else if (t==2) {
				if (wall[0][4]) return false;
			} else if (t==3) {
				if (wall[0][1]) return false;
			} else if (t==4) {
				if (wall[0][2]) return false;
			}
		} else if (r == 2) {
			if (t==0) {
				if (wall[1][4]) return false;
			} else if (t==1) {
				if (wall[1][1]) return false;
			} else if (t==2) {
				if (wall[1][0]) return false;
			} else if (t==3) {
				if (wall[1][2]) return false;
			} else if (t==4) {
				if (wall[1][3]) return false;
			}
		} else if (r == 3) {
			if (t==0) {
				if (wall[2][0]) return false;
			} else if (t==1) {
				if (wall[2][2]) return false;
			} else if (t==2) {
				if (wall[2][1]) return false;
			} else if (t==3) {
				if (wall[2][3]) return false;
			} else if (t==4) {
				if (wall[2][4]) return false;
			}
		} else if (r == 4) {
			if (t==0) {
				if (wall[3][1]) return false;
			} else if (t==1) {
				if (wall[3][3]) return false;
			} else if (t==2) {
				if (wall[3][2]) return false;
			} else if (t==3) {
				if (wall[3][4]) return false;
			} else if (t==4) {
				if (wall[3][0]) return false;
			}
		} else if (r == 5) {
			if (t==0) {
				if (wall[4][2]) return false;
			} else if (t==1) {
				if (wall[4][4]) return false;
			} else if (t==2) {
				if (wall[4][3]) return false;
			} else if (t==3) {
				if (wall[4][0]) return false;
			} else if (t==4) {
				if (wall[4][1]) return false;
			}
		}
		if (getRow(r).size() > 0) {
			if (hand.get(0) != getRow(r).get(0)) return false;
		}
		if (rowIsFull(r)) {
			return false;
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
		if (r==1) return (row1.size() == 1);
		if (r==2) return (row2.size() == 2);
		if (r==3) return (row3.size() == 3);
		if (r==4) return (row4.size() == 4);
		if (r==5) return (row5.size() == 5);
		System.out.println("rowIsFull() method");
		return true;
	}
	
	public void eatIt() {
		int k = hand.size();
		for (int i=0; i<k; i++) {
			extraTiles.add(hand.remove(0));
		}
	}
	
	public ArrayList<Integer> addTilesToRow(int row) {
		
		ArrayList<Integer> out = new ArrayList<Integer>();
		
		if (hand.contains(5)) {
			extraTiles.add(hand.remove(hand.indexOf(5)));
		}
		/* if(row > 5 || row < 0) {
			System.out.println("the row that is going into addTilesToRow in GameBoard class is either greater than 5 or less than 0");
			return;
		}  else if (!rowIsEmpty(row) && !rowIsFull(row) && getTile(row) != hand.get(0)) {
			System.out.println("you are adding a different colored tile to an already filled row");
			return;
		} else */ if (hand.size() + getRow(row).size() > row) {
			int k = hand.size();
			for (int i=0; i<k; i++) {
				if (!rowIsFull(row)) {
					getRow(row).add(hand.remove(0));
				} else {
					System.out.println("REEE");
					if (extraTiles.size() >= 7) {
						out.add(hand.remove(0));
					} else {
						extraTiles.add(hand.remove(0));
					}
					
				}
			}
		} else {

			int k = hand.size();
			for (int j=0; j<k; j++) {
				getRow(row).add(hand.remove(0));
			}

		}
		return out;
	}
	
	public boolean ready() {
		if (row1.size() == 1) return false;
		if (row2.size() == 2) return false;
		if (row3.size() == 3) return false;
		if (row4.size() == 4) return false;
		if (row5.size() == 5) return false;
		if (extraTiles.size() > 0) return false;
		return true;
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

	public boolean checkWall() {
		for (int i=0; i<5; i++) {

			if (wall[i][0] && wall[i][1] && wall[i][2] && wall[i][3] && wall[i][4]) {
				return true;
			}

		}
		return false;
	}

	public void addToExtraTiles(int t) {
		extraTiles.add(t);
	}

	public void setScore(int x) {
		score = x;
	}
	
	public String toString() {
		return String.valueOf(playerID);
	}
	
	public ArrayList<Integer> getExtraTiles() {
		return extraTiles;
	}
	
	public int addBonuses() {
		int b = 0;
		for (int i=0; i<5; i++) {
			if (wall[i][0] && wall[i][1] && wall[i][2] && wall[i][3] && wall[i][4]) {
				b = b + 2;
			}
		}
		for (int j=0; j<5; j++) {
			if (wall[0][j] && wall[1][j] && wall[2][j] && wall[3][j] && wall[4][j]) {
				b = b + 7;
			}
		}
		if (wall[0][0] && wall[1][1] && wall[2][2] && wall[3][3] && wall[4][4]) {
			b = b + 10;
		}
		if (wall[0][1] && wall[1][2] && wall[2][3] && wall[3][4] && wall[4][0]) {
			b = b + 10;
		}
		if (wall[0][2] && wall[1][3] && wall[2][4] && wall[3][0] && wall[4][1]) {
			b = b + 10;
		}
		if (wall[0][3] && wall[1][4] && wall[2][0] && wall[3][1] && wall[4][2]) {
			b = b + 10;
		}
		if (wall[0][4] && wall[1][0] && wall[2][1] && wall[3][2] && wall[4][3]) {
			b = b + 10;
		}
		return b;
	}
	
	public boolean isDone() {
		return isDone;
	}
	
	public void setIsDone() {
		isDone = true;
	}
	
	public boolean[][] getWall() {
		return wall;
	}
}
