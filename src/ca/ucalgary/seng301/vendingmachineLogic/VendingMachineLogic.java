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

import CommunicationFacade.DisplayHandler;
import CommunicationFacade.LEDHandler;
import CommunicationFacade.MessageHandler;
import FundsFacade.AbstractFundsHandler;
import FundsFacade.PaymentMethod_Coin;
import PurchaseFacade.ButtonHandler;
import PurchaseFacade.Coin;
import ca.ucalgary.seng301.vendingmachine.hardware.*;

public class VendingMachineLogic {
    protected int availableFunds = 0;
    private VendingMachine vendingMachine;
    private ButtonHandler buttonHandler;
    private DisplayHandler displayListener;
    private MessageHandler messageHandler;
    private LEDHandler ledHandler;
    
    /*Payment Methods*/
    private AbstractFundsHandler paymentMethod1;
    
    public VendingMachineLogic(VendingMachine vm) {
	vendingMachine = vm;
	paymentMethod1 = new PaymentMethod_Coin(vendingMachine);
	displayListener = new DisplayHandler();
	messageHandler = new MessageHandler(vendingMachine);
	ledHandler = new LEDHandler(vendingMachine);
	buttonHandler = new ButtonHandler(vendingMachine);
	
	//messageHandler = new MessageHandler(vendingMachine);
	
    }

	public ButtonHandler getButtonHandler() {
		return buttonHandler;
	}


}
