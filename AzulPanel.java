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

	private BufferedImage azulBoard, blackTile, cyanTile, factory, iceTile, oneTile, orangeTile, redTile, woodBackground;
	private GameState gs = GameState.PLAY;
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
		g.drawImage(woodBackground, 0, 0,getWidth(), getHeight(), null);
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
					AL.addTileToWall(players.get(0).getRow(1));
				} else if (AL.getCurrentRow() == 2) {
					AL.addTileToWall(players.get(0).getRow(2));
				} else if (AL.getCurrentRow() == 3) {
					AL.addTileToWall(players.get(0).getRow(3));
				} else if (AL.getCurrentRow() == 4) {
					AL.addTileToWall(players.get(0).getRow(4));
				} else if (AL.getCurrentRow() == 5) {
					AL.addTileToWall(players.get(0).getRow(5));
				} else if (AL.getCurrentRow() == 6) {
					Collections.rotate(players,3);
				}
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
		if (players.get(0).getRow(2).size() > 0) {
			g.drawImage(getTileImage(players.get(0).getRow(2).get(0)),246,294,41,41,null);
			if (players.get(0).getRow(2).size() > 1) {
				g.drawImage(getTileImage(players.get(0).getRow(2).get(1)),201,294,41,41,null);
			}
		}
		if (players.get(0).getRow(3).size() > 0) {
			g.drawImage(getTileImage(players.get(0).getRow(3).get(0)),246,339,41,41,null);
			if (players.get(0).getRow(3).size() > 1) {
				g.drawImage(getTileImage(players.get(0).getRow(3).get(1)),201,339,41,41,null);
				if (players.get(0).getRow(3).size() > 2) {
					g.drawImage(getTileImage(players.get(0).getRow(3).get(2)),156,339,41,41,null);
				}
			}
		}
		if (players.get(0).getRow(4).size() > 0) {
			g.drawImage(getTileImage(players.get(0).getRow(4).get(0)),246,384,41,41,null);
			if (players.get(0).getRow(4).size() > 1) {
				g.drawImage(getTileImage(players.get(0).getRow(4).get(0)),201,384,41,41,null);
				if (players.get(0).getRow(4).size() > 2) {
					g.drawImage(getTileImage(players.get(0).getRow(4).get(0)),156,384,41,41,null);
					if (players.get(0).getRow(4).size() > 3) {
						g.drawImage(getTileImage(players.get(0).getRow(4).get(0)),111,384,41,41,null);
					}
				}
			}
		}
		if (players.get(0).getRow(5).size() > 0) {
			g.drawImage(getTileImage(players.get(0).getRow(5).get(0)),246,429,41,41,null);
			if (players.get(0).getRow(5).size() > 1) {
				g.drawImage(getTileImage(players.get(0).getRow(5).get(0)),201,429,41,41,null);
				if (players.get(0).getRow(5).size() > 2) {
					g.drawImage(getTileImage(players.get(0).getRow(5).get(0)),156,429,41,41,null);
					if (players.get(0).getRow(5).size() > 3) {
						g.drawImage(getTileImage(players.get(0).getRow(5).get(0)),111,429,41,41,null);
						if (players.get(0).getRow(5).size() > 4) {
							g.drawImage(getTileImage(players.get(0).getRow(5).get(0)),66,429,41,41,null);
						}
					}
				}
			}
		}
		
		if (players.get(0).getExtraTiles().size() > 0) {
			g.drawImage(getTileImage(players.get(0).getExtraTiles().get(0)),68,507,41,41,null);
			if (players.get(0).getExtraTiles().size() > 1) {
				g.drawImage(getTileImage(players.get(0).getExtraTiles().get(1)),116,507,41,41,null);
				if (players.get(0).getExtraTiles().size() > 2) {
					g.drawImage(getTileImage(players.get(0).getExtraTiles().get(2)),164,507,41,41,null);
					if (players.get(0).getExtraTiles().size() > 3) {
						g.drawImage(getTileImage(players.get(0).getExtraTiles().get(3)),212,507,41,41,null);
						if (players.get(0).getExtraTiles().size() > 4) {
							g.drawImage(getTileImage(players.get(0).getExtraTiles().get(4)),260,507,41,41,null);
				
						}
					}
				}
			}
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
			g.fillOval(75+23*(players.get(0).getScore()-1), 124, 15, 15);
		} else if (players.get(0).getScore() == 0) {
			g.fillOval(75, 74, 15, 15);
		} else if (players.get(0).getScore() > 40 && players.get(0).getScore() <=60) {
			g.fillOval(75+23*(players.get(0).getScore()-1), 147, 15, 15);
		} else if (players.get(0).getScore() > 60 && players.get(0).getScore() <=80) {
			g.fillOval(75+23*(players.get(0).getScore()-1), 170, 15, 15);
		} else if (players.get(0).getScore() > 80 && players.get(0).getScore() <=100) {
			g.fillOval(75+23*(players.get(0).getScore()-1), 193, 15, 15);
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
		
		if (players.get(3).getRow(2).size() > 0) {
			g.drawImage(getTileImage(players.get(3).getRow(2).get(0)),167+425+425,314,35,35,null);
			if (players.get(3).getRow(2).size() > 1) {
				g.drawImage(getTileImage(players.get(3).getRow(2).get(1)),129+425+425,314,35,35,null);
			}
		}
		if (players.get(3).getRow(3).size() > 0) {
			g.drawImage(getTileImage(players.get(3).getRow(3).get(0)),167+425+425,351,35,35,null);
			if (players.get(3).getRow(3).size() > 1) {
				g.drawImage(getTileImage(players.get(3).getRow(3).get(1)),129+425+425,351,35,35,null);
				if (players.get(3).getRow(3).size() > 2) {
					g.drawImage(getTileImage(players.get(3).getRow(3).get(2)),92+425+425,351,35,35,null);
				}
			}
		}
		if (players.get(3).getRow(4).size() > 0) {
			g.drawImage(getTileImage(players.get(3).getRow(4).get(0)),167+425+425,388,35,35,null);
			if (players.get(3).getRow(4).size() > 1) {
				g.drawImage(getTileImage(players.get(3).getRow(4).get(0)),129+425+425,388,35,35,null);
				if (players.get(3).getRow(4).size() > 2) {
					g.drawImage(getTileImage(players.get(3).getRow(4).get(0)),92+425+425,388,35,35,null);
					if (players.get(3).getRow(4).size() > 3) {
						g.drawImage(getTileImage(players.get(3).getRow(4).get(0)),55+425+425,388,35,35,null);
					}
				}
			}
		}
		if (players.get(3).getRow(5).size() > 0) {
			g.drawImage(getTileImage(players.get(3).getRow(5).get(0)),167+425+425,425,35,35,null);
			if (players.get(3).getRow(5).size() > 1) {
				g.drawImage(getTileImage(players.get(3).getRow(5).get(0)),129+425+425,425,35,35,null);
				if (players.get(3).getRow(5).size() > 2) {
					g.drawImage(getTileImage(players.get(3).getRow(5).get(0)),92+425+425,425,35,35,null);
					if (players.get(3).getRow(5).size() > 3) {
						g.drawImage(getTileImage(players.get(3).getRow(5).get(0)),55+425+425,425,35,35,null);
						if (players.get(3).getRow(5).size() > 4) {
							g.drawImage(getTileImage(players.get(3).getRow(5).get(0)),18+425+425,425,35,35,null);
						}
					}
				}
			}
		}
		if (players.get(1).getRow(1).size() > 0) {
			g.drawImage(getTileImage(players.get(1).getRow(1).get(0)),167,277,35,35,null);
		}
		if (players.get(1).getRow(2).size() > 0) {
			g.drawImage(getTileImage(players.get(1).getRow(2).get(0)),167,314,35,35,null);
			if (players.get(1).getRow(2).size() > 1) {
				g.drawImage(getTileImage(players.get(1).getRow(2).get(1)),129,314,35,35,null);
			}
		}
		if (players.get(1).getRow(3).size() > 0) {
			g.drawImage(getTileImage(players.get(1).getRow(3).get(0)),167,351,35,35,null);
			if (players.get(1).getRow(3).size() > 1) {
				g.drawImage(getTileImage(players.get(1).getRow(3).get(1)),129,351,35,35,null);
				if (players.get(1).getRow(3).size() > 2) {
					g.drawImage(getTileImage(players.get(1).getRow(3).get(2)),92,351,35,35,null);
				}
			}
		}
		if (players.get(1).getRow(4).size() > 0) {
			g.drawImage(getTileImage(players.get(1).getRow(4).get(0)),167,388,35,35,null);
			if (players.get(1).getRow(4).size() > 1) {
				g.drawImage(getTileImage(players.get(1).getRow(4).get(0)),129,388,35,35,null);
				if (players.get(1).getRow(4).size() > 2) {
					g.drawImage(getTileImage(players.get(1).getRow(4).get(0)),92,388,35,35,null);
					if (players.get(1).getRow(4).size() > 3) {
						g.drawImage(getTileImage(players.get(1).getRow(4).get(0)),55,388,35,35,null);
					}
				}
			}
		}
		if (players.get(1).getRow(5).size() > 0) {
			g.drawImage(getTileImage(players.get(1).getRow(5).get(0)),167,425,35,35,null);
			if (players.get(1).getRow(5).size() > 1) {
				g.drawImage(getTileImage(players.get(1).getRow(5).get(0)),129,425,35,35,null);
				if (players.get(1).getRow(5).size() > 2) {
					g.drawImage(getTileImage(players.get(1).getRow(5).get(0)),92,425,35,35,null);
					if (players.get(1).getRow(5).size() > 3) {
						g.drawImage(getTileImage(players.get(1).getRow(5).get(0)),55,425,35,35,null);
						if (players.get(1).getRow(5).size() > 4) {
							g.drawImage(getTileImage(players.get(1).getRow(5).get(0)),18,425,35,35,null);
						}
					}
				}
			}
		}
		
		if (players.get(2).getRow(1).size() > 0) {
			g.drawImage(getTileImage(players.get(2).getRow(1).get(0)),167+425,277,35,35,null);
		}
		if (players.get(2).getRow(2).size() > 0) {
			g.drawImage(getTileImage(players.get(2).getRow(2).get(0)),167+425,314,35,35,null);
			if (players.get(2).getRow(2).size() > 1) {
				g.drawImage(getTileImage(players.get(2).getRow(2).get(1)),129+425,314,35,35,null);
			}
		}
		if (players.get(2).getRow(3).size() > 0) {
			g.drawImage(getTileImage(players.get(2).getRow(3).get(0)),167+425,351,35,35,null);
			if (players.get(2).getRow(3).size() > 1) {
				g.drawImage(getTileImage(players.get(2).getRow(3).get(1)),129+425,351,35,35,null);
				if (players.get(2).getRow(3).size() > 2) {
					g.drawImage(getTileImage(players.get(2).getRow(3).get(2)),92+425,351,35,35,null);
				}
			}
		}
		if (players.get(2).getRow(4).size() > 0) {
			g.drawImage(getTileImage(players.get(2).getRow(4).get(0)),167+425,388,35,35,null);
			if (players.get(2).getRow(4).size() > 1) {
				g.drawImage(getTileImage(players.get(2).getRow(4).get(0)),129+425,388,35,35,null);
				if (players.get(2).getRow(4).size() > 2) {
					g.drawImage(getTileImage(players.get(2).getRow(4).get(0)),92+425,388,35,35,null);
					if (players.get(2).getRow(4).size() > 3) {
						g.drawImage(getTileImage(players.get(2).getRow(4).get(0)),55+425,388,35,35,null);
					}
				}
			}
		}
		if (players.get(2).getRow(5).size() > 0) {
			g.drawImage(getTileImage(players.get(2).getRow(5).get(0)),167+425,425,35,35,null);
			if (players.get(2).getRow(5).size() > 1) {
				g.drawImage(getTileImage(players.get(2).getRow(5).get(0)),129+425,425,35,35,null);
				if (players.get(2).getRow(5).size() > 2) {
					g.drawImage(getTileImage(players.get(2).getRow(5).get(0)),92+425,425,35,35,null);
					if (players.get(2).getRow(5).size() > 3) {
						g.drawImage(getTileImage(players.get(2).getRow(5).get(0)),55+425,425,35,35,null);
						if (players.get(2).getRow(5).size() > 4) {
							g.drawImage(getTileImage(players.get(2).getRow(5).get(0)),18+425,425,35,35,null);
						}
					}
				}
			}
		}
		if (players.get(1).getExtraTiles().size() > 0) {
			g.drawImage(getTileImage(players.get(1).getExtraTiles().get(0)),17,493,33,33,null);
			if (players.get(1).getExtraTiles().size() > 1) {
				g.drawImage(getTileImage(players.get(1).getExtraTiles().get(1)),57,493,33,33,null);
				if (players.get(1).getExtraTiles().size() > 2) {
					g.drawImage(getTileImage(players.get(1).getExtraTiles().get(2)),97,493,33,33,null);
					if (players.get(1).getExtraTiles().size() > 3) {
						g.drawImage(getTileImage(players.get(1).getExtraTiles().get(3)),137,493,33,33,null);
						if (players.get(1).getExtraTiles().size() > 4) {
							g.drawImage(getTileImage(players.get(1).getExtraTiles().get(4)),177,493,33,33,null);
				
						}
					}
				}
			}
		}
		if (players.get(2).getExtraTiles().size() > 0) {
			g.drawImage(getTileImage(players.get(2).getExtraTiles().get(0)),17,493,33,33,null);
			if (players.get(2).getExtraTiles().size() > 1) {
				g.drawImage(getTileImage(players.get(2).getExtraTiles().get(1)),57+425,493,33,33,null);
				if (players.get(2).getExtraTiles().size() > 2) {
					g.drawImage(getTileImage(players.get(2).getExtraTiles().get(2)),97+425,493,33,33,null);
					if (players.get(2).getExtraTiles().size() > 3) {
						g.drawImage(getTileImage(players.get(2).getExtraTiles().get(3)),136+425,493,33,33,null);
						if (players.get(2).getExtraTiles().size() > 4) {
							g.drawImage(getTileImage(players.get(2).getExtraTiles().get(4)),177+425,493,33,33,null);
				
						}
					}
				}
			}
		}
		
		if (players.get(3).getExtraTiles().size() > 0) {
			g.drawImage(getTileImage(players.get(3).getExtraTiles().get(0)),17+425+425,493,33,33,null);
			if (players.get(3).getExtraTiles().size() > 1) {
				g.drawImage(getTileImage(players.get(3).getExtraTiles().get(1)),57+425+425,493,33,33,null);
				if (players.get(3).getExtraTiles().size() > 2) {
					g.drawImage(getTileImage(players.get(3).getExtraTiles().get(2)),97+425+425,493,33,33,null);
					if (players.get(3).getExtraTiles().size() > 3) {
						g.drawImage(getTileImage(players.get(3).getExtraTiles().get(3)),137+425+425,493,33,33,null);
						if (players.get(3).getExtraTiles().size() > 4) {
							g.drawImage(getTileImage(players.get(3).getExtraTiles().get(4)),177+425+425,493,33,33,null);
				
						}
					}
				}
			}
		}
		
		
		
	}
	
	public void drawScoreRow1(Graphics g) {
		
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
		if(AL.getCurrentRow() != 6) {
			g.drawString("TO SCORE ROW " + AL.getCurrentRow(), 690, 130);
		} else {
			g.drawString("TO SCORE THE NEXT PLAYER", 640, 130);
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
		VIEWFACTORY9;
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
