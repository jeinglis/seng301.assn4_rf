package ca.ucalgary.seng301.vendingmachineLogic;

import ca.ucalgary.seng301.vendingmachine.hardware.VendingMachine;

public class ExactChangeLight {
	
	VendingMachine vendingMachine;	
	int receptacleCapacity = 10;
	
	public ExactChangeLight(VendingMachine vm){
		vendingMachine = vm;
	}
	
	protected Boolean exactChangeOnlyCheck(){
		// changeLimit = (highestCoinAccepted*receptacleCapacity) - cheapestProduct - credit
		// if (changeLimit > availableCoinsInRacks) exactChangeLight -> ON
		int changeLimit = 0;
		int racksTotal = 0;
		int highestCoinAccepted = 0;
		int cheapestProductPrice = vendingMachine.getProductKindCost(0);
		for(int i = 0; i < vendingMachine.getNumberOfCoinRacks(); i++)
			if(vendingMachine.getCoinKindForRack(i) > highestCoinAccepted)
				highestCoinAccepted = vendingMachine.getCoinKindForRack(i);
		for(int i = 0; i < vendingMachine.getNumberOfProductRacks(); i++)
			if(vendingMachine.getProductKindCost(i) < cheapestProductPrice)
				cheapestProductPrice = vendingMachine.getProductKindCost(i);
		changeLimit = (highestCoinAccepted*receptacleCapacity)-cheapestProductPrice-AbstractFundsHandler.getAvailableFunds();
		for(int i = 0; i < vendingMachine.getNumberOfCoinRacks(); i++)
			for(int j = 0; j < vendingMachine.getCoinRack(i).size(); j++)
				racksTotal += vendingMachine.getCoinKindForRack(i);
		if(changeLimit > racksTotal)
			return true;	
		
		return false;
	}
	
	

}

