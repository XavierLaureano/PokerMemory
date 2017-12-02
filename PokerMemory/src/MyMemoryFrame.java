import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MyMemoryFrame extends MemoryFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel scoreLabel;


	public MyMemoryFrame(){
		
		super();
		
		JMenuBar menuBar = this.getJMenuBar();
        JMenu memoryMenu = null;
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            if (menuBar.getMenu(i).getText().equals("Memory")) {
                memoryMenu = menuBar.getMenu(i);
                break;
            }
        }
        
        JMenu mnHelp = null;
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            if (menuBar.getMenu(i).getText().equals("Help")) {
                mnHelp = menuBar.getMenu(i);
                break;
            }
            menuBar.getMenu(1).remove(0);
            menuBar.getMenu(1).remove(0);
        }
        
		
		ActionListener menuHandler = new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				dprintln("actionPerformed " + e.getActionCommand());
				try {
					if(e.getActionCommand().equals("Flush Level")) newGame("flushlevel");
					else if(e.getActionCommand().equals("Straight Level")) newGame("straightlevel");
					else if(e.getActionCommand().equals("Combo Level")) newGame("combolevel");
					else if(e.getActionCommand().equals("How To Play")) showInstructions();
					else if(e.getActionCommand().equals("About")) showAbout();
				} catch (IOException e2) {
					e2.printStackTrace(); throw new RuntimeException("IO ERROR");
				}
			}
		};
		
		JMenuItem flushMenuItem = new JMenuItem("Flush Level");
		flushMenuItem.addActionListener(menuHandler);
		memoryMenu.add(flushMenuItem);
		
		JMenuItem straightMenuItem = new JMenuItem("Straight Level");
		straightMenuItem.addActionListener(menuHandler);
		memoryMenu.add(straightMenuItem);
		
		JMenuItem comboMenuItem = new JMenuItem("Combo Level");
		comboMenuItem.addActionListener(menuHandler);
		memoryMenu.add(comboMenuItem);

		JMenuItem mntmHowToPlay = new JMenuItem("How To Play");
		mntmHowToPlay.addActionListener(menuHandler);
		mnHelp.add(mntmHowToPlay);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(menuHandler);
		mnHelp.add(mntmAbout);
		
	}
	
	public void getScore() {
		this.scoreLabel.getText();
	}
		
	@Override
	public void newGame(String difficultyMode) throws IOException
	{
		// Reset the turn counter label
		this.getTurnCounterLabel().reset();
		setScore(0);

		// make a new card field with cards, and add it to the window

		if(difficultyMode.equalsIgnoreCase("equalpair")){
			this.setGameLevel(new MyEqualPairLevel(this.getTurnCounterLabel(), this));
			this.getLevelDescriptionLabel().setText("Equal Pair Level");
			this.getTurnCounterLabel().reset();
			
			// clear out the content pane (removes turn counter label and card field)
			BorderLayout bl  = (BorderLayout) this.getContentPane().getLayout();
			this.getContentPane().remove(bl.getLayoutComponent(BorderLayout.CENTER));
			this.getContentPane().add(showCardDeck(), BorderLayout.CENTER);

			// show the window (in case this is the first game)
			this.setVisible(true);	
		}
		else if(difficultyMode.equalsIgnoreCase("ranktrio")){
			this.setGameLevel(new MyRankTrioLevel(this.getTurnCounterLabel(), this));
			this.getLevelDescriptionLabel().setText("Same Rank Trio Level");
			this.getTurnCounterLabel().reset();
			
			// clear out the content pane (removes turn counter label and card field)
			BorderLayout bl  = (BorderLayout) this.getContentPane().getLayout();
			this.getContentPane().remove(bl.getLayoutComponent(BorderLayout.CENTER));
			this.getContentPane().add(showCardDeck(), BorderLayout.CENTER);

			// show the window (in case this is the first game)
			this.setVisible(true);	
		}
		else if(difficultyMode.equalsIgnoreCase("flushlevel")) {
			this.setGameLevel(new FlushLevel(this.getTurnCounterLabel(), this));
			this.getLevelDescriptionLabel().setText("Flush Level");
			this.getTurnCounterLabel().reset();
			
			// clear out the content pane (removes turn counter label and card field)
			BorderLayout bl  = (BorderLayout) this.getContentPane().getLayout();
			this.getContentPane().remove(bl.getLayoutComponent(BorderLayout.CENTER));
			this.getContentPane().add(showCardDeck(), BorderLayout.CENTER);

			// show the window (in case this is the first game)
			this.setVisible(true);			
		}
		
		else if(difficultyMode.equalsIgnoreCase("straightlevel")) {
			this.setGameLevel(new StraightLevel(this.getTurnCounterLabel(), this));
			this.getLevelDescriptionLabel().setText("Straight Level");
			this.getTurnCounterLabel().reset();
			
			// clear out the content pane (removes turn counter label and card field)
			BorderLayout bl  = (BorderLayout) this.getContentPane().getLayout();
			this.getContentPane().remove(bl.getLayoutComponent(BorderLayout.CENTER));
			this.getContentPane().add(showCardDeck(), BorderLayout.CENTER);
	
			// show the window (in case this is the first game)
			this.setVisible(true);
		}
		
		else if(difficultyMode.equalsIgnoreCase("combolevel")) {
			this.setGameLevel(new ComboLevel(this.getTurnCounterLabel(), this));
			this.getLevelDescriptionLabel().setText("Combo Level");
			this.getTurnCounterLabel().reset();
			
			// clear out the content pane (removes turn counter label and card field)
			BorderLayout bl  = (BorderLayout) this.getContentPane().getLayout();
			this.getContentPane().remove(bl.getLayoutComponent(BorderLayout.CENTER));
			this.getContentPane().add(showCardDeck(), BorderLayout.CENTER);

			// show the window (in case this is the first game)
			this.setVisible(true);
		}
		
		else {
			super.newGame(difficultyMode);;
		}
	}
	private void showInstructions(){
		dprintln("MemoryGame.showInstructions()");
		final String HOWTOPLAYTEXT = 
				"How To Play\r\n" +
						"\r\n" +
						"EQUAL PAIR Level\r\n"+
						"The game consists of 8 pairs of cards.  At the start of the game, "+
						"every card is face down.  The object is to find all the pairs and "+
						"turn them face up.\r\n"+
						"\r\n"+
						"Click on two cards to turn them face up. If the cards are the "+
						"same, then you have discovered a pair.  The pair will remain "+
						"turned up.  If the cards are different, they will flip back\r\n"+
						"over automatically after a short delay.  Continue flipping "+
						"cards until you have discovered all of the pairs.  The game "+
						"is won when all cards are face up.\r\n"+
						"\r\n"+
						"SAME RANK TRIO Level\r\n"+
						"The game consists of a grid of distinct cards.  At the start of the game, "+
						"every card is face down.  The object is to find all the trios "+
						"of cards with the same rank and turn them face up.\r\n"+
						"\r\n"+
						"Click on three cards to turn them face up. If the cards have the "+
						"same rank, then you have discovered a trio.  The trio will remain "+
						"turned up.  If the cards are different, they will flip back\r\n"+
						"over automatically after a short delay.  Continue flipping "+
						"cards until you have discovered all of the trios.  The game "+
						"is won when all trios are face up.\r\n"+
						"\r\n"+
						"Each time you flip three cards up, the turn counter will "+
						"increase.  Try to win the game in the fewest number of turns!\r\n"+
						"\r\n"+
						"FLUSH Level\r\n"+
						"The game consists of a grid of distinct cards.  At the start of the game, "+
						"every card is face down.  The object is to find a flush.\r\n"+
						"\r\n"+
						"Click on five cards to turn them face up. If the cards have the "+
						"same suit, then you have discovered a flush.  The flush will remain "+
						"turned up.  If the cards are different, they will flip back "+
						"over automatically after a short delay.  Continue flipping\r\n"+
						"cards until you have discovered all of the flushes.  The game "+
						"is won when there are no more flush posibilities in the game.\r\n"+
						"\r\n"+
						"Each time you flip five cards up, the turn counter will "+
						"increase.  Try to win the game in the fewest number of turns!\r\n"+
						"\r\n"+
						"STRAIGHT Level\r\n"+
						"The game consists of a grid of distinct cards.  At the start of the game, "+
						"every card is face down.  The object is to find a straight set of cards.\r\n"+
						"\r\n"+
						"Click on five cards to turn them face up. If the cards have succesive numbers "+
						", then you have discovered a straight.  The straight will remain "+
						"turned up." +
						"If the cards are not straight, they will flip back\r\n"+
						"over automatically after a short delay.  Continue flipping "+
						"cards until you have discovered all of the straights.  The game "+
						"is won when there are no more straight posibilities in the game.\r\n"+
						"\r\n"+
						"Each time you flip five cards up, the turn counter will "+
						"increase.  Try to win the game in the fewest number of turns!\r\n"+
						"\r\n"+
						"COMBO Level\r\n";

		JOptionPane.showMessageDialog(this, HOWTOPLAYTEXT, "How To Play", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void showAbout()
	{
		dprintln("MemoryGame.showAbout()");
		final String ABOUTTEXT = "Game Customized at UPRM. Originally written by Mike Leonhard";

		JOptionPane.showMessageDialog(this, ABOUTTEXT
				, "About Memory Game", JOptionPane.PLAIN_MESSAGE);
	}
}