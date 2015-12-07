package ca.ucalgary.seng301.vendingmachineLogic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import PaymentMethodFacade.PaymentMethod_Coin;
import ca.ucalgary.seng301.vendingmachine.Coin;
import ca.ucalgary.seng301.vendingmachine.hardware.*;

public class VendingMachineLogic {
    private VendingMachine vendingMachine;
    private ButtonHandler buttonHandler;
    private DisplayHandler displayHandler;
    //private DisplayMessages displayMessages;
    private ExactChangeLight lightHandler;

    
    /*Payment Methods*/
    private AbstractFundsHandler paymentMethods;
    
    public VendingMachineLogic(VendingMachine vm) {
	vendingMachine = vm;
	displayHandler = new DisplayHandler(vendingMachine);
	vendingMachine.getDisplay().register(displayHandler);
	lightHandler = new ExactChangeLight(vendingMachine);
	paymentMethods= new PaymentMethod_Coin(vendingMachine);
	buttonHandler = new ButtonHandler(vendingMachine);

	
	vm.getDisplay().register(displayHandler);

	// vm.getCoinReceptacle().register(this);

    }

	public ButtonHandler getButtonHandler() {
		return buttonHandler;
	}


}
