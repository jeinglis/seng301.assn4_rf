package ca.ucalgary.seng301.vendingmachineLogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import CommunicationFacade.DisplayMessages;
import ca.ucalgary.seng301.vendingmachine.hardware.*;


public class ButtonHandler implements ButtonListener{
	
	VendingMachine vendingMachine = null;
	private ChangeHandler changeHandler;
	private ExactChangeLight exactChangeLight;
	private Map<Button, Integer> buttonToIndex = new HashMap<>();;

	public ButtonHandler(VendingMachine vm){
		vendingMachine = vm;
		changeHandler = new ChangeHandler(vendingMachine);
		exactChangeLight = new ExactChangeLight(vendingMachine);
		buttonToIndex = new HashMap<>();
		
		//register selection buttons
		for(int i = 0; i < vm.getNumberOfSelectionButtons(); i++) {
		    Button sb = vendingMachine.getSelectionButton(i);
		    sb.register(this);
		    buttonToIndex.put(sb, i);
		}
		
		//register return button
		vm.getReturnButton().register(this);
		buttonToIndex.put(vm.getReturnButton(), vm.getNumberOfSelectionButtons());

	}


    @Override
    public void pressed (Button button) {
    	
	Integer index = buttonToIndex.get(button); 
	
	if(index == null)
	    throw new SimulationException("An invalid selection button was pressed");
	
	if(button == vendingMachine.getReturnButton() ){

		try {
			vendingMachine.getCoinReceptacle().returnCoins();
			AbstractFundsHandler.setAvailableFunds(0);
			System.out.printf("in try");

		} catch (CapacityExceededException | DisabledException e) {
			System.out.printf("in catch");

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return;
	}
	
	int cost = vendingMachine.getProductKindCost(index);
	
	if(cost <= AbstractFundsHandler.getAvailableFunds() ) {
	    ProductRack pcr = vendingMachine.getProductRack(index);
	    if(pcr.size() > 0) {
		    try {
			    pcr.dispenseProduct();
				AbstractFundsHandler.setAvailableFunds(changeHandler.deliverChange(cost, AbstractFundsHandler.getAvailableFunds()));
				vendingMachine.getCoinReceptacle().storeCoins();

			} catch (CapacityExceededException | DisabledException e) {
				e.printStackTrace();
				vendingMachine.enableSafety();

			} catch (EmptyException e) {
				e.printStackTrace();
			}
	    }
	    
	    //if the exact change only condition evaluates to true 
	    //turn on the exact change only light
	    if(exactChangeLight.exactChangeOnlyCheck())
	    	vendingMachine.getExactChangeLight().activate();
	    else
	    	vendingMachine.getExactChangeLight().deactivate();

	    
	}
	else {
	    DisplayMessages.setDisplayInsufficientFunds(cost);;
	    final Timer timer = new Timer();
	    timer.schedule(new TimerTask() {
		@Override
		public void run() {
		    timer.cancel();
		    DisplayMessages.setDisplayFundsIn();
		}
	    }, 5000);
	}
	if (AbstractFundsHandler.empty());
    	DisplayMessages.setDefaultDisplay();
  }

	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
	}
	
	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
	}

}
