public class ScoreLabel extends TurnsTakenCounterLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// data fields
	private int scoreValue = 0;
	//private  String DESCRIPTION;
	
	public ScoreLabel()
	{
		super();
		reset();
	}
	
	public int getScore()
	{
		return this.scoreValue;
	}
	
	/**
	 * Update the text label with the current counter value
	*/
	private void update()
	{
		this.setText("" + this.getScore());
	}
	
	/**
	 * Increments the counter and updates the text label
	*/
	public void increment(int amount)
	{
		scoreValue = getScore() + amount;
		update();
	}
	
	/**
	 * Resets the counter to zero and updates the text label
	*/
	public void reset()
	{
		this.scoreValue = 0;
		update();
	}
}