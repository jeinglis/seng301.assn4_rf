package ca.ucalgary.seng301.vendingmachineLogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import ca.ucalgary.seng301.vendingmachine.hardware.*;


public class ButtonHandler implements ButtonListener{
	
	VendingMachine vendingMachine = null;
	private ChangeHandler changeHandler;
	private MessageHandler messageHandler;	
	private Map<Button, Integer> buttonToIndex;



	public ButtonHandler(VendingMachine vm,MessageHandler mh){
		vendingMachine = vm;
		messageHandler = mh;		
		changeHandler = new ChangeHandler(vendingMachine);
		buttonToIndex = new HashMap<>();
		
		//register selection buttons
		for(int i = 0; i < vm.getNumberOfSelectionButtons(); i++) {
		    Button sb = vendingMachine.getSelectionButton(i);
		    sb.register(this);
		    buttonToIndex.put(sb, i);
		   System.out.printf("button %d registered \n",i); //TODO delete this
		}
//		
//		//register return button
//		vm.getReturnButton().register(this);
//		buttonToIndex.put(vm.getReturnButton(), vm.getNumberOfSelectionButtons());
//		

	}
	
    
    public ChangeHandler getChangeHandler() {
		return changeHandler;
	}




    @Override
    public void pressed (Button button) {
	Integer index = buttonToIndex.get(button); 
	System.out.printf("%d pressed \n",index); //TODO delete this
	if(index == null)
	    throw new SimulationException("An invalid selection button was pressed");
	
//	if(button == vendingMachine.getReturnButton() ){
//		if(AbstractFundsHandler.getAvailableFunds() <= 0) return;
//		
//		try {
//			vendingMachine.getCoinReceptacle().returnCoins();
//			AbstractFundsHandler.setAvailableFunds(0);
//		}
//		catch(DisabledException | CapacityExceededException e) {
//		    throw new SimulationException(e);
//		}
//	}
	
	int cost = vendingMachine.getProductKindCost(index);
	System.out.printf(" item costs %d \n",cost); //TODO delete this

	
	
	if(cost <= AbstractFundsHandler.getAvailableFunds() ) {
		System.out.printf("in the correct if available funds = %d \n",AbstractFundsHandler.getAvailableFunds()); //TODO delete this
	    ProductRack pcr = vendingMachine.getProductRack(index);
	    if(pcr.size() > 0) {
		try {
		    pcr.dispenseProduct();
		    vendingMachine.getCoinReceptacle().storeCoins();
		    AbstractFundsHandler.setAvailableFunds(changeHandler.deliverChange(cost, AbstractFundsHandler.getAvailableFunds()));
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
	if (AbstractFundsHandler.empty())
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
