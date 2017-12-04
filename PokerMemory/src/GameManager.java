/**
 * Main class for Memory game
 *
 * @author Michael Leonhard (Original Author)
 * @author Modified by Bienvenido Vélez (UPRM)
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

		int playMore = 2;
		while (playMore != 1) {
			instance.newGame("easy");

			while(!instance.gameOver()) {
				Thread.sleep(500);
			}
			playMore = JOptionPane.showConfirmDialog(null, "Play Again?", "YOU WON!!!", JOptionPane.YES_NO_OPTION);
			System.out.println(playMore+"");
		}
		System.exit(0);
	}
}
