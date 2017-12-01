import java.util.Arrays;

import javax.swing.JFrame;

public class ComboLevel extends StraightLevel {
	
	long scoreValue = 0;
	int value = 0;

	protected ComboLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		// TODO Auto-generated constructor stub
	}
	
	public void comboTurnUp(){
		
	}
	
	@Override
	protected boolean turnUp(Card card) {
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
				
				int orderSequence[] = {(cardValue(card.getRank())), 
										cardValue(otherCard1.getRank()), 
									   	cardValue(otherCard2.getRank()), 
									   	cardValue(otherCard3.getRank()), 
									   	cardValue(otherCard4.getRank())};
				
				Arrays.sort(orderSequence);
				
				int comboArray[] = {comboCardValue(card.getRank()), 
									comboCardValue(otherCard1.getRank()), 
									comboCardValue(otherCard2.getRank()), 
									comboCardValue(otherCard3.getRank()), 
									comboCardValue(otherCard4.getRank())};
				
				Arrays.sort(comboArray);
				
				if(orderSequence[1] - orderSequence[0] == 1 && orderSequence[2] - orderSequence[1] == 1 
				&& orderSequence[3] - orderSequence[2] == 1 && orderSequence[4] - orderSequence[3] == 1) 
//				if(orderSequence[0]+1 == orderSequence[1] && orderSequence[1]+1 == orderSequence[2]
//				&& orderSequence[2]+1 == orderSequence[3] && orderSequence[3]+1 == orderSequence[4])
				{
//					if((card.getSuit().equals(otherCard1.getSuit())) && (card.getSuit().equals(otherCard2.getSuit())) 
//					&& (card.getSuit().equals(otherCard3.getSuit())) && (card.getSuit().equals(otherCard4.getSuit()))) 
//					{
//						scoreValue -= 5;
//						getMainFrame().setScore(scoreValue);
//					}
					
					//else
					{
						scoreValue += 1000 + 100*orderSequence[4];
						getMainFrame().setScore(scoreValue);
						// Five cards match, so remove them from the list (they will remain face up)
						this.getTurnedCardsBuffer().clear();
					}
				}
				
//				else if((card.getSuit().equals(otherCard1.getSuit())) && (card.getSuit().equals(otherCard2.getSuit())) 
//						&& (card.getSuit().equals(otherCard3.getSuit())) && (card.getSuit().equals(otherCard4.getSuit())))
//				{
//						
//					scoreValue += (700 + scorePerCard.CardValue(card.getRank()) +
//								   scorePerCard.CardValue(otherCard1.getRank()) +
//								   scorePerCard.CardValue(otherCard2.getRank()) + 
//								   scorePerCard.CardValue(otherCard3.getRank()) +
//								   scorePerCard.CardValue(otherCard4.getRank()));
//					getMainFrame().setScore(scoreValue);
//					// Five cards match, so remove them from the list (they will remain face up)
//					this.getTurnedCardsBuffer().clear();
//				}
				
				else if(comboArray[0] == 10 
					 && comboArray[1] == 11
					 && comboArray[2] == 12 
					 && comboArray[3] == 13 
					 && comboArray[4] == 14 
					 && card.getSuit().equals(otherCard1.getSuit()) 
					 && card.getSuit().equals(otherCard2.getSuit()) 
					 && card.getSuit().equals(otherCard3.getSuit()) 
					 && card.getSuit().equals(otherCard4.getSuit()))
				{
					scoreValue += 10000;
					getMainFrame().setScore(scoreValue);
					this.getTurnedCardsBuffer().clear();
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
	
	public int comboCardValue(String s){
		switch(s)
		{
			case("a"):
				value = 14;
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
				
			default:
				value = Integer.valueOf(s);
		}
		return value;
	}
}