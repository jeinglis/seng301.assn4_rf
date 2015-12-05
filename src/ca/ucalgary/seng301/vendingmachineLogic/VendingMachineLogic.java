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

import ca.ucalgary.seng301.vendingmachine.Coin;
import ca.ucalgary.seng301.vendingmachine.hardware.*;

public class VendingMachineLogic {
    protected int availableFunds = 0;
    private VendingMachine vendingMachine;
    private ButtonHandler buttonHandler;
    private ChangeHandler changeHandler;
	private AbstractFundsHandler paymentMethod1;
    private MessageHandler messageHandler;
    
    public VendingMachineLogic(VendingMachine vm) {
	vendingMachine = vm;
	
	paymentMethod1 = new PaymentMethod_Coin(vendingMachine);
	buttonHandler = new ButtonHandler(vendingMachine,messageHandler);
	changeHandler = new ChangeHandler(vendingMachine);
	messageHandler = new MessageHandler(vendingMachine);
	
    }
    
    public ChangeHandler getChangeHandler() {
		return changeHandler;
	}




}
