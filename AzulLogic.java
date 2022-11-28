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
	
	public void addTileToWall(ArrayList<Integer> row) {
		int t = row.get(0);
		
		for (int c=0; c<row.size()-1; c++) {
			discard.add(t);
		}
		int r = players.get(0).getIntRow(row);
		row.clear();
	
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
					scoreRow(0,3);
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
	
	public int scoreRow(int x, int y) {
		int s = 1;
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
		for(int i=0; i<5; i++) {
			for (int j=0; j<20; j++) {
				bagOfTiles.add(i);
			}
		}
		Collections.shuffle(bagOfTiles);
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
		} else {
			return 6;
		}
	}
	
	public void refillBag() {
		for (int i=0; i<discard.size(); i++) {
			bagOfTiles.add(discard.remove(0));
		}
	}
	
	public void fillFactories() {
		Collections.shuffle(bagOfTiles);
		for (int k=0; k<4; k++) {
			for (int i=0; i<9; i++) {
				if (bagOfTiles.size() == 0) {
					refillBag();
				} 
				factories.get(i).getFactoryTiles().add(bagOfTiles.remove(0));
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
	
	public ArrayList<Integer> getCenterpile() {
		return centerpile;
	}
	
	public boolean isRoundOver() {
		return this.roundIsOver;
	}
}
