package PaymentMethodFacade;
import ca.ucalgary.seng301.vendingmachine.Coin;
import ca.ucalgary.seng301.vendingmachine.hardware.*;
import ca.ucalgary.seng301.vendingmachineLogic.AbstractFundsHandler;




public class PaymentMethod_Coin extends AbstractFundsHandler implements CoinSlotListener, CoinRackListener{

	VendingMachine vendingMachine;
	int valueOfCoinsIn;
	
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
		valueOfCoinsIn +=coin.getValue();

	}

	@Override
	public void coinRejected(CoinSlot coinSlotSimulator, Coin coin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void returnFunds() {
		try {
			vendingMachine.getCoinReceptacle().returnCoins();
			//availableFunds = 0;
			availableFunds -= valueOfCoinsIn;
		} catch (CapacityExceededException | DisabledException e) {
			e.printStackTrace();
			System.out.println("something fucked up");
			vendingMachine.enableSafety();

		}

		
	}

	@Override
	public void coinsFull(CoinRack rack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinsEmpty(CoinRack rack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinAdded(CoinRack rack, Coin coin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinRemoved(CoinRack rack, Coin coin) {
		// TODO Auto-generated method stub
		
	}


	
	
}
