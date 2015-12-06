package ca.ucalgary.seng301.vendingmachineLogic;


public abstract class AbstractFundsHandler {
	protected static int availableFunds;

	public AbstractFundsHandler(){
	}
	
	protected void incrementFunds(int value){
		//messageHandler.setDisplay("Money In");
		availableFunds += value;
	}
	protected static void decrementFunds(int value){
		availableFunds -=value;
	}

	protected static int getAvailableFunds() {
		return availableFunds;
	}
	protected static void setAvailableFunds(int value) {
		availableFunds = value;
	}

	protected static Boolean empty(){
		if(availableFunds == 0)
			return true;
		else
			return false;
	}

}
