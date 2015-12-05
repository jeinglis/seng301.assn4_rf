package ca.ucalgary.seng301.vendingmachineLogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import ca.ucalgary.seng301.vendingmachine.hardware.*;


public class ButtonHandler implements ButtonListener{
	
	VendingMachine vendingMachine = null;
	FundsHandler funds;
	private ChangeHandler changeHandler;
	private MessageHandler messageHandler;	
	private Map<Button, Integer> buttonToIndex = new HashMap<>();

	
	public ButtonHandler(VendingMachine vm, FundsHandler inFunds, MessageHandler mh){
		vendingMachine = vm;
		changeHandler = new ChangeHandler(vm,inFunds);
		messageHandler = mh;
		funds = inFunds;
	}
	
	//register selection buttons
	for(int i = 0; i < vm.getNumberOfSelectionButtons(); i++) {
	    Button sb = vendingMachine.getSelectionButton(i);
	    sb.register(this);
	    buttonToIndex.put(sb, i);
	}
	
	//register return button
	vm.getReturnButton().register(this);
	buttonToIndex.put(vm.getReturnButton(), vm.getNumberOfSelectionButtons());
	
    @Override
    public void pressed (Button button) {
	Integer index = buttonToIndex.get(button);

	if(index == null)
	    throw new SimulationException("An invalid selection button was pressed");
	
	if(button == vendingMachine.getReturnButton() ){
		if(funds.getAvailableFunds() <= 0) return;
		
		try {
			vendingMachine.getCoinReceptacle().returnCoins();
			funds.returnFunds();
			
		}
		catch(DisabledException | CapacityExceededException e) {
		    throw new SimulationException(e);
		}
	}
	
	int cost = vendingMachine.getProductKindCost(index);
	
	if(cost <= funds.getAvailableFunds() ) {
	    ProductRack pcr = vendingMachine.getProductRack(index);
	    if(pcr.size() > 0) {
		try {
		    pcr.dispenseProduct();
		    vendingMachine.getCoinReceptacle().storeCoins();
		    funds.setAvailableFunds(changeHandler.deliverChange(cost, funds.getAvailableFunds()));
		}
		catch(DisabledException | EmptyException | CapacityExceededException e) {
		    throw new SimulationException(e);
		}
	    }
	}
	else {
		messageHandler.setDisplay("Insufficient Funds");
	    final Timer timer = new Timer();
	    timer.schedule(new TimerTask() {
		@Override
		public void run() {
		    timer.cancel();
		}
	    }, 5000);
	    messageHandler.setDisplay("Money In");	
	}
	if (funds.empty())
		 messageHandler.setDisplay("Default");
  }



	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
    	vendingMachine.getOutOfOrderLight().deactivate();
	}
	
	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
    	vendingMachine.getOutOfOrderLight().activate();	
	}

}
