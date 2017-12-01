import javax.swing.JFrame;

public class MyEqualPairLevel extends EqualPairLevel {
	
	long scoreValue = 0;
	
	protected MyEqualPairLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
	}
	
	@Override
	protected boolean turnUp(Card card) {
		// the card may be turned
		if(this.getTurnedCardsBuffer().size() < getCardsToTurnUp()) 
		{
			this.getTurnedCardsBuffer().add(card);
			if(this.getTurnedCardsBuffer().size() == getCardsToTurnUp())
			{
				// there are two cards faced up
				// record the player's turn
				this.getTurnsTakenCounter().increment();
				// get the other card (which was already turned up)
				Card otherCard = (Card) this.getTurnedCardsBuffer().get(0);
				// the cards match, so remove them from the list (they will remain face up)
				if(otherCard.getNum() == card.getNum()){
					scoreValue += 50;
					getMainFrame().setScore(scoreValue);
					this.getTurnedCardsBuffer().clear();
				}
				// the cards do not match, so start the timer to turn them down
				else {
					scoreValue -= 5;
					getMainFrame().setScore(scoreValue);
					this.getTurnDownTimer().start();
				}
			}
			return true;
		}
		// there are already the number of EasyMode (two face up cards) in the turnedCardsBuffer
		return false;
	}
	@Override
	protected boolean  isGameOver(){

		for (int i =0; i< this.getGrid().size();i++)
			if(!this.getGrid().get(i).isFaceUp()) return false;

		return true;
	}
}
