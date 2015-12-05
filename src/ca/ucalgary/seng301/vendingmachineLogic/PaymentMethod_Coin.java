package ca.ucalgary.seng301.vendingmachineLogic;
import ca.ucalgary.seng301.vendingmachine.hardware.*;




public class PaymentMethod_Coin {

	VendingMachine vendingMachine;
	
	public PaymentMethod_Coin(VendingMachine vm){
		vendingMachine = vm;
		vendingMachine.getCoinSlot().register(this);

		
	}
	
	vendingMachine.getCoinSlot().register(this);

	
	
}
