package ca.ucalgary.seng301.vendingmachineLogic;
import ca.ucalgary.seng301.vendingmachine.hardware.*;
import java.util.*;



public abstract class FundsHandler {
	protected static int availableFunds;

	public FundsHandler(){
	}
	
	public void incrementFunds(int value){
		//messageHandler.setDisplay("Money In");
		availableFunds += value;
	}
	public void decrementFunds(int value){
		availableFunds -=value;
	}

	public static int getAvailableFunds() {
		return availableFunds;
	}
	public void setAvailableFunds(int value) {
		availableFunds = value;
	}

	public Boolean empty(){
		if(availableFunds == 0)
			return true;
		else
			return false;
	}

}
