import java.util.*;
import java.lang.*;


public class AzulLogic {
	private ArrayList<GameBoard> players;
	private boolean isOver;
	private boolean roundIsOver;
	private ArrayList<Factory> factories;
	private ArrayList<Integer> centerpile;
	private ArrayList<Integer> bagOfTiles;
	private ArrayList<Integer> discard;
	private int nextPlayerID;
	private ArrayList<Integer> winner;
	private int bonusCnt;
	
	public AzulLogic() {
		players = new ArrayList<GameBoard>();
		for (int i=0; i<4; i++) {
			players.add(new GameBoard(0, i+1));
		}
		factories = new ArrayList<Factory>();
		for (int i=1; i<=9; i++) {
			factories.add(new Factory(i));
		}
		bagOfTiles = new ArrayList<Integer>();
		centerpile = new ArrayList<Integer>();
		isOver = false;
		roundIsOver = false;
		fillBag();
		fillFactories();
		discard = new ArrayList<Integer>();
		centerpile.add(5);
		System.out.println(players);
		nextPlayerID = 1;
		winner = new ArrayList<Integer>();
		bonusCnt=0;
	}
	
	public void endTurn() {
		// this will need MUCH more work later on, this is just to progress the game for now
		if (factories.get(0).getFactoryTiles().isEmpty() && factories.get(1).getFactoryTiles().isEmpty() && 
				factories.get(2).getFactoryTiles().isEmpty() && factories.get(3).getFactoryTiles().isEmpty() && 
				factories.get(4).getFactoryTiles().isEmpty() && factories.get(5).getFactoryTiles().isEmpty() && 
				factories.get(6).getFactoryTiles().isEmpty() && factories.get(7).getFactoryTiles().isEmpty() && 
				factories.get(8).getFactoryTiles().isEmpty() && (centerpile.size() == 0)) {
			Collections.rotate(players, 3);
		} else {
			Collections.rotate(players, 3);
		}
		
	}
	
	public void addTileToWall(int r) {
		
		int t = players.get(0).getRow(r).get(0);
		for (int c = 0; c<r-1; c++) {
			if(players.get(0).getExtraTiles().size() <=7) discard.add(t);
		}
		players.get(0).getRow(r).clear();

		if (r==1) {
			if (t==0) {
				if (!players.get(0).getWall()[0][3]) {
					players.get(0).getWall()[0][3] = true;
					scoreRow(0,3);
				}
			} else if (t==1) {
				if (!players.get(0).getWall()[0][0]) {
					players.get(0).getWall()[0][0] = true;
					scoreRow(0,0);
				}
			} else if (t==2) {
				if (!players.get(0).getWall()[0][4]) {
					players.get(0).getWall()[0][4] = true;
					scoreRow(0,4);
				}
			} else if(t==3) {
				if (!players.get(0).getWall()[0][1]) {
					players.get(0).getWall()[0][1] = true;
					scoreRow(0,1);
				}
			} else if (t==4) {
				if (!players.get(0).getWall()[0][2]) {
					players.get(0).getWall()[0][2] = true;
					scoreRow(0,2);
				}
			}
		} else if (r==2) {
			if (t==0) {
				if (!players.get(0).getWall()[1][4]) {
					players.get(0).getWall()[1][4] = true;
					scoreRow(1,4);
				}
			} else if (t==1) {
				if (!players.get(0).getWall()[1][1]) {
					players.get(0).getWall()[1][1] = true;
					scoreRow(1,1);
				}
			} else if (t==2) {
				if (!players.get(0).getWall()[1][0]) {
					players.get(0).getWall()[1][0] = true;
					scoreRow(1,0);
				}
			} else if(t==3) {
				if (!players.get(0).getWall()[1][2]) {
					players.get(0).getWall()[1][2] = true;
					scoreRow(1,2);
				}
			} else if (t==4) {
				if (!players.get(0).getWall()[1][3]) {
					players.get(0).getWall()[1][3] = true;
					scoreRow(1,3);
				}
			}
		} else if (r==3) {
			if (t==0) {
				if (!players.get(0).getWall()[2][0]) {
					players.get(0).getWall()[2][0] = true;
					scoreRow(2,0);
				}
			} else if (t==1) {
				if (!players.get(0).getWall()[2][2]) {
					players.get(0).getWall()[2][2] = true;
					scoreRow(2,2);
				}
			} else if (t==2) {
				if (!players.get(0).getWall()[2][1]) {
					players.get(0).getWall()[2][1] = true;
					scoreRow(2,1);
				}
			} else if(t==3) {
				if (!players.get(0).getWall()[2][3]) {
					players.get(0).getWall()[2][3] = true;
					scoreRow(2,3);
				}
			} else if (t==4) {
				if (!players.get(0).getWall()[2][4]) {
					players.get(0).getWall()[2][4] = true;
					scoreRow(2,4);
				}
			}
		} else if (r==4) {
			if (t==0) {
				if (!players.get(0).getWall()[3][1]) {
					players.get(0).getWall()[3][1] = true;
					scoreRow(3,1);
				}
			} else if (t==1) {
				if (!players.get(0).getWall()[3][3]) {
					players.get(0).getWall()[3][3] = true;
					scoreRow(3,3);
				}
			} else if (t==2) {
				if (!players.get(0).getWall()[3][2]) {
					players.get(0).getWall()[3][2] = true;
					scoreRow(3,2);
				}
			} else if(t==3) {
				if (!players.get(0).getWall()[3][4]) {
					players.get(0).getWall()[3][4] = true;
					scoreRow(3,4);
				}
			} else if (t==4) {
				if (!players.get(0).getWall()[3][0]) {
					players.get(0).getWall()[3][0] = true;
					scoreRow(3,0);
				}
			}
		} else if (r==5) {
			if (t==0) {
				if (!players.get(0).getWall()[4][2]) {
					players.get(0).getWall()[4][2] = true;
					scoreRow(4,2);
				}
			} else if (t==1) {
				if (!players.get(0).getWall()[4][4]) {
					players.get(0).getWall()[4][4] = true;
					scoreRow(4,4);
				}
			} else if (t==2) {
				if (!players.get(0).getWall()[4][3]) {
					players.get(0).getWall()[4][3] = true;
					scoreRow(4,3);
				}
			} else if(t==3) {
				if (!players.get(0).getWall()[4][0]) {
					players.get(0).getWall()[4][0] = true;
					scoreRow(4,0);
				}
			} else if (t==4) {
				if (!players.get(0).getWall()[4][1]) {
					players.get(0).getWall()[4][1] = true;
					scoreRow(4,1);
				}
			}
		}

	}
	
	public GameBoard getPlayer(int ID) {
		for (int i=0; i<4; i++) {
			if (players.get(i).getPlayerID() == ID) {
				return players.get(i);
			} 
		}
		return players.get(0);
	}
	
	public int scoreRow(int x, int y) {
		int s = 1;
		
		/* System.out.println("x: " + x + " , y: " + y);
        for (boolean[] ints : players.get(0).getWall()) {
            System.out.println(Arrays.toString(ints));
        } */
        
		if (x-1 >= 0 && players.get(0).getWall()[x-1][y]) {
			s++;
			if (x-2>=0 && players.get(0).getWall()[x-2][y]) {
				s++;
				if (x-3>=0 && players.get(0).getWall()[x-3][y]) {
					s++;
					if (x-4>=0 && players.get(0).getWall()[x-4][y]) {
						s++;
					}  
				}
			}
		}
		if (x+1 <= 4 && players.get(0).getWall()[x+1][y]) {
			s++;
			if (x+2<= 4 && players.get(0).getWall()[x+2][y]) {
				s++;
				if (x+3<= 4 && players.get(0).getWall()[x+3][y]) {
					s++;
					if (x+4<= 4 && players.get(0).getWall()[x+4][y]) {
						s++;
					}  
				}
			}
		}
		if (y-1 >= 0 && players.get(0).getWall()[x][y-1]) {
			s++;
			if (y-2>=0 && players.get(0).getWall()[x][y-2]) {
				s++;
				if (y-3>=0 && players.get(0).getWall()[x][y-3]) {
					s++;
					if (y-4>=0 && players.get(0).getWall()[x][y-4]) {
						s++;
					}  
				}
			}
		}
		if (y+1 <= 4 && players.get(0).getWall()[x][y+1]) {
			s++;
			if (y+2<= 4 && players.get(0).getWall()[x][y+2]) {
				s++;
				if (y+3<= 4 && players.get(0).getWall()[x][y+3]) {
					s++;
					if (y+4<= 4 && players.get(0).getWall()[x][y+4]) {
						s++;
					}  
				}
			}
		}

		
		if (x-1>=0 && players.get(0).getWall()[x-1][y] && y-1>=0 && players.get(0).getWall()[x][y-1]) {
			s++;
		} else if (x-1>=0 && players.get(0).getWall()[x-1][y] && y+1<5 && players.get(0).getWall()[x][y+1]) {
			s++;
		} else if (x+1<5 && players.get(0).getWall()[x+1][y] && y+1<5 && players.get(0).getWall()[x][y+1]) {
			s++;
		} else if (x+1<5 && players.get(0).getWall()[x+1][y] && y-1>=0 && players.get(0).getWall()[x][y-1]) {
			s++;
		}
		/* if (((players.get(0).getWall()[x-1][y]) || players.get(0).getWall()[x+1][y]) && (players.get(0).getWall()[x][y-1]) || players.get(0).getWall()[x][y+1]) {
			s = s+1;
		} */
		
		players.get(0).addScore(s);
		return s;
	}
	
	public void endRound() {
		
	}
	
	public void clearCenterpile(int t) {
		for (int i=centerpile.size()-1; i>=0; i--) {
			if (centerpile.get(i) == t || centerpile.get(i) == 5) {
				players.get(0).getHand().add(centerpile.remove(i));
			}
		}
	}
	
	public void clearFactory(Factory f, int t) {
		for (int i=f.getFactoryTiles().size()-1; i>=0; i--) {
			if (f.getFactoryTiles().get(i) == t) {
				players.get(0).getHand().add(f.getFactoryTiles().remove(i));
			} else {
				centerpile.add(f.getFactoryTiles().remove(i));
			}
		}
	}
	
	public void fillBag() {
		 /* for(int i=0; i<5; i++) {
	            for (int j=0; j<20; j++) {
	                bagOfTiles.add(i);
	            }
	       }
	       Collections.shuffle(bagOfTiles); */
		for(int i=0; i<5; i++) {
			for (int j=0; j<20; j++) {
				bagOfTiles.add(i);
			}
		}
		Collections.shuffle(bagOfTiles); 
	} 
	
	public void clearFloorLine() {
		if (players.get(0).getExtraTiles().size() == 1) {
			players.get(0).addScore(-1);
		} else if (players.get(0).getExtraTiles().size() == 2) {
			players.get(0).addScore(-2);
		} else if (players.get(0).getExtraTiles().size() == 3) {
			players.get(0).addScore(-4);
		} else if (players.get(0).getExtraTiles().size() == 4) {
			players.get(0).addScore(-6);
		} else if (players.get(0).getExtraTiles().size() == 5) {
			players.get(0).addScore(-8);
		} else if (players.get(0).getExtraTiles().size() == 6) {
			players.get(0).addScore(-11);
		} else if (players.get(0).getExtraTiles().size() >= 7) {
			players.get(0).addScore(-14);
		}
		if (players.get(0).getExtraTiles().contains(5)) {
			nextPlayerID = players.get(0).getPlayerID();
		}
		int a = players.get(0).getExtraTiles().size();
		for (int i=0; i<a; i++) {
			
			if (players.get(0).getExtraTiles().get(0) == 5) {
				System.out.println("a");
				centerpile.add(players.get(0).getExtraTiles().remove(0));
			} else {
				System.out.println("b");
				discard.add(players.get(0).getExtraTiles().remove(0));
			}
			
		}
		if (players.get(0).getScore() <= 0) {
			players.get(0).setScore(0);
		}
	}
	
	public void newRound() {

		fillFactories();
		if (nextPlayerID == 1) {
			while (players.get(0).getPlayerID() != 1) {
				Collections.rotate(players, 1);
			}
		} else if (nextPlayerID == 2) {
			while (players.get(0).getPlayerID() != 2) {
				Collections.rotate(players, 1);
			}
		} else if (nextPlayerID == 3) {
			while (players.get(0).getPlayerID() != 3) {
				Collections.rotate(players, 1);
			}
		} else if (nextPlayerID == 4) {
			while (players.get(0).getPlayerID() != 4) {
				Collections.rotate(players, 1);
			}
		}
		int a = 0; int b = 0; int c = 0; int d = 0; int e = 0;
		
		for (int i=0; i<9; i++) {
			for (int j=0; j<factories.get(i).getFactoryTiles().size(); j++) {
				if (factories.get(i).getFactoryTiles().get(j) == 0) {
					a++;
				} else if (factories.get(i).getFactoryTiles().get(j) == 1) {
					b++;
				} else if (factories.get(i).getFactoryTiles().get(j) == 3) {
					d++;
				} else if (factories.get(i).getFactoryTiles().get(j) == 4) {
					e++;
				} else if (factories.get(i).getFactoryTiles().get(j) == 2) {
					c++;
				}
			}
		}
		for (int i=0; i<centerpile.size(); i++) {
			if (centerpile.get(i) == 0) {
				a++;
			} else if (centerpile.get(i) == 1) {
				b++;
			} else if (centerpile.get(i) == 2) {
				c++;
			} else if (centerpile.get(i) == 3) {
				d++;
			} else if (centerpile.get(i) == 4) {
				e++;
			} 
		} 
		for (int i=0; i<discard.size(); i++) {
			if (discard.get(i) == 0) {
				a++;
			} else if (discard.get(i) == 1) {
				b++;
			} else if (discard.get(i) == 2) {
				c++;
			} else if (discard.get(i) == 3) {
				d++;
			} else if (discard.get(i) == 4) {
				e++;
			} 
		}  
		for (int i=0; i<bagOfTiles.size(); i++) {
			if (bagOfTiles.get(i) == 0) {
				a++;
			} else if (bagOfTiles.get(i) == 1) {
				b++;
			} else if (bagOfTiles.get(i) == 2) {
				c++;
			} else if (bagOfTiles.get(i) == 3) {
				d++;
			} else if (bagOfTiles.get(i) == 4) {
				e++;
			} 
		} 
		System.out.println("black" + a + "cyan" + b + "ice" + c + "orange" + d + "red" + e);
	}
	
	public int getCurrentRow() {
		
		if (players.get(0).getRow(1).size() == 1) {
			return 1;
		} else if (players.get(0).getRow(2).size() == 2) {
			return 2;
		} else if (players.get(0).getRow(3).size() == 3) {
			return 3;
		} else if (players.get(0).getRow(4).size() == 4) {
			return 4;
		} else if (players.get(0).getRow(5).size() == 5) {
			return 5;
		} else if (players.get(0).getExtraTiles().size() > 0) {
			return 6;
		} else if (players.get(0).ready() && players.get(1).ready() && players.get(2).ready() && players.get(3).ready()) {
			return 8;
		} else {
			return 7;
		}
		
		
	}
	
	
	public void refillBag() {
		System.out.println("UR REFILLING THE BAG");
		int a = discard.size();
		for (int i=0; i<a; i++) {
			bagOfTiles.add(discard.remove(0));
		}
		if (!discard.isEmpty()) {
			System.out.println("DICARD ISNT EMPTY");
		}
	}
	
	public void fillFactories() {
		
		Collections.shuffle(bagOfTiles);

        /* for (int k=0; k<4; k++) {
            for (int i=0; i<9; i++) {
                if (bagOfTiles.size() < 5 ) {
                    fillBag();
                    Collections.shuffle(bagOfTiles);
                } 
                System.out.println(bagOfTiles);
                factories.get(i).getFactoryTiles().add(bagOfTiles.remove(0));
            }
        } */
        
		Collections.shuffle(bagOfTiles);
		
		for (int i=0; i<9; i++) {
			for (int k=0; k<4; k++) {
				if (bagOfTiles.size() == 0) {
					refillBag();
					Collections.shuffle(bagOfTiles);
					
				} 	
				if (bagOfTiles.size() > 0) factories.get(i).getFactoryTiles().add(bagOfTiles.remove(0));		
			}				
		}
		
	}

	public ArrayList<GameBoard> getPlayers() {
		return players;
	}
	
	public void setupRound() {
		Collections.shuffle(bagOfTiles);
		if (bagOfTiles.size() == 0) {
			refillBag();
		}
	}
	
	public ArrayList<Factory> getFactories() {
		return factories;
	}
	
	public int getColorCntCP(int k) {
		int x = 0;
		for (int i=0; i<centerpile.size(); i++) {
			if (centerpile.get(i) == k) {
				x++;
			}
		}
		return x;
	}
	
	public int getColorCntDiscard(int k) {
		int x = 0;
		for (int i=0; i<discard.size(); i++) {
			if (discard.get(i) == k) {
				x++;
			}
		}
		return x;
	}
	
	public int getColorCntBag(int k) {
		int x = 0;
		for (int i=0; i<bagOfTiles.size(); i++) {
			if (bagOfTiles.get(i) == k) {
				x++;
			}
		}
		return x;
	}
	
	public boolean checkWall() {
		
		for (int i=0; i<players.size(); i++) {
			if (players.get(i).checkWall()) {
				return true;
			}
		}
		return false;
	}
	
	public int getBonusCnt() {
		return bonusCnt;
	}
	
	public void addBonuses() {
		players.get(0).addBonuses();
		bonusCnt++;
	}
	
	public void findWinner() {
		int s1 = players.get(0).getScore();
		int s2 = players.get(1).getScore();
		int s3 = players.get(2).getScore();
		int s4 = players.get(3).getScore();
		int max = Math.max(s1, s2);
		max = Math.max(max, s3);
		max = Math.max(max, s4);
		if (max == s1) {
			winner.add(players.get(0).getPlayerID());
			winner.add(players.get(0).getScore());
			max = Math.max(s2, s3);
			max = Math.max(max, s4);
			if (max == s2) {
				winner.add(players.get(1).getPlayerID());
				winner.add(players.get(1).getScore());
				max = Math.max(s3, s4);
				if (max == s3) {
					winner.add(players.get(2).getPlayerID());
					winner.add(players.get(2).getScore());
					winner.add(players.get(3).getPlayerID());
					winner.add(players.get(3).getScore());
				}
				if (max == s4) {
					winner.add(players.get(3).getPlayerID());
					winner.add(players.get(3).getScore());
					winner.add(players.get(2).getPlayerID());
					winner.add(players.get(2).getScore());
				}
			}
			if (max == s3) {
				winner.add(players.get(2).getPlayerID());
				winner.add(players.get(2).getScore());
				max = Math.max(s2, s4);
				if (max == s2) {
					winner.add(players.get(1).getPlayerID());
					winner.add(players.get(1).getScore());
					winner.add(players.get(3).getPlayerID());
					winner.add(players.get(3).getScore());
				}
				if (max == s4) {
					winner.add(players.get(3).getPlayerID());
					winner.add(players.get(3).getScore());
					winner.add(players.get(1).getPlayerID());
					winner.add(players.get(1).getScore());
				}
			}
			if (max == s4) {
				winner.add(players.get(3).getPlayerID());
				winner.add(players.get(3).getScore());
				max = Math.max(s2, s3);
				if (max == s3) {
					winner.add(players.get(2).getPlayerID());
					winner.add(players.get(2).getScore());
					winner.add(players.get(1).getPlayerID());
					winner.add(players.get(1).getScore());
				}
				if (max == s2) {
					winner.add(players.get(1).getPlayerID());
					winner.add(players.get(1).getScore());
					winner.add(players.get(2).getPlayerID());
					winner.add(players.get(2).getScore());
				}
			}
		}
		if (max == s2) {
			winner.add(players.get(1).getPlayerID());
			winner.add(players.get(1).getScore());
			max = Math.max(s1, s3);
			max = Math.max(max, s4);
			if (max == s1) {
				winner.add(players.get(0).getPlayerID());
				winner.add(players.get(0).getScore());
				max = Math.max(s3, s4);
				if (max == s3) {
					winner.add(players.get(2).getPlayerID());
					winner.add(players.get(2).getScore());
					winner.add(players.get(3).getPlayerID());
					winner.add(players.get(3).getScore());
				}
				if (max == s4) {
					winner.add(players.get(3).getPlayerID());
					winner.add(players.get(3).getScore());
					winner.add(players.get(2).getPlayerID());
					winner.add(players.get(2).getScore());
				}
			}
			if (max == s3) {
				winner.add(players.get(2).getPlayerID());
				winner.add(players.get(2).getScore());
				max = Math.max(s1, s4);
				if (max == s1) {
					winner.add(players.get(0).getPlayerID());
					winner.add(players.get(0).getScore());
					winner.add(players.get(3).getPlayerID());
					winner.add(players.get(3).getScore());
				}
				if (max == s4) {
					winner.add(players.get(3).getPlayerID());
					winner.add(players.get(3).getScore());
					winner.add(players.get(0).getPlayerID());
					winner.add(players.get(0).getScore());
				}
			}
			if (max == s4) {
				winner.add(players.get(3).getPlayerID());
				winner.add(players.get(3).getScore());
				max = Math.max(s1, s3);
				if (max == s3) {
					winner.add(players.get(2).getPlayerID());
					winner.add(players.get(2).getScore());
					winner.add(players.get(0).getPlayerID());
					winner.add(players.get(0).getScore());
				}
				if (max == s1) {
					winner.add(players.get(0).getPlayerID());
					winner.add(players.get(0).getScore());
					winner.add(players.get(2).getPlayerID());
					winner.add(players.get(2).getScore());
				}
			}
		}
		if (max == s3) {
			winner.add(players.get(2).getPlayerID());
			winner.add(players.get(2).getScore());
			max = Math.max(s2, s1);
			max = Math.max(max, s4);
			if (max == s2) {
				winner.add(players.get(1).getPlayerID());
				winner.add(players.get(1).getScore());
				max = Math.max(s1, s4);
				if (max == s1) {
					winner.add(players.get(0).getPlayerID());
					winner.add(players.get(0).getScore());
					winner.add(players.get(3).getPlayerID());
					winner.add(players.get(3).getScore());
				}
				if (max == s4) {
					winner.add(players.get(3).getPlayerID());
					winner.add(players.get(3).getScore());
					winner.add(players.get(0).getPlayerID());
					winner.add(players.get(0).getScore());
				}
			}
			if (max == s1) {
				winner.add(players.get(0).getPlayerID());
				winner.add(players.get(0).getScore());
				max = Math.max(s2, s4);
				if (max == s2) {
					winner.add(players.get(1).getPlayerID());
					winner.add(players.get(1).getScore());
					winner.add(players.get(3).getPlayerID());
					winner.add(players.get(3).getScore());
				}
				if (max == s4) {
					winner.add(players.get(3).getPlayerID());
					winner.add(players.get(1).getPlayerID());
					winner.add(players.get(1).getScore());
					winner.add(players.get(3).getScore());
				}
			}
			if (max == s4) {
				winner.add(players.get(3).getPlayerID());
				winner.add(players.get(3).getScore());
				max = Math.max(s2, s1);
				if (max == s1) {
					winner.add(players.get(0).getPlayerID());
					winner.add(players.get(0).getScore());
					winner.add(players.get(1).getPlayerID());
					winner.add(players.get(1).getScore());
				}
				if (max == s2) {
					winner.add(players.get(1).getPlayerID());
					winner.add(players.get(1).getScore());
					winner.add(players.get(0).getPlayerID());
					winner.add(players.get(0).getScore());
				}
			}
		}
		if (max == s4) {
			winner.add(players.get(3).getPlayerID());
			winner.add(players.get(3).getScore());
			max = Math.max(s2, s3);
			max = Math.max(max, s1);
			if (max == s2) {
				winner.add(players.get(1).getPlayerID());
				winner.add(players.get(1).getScore());
				max = Math.max(s3, s1);
				if (max == s3) {
					winner.add(players.get(2).getPlayerID());
					winner.add(players.get(2).getScore());
					winner.add(players.get(0).getPlayerID());
					winner.add(players.get(0).getScore());
				}
				if (max == s1) {
					winner.add(players.get(0).getPlayerID());
					winner.add(players.get(0).getScore());
					winner.add(players.get(2).getPlayerID());
					winner.add(players.get(2).getScore());
				}
			}
			if (max == s3) {
				winner.add(players.get(2).getPlayerID());
				max = Math.max(s2, s1);
				if (max == s2) {
					winner.add(players.get(1).getPlayerID());
					winner.add(players.get(1).getScore());
					winner.add(players.get(0).getPlayerID());
					winner.add(players.get(0).getScore());
				}
				if (max == s1) {
					winner.add(players.get(0).getPlayerID());
					winner.add(players.get(0).getScore());
					winner.add(players.get(1).getPlayerID());
					winner.add(players.get(1).getScore());
				}
			}
			if (max == s1) {
				winner.add(players.get(0).getPlayerID());
				winner.add(players.get(0).getScore());
				max = Math.max(s2, s3);
				if (max == s3) {
					winner.add(players.get(2).getPlayerID());
					winner.add(players.get(2).getScore());
					winner.add(players.get(1).getPlayerID());
					winner.add(players.get(1).getScore());
				}
				if (max == s2) {
					winner.add(players.get(1).getPlayerID());
					winner.add(players.get(1).getScore());
					winner.add(players.get(2).getPlayerID());
					winner.add(players.get(2).getScore());
				}
			}
		}
	}
	
	public ArrayList<Integer> getCenterpile() {
		return centerpile;
	}
	
	public ArrayList<Integer> getWinner() {
		return winner;
	}
	
	public void incBonusNum() {
		bonusCnt++;
	}
	
	public ArrayList<Integer> getDiscard()  {
		return discard;
	}
	
	public boolean isRoundOver() {
		return this.roundIsOver;
	}
	
	public ArrayList<Integer> getBag() {
		return bagOfTiles;
	}
}
