import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


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
		JTextArea textArea = new JTextArea(30,55);
		dprintln("MemoryGame.showInstructions()");
		final String HOWTOPLAYTEXT = 
				"How To Play\r\n" +
						"\r\n" +
						"EQUAL PAIR Level\r\n"+
						"The game consists of 8 pairs of cards.  At the start of the game,\r\n"+
						"every card is face down.  The object is to find all the pairs and\r\n"+
						"turn them face up.\r\n"+
						"\r\n"+
						"Click on two cards to turn them face up. If the cards are the \r\n"+
						"same, then you have discovered a pair.  The pair will remain\r\n"+
						"turned up.  If the cards are different, they will flip back\r\n"+
						"over automatically after a short delay and you will lose points.  Continue flipping\r\n"+
						"cards until you have discovered all of the pairs.  The game\r\n"+
						"is won when all cards are face up.\r\n"+
						"\r\n"+
						"Each Fail: -5 points\r\n"+
						"Each Pair: 50 points \r\n"+
						"\r\n"+
						"SAME RANK TRIO Level\r\n"+
						"The game consists of a grid of distinct cards.  At the start of the game,\r\n"+
						"every card is face down.  The object is to find all the trios \r\n"+
						"of cards with the same rank and turn them face up.\r\n"+
						"\r\n"+
						"Click on three cards to turn them face up. If the cards have the \r\n"+
						"same rank, then you have discovered a trio.  The trio will remain\r\n"+
						"turned up.  If the cards are different, they will flip back\r\n"+
						"over automatically after a short delay and you will lose points.  Continue flipping\r\n"+
						"cards until you have discovered all of the trios.  The game\r\n"+
						"is won when all cards are face up.\r\n"+
						"\r\n"+
						"Each time you flip three cards up, the turn counter will\r\n"+
						"increase.  Try to win the game in the fewest number of turns!\r\n"+
						"\r\n"+
						"Each Fail: -5 points\r\n"+
						"Each Trio: 100 points\r\n"+
						"\r\n"+
						"FLUSH Level\r\n"+
						"The game consists of a grid of distinct cards.  At the start of the game,\r\n"+
						"every card is face down.  The object is to find a flush.\r\n"+
						"\r\n"+
						"Click on five cards to turn them face up. If the cards have the\r\n"+
						"same suit, then you have discovered a flush.  The flush will remain\r\n"+
						"turned up.  If the cards are different, they will flip back over\r\n"+
						"automatically after a short delay and you will lose points.  Continue flipping\r\n"+
						"cards until you have discovered all of the flushes.  The game\r\n"+
						"is won when there are no more flush posibilities in the game.\r\n"+
						"\r\n"+
						"Each time you flip five cards up, the turn counter will\r\n"+
						"increase.  Try to win the game in the fewest number of turns!\r\n"+
						"\r\n"+
						"Each Fail: -5 points\r\n"+
						"Each Flush: 700 points + sum of the ranks in all the cards (A = 20) \r\n"+
						"\r\n"+
						"STRAIGHT Level\r\n"+
						"The game consists of a grid of distinct cards.  At the start of the game,\r\n"+
						"every card is face down.  The object is to find a Straight.\r\n"+
						"\r\n"+
						"Click on five cards to turn them face up. If the cards have a\r\n"+
						"sequential rank in at least two different suits, then you have discovered a Straight.\r\n"+
						"The Straight will remain\r\n"+
						"turned up.  If the cards are different, they will flip back over\r\n"+
						"automatically after a short delay and you will lose points.  Continue flipping\r\n"+
						"cards until you have discovered all of the Straights.  The game\r\n"+
						"is won when there are no more Straight posibilities in the game.\r\n"+
						"\r\n"+
						"Each time you flip five consecutive cards up, the turn counter will\r\n"+
						"increase.  Try to win the game in the fewest number of turns!\r\n"+
						"\r\n"+
						"Each Fail: -5 points\r\n"+
						"Each Straight: 1000 points + 100 times the rank of the highest card in the obtained sequence (A = 20) \r\n"+
						"\r\n"+
						"COMBO Level\r\n"+
						"The game consists of a grid of distinct cards.  At the start of the game,\r\n"+
						"every card is face down.  The object is to find a Flush, Straight or Royal Flush.\r\n"+
						"\r\n"+
						"Click on five cards to turn them face up. If the cards have a\r\n"+
						"sequential rank in at least two different suits (Straight), a same suit in all cards (Flush) or \r\n"+
						"a combination of 10, J, Q, K and A (Royal Flush) then you may select the option you want to play.\r\n"+
						"If you decide to, the cards will flip back over\r\n"+
						"automatically after a short delay and you will lose points.  Continue flipping\r\n"+
						"cards until you have discovered all of the Straights, Flushes or Royal Flushes.  The game\r\n"+
						"is won when there are no more Straight, Flushes or Royal Flushes posibilities in the game.\r\n"+
						"\r\n"+
						"Each time you flip five consecutive cards up and choose an option, the turn counter will\r\n"+
						"increase.  Try to win the game in the fewest number of turns!\r\n"+
						"\r\n"+
						"Each Fail/Pass: -5 points\r\n"+
						"Each Flush: 700 points + sum of the ranks in all the cards (A = 20) \r\n"+
						"Each Straight: 1,000 points + 100 times the rank of the highest card in the sequence (A = 20) \r\n"+
						"Each Royal Flush = 100,000 points "+
						"\r\n";
		textArea.setText(HOWTOPLAYTEXT);
		textArea.setEditable(false);
		//Add Scroll-bar to the How To Play Menu
		JScrollPane scrolling = new JScrollPane(textArea);
		JOptionPane.showMessageDialog(this, scrolling, "How To Play", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void showAbout()
	{
		dprintln("MemoryGame.showAbout()");
		final String ABOUTTEXT = "Game Customized at UPRM. Originally written by Mike Leonhard";

		JOptionPane.showMessageDialog(this, ABOUTTEXT
				, "About Memory Game", JOptionPane.PLAIN_MESSAGE);
	}
}