import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class FlushLevel extends RankTrioLevel {
	
	long scoreValue = 0;
	ScorePerCard scorePerCard = new ScorePerCard();

	protected FlushLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame) {
		super(validTurnTime, mainFrame);
		this.getTurnsTakenCounter().setDifficultyModeLabel("Flush Level");
		this.setCardsToTurnUp(5);
		this.setCardsPerRow(10);
		this.setRowsPerGrid(5);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void makeDeck() {
		
		//back card
		ImageIcon backIcon = this.getCardIcons()[this.getTotalCardsPerDeck()];

		int cardsToAdd[] = new int[getRowsPerGrid() * getCardsPerRow()];
		for(int i = 0; i < (getRowsPerGrid() * getCardsPerRow()); i++)
		{
			cardsToAdd[i] = i;
		}

		// randomize the order of the deck
		this.randomizeIntArray(cardsToAdd);

		// make each card object
		for(int i = 0; i < cardsToAdd.length; i++)
		{
			// number of the card, randomized
			int num = cardsToAdd[i];
			// make the card object and add it to the panel
			String rank = cardNames[num].substring(0, 1);
			String suit = cardNames[num].substring(1, 2);
			this.getGrid().add( new Card(this, this.getCardIcons()[num], backIcon, num, rank, suit));
		}
	}
	
	@Override
	protected boolean turnUp(Card card) {
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
				if((card.getSuit().equals(otherCard1.getSuit())) && (card.getSuit().equals(otherCard2.getSuit())) 
				&& (card.getSuit().equals(otherCard3.getSuit())) && (card.getSuit().equals(otherCard4.getSuit())))
				{
					scoreValue += (700 + scorePerCard.CardValue(card.getRank()) +
								   scorePerCard.CardValue(otherCard1.getRank()) +
								   scorePerCard.CardValue(otherCard2.getRank()) + 
								   scorePerCard.CardValue(otherCard3.getRank()) +
								   scorePerCard.CardValue(otherCard4.getRank()));
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
			return true;
		}
		return false;
	}
}