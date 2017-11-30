import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class StraightLevel extends FlushLevel {
	
	int value = 0;

	protected StraightLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected boolean turnUp(Card card) {
		long scoreValue = 0;
		//ScorePerCard scorePerCard = new ScorePerCard(null, null, null, getTotalCardsPerDeck(), null, null);
		// TODO Auto-generated method stub
		// the card may be turned
		if(this.getTurnedCardsBuffer().size() < getCardsToTurnUp()) 
		{
			// add the card to the list
			this.getTurnedCardsBuffer().add(card);
			if(this.getTurnedCardsBuffer().size() == getCardsToTurnUp())
			{
				// We are uncovering the last cards in this turn
				// Record the player's turn
				this.getTurnsTakenCounter().increment();
				// get the other card (which was already turned up)
				Card otherCard1 = (Card) this.getTurnedCardsBuffer().get(0);
				Card otherCard2 = (Card) this.getTurnedCardsBuffer().get(1);
				Card otherCard3 = (Card) this.getTurnedCardsBuffer().get(2);
				Card otherCard4 = (Card) this.getTurnedCardsBuffer().get(3);
				
//				int orderSequence[] = {Integer.valueOf(card.getRank()), 
//									   Integer.valueOf(otherCard1.getRank()), 
//									   Integer.valueOf(otherCard2.getRank()), 
//									   Integer.valueOf(otherCard3.getRank()), 
//									   Integer.valueOf(otherCard4.getRank())};
				
				int orderSequence[] = {CardValue(card.getRank()),
									   CardValue(otherCard1.getRank()),
									   CardValue(otherCard2.getRank()),
									   CardValue(otherCard3.getRank()),
									   CardValue(otherCard4.getRank())};
				
				int max = orderSequence[0];
				for(int i=0; i<5; i++)
				{
					if(max <= orderSequence[i])
					{
						max = orderSequence[i];
						if(max == 20){
							orderSequence[4] = 14;
						}
					}
				}
				
				if((orderSequence[4] - orderSequence[3] == 1) && (orderSequence[3] - orderSequence[2] == 1) 
				&& (orderSequence[2] - orderSequence[1] == 1) && (orderSequence[1] - orderSequence[0] == 1)) 
				{
					if((card.getSuit().equals(otherCard1.getSuit())) && (card.getSuit().equals(otherCard2.getSuit())) 
					&& (card.getSuit().equals(otherCard3.getSuit())) && (card.getSuit().equals(otherCard4.getSuit()))) 
					{
						scoreValue -= 5;
						getMainFrame().setScore(scoreValue);
					}
					
					else{
						scoreValue += 1000 + 100*max;
						getMainFrame().setScore(scoreValue);
						// Five cards match, so remove them from the list (they will remain face up)
						this.getTurnedCardsBuffer().clear();
					}
				}
				else
				{
					//scoreValue -= 5;
					//getMainFrame().setScore(scoreValue);
					// The cards do not match, so start the timer to turn them down
					this.getTurnDownTimer().start();
				}
			}
			return true;
		}
		return false;
	}
	
	public int CardValue(String s){
		switch (s)
		{
			case("a"):
				value = 20;
				break;
				
			case("k"):
				value = 13;
				break;
				
			case("q"):
				value = 12;
				break;
				
			case("j"):
				value = 11;
				break;
				
			case("t"):
				value = 10;
				break;
			
			case("2"):
				value = 2;
				break;
				
			case("3"):
				value = 3;
				break;
				
			case("4"):
				value = 4;
				break;
				
			case("5"):
				value = 5;
				break;
				
			case("6"):
				value = 6;
				break;
				
			case("7"):
				value = 7;
				break;
				
			case("8"):
				value = 8;
				break;
				
			case("9"):
				value = 9;
				break;
		}
		return value;
	}

}
