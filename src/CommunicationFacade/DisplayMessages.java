package CommunicationFacade;

import ca.ucalgary.seng301.vendingmachineLogic.AbstractFundsHandler;
import ca.ucalgary.seng301.vendingmachineLogic.DisplayHandler;

public class DisplayMessages 
{
	//protected static String defaultMessage = "Drink Pop!";

	public DisplayMessages(){
	}

	public static void setDefaultDisplay(){
		DisplayHandler.display("Drink Pop!");
	}
	public static void setDisplayInsufficientFunds(int cost){
//		String message = "Cost is:";
//		message.concat((productCost.toString()));
//		DisplayHandler.display(message);
		DisplayHandler.display("Cost is" + cost);
	}
	public static void setDisplayFundsIn(){
		int funds = AbstractFundsHandler.getAvailableFunds();
		DisplayHandler.display("Total:" + funds + "units");
	}

}
