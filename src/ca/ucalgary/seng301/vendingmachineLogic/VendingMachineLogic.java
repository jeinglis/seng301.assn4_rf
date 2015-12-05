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

	private FundsHandler funds;
    private MessageHandler messageHandler;
    
    public VendingMachineLogic(VendingMachine vm) {
	vendingMachine = vm;
	funds = new FundsHandler(vendingMachine,messageHandler);
	buttonHandler = new ButtonHandler(vendingMachine, funds,messageHandler);
	changeHandler = new ChangeHandler(vendingMachine,funds);
	messageHandler = new MessageHandler(vendingMachine, funds);
    }
    
    public ChangeHandler getChangeHandler() {
		return changeHandler;
	}




}
