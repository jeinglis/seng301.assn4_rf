package ca.ucalgary.seng301.vendingmachineLogic;

import CommunicationFacade.DisplayMessages;


public abstract class AbstractFundsHandler {
	protected static int availableFunds;

	public AbstractFundsHandler(){
		availableFunds =0;
	}
	
	protected void incrementFunds(int value){
		availableFunds += value;
	    DisplayMessages.setDisplayFundsIn();
	}
	protected static void decrementFunds(int value){
		availableFunds -= value;
	}

	public static int getAvailableFunds() {
		return availableFunds;
	}
	public static void setAvailableFunds(int value) {
		availableFunds = value;
	}

	public static Boolean empty(){
		if(availableFunds == 0)
			return true;
		else
			return false;
	}
	
	public void returnFunds() {
	}

}
