import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MyMemoryFrame extends MemoryFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


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
		
		ActionListener menuHandler = new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				dprintln("actionPerformed " + e.getActionCommand());
				try {
					if(e.getActionCommand().equals("Flush Level")) newGame("flushlevel");
//					else if(e.getActionCommand().equals("Straight Level")) newGame("straightlevel");
//					else if(e.getActionCommand().equals("Combo Level")) newGame("combolevel");
				} catch (IOException e2) {
					e2.printStackTrace(); throw new RuntimeException("IO ERROR");
				}
			}
		};
		
		JMenuItem flushMenuItem = new JMenuItem("Flush Level");
		flushMenuItem.addActionListener(menuHandler);
		memoryMenu.add(flushMenuItem);
		
//		JMenuItem straightMenuItem = new JMenuItem("Straight Level");
//		flushMenuItem.addActionListener(menuHandler);
//		memoryMenu.add(straightMenuItem);
//		
//		JMenuItem comboMenuItem = new JMenuItem("Combo Level");
//		flushMenuItem.addActionListener(menuHandler);
//		memoryMenu.add(comboMenuItem);
		
		
	}
	
	
	public void newGame(String difficultyMode) throws IOException
	{
		// Reset the turn counter label
		this.getTurnCounterLabel().reset();

		// make a new card field with cards, and add it to the window

		if(difficultyMode.equalsIgnoreCase("flushlevel")) {
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
		
//		else if(difficultyMode.equalsIgnoreCase("straightlevel")) {
//			this.setGameLevel(new FlushLevel(this.getTurnCounterLabel(), this));
//			this.getLevelDescriptionLabel().setText("Straight Level");
//			this.getTurnCounterLabel().reset();
//			
//			// clear out the content pane (removes turn counter label and card field)
//			BorderLayout bl  = (BorderLayout) this.getContentPane().getLayout();
//			this.getContentPane().remove(bl.getLayoutComponent(BorderLayout.CENTER));
//			this.getContentPane().add(showCardDeck(), BorderLayout.CENTER);
//	
//			// show the window (in case this is the first game)
//			this.setVisible(true);
//		}
//		
//		else if(difficultyMode.equalsIgnoreCase("combolevel")) {
//			this.setGameLevel(new FlushLevel(this.getTurnCounterLabel(), this));
//			this.getLevelDescriptionLabel().setText("Combo Level");
//			this.getTurnCounterLabel().reset();
//			
//			// clear out the content pane (removes turn counter label and card field)
//			BorderLayout bl  = (BorderLayout) this.getContentPane().getLayout();
//			this.getContentPane().remove(bl.getLayoutComponent(BorderLayout.CENTER));
//			this.getContentPane().add(showCardDeck(), BorderLayout.CENTER);
//
//			// show the window (in case this is the first game)
//			this.setVisible(true);
//		}
		
		else {
			super.newGame(difficultyMode);;
		}
	}
	
	public void pushDummyMethod(){
		
	}
}