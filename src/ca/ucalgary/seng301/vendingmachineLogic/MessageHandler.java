package ca.ucalgary.seng301.vendingmachineLogic;

import ca.ucalgary.seng301.vendingmachine.hardware.*;

public class MessageHandler 
{
	VendingMachine vendingMachine = null;
	Display disp = null;
	FundsHandler funds;
	
	
	public MessageHandler(VendingMachine vm, FundsHandler inFunds){
		vendingMachine = vm;
		funds = inFunds;
	    disp = vendingMachine.getDisplay();
	    disp.display("Drink Pop!");	
	}
	
	public void setDisplayMessages(){}
	
	public void setLED(){}
	
	
	public void setDisplay(String state, int cost){
		disp.display("cost is:");
	}
	
	public void setDisplay(String state){
		switch (state){
		
		case "Default":
			disp.display("Drink Pop!");
			break;
		case "Coins In":
			disp.display("total "+ funds.getAvailableFunds() +"units");
			break;
	}
	

	}
}