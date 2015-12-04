package ca.ucalgary.seng301.vendingmachineLogic;
import ca.ucalgary.seng301.vendingmachine.hardware.*;




public class PaymentMethod_Coin extends CurrencyHandler {

	public PaymentMethod_Coin(VendingMachine vendingMachine){
		vendingMachine.getCoinSlot().register(this);

		
	}
	
	vm.getCoinSlot().register(this);

	
	
}
