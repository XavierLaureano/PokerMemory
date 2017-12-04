import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.ArrayList;

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
				

				int straightArray[] = {(valueOfCards.cardValue(card.getRank())), 
										valueOfCards.cardValue(otherCard1.getRank()), 
										valueOfCards.cardValue(otherCard2.getRank()), 
										valueOfCards.cardValue(otherCard3.getRank()), 
										valueOfCards.cardValue(otherCard4.getRank())};
				Arrays.sort(straightArray);
				
				int comboArray[] = {valueOfCards.cardValue(card.getRank()), 
									valueOfCards.cardValue(otherCard1.getRank()), 
									valueOfCards.cardValue(otherCard2.getRank()), 
									valueOfCards.cardValue(otherCard3.getRank()), 
									valueOfCards.cardValue(otherCard4.getRank())};
				Arrays.sort(comboArray);
				
				card.faceUp();

				Object selection[] = {"Flush", "Straight", "Royal Flush", "Pass"};
				
				int index = JOptionPane.showOptionDialog(null, 
														 "                Select The Desired Hand", 
														 "SELECTION", 
														 JOptionPane.DEFAULT_OPTION, 
														 JOptionPane.WARNING_MESSAGE, 
														 null, selection, selection[0]);
				
				
				if(index == 0)
				{
					//Flush
					if((card.getSuit().equals(otherCard1.getSuit())) 
					&& (card.getSuit().equals(otherCard2.getSuit())) 
					&& (card.getSuit().equals(otherCard3.getSuit())) 
					&& (card.getSuit().equals(otherCard4.getSuit())))
					{
						scoreValue += (700 + valueOfCards.cardValue(card.getRank()) +
								 			 valueOfCards.cardValue(otherCard1.getRank()) +
								 			 valueOfCards.cardValue(otherCard2.getRank()) + 
								 			 valueOfCards.cardValue(otherCard3.getRank()) +
								 			 valueOfCards.cardValue(otherCard4.getRank()));
						getMainFrame().setScore(scoreValue);
						// Five cards match, so remove them from the list (they will remain face up)
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
				
				if(index == 1)
				{
					//Straight
					if(straightArray[0]+1 == straightArray[1] 
					&& straightArray[1]+1 == straightArray[2]
					&& straightArray[2]+1 == straightArray[3] 
					&& straightArray[3]+1 == straightArray[4])
					{
						if(card.getSuit().equals(otherCard1.getSuit()) 
						&&(card.getSuit().equals(otherCard2.getSuit())) 
						&&(card.getSuit().equals(otherCard3.getSuit())) 
						&&(card.getSuit().equals(otherCard4.getSuit())))
						{
							scoreValue -= 5;
							getMainFrame().setScore(scoreValue);
							// The cards do not match, so start the timer to turn them down
							this.getTurnDownTimer().start();
						}
						
						else
						{
							scoreValue += 1000 + 100*straightArray[4];
							getMainFrame().setScore(scoreValue);
							// Five cards match, so remove them from the list (they will remain face up)
							this.getTurnedCardsBuffer().clear();
						}
					}
					
					else if(straightArray[0] == 2 && straightArray[4] == 20)
					{
						scoreValue += 1000 + 100*straightArray[3];
						getMainFrame().setScore(scoreValue);
						// Five cards match, so remove them from the list (they will remain face up)
						this.getTurnedCardsBuffer().clear();
					}
					
					else if(straightArray[0] == 10 && straightArray[4] == 20)
					{
						scoreValue += 1000 + 100*20;
						getMainFrame().setScore(scoreValue);
						// Five cards match, so remove them from the list (they will remain face up)
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
				
				if(index == 2 || index == 3)
				{
					//Royal Flush
					if(comboArray[0] == 10 
				    && comboArray[1] == 11
				    && comboArray[2] == 12 
				    && comboArray[3] == 13 
				    && comboArray[4] == 14 
				    && card.getSuit().equals(otherCard1.getSuit()) 
				    && card.getSuit().equals(otherCard2.getSuit()) 
				    && card.getSuit().equals(otherCard3.getSuit()) 
				    && card.getSuit().equals(otherCard4.getSuit()))
					{
						if (index == 3)
						{
							scoreValue -= 5;
							getMainFrame().setScore(scoreValue);
							// The cards do not match, so start the timer to turn them down
							this.getTurnDownTimer().start();
						}
						
						else
						{
							scoreValue += 10000;
							getMainFrame().setScore(scoreValue);
							// Five cards match, so remove them from the list (they will remain face up)
							this.getTurnedCardsBuffer().clear();
						}
					}
					
					else
					{
						scoreValue -= 5;
						getMainFrame().setScore(scoreValue);
						// The cards do not match, so start the timer to turn them down
						this.getTurnDownTimer().start();
					}
				}
				
			}
			return true;
		}
		return false;
	}
	
	@Override
	protected boolean isGameOver()
	{
		ArrayList<Card> grid = new ArrayList<Card>();
		for(int i = 0; i < this.getGrid().size(); i++)
		{
			if(!this.getGrid().get(i).isFaceUp())
				grid.add(getGrid().get(i));
		}
		if(grid.size() == 10){
			return true;
		}
		else
			return false;
		}
}