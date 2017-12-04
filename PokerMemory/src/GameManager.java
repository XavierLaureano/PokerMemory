/**
 * Main class for Memory game
 *
 * @author Michael Leonhard (Original Author)
 * @author Modified by Bienvenido VÃ©lez (UPRM)
 * @version Sept 2017
 */

import java.io.IOException;
import javax.swing.JOptionPane;

public class GameManager {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		// make an instance of the main game class
		MyMemoryFrame instance = new MyMemoryFrame();
		Object hello[] = {"Brrr", "No pa"};
		Object end[] = {"Dale pa", "No bebol"};
		
		int playMore = 2;
		int Hello = JOptionPane.showOptionDialog(null, "¿Vas a meterle?", "¿Eres Real Hasta La Muerte?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,  null, hello, hello[0]);
		while (playMore != 1 && Hello == 0) {
			instance.newGame("easy");

			while(!instance.gameOver()) {
				Thread.sleep(500);
			}
			playMore = JOptionPane.showOptionDialog(null, "Metele de nuevo", "¿Eres Real Hasta La Muerte?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,  null, end, end[0]);
			System.out.println(playMore+"");
		}
		System.exit(0);
	}
}
