public class ScorePerCard {
	
	
	long value = 0;
	
	public long RankCardValue(String s)
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
				value = 0;
		}
		return value;
	}
	
	public long SuitCardValue(String s){
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
