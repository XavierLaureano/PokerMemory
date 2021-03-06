//Java Project #1 Poker Memory
//Team Losilluminatisbrrr
//Bryan Figueroa Maldonado
//Xavier Laureano Rivera

import javax.swing.JFrame;

public class MyRankTrioLevel extends RankTrioLevel {
	
	long scoreValue = 0;
	int countTrio = 0;
	ValueOfCards scorePerCard = new ValueOfCards();
	
	protected MyRankTrioLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected boolean turnUp(Card card) {
		countTrio = 0;
		
		// the card may be turned
		if(this.getTurnedCardsBuffer().size() < getCardsToTurnUp()) 
		{
			// add the card to the list
			this.getTurnedCardsBuffer().add(card);
			if(this.getTurnedCardsBuffer().size() == getCardsToTurnUp())
			{
				// We are uncovering the last card in this turn
				// Record the player's turn
				this.getTurnsTakenCounter().increment();
				
				// get the other card (which was already turned up)
				Card otherCard1 = (Card) this.getTurnedCardsBuffer().get(0);
				Card otherCard2 = (Card) this.getTurnedCardsBuffer().get(1);

				if((card.getRank().equals(otherCard1.getRank())) && (card.getRank().equals(otherCard2.getRank()))) 
				{
					scoreValue += 100 + 3*(scorePerCard.cardValue(card.getRank()));
					getMainFrame().setScore(scoreValue);
					
					// Three cards match, so remove them from the list (they will remain face up)
					this.getTurnedCardsBuffer().clear();
					countTrio++;
				}
				else 
				{
					scoreValue -= 5;
					getMainFrame().setScore(scoreValue);
					// The cards do not match, so start the timer to turn them down
					this.getTurnDownTimer().start();
				}
			}
			return true;
	}
		return false;
}
	@Override
	protected boolean isGameOver(){
		// If 14 cards remain, end game
		if(countTrio == 12)
			return true;
		else
		return false;	
		
	}
}

