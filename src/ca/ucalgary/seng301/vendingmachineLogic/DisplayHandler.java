package ca.ucalgary.seng301.vendingmachineLogic;

import ca.ucalgary.seng301.vendingmachine.hardware.AbstractHardware;
import ca.ucalgary.seng301.vendingmachine.hardware.AbstractHardwareListener;
import ca.ucalgary.seng301.vendingmachine.hardware.Display;
import ca.ucalgary.seng301.vendingmachine.hardware.DisplayListener;
import ca.ucalgary.seng301.vendingmachine.hardware.VendingMachine;

public class DisplayHandler implements DisplayListener {
	private static VendingMachine vm;
	private static String oldMsg;
	//private Boolean defaultIsOld = true;


	public DisplayHandler(VendingMachine vendingMachine){
		vm= vendingMachine;
		//vm.getDisplay().register(this);
		vm.getDisplay().display("Drink Pop!");
	}
	
	
	public static String getOldMsg() {
		return oldMsg;
	}


	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
		vm.getOutOfOrderLight().deactivate();

	}

	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
		vm.getOutOfOrderLight().activate();
	}

	@Override
	public void messageChange(Display display, String oldMsg, String newMsg) {
		if(!oldMsg.equals(newMsg)){
			DisplayHandler.oldMsg=oldMsg;
		}

	}
	public static void display(String msg){
		vm.getDisplay().display(msg);
	}





}
