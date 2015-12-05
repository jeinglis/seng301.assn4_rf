package ca.ucalgary.seng301.vendingmachineLogic;
import ca.ucalgary.seng301.vendingmachine.Coin;
import ca.ucalgary.seng301.vendingmachine.hardware.*;




public class PaymentMethod_Coin extends FundsHandler implements CoinSlotListener {

	VendingMachine vendingMachine;
	
	public PaymentMethod_Coin(VendingMachine vm){
		super();
		vendingMachine = vm;
		vendingMachine.getCoinSlot().register(this);
	}

	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validCoinInserted(CoinSlot coinSlotSimulator, Coin coin) {
		incrementFunds(coin.getValue());
	}

	@Override
	public void coinRejected(CoinSlot coinSlotSimulator, Coin coin) {
		// TODO Auto-generated method stub
		
	}
	

	
	
}
