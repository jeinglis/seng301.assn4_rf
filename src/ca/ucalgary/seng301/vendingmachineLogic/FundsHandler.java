package ca.ucalgary.seng301.vendingmachineLogic;
import ca.ucalgary.seng301.vendingmachine.hardware.*;
import java.util.*;



public class FundsHandler {
	private VendingMachine vendingMachine = null;
	private int availableFunds;
	private MessageHandler messageHandler;
	

	public FundsHandler(VendingMachine vm, MessageHandler mh){
		vendingMachine = vm;
		messageHandler = mh;
		availableFunds = 0;
	}
	
	public void insertFunds(int value){
		messageHandler.setDisplay("Money In");
	}
	
	public void returnFunds(){	
	}
	
	public int getAvailableFunds() {
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
