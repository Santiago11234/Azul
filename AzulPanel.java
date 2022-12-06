import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import java.lang.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;
import java.awt.Graphics2D;

public class AzulPanel extends JPanel implements MouseListener {

	private BufferedImage azulBoard, blackTile, cyanTile, factory, iceTile, oneTile, orangeTile, redTile, woodBackground, homeScreen, wallPaper, bag, bag2;
	private GameState gs = GameState.HOME;
	private AzulLogic AL;
	private ArrayList<Factory> factories;
	private ArrayList<GameBoard> players;

	
	public AzulPanel() {
		
		try {
			azulBoard = ImageIO.read(AzulPanel.class.getResource("/Images/AzulBoard.jpg"));
			blackTile = ImageIO.read(AzulPanel.class.getResource("/Images/BlackTile.png"));
			cyanTile = ImageIO.read(AzulPanel.class.getResource("/Images/CyanTile.png"));
			factory = ImageIO.read(AzulPanel.class.getResource("/Images/Factory.png"));
			iceTile = ImageIO.read(AzulPanel.class.getResource("/Images/IceTile.png"));
			oneTile = ImageIO.read(AzulPanel.class.getResource("/Images/OneTile.png"));
			orangeTile = ImageIO.read(AzulPanel.class.getResource("/Images/orangeTile.png"));
			redTile = ImageIO.read(AzulPanel.class.getResource("/Images/redTile.png"));
			woodBackground = ImageIO.read(AzulPanel.class.getResource("/Images/woodBackground.jpg"));
			homeScreen = ImageIO.read(AzulPanel.class.getResource("/Images/HomeScreen.png"));
			wallPaper = ImageIO.read(AzulPanel.class.getResource("/Images/wallPaper.jpg"));
			bag = ImageIO.read(AzulPanel.class.getResource("/Images/Bag.png"));
			bag2 = ImageIO.read(AzulPanel.class.getResource("/Images/Bag2.png"));
		}
        catch(Exception E){
            System.out.println("Exception Error");
            return;
        }
		AL = new AzulLogic();
		factories = AL.getFactories();
		players = AL.getPlayers();

		addMouseListener(this);
	}
	
	public void paint(Graphics g) {
		// super.paint(g);

		if (gs != GameState.HOME) g.drawImage(woodBackground, 0, 0,getWidth(), getHeight(), null);
		g.setColor(new Color(255,255,255,127));
		
		if (gs == GameState.PLAY) {						
			drawPlay(g);
		} else if (isViewingFactory()) {
			drawViewingFactory(g);
		} else if (gs == GameState.VIEWCENTERPILE) {
			drawViewingCenterpile(g);
		} else if (gs == GameState.ADDTILETOSTAIRCASE) {
			drawAddTilesToStairCase(g);
		} else if (gs == GameState.ENDTURN) {
			drawEndTurn(g);
		} else if (gs == GameState.VIEWOTHERGAMEBOARDS) {
			drawViewingOtherGameBoards(g);
		} else if (gs == GameState.CLICKTOCONTINUE) {
			drawClickToContinue(g);
		} else if (gs == GameState.HOME) {
			drawHome(g);
		} else if (gs == GameState.OVER) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0,getWidth(), getHeight());
			g.setFont(new Font("Calibri", Font.BOLD, 100)); 
			g.drawString("PLAYER[s] " + AL.getWinner() + " WINS THE GAME!", 100, 100);
		} else if (gs == GameState.VIEWBAG) {
			drawViewBag(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		System.out.println("(" + x + ", " + y + ")");
		if (gs == GameState.PLAY) {
			if (Math.pow((x-805),2) + Math.pow((y-69),2) <= 4900) {
				gs = GameState.VIEWFACTORY1;
			} else if (Math.pow((x-1025),2) + Math.pow((y-69),2) <= 4900) {
				gs = GameState.VIEWFACTORY2;
			} else if (Math.pow((x-660),2) + Math.pow((y-200),2) <= 4900) {
				gs = GameState.VIEWFACTORY9;
			} else if (Math.pow((x-1180),2) + Math.pow((y-200),2) <= 4900) {
				gs = GameState.VIEWFACTORY3;
			} else if (Math.pow((x-620),2) + Math.pow((y-375),2) <= 4900) {
				gs = GameState.VIEWFACTORY8;
			} else if (Math.pow((x-1190),2) + Math.pow((y-375),2) <= 4900) {
				gs = GameState.VIEWFACTORY4;
			} else if (Math.pow((x-720),2) + Math.pow((y-535),2) <= 4900) {
				gs = GameState.VIEWFACTORY7;
			} else if (Math.pow((x-1100),2) + Math.pow((y-535),2) <= 4900) {
				gs = GameState.VIEWFACTORY5;
			} else if (Math.pow((x-915),2) + Math.pow((y-580),2) <= 4900) {
				gs = GameState.VIEWFACTORY6;
			} else if (Math.pow((x-910),2) + Math.pow((y-320),2) <= 16900) {
				gs = GameState.VIEWCENTERPILE;
			} else if (x>=50 && x<=200 && y>=0 && y<= 69) { 
				gs = GameState.VIEWOTHERGAMEBOARDS;
			} else if (x>=1130 && x<= 1280 && y>=-10 && y<=140) { // g.drawImage(bag,1130,-10,150,150,null);
				gs = GameState.VIEWBAG;
			}
		} else if (isViewingFactory()) {
			if (x>=804 && x<=1044 && y>=12 && y<=86) {
				gs = GameState.PLAY;
			} else if (x>=644 && x<=752 && y>=554 && y<=620) {
				if (!factories.get(this.getFactoryNum()-1).getFactoryTiles().contains(0)) return;
				AL.clearFactory(factories.get(this.getFactoryNum()-1),0);
				gs = GameState.ADDTILETOSTAIRCASE;
			} else if (x>=752 && x<=860 && y>=554 && y<=620) {
				if (!factories.get(this.getFactoryNum()-1).getFactoryTiles().contains(1)) return;
				AL.clearFactory(factories.get(this.getFactoryNum()-1),1);
				gs = GameState.ADDTILETOSTAIRCASE;
			} else if (x>=861 && x<=969 && y>=554 && y<=620) {
				if (!factories.get(this.getFactoryNum()-1).getFactoryTiles().contains(2)) return;
				AL.clearFactory(factories.get(this.getFactoryNum()-1),2);
				gs = GameState.ADDTILETOSTAIRCASE;
			} else if (x>=970 && x<=1078 && y>=554 && y<=620) {
				if (!factories.get(this.getFactoryNum()-1).getFactoryTiles().contains(3)) return;
				AL.clearFactory(factories.get(this.getFactoryNum()-1),3);
				gs = GameState.ADDTILETOSTAIRCASE;
			} else if (x>=1078 && x<=1186 && y>=554 && y<=620) {
				if (!factories.get(this.getFactoryNum()-1).getFactoryTiles().contains(4)) return;
				AL.clearFactory(factories.get(this.getFactoryNum()-1),4);
				gs = GameState.ADDTILETOSTAIRCASE;
			}
			
		} else if (gs == GameState.VIEWCENTERPILE) {
			if (x>=804 && x<=1044 && y>=12 && y<=86) {
				gs = GameState.PLAY;
			} else if (x>=644 && x<=752 && y>=554 && y<=620) {
				if (!AL.getCenterpile().contains(0)) return;
				AL.clearCenterpile(0);
				gs = GameState.ADDTILETOSTAIRCASE;
			} else if (x>=752 && x<=860 && y>=554 && y<=620) {
				if (!AL.getCenterpile().contains(1)) return;
				AL.clearCenterpile(1);
				gs = GameState.ADDTILETOSTAIRCASE;
			} else if (x>=861 && x<=969 && y>=554 && y<=620) {
				if (!AL.getCenterpile().contains(2)) return;
				AL.clearCenterpile(2);
				gs = GameState.ADDTILETOSTAIRCASE;
			} else if (x>=970 && x<=1078 && y>=554 && y<=620) {
				if (!AL.getCenterpile().contains(3)) return;
				AL.clearCenterpile(3);
				gs = GameState.ADDTILETOSTAIRCASE;
			} else if (x>=1078 && x<=1186 && y>=554 && y<=620) {
				if (!AL.getCenterpile().contains(4)) return;
				AL.clearCenterpile(4);
				gs = GameState.ADDTILETOSTAIRCASE;
			}
		} else if (gs == GameState.ADDTILETOSTAIRCASE) {
			if (x>= 557 && x<= 1267 && y>= 130 && y<=250) {

				if (x>=556 && x< 556 + 142) {
					if (!players.get(0).canPlaceTileInRow(1)) return;
					players.get(0).addTilesToRow(1);
				} else if (x>=556+142 && x< 556+142+142) {
					if (!players.get(0).canPlaceTileInRow(2)) return;
					players.get(0).addTilesToRow(2);
				} else if (x>=556+142+142 && x< 556+142+142+142) {
					if (!players.get(0).canPlaceTileInRow(3)) return;
					players.get(0).addTilesToRow(3);
				} else if (x>=556+142+142+142 && x< 556+142+142+142+142) {
					if (!players.get(0).canPlaceTileInRow(4)) return;
					players.get(0).addTilesToRow(4);
				} else if (x>=556+142+142+142+142 && x< 556+142+142+142+142+142) {
					if (!players.get(0).canPlaceTileInRow(5)) return;
					players.get(0).addTilesToRow(5);
				}
				
				gs = GameState.ENDTURN;
				
			}
			
		} else if (gs == GameState.ENDTURN) {
			
			if (x>= 280 && x <= 480 && y >= 0 && y <= 69) {
				// rando shit
				if (factories.get(0).getFactoryTiles().isEmpty() && factories.get(1).getFactoryTiles().isEmpty() && 
						factories.get(2).getFactoryTiles().isEmpty() && factories.get(3).getFactoryTiles().isEmpty() && 
						factories.get(4).getFactoryTiles().isEmpty() && factories.get(5).getFactoryTiles().isEmpty() && 
						factories.get(6).getFactoryTiles().isEmpty() && factories.get(7).getFactoryTiles().isEmpty() && 
						factories.get(8).getFactoryTiles().isEmpty() && (AL.getCenterpile().size() == 0)) {
					AL.endTurn();
					gs = GameState.CLICKTOCONTINUE;
				} else {
					AL.endTurn();
					gs = GameState.PLAY;
				}

				
			}
		} else if (gs == GameState.VIEWOTHERGAMEBOARDS) {
			if (x>=50 && x<=250 && y>=2 && y<=62) {
				gs = GameState.PLAY;
			}
		} else if (gs == GameState.CLICKTOCONTINUE) {
			if (x>=280 && x<=480 && y>=0 && y<=69) { 
				// logic: row 1 to change, discard change, wall change, etc...
				// graphic: row 1 to change, wall change
				if (AL.getCurrentRow() == 1) {
					AL.addTileToWall(1);
				} else if (AL.getCurrentRow() == 2) {
					AL.addTileToWall(2);
				} else if (AL.getCurrentRow() == 3) {
					AL.addTileToWall(3);
				} else if (AL.getCurrentRow() == 4) {
					AL.addTileToWall(4);
				} else if (AL.getCurrentRow() == 5) {
					AL.addTileToWall(5);
				} else if (AL.getCurrentRow() == 6) {
					AL.clearFloorLine();
				} else if (AL.getCurrentRow() == 7) {
					Collections.rotate(players,3);
				} else if (AL.getCurrentRow() == 8) {
					if (AL.checkWall() == true) {
						AL.addBonuses();
						if (AL.getBonusCnt() == 4) {
							AL.findWinner();
							gs = GameState.OVER;
						} else {
							Collections.rotate(players,3);
						}

					} else {
						AL.newRound();
						gs = GameState.PLAY;
					}
				}
			}
		} else if (gs == GameState.HOME) { // g.drawRect(458, 524, 370, 97);
			if (x >= 458 && x<= 828 && y>=524 && y<= 621) {
				gs = GameState.PLAY;
			}
		} else if (gs == GameState.VIEWBAG) {
			if (x>=501 && x<= 781 && y>=2 && y<= 58) {
				gs = GameState.PLAY;
			}				
		}
		repaint();
	}
			
	public void drawPlay(Graphics g) {
		drawBoard(g);
		// VIEW BUTTON
		g.setColor(new Color(255,255,255,127));
		g.fillRect(50,0,200,69); 
		g.setColor(Color.BLACK);
		g.drawRect(50, 0, 200, 69);
		g.setFont(new Font("Calibri", Font.BOLD, 40)); 
		g.drawString("VIEW", 95, 50); // view button
		// FACTORIES 
		g.drawImage(factory,735,0,140,140,null); // FACTORY 1, INCREASES BY GOING CLOCKWISE;
		g.drawImage(factory,955,0,140,140,null);
		g.drawImage(factory,590,130,140,140,null);
		g.drawImage(factory,1110,130,140,140,null);
		g.drawImage(factory,550,305,140,140,null);
		g.drawImage(factory,1120,305,140,140,null);
		g.drawImage(factory,650,465,140,140,null);			
		g.drawImage(factory,1030,465,140,140,null);
		g.drawImage(factory,845,510,140,140,null);		
		g.setColor(new Color(255,0,0,127));		
		g.fillOval(780, 190, 260, 260);
		g.setColor(new Color(0,0,0,255));
		g.setFont(new Font("Calibri", Font.BOLD, 25)); 
		g.drawString("CENTERPILE", 840, 330);
		g.drawImage(bag,1130,-10,150,150,null);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.BOLD, 20)); 
		g.drawString("TILES: " + AL.getBag().size(), 1170,75);
		g.setFont(new Font("Calibri", Font.BOLD, 25)); 
		
		g.setColor(new Color(0,0,0,127));
		if (factories.get(0).isEmpty()) {
			g.fillOval(735, 0, 140, 140);
		}
		if (factories.get(1).isEmpty()) {
			g.fillOval(955, 0, 140, 140);
		}
		if (factories.get(2).isEmpty()) {
			g.fillOval(1110, 130, 140, 140);
		}
		if (factories.get(3).isEmpty()) {
			g.fillOval(1120, 305, 140, 140);
		}
		if (factories.get(4).isEmpty()) {
			g.fillOval(1030, 465, 140, 140);
		}		
		if (factories.get(5).isEmpty()) {
			g.fillOval(845, 510, 140, 140);
		}
		if (factories.get(6).isEmpty()) {
			g.fillOval(650, 465, 140, 140);
		}
		if (factories.get(7).isEmpty()) {
			g.fillOval(550, 305, 140, 140);
		}
		if (factories.get(8).isEmpty()) {
			g.fillOval(590, 130, 140, 140);
		}		
		if (AL.getCenterpile().isEmpty()) {
			g.fillOval(780, 190, 260, 260);
		}

		g.setColor(Color.BLACK);
	}

	public void drawViewingFactory(Graphics g) {
		drawBoard(g);
		g.drawImage(factory,700,90,450,450,null);
		g.setColor(new Color(255,255,255,127));
		g.fillRect(804, 12, 240, 74);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.BOLD, 55)); 
		g.drawString("BACK", 850, 65);
		g.drawRect(803, 11, 241, 75);
		if(factories.get(getFactoryNum()-1).getFactoryTiles().size()>0) {
			g.drawImage(getTileImage(factories.get(getFactoryNum()-1).getFactoryTiles().get(0)),784,184,130,130,null);
			if(factories.get(getFactoryNum()-1).getFactoryTiles().size()>1) {
				g.drawImage(getTileImage(factories.get(getFactoryNum()-1).getFactoryTiles().get(1)),915,184,130,130,null);
				if (factories.get(getFactoryNum()-1).getFactoryTiles().size()>2) {
					g.drawImage(getTileImage(factories.get(getFactoryNum()-1).getFactoryTiles().get(2)),784,315,130,130,null);
					if (factories.get(getFactoryNum()-1).getFactoryTiles().size()>3) {
						g.drawImage(getTileImage(factories.get(getFactoryNum()-1).getFactoryTiles().get(3)),915,315,130,130,null);
					}
				}
			}
		}
		g.setColor(new Color(255,255,255,127));
		g.fillRect(645, 555, 540, 65);
		g.setColor(new Color(0,100,255,255));
		g.fillRect(573, 510, 70, 140);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.BOLD, 40));
		g.drawString("T", 590, 540);
		g.drawString("A", 590, 570);
		g.drawString("K", 590, 605);
		g.drawString("E", 590, 640);
		g.drawRect(572,510,72,142);
		g.drawRect(644, 554, 108, 66);		
		g.drawRect(752, 554, 108, 66);
		g.drawRect(861, 554, 108, 66);
		g.drawRect(970, 554, 108, 66);
		g.drawRect(1078, 554, 108, 66);
		
		
		g.setColor(new Color(255,255,255,127));
		g.fillRect(624, 184, 60, 260);
		g.setColor(Color.BLACK);
		g.drawImage(blackTile,680,570,36,36,null);
		g.drawImage(cyanTile,788,570,36,36,null);
		g.drawImage(iceTile,896,570,36,36,null);
		g.drawImage(orangeTile,1006,570,36,36,null);
		g.drawImage(redTile,1114,570,36,36,null);
		g.setColor(Color.BLACK);
		g.drawRect(624, 184, 60, 260);
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform defaultAt = g2d.getTransform();
		AffineTransform at = new AffineTransform();
		at.rotate(- Math.PI / 2);
		g2d.setTransform(at);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.BOLD, 75)); 
		g2d.drawString("Factory " + this.getFactoryNum(), -612, 1000);
		g2d.setTransform(defaultAt);
		
		g.setColor(new Color(255,0,0,127));
		if (!AL.getFactories().get(getFactoryNum()-1).getFactoryTiles().contains(0))  {
			g.fillRect(645, 555, 106, 65);
		}
		if (!AL.getFactories().get(getFactoryNum()-1).getFactoryTiles().contains(1)) {
			g.fillRect(753, 555, 106, 65);
		}
		if (!AL.getFactories().get(getFactoryNum()-1).getFactoryTiles().contains(2)) {
			g.fillRect(862, 555, 106, 65);
		}
		if (!AL.getFactories().get(getFactoryNum()-1).getFactoryTiles().contains(3)) {
			g.fillRect(971, 555, 106, 65);
		}
		if (!AL.getFactories().get(getFactoryNum()-1).getFactoryTiles().contains(4)) {
			g.fillRect(1078, 555, 106, 65);
		}

	
		g.setColor(Color.BLACK);
	}
	
	public void drawViewingCenterpile(Graphics g) {
		drawBoard(g);
		g.setColor(new Color(255,0,0,127));	
		g.fillOval(700,90,450,450);
		g.setColor(new Color(255,255,255,127));
		g.fillRect(804, 12, 240, 74);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.BOLD, 55)); 
		g.drawString("BACK", 850, 65);
		g.setColor(new Color(255,255,255,127));
		g.fillRect(645, 555, 540, 65);
		g.setColor(new Color(0,100,255,255)); // blue
		g.fillRect(573, 510, 70, 140);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.BOLD, 40)); 
		g.drawString("T", 590, 540);
		g.drawString("A", 590, 570);
		g.drawString("K", 590, 605);
		g.drawString("E", 590, 640);
		g.drawRect(572,510,72,142);
		g.drawRect(644, 554, 108, 66);		
		g.drawRect(752, 554, 108, 66);
		g.drawRect(861, 554, 108, 66);
		g.drawRect(970, 554, 108, 66);
		g.drawRect(1078, 554, 108, 66);
		g.drawImage(blackTile,680,570,36,36,null);
		g.drawImage(cyanTile,788,570,36,36,null);
		g.drawImage(iceTile,896,570,36,36,null);
		g.drawImage(orangeTile,1006,570,36,36,null);
		g.drawImage(redTile,1114,570,36,36,null);
		
		g.drawImage(blackTile,865,105,80,80,null);
		g.drawImage(cyanTile,865,190,80,80,null);
		g.drawImage(iceTile,865,275,80,80,null);
		g.drawImage(orangeTile,865,370,80,80,null);
		g.drawImage(redTile,865,455,80,80,null);
		
		g.setFont(new Font("Calibri", Font.BOLD, 40)); 
		g.drawString("x" + AL.getColorCntCP(0), 950, 155);
		g.drawString("x" + AL.getColorCntCP(1), 950, 240);
		g.drawString("x" + AL.getColorCntCP(2), 950, 325);
		g.drawString("x" + AL.getColorCntCP(3), 950, 410);
		g.drawString("x" + AL.getColorCntCP(4), 950, 495);
		
		if (AL.getCenterpile().contains(5)) {
			g.drawImage(oneTile,1025,275,80,80,null);
			g.drawRect(644, 488, 108, 66);
			g.setColor(new Color(255,255,255,127));
			g.fillRect(644, 488, 108, 66);	
			g.drawImage(oneTile,680,504,36,36,null);
			g.setColor(new Color(200,200,0,127));
			g.fillRect(644, 488, 108, 66);
		}
		
		g.setColor(new Color(255,0,0,127));
		if (!AL.getCenterpile().contains(0))  {
			g.fillRect(645, 555, 106, 65);
		}
		if (!AL.getCenterpile().contains(1)) {
			g.fillRect(753, 555, 106, 65);
		}
		if (!AL.getCenterpile().contains(2)) {
			g.fillRect(862, 555, 106, 65);
		}
		if (!AL.getCenterpile().contains(3)) {
			g.fillRect(971, 555, 106, 65);
		}
		if (!AL.getCenterpile().contains(4)) {
			g.fillRect(1078, 555, 106, 65);
		}
		g.setColor(Color.BLACK);
	}
	
	public void drawAddTilesToStairCase(Graphics g) {
		drawBoard(g);
		g.setColor(new Color(255,255,255,127));
		g.fillRect(715, 33, 415, 60);
		g.setFont(new Font("Calibri", Font.BOLD, 20)); 
		g.setColor(Color.BLACK);
		g.drawString("SELECT A ROW TO PLACE THE TILES IN", 765, 65);
		g.setColor(new Color(255,255,255,127));
		g.fillRect(557,130,710,120);
		g.setColor(Color.BLACK);
		g.drawRect(556,130,142,120);
		g.drawRect(556+142,130,142,120);
		g.drawRect(556+142+142,130,142,120);
		g.drawRect(556+142+142+142,130,142,120);
		g.drawRect(556+142+142+142+142,130,144,120);
		g.setFont(new Font("Calibri", Font.BOLD, 60)); 
		g.drawString("1", 596, 212);
		g.drawString("2", 596+142, 212);
		g.drawString("3", 586+142+142, 212);
		g.drawString("4", 596+142+142+142, 212);
		g.drawString("5", 596+142+142+142+142, 212);
		g.setFont(new Font("Calibri", Font.BOLD, 30));
		g.drawString("st", 596+30, 182);
		g.drawString("nd", 596+142+30, 182);
		g.drawString("rd", 586+142+142+30, 182);
		g.drawString("th", 596+142+142+142+30, 182);
		g.drawString("th", 596+142+142+142+142+30, 182);
		g.setColor(new Color(255,0,0,127));
		if (!players.get(0).canPlaceTileInRow(1)) {
			g.fillRect(557,131,141,119);
		}
		if (!players.get(0).canPlaceTileInRow(2)) {
			g.fillRect(557+142,131,141,119);
		}
		if (!players.get(0).canPlaceTileInRow(3)) {
			g.fillRect(557+142+142,131,141,119);
		}
		if (!players.get(0).canPlaceTileInRow(4)) {
			g.fillRect(557+142+142+142,131,141,119);
		}
		if (!players.get(0).canPlaceTileInRow(5)) {
			g.fillRect(557+142+142+142+142,131,141,119);
		}
		g.setColor(Color.BLACK);
		for (int n=0; n<players.get(0).getHand().size(); n++) {
			g.drawImage(getTileImage(players.get(0).getHand().get(n)),555+45*n,292,41,41,null);
		}
				
	}
	
	public void drawEndTurn(Graphics g) {
		drawBoard(g);
		g.setColor(new Color(255,255,255,127));
		g.fillRect(280,0,200,69);
		g.setColor(Color.BLACK);
		g.drawRect(280, 0, 200, 69);
		g.setFont(new Font("Calibri", Font.BOLD, 40)); 
		g.drawString("END", 335, 50); // end button
		g.drawString("CLICK THE END BUTTON", 650, 65);
		g.drawString("TO END TURN", 715, 130);
	}
	
	public void drawBoard(Graphics g) {
		// g.fillRect(50,0,200,69); 
		// g.fillRect(280,0,200,69);
		g.setColor(Color.BLACK);
		// g.drawRect(50, 0, 200, 69);
		//g.drawRect(280, 0, 200, 69);
		// g.setFont(new Font("Calibri", Font.BOLD, 40)); 
		// g.setFont(new Font("Calibri", Font.BOLD, 40)); g.drawString("VIEW", 95, 50); // view button
		// g.drawString("END", 335, 50); // end button
		g.drawImage(azulBoard,50,70,null);	//draw main board	
		g.drawRect(50,70,500,507);
		g.setColor(new Color(0,0,0,127));
		g.fillRect(311, 248, 221, 224);
		if (players.get(0).getRow(1).size() > 0) {
			g.drawImage(getTileImage(players.get(0).getRow(1).get(0)),246,249,41,41,null);
		}
		for (int a=0; a<players.get(0).getRow(2).size(); a++) {
			g.drawImage(getTileImage(players.get(0).getRow(2).get(0)),246-45*a,294,41,41,null);
		}
		for (int a=0; a<players.get(0).getRow(3).size(); a++) {
			g.drawImage(getTileImage(players.get(0).getRow(3).get(0)),246-45*a,339,41,41,null);
		}
		for (int a=0; a<players.get(0).getRow(4).size(); a++) {
			g.drawImage(getTileImage(players.get(0).getRow(4).get(0)),246-45*a,384,41,41,null);
		}
		for (int a=0; a<players.get(0).getRow(5).size(); a++) {
			g.drawImage(getTileImage(players.get(0).getRow(5).get(0)),246-45*a,429,41,41,null);
		}
		for (int a=0; a<players.get(0).getExtraTiles().size(); a++) {
			g.drawImage(getTileImage(players.get(0).getExtraTiles().get(a)),68+48*a,507,41,41,null);
		}

		
		g.setColor(new Color(255,255,255,127));
		g.fillRect(2,215,48,205);
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform defaultAt = g2d.getTransform();
		AffineTransform at = new AffineTransform();
		at.rotate(- Math.PI / 2);
		g2d.setTransform(at);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.BOLD, 75)); 
		g2d.drawString("Player #" + players.get(0).getPlayerID(), -625, 58);
		g2d.setTransform(defaultAt);
		g.drawRect(2,215,48,205);
		if (players.get(0).getScore() <= 20 && players.get(0).getScore() >= 1) {
			g.fillOval(75+23*(players.get(0).getScore()-1), 101, 15, 15);
		} else if (players.get(0).getScore() > 20 && players.get(0).getScore() <=40) {
			g.fillOval(75+23*((players.get(0).getScore()-1) - 20), 124, 15, 15);
		} else if (players.get(0).getScore() == 0) {
			g.fillOval(75, 74, 15, 15);
		} else if (players.get(0).getScore() > 40 && players.get(0).getScore() <=60) {
			g.fillOval(75+23*((players.get(0).getScore()-1) - 40), 147, 15, 15);
		} else if (players.get(0).getScore() > 60 && players.get(0).getScore() <=80) {
			g.fillOval(75+23*((players.get(0).getScore()-1) - 60), 170, 15, 15);
		} else if (players.get(0).getScore() > 80 && players.get(0).getScore() <=100) {
			g.fillOval(75+23*((players.get(0).getScore()-1) - 80), 193, 15, 15);
		}
		
		drawWall(g);
		
	}
	
	public void drawViewingOtherGameBoards(Graphics g) {
		g.drawImage(azulBoard, 2, 130, 420, 420, null);
		g.drawImage(azulBoard, 427, 130, 420, 420, null);
		g.drawImage(azulBoard, 2+425+425, 130, 420, 420, null);
		g.setColor(new Color(255,255,255,127));
		g.fillRect(50,2,200,60); 
		g.fillRect(14, 72, 397, 51);
		g.fillRect(434, 72, 397, 51);
		g.fillRect(854, 72, 397, 51);
		g.setColor(new Color(0,0,0,127));
		g.fillRect(219, 278, 189, 187);
		g.fillRect(219+425, 278, 189, 187);
		g.fillRect(219+425+425, 278, 189, 187);
		g.setFont(new Font("Calibri", Font.BOLD, 40));
		g.setColor(Color.BLACK);
		g.drawRect(50,2,200,60); 
		g.drawRect(14, 72, 397, 51);
		g.drawRect(434, 72, 397, 51);
		g.drawRect(854, 72, 397, 51);
		g.drawString("BACK", 100, 45);
		if (players.get(0).getPlayerID() == 1) {
			g.drawString("Player #2's Gameboard",20,110);

			g.drawString("Player #3's Gameboard",440,110);

			g.drawString("Player #4's Gameboard",860,110);

		} else if (players.get(0).getPlayerID() == 2) {

			g.drawString("Player #3's Gameboard",20,110);

			g.drawString("Player #4's Gameboard",440,110);

			g.drawString("Player #1's Gameboard",860,110);

		} else if (players.get(0).getPlayerID() == 3) {
			g.drawString("Player #4's Gameboard",20,110);
			g.drawString("Player #1's Gameboard",440,110);
			g.drawString("Player #2's Gameboard",860,110);
		} else if (players.get(0).getPlayerID() == 4) {
			g.drawString("Player #1's Gameboard",20,110);
			g.drawString("Player #2's Gameboard",440,110);
			g.drawString("Player #3's Gameboard",860,110);
		}
		
		if (players.get(3).getRow(1).size() > 0) {
			g.drawImage(getTileImage(players.get(3).getRow(1).get(0)),167+425+425,277,35,35,null);
		}
		for (int a=0; a<players.get(3).getRow(2).size(); a++) {
			g.drawImage(getTileImage(players.get(3).getRow(2).get(0)),166+425+425-38*a,314,35,35,null);
		}
		for (int b=0; b<players.get(3).getRow(3).size(); b++) {
			g.drawImage(getTileImage(players.get(3).getRow(3).get(0)),166+425+425-38*b,351,35,35,null);
		}
		for (int c=0; c<players.get(3).getRow(4).size(); c++) {
			g.drawImage(getTileImage(players.get(3).getRow(4).get(0)),166+425+425-38*c,388,35,35,null);
		}
		for (int d=0; d<players.get(3).getRow(5).size(); d++) {
			g.drawImage(getTileImage(players.get(3).getRow(5).get(0)),166+425+425-38*d,425,35,35,null);
		}
		
		
		if (players.get(1).getRow(1).size() > 0) {
			g.drawImage(getTileImage(players.get(1).getRow(1).get(0)),167,277,35,35,null);
		}
		for (int a=0; a<players.get(1).getRow(2).size(); a++) {
			g.drawImage(getTileImage(players.get(1).getRow(2).get(0)),166-38*a,314,35,35,null);
		}
		for (int b=0; b<players.get(1).getRow(3).size(); b++) {
			g.drawImage(getTileImage(players.get(1).getRow(3).get(0)),166-38*b,351,35,35,null);
		}
		for (int c=0; c<players.get(1).getRow(4).size(); c++) {
			g.drawImage(getTileImage(players.get(1).getRow(4).get(0)),166-38*c,388,35,35,null);
		}
		for (int d=0; d<players.get(1).getRow(5).size(); d++) {
			g.drawImage(getTileImage(players.get(1).getRow(5).get(0)),166-38*d,425,35,35,null);
		}
		
		if (players.get(2).getRow(1).size() > 0) {
			g.drawImage(getTileImage(players.get(2).getRow(1).get(0)),167+425,277,35,35,null);
		}
		for (int a=0; a<players.get(2).getRow(2).size(); a++) {
			g.drawImage(getTileImage(players.get(2).getRow(2).get(0)),166+425-38*a,314,35,35,null);
		}
		for (int b=0; b<players.get(2).getRow(3).size(); b++) {
			g.drawImage(getTileImage(players.get(2).getRow(3).get(0)),166+425-38*b,351,35,35,null);
		}
		for (int c=0; c<players.get(2).getRow(4).size(); c++) {
			g.drawImage(getTileImage(players.get(2).getRow(4).get(0)),166+425-38*c,388,35,35,null);
		}
		for (int d=0; d<players.get(2).getRow(5).size(); d++) {
			g.drawImage(getTileImage(players.get(2).getRow(5).get(0)),166+425-38*d,425,35,35,null);
		}
		for (int e=0; e<players.get(1).getExtraTiles().size(); e++) {
			g.drawImage(getTileImage(players.get(1).getExtraTiles().get(e)),17+e*40,493,33,33,null);
		}
		for (int f=0; f<players.get(2).getExtraTiles().size(); f++) {
			g.drawImage(getTileImage(players.get(2).getExtraTiles().get(f)),17+425+40*f,493,33,33,null);
		}
		for (int h=0; h<players.get(3).getExtraTiles().size(); h++) {
			g.drawImage(getTileImage(players.get(3).getExtraTiles().get(h)),17+425+425+40*h,493,33,33,null);
		}
		g.setColor(Color.BLACK);
		
		if (players.get(1).getScore() <= 20 && players.get(1).getScore() >= 1) {
			g.fillOval(23+20*(players.get(1).getScore()-1), 153, 10, 10);
		} else if (players.get(1).getScore() > 20 && players.get(1).getScore() <=40) {
			g.fillOval(23+20*(players.get(1).getScore()-21), 175, 10, 10);
		} else if (players.get(1).getScore() == 0) {
			g.fillOval(23,134,10,10);
		} else if (players.get(1).getScore() > 40 && players.get(1).getScore() <=60) {
			g.fillOval(23+20*(players.get(1).getScore()-41), 197, 10, 10);
		} else if (players.get(1).getScore() > 60 && players.get(1).getScore() <=80) {
			g.fillOval(23+20*(players.get(1).getScore()-61), 219, 10, 10);
		} else if (players.get(1).getScore() > 80 && players.get(1).getScore() <=100) {
			g.fillOval(23+20*(players.get(1).getScore()-81), 241, 10, 10);
		}
		
		if (players.get(2).getScore() <= 20 && players.get(2).getScore() >= 1) {
			g.fillOval(23+20*(players.get(2).getScore()-1)+425, 153, 10, 10);
		} else if (players.get(2).getScore() > 20 && players.get(2).getScore() <=40) {
			g.fillOval(23+20*(players.get(2).getScore()-21)+425, 175, 10, 10);
		} else if (players.get(2).getScore() == 0) {
			g.fillOval(23+425,134,10,10);
		} else if (players.get(2).getScore() > 40 && players.get(2).getScore() <=60) {
			g.fillOval(23+20*(players.get(2).getScore()-41)+425, 197, 10, 10);
		} else if (players.get(2).getScore() > 60 && players.get(2).getScore() <=80) {
			g.fillOval(23+20*(players.get(2).getScore()-61)+425, 219, 10, 10);
		} else if (players.get(2).getScore() > 80 && players.get(2).getScore() <=100) {
			g.fillOval(23+20*(players.get(2).getScore()-81)+425, 241, 10, 10);
		}
		
		if (players.get(3).getScore() <= 20 && players.get(3).getScore() >= 1) {
			g.fillOval(23+20*(players.get(3).getScore()-1)+425+425, 153, 10, 10);
		} else if (players.get(3).getScore() > 20 && players.get(3).getScore() <=40) {
			g.fillOval(23+20*(players.get(3).getScore()-21)+425+425, 175, 10, 10);
		} else if (players.get(3).getScore() == 0) {
			g.fillOval(23+425+425,134,10,10);
		} else if (players.get(3).getScore() > 40 && players.get(3).getScore() <=60) {
			g.fillOval(23+20*(players.get(3).getScore()-41)+425+425, 197, 10, 10);
		} else if (players.get(3).getScore() > 60 && players.get(3).getScore() <=80) {
			g.fillOval(23+20*(players.get(3).getScore()-61)+425+425, 219, 10, 10);
		} else if (players.get(3).getScore() > 80 && players.get(3).getScore() <=100) {
			g.fillOval(23+20*(players.get(3).getScore()-81)+425+425, 241, 10, 10);
		}

		drawOtherWalls(g);
		
	}
	
	public void drawOtherWalls(Graphics g) {
		boolean[][] wall = players.get(1).getWall();
		for (int r=0; r<5; r++) {
			for (int c=0; c<5; c++) {
				if (wall[r][c]) {
					if (((r==0)&&(c==0)) || ((r==1)&&(c==1)) || ((r==2)&&(c==2)) || ((r==3)&&(c==3)) || ((r==4)&&(c==4))) {
						g.drawImage(cyanTile, 223+37*c, 279+37*r, 33,33,null);
					} else if (((r==0) && (c==1)) || ((r==1) && (c==2)) || ((r==2) && (c==3)) || ((r==3) && (c==4)) || ((r==4) && (c==0))) {
						g.drawImage(orangeTile, 223+37*c, 279+37*r, 33,33,null);
					} else if (((r==0) && (c==2)) || ((r==1) && (c==3)) || ((r==2) && (c==4)) || ((r==3) && (c==0)) || ((r==4) && (c==1))) {
						g.drawImage(redTile, 223+37*c, 279+37*r, 33,33,null);
					} else if (((r==0) && (c==3)) || ((r==1) && (c==4)) || ((r==2) && (c==0)) || ((r==3) && (c==1)) || ((r==4) && (c==2))) {
						g.drawImage(blackTile, 223+37*c, 279+37*r, 33,33,null);
					} else if (((r==0) && (c==4)) || ((r==1) && (c==0)) || ((r==2) && (c==1)) || ((r==3) && (c==2)) || ((r==4) && (c==3))) {
						g.drawImage(iceTile, 223+37*c, 279+37*r, 33,33,null);
					}
				}
			}
		}
		wall = players.get(2).getWall();
		for (int r=0; r<5; r++) {
			for (int c=0; c<5; c++) {
				if (wall[r][c]) {
					if (((r==0)&&(c==0)) || ((r==1)&&(c==1)) || ((r==2)&&(c==2)) || ((r==3)&&(c==3)) || ((r==4)&&(c==4))) {
						g.drawImage(cyanTile, 223+37*c+425, 279+37*r, 33,33,null);
					} else if (((r==0) && (c==1)) || ((r==1) && (c==2)) || ((r==2) && (c==3)) || ((r==3) && (c==4)) || ((r==4) && (c==0))) {
						g.drawImage(orangeTile, 223+37*c+425, 279+37*r, 33,33,null);
					} else if (((r==0) && (c==2)) || ((r==1) && (c==3)) || ((r==2) && (c==4)) || ((r==3) && (c==0)) || ((r==4) && (c==1))) {
						g.drawImage(redTile, 223+37*c+425, 279+37*r, 33,33,null);
					} else if (((r==0) && (c==3)) || ((r==1) && (c==4)) || ((r==2) && (c==0)) || ((r==3) && (c==1)) || ((r==4) && (c==2))) {
						g.drawImage(blackTile, 223+37*c+425, 279+37*r, 33,33,null);
					} else if (((r==0) && (c==4)) || ((r==1) && (c==0)) || ((r==2) && (c==1)) || ((r==3) && (c==2)) || ((r==4) && (c==3))) {
						g.drawImage(iceTile, 223+37*c+425, 279+37*r, 33,33,null);
					}
				}
			}
		}
		wall = players.get(2).getWall();
		for (int r=0; r<5; r++) {
			for (int c=0; c<5; c++) {
				if (wall[r][c]) {
					if (((r==0)&&(c==0)) || ((r==1)&&(c==1)) || ((r==2)&&(c==2)) || ((r==3)&&(c==3)) || ((r==4)&&(c==4))) {
						g.drawImage(cyanTile, 223+37*c+425+425, 279+37*r, 33,33,null);
					} else if (((r==0) && (c==1)) || ((r==1) && (c==2)) || ((r==2) && (c==3)) || ((r==3) && (c==4)) || ((r==4) && (c==0))) {
						g.drawImage(orangeTile, 223+37*c+425+425, 279+37*r, 33,33,null);
					} else if (((r==0) && (c==2)) || ((r==1) && (c==3)) || ((r==2) && (c==4)) || ((r==3) && (c==0)) || ((r==4) && (c==1))) {
						g.drawImage(redTile, 223+37*c+425+425, 279+37*r, 33,33,null);
					} else if (((r==0) && (c==3)) || ((r==1) && (c==4)) || ((r==2) && (c==0)) || ((r==3) && (c==1)) || ((r==4) && (c==2))) {
						g.drawImage(blackTile, 223+37*c+425+425, 279+37*r, 33,33,null);
					} else if (((r==0) && (c==4)) || ((r==1) && (c==0)) || ((r==2) && (c==1)) || ((r==3) && (c==2)) || ((r==4) && (c==3))) {
						g.drawImage(iceTile, 223+37*c+425+425, 279+37*r, 33,33,null);
					}
				}
			}
		}
	}

	public void drawWall(Graphics g) {
		boolean[][] wall = players.get(0).getWall();
		for (int r=0; r<5; r++) {
			for (int c=0; c<5; c++) {
				if (wall[r][c]) {
					if (((r==0)&&(c==0)) || ((r==1)&&(c==1)) || ((r==2)&&(c==2)) || ((r==3)&&(c==3)) || ((r==4)&&(c==4))) {
						g.drawImage(cyanTile, 312+45*c, 250+45*r, 38,38,null);
					} else if (((r==0) && (c==1)) || ((r==1) && (c==2)) || ((r==2) && (c==3)) || ((r==3) && (c==4)) || ((r==4) && (c==0))) {
						g.drawImage(orangeTile, 312+45*c, 250+45*r, 38,38,null);
					} else if (((r==0) && (c==2)) || ((r==1) && (c==3)) || ((r==2) && (c==4)) || ((r==3) && (c==0)) || ((r==4) && (c==1))) {
						g.drawImage(redTile, 312+45*c, 250+45*r, 38,38,null);
					} else if (((r==0) && (c==3)) || ((r==1) && (c==4)) || ((r==2) && (c==0)) || ((r==3) && (c==1)) || ((r==4) && (c==2))) {
						g.drawImage(blackTile, 312+45*c, 250+45*r, 38,38,null);
					} else if (((r==0) && (c==4)) || ((r==1) && (c==0)) || ((r==2) && (c==1)) || ((r==3) && (c==2)) || ((r==4) && (c==3))) {
						g.drawImage(iceTile, 312+45*c, 250+45*r, 38,38,null);
					}
				}
			}
		}
	}
	
	public void drawClickToContinue(Graphics g) {
		drawBoard(g);
		g.setColor(Color.BLACK);
		g.setColor(new Color(255,255,255,127));
		g.fillRect(280,0,200,69);
		g.setColor(Color.BLACK);
		g.drawRect(280, 0, 200, 69);
		g.setFont(new Font("Calibri", Font.BOLD, 40)); 
		g.drawString("CONTINUE", 285, 50); // end button
		g.drawString("CLICK THE CONTINUE BUTTON", 650, 65);
		if (AL.getCurrentRow() == 8) {
			g.drawString("TO BEGIN THE NEXT ROUND", 690, 130);
		} else {
			if(AL.getCurrentRow() != 6 && AL.getCurrentRow() != 7) {
				g.drawString("TO SCORE ROW " + AL.getCurrentRow(), 690, 130);
			} else if (AL.getCurrentRow() == 7) {
				g.drawString("TO SCORE THE NEXT PLAYER", 640, 130);
			} else if (AL.getCurrentRow() == 6) {
				g.drawString("TO CLEAR FLOOR LINE", 690, 130);
			}
		}
	}
	
	public void drawViewBag(Graphics g) {
		g.drawImage(bag2, 326,13, 624, 624, null);
		
		g.drawImage(blackTile,512,139,80,80,null);
		g.drawImage(cyanTile,512,139+85,80,80,null);
		g.drawImage(iceTile,512,139+85+85,80,80,null);
		g.drawImage(orangeTile,512,139+85+85+85,80,80,null);
		g.drawImage(redTile,512,139+85+85+85+85,80,80,null);
		g.setColor(new Color(255,255,255,127));
		g.fillRect(501, 2, 280, 56);
		g.setFont(new Font("Calibri", Font.BOLD, 40)); 
		g.setColor(Color.BLACK);
		g.drawString("x" + AL.getColorCntBag(0), 512+85, 139+50);
		g.drawString("x" + AL.getColorCntBag(1), 512+85, 139+85+50);
		g.drawString("x" + AL.getColorCntBag(2), 512+85, 139+85+85+50);
		g.drawString("x" + AL.getColorCntBag(3), 512+85, 139+85+85+85+50);
		g.drawString("x" + AL.getColorCntBag(4), 512+85, 139+85+85+85+85+50);
		g.drawRect(501, 2, 280, 56);		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Calibri", Font.BOLD, 45)); 
		g.drawString("BACK", 586, 40);
	}
	
	public void drawHome(Graphics g) {
		g.drawImage(wallPaper, 0, 0,getWidth(), getHeight(), null);
		g.setColor(new Color(0,100,100,127));
		g.fillRect(0, 0,getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.fillRect(326, 13, 636, 636);
		g.drawImage(homeScreen, 338, 24,612, 612, null);
		g.drawRect(458, 524, 370, 97);
	}
	
	enum GameState {

		HOME,
		PLAY,
		VIEWOTHERGAMEBOARDS,
		ENDTURN,
		ADDTILETOSTAIRCASE,
		SCOREROW1,
		SCOREROW2,
		SCOREROW3,
		SCOREROW4,
		SCOREROW5,
		CLICKTOCONTINUE,
		VIEWCENTERPILE,
		VIEWFACTORY1,
		VIEWFACTORY2,
		VIEWFACTORY3,
		VIEWFACTORY4,
		VIEWFACTORY5,
		VIEWFACTORY6,
		VIEWFACTORY7,
		VIEWFACTORY8,
		VIEWFACTORY9,
		ADDBONUSES,
		VIEWBAG,
		OVER;
	}
	
	public int getFactoryNum() {
		if (gs == GameState.VIEWFACTORY1) return 1;
		if (gs == GameState.VIEWFACTORY2) return 2;
		if (gs == GameState.VIEWFACTORY3) return 3;
		if (gs == GameState.VIEWFACTORY4) return 4;
		if (gs == GameState.VIEWFACTORY5) return 5;
		if (gs == GameState.VIEWFACTORY6) return 6;
		if (gs == GameState.VIEWFACTORY7) return 7;
		if (gs == GameState.VIEWFACTORY8) return 8;
		if (gs == GameState.VIEWFACTORY9) return 9;
		return 0;
	}
	
	public BufferedImage getTileImage(int t) {
		if (t >= 0 && t <= 5) {
			
			switch(t) {
			
			case 0: return blackTile;
			case 1: return cyanTile;
			case 2: return iceTile;
			case 3: return orangeTile;
			case 4: return redTile;
			case 5: return oneTile;
						
			}
		
		}
		System.out.println("You are returning a null image in getTileImage() method");
		return null;
	}

	public boolean isViewingFactory() {
		return (gs == GameState.VIEWFACTORY1) || (gs == GameState.VIEWFACTORY2) || (gs == GameState.VIEWFACTORY3) || (gs == GameState.VIEWFACTORY4) || (gs == GameState.VIEWFACTORY5) || (gs == GameState.VIEWFACTORY6) || (gs == GameState.VIEWFACTORY7) || (gs == GameState.VIEWFACTORY8) || (gs == GameState.VIEWFACTORY9);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
