package ca.ucalgary.seng301.vendingmachineLogic;
import java.util.Timer;
import java.util.TimerTask;

import ca.ucalgary.seng301.vendingmachine.hardware.*;


public class ButtonLogic implements ButtonListener{
	VendingMachine vendingMachine = null;
	
	
	public ButtonLogic(VendingMachine vm){
		vendingMachine = vm;
		
		
	}
	
	

	
	for(int i = 0; i < vm.getNumberOfSelectionButtons(); i++) {
	    Button sb = vm.getSelectionButton(i);
	    sb.register(this);
	    buttonToIndex.put(sb, i);
	}
	
	 vm.getReturnButton().register(this);
	buttonToIndex.put(vm.getReturnButton(), vm.getNumberOfSelectionButtons());
	
    @Override
    public void pressed(Button button) {
	Integer index = buttonToIndex.get(button);

	if(index == null)
	    throw new SimulationException("An invalid selection button was pressed");
	
	if(button == vendingMachine.getReturnButton() ){
		if(availableFunds <= 0) return;
		
		try {
			vendingMachine.getCoinReceptacle().returnCoins();
			availableFunds = 0;
			display("default");
		}
		catch(DisabledException | CapacityExceededException e) {
		    throw new SimulationException(e);
		}
	}
	
	int cost = vendingMachine.getProductKindCost(index);
	
	if(cost <= availableFunds) {
	    ProductRack pcr = vendingMachine.getProductRack(index);
	    if(pcr.size() > 0) {
		try {
		    pcr.dispenseProduct();
		    vendingMachine.getCoinReceptacle().storeCoins();
		    availableFunds = deliverChange(cost, availableFunds);
		}
		catch(DisabledException | EmptyException | CapacityExceededException e) {
		    throw new SimulationException(e);
		}
	    }
	}
	else {
	    display("Insufficient Funds");
	    final Timer timer = new Timer();
	    timer.schedule(new TimerTask() {
		@Override
		public void run() {
		    timer.cancel();
		}
	    }, 5000);
	    display("Money In");	
	}
	if (availableFunds == 0)
		display("Default");
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

    
	
	
	
	
//	
//	vm.getReturnButton().register(new ButtonListener() {
//		
//
//
//		@Override
//		public void pressed(Button button) {
//			if(availableFunds <= 0) return;
//			
//				try {
//					vendingMachine.getCoinReceptacle().returnCoins();
//					availableFunds = 0;
//					display("default");
//				}
//				catch(DisabledException | CapacityExceededException e) {
//				    throw new SimulationException(e);
//				}
//		}			
//		
//	});
//	
//	for(int i = 0; i < vm.getNumberOfCoinRacks(); i++) {
//	    int value = vm.getCoinKindForRack(i);
//	    valueToIndexMap.put(value, i);
//	}


}
