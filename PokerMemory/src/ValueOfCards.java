public class ValueOfCards {
	
	
	int value = 0;
	
	public int cardValue(String s)
	{		
		switch(s)
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
				
			default:
				value = Integer.valueOf(s);
		}
		return value;
	}
}
