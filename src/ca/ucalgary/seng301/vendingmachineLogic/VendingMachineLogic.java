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
    package int availableFunds = 0;
    private VendingMachine vendingMachine;
    private Map<Button, Integer> buttonToIndex = new HashMap<>();
    private Map<Integer, Integer> valueToIndexMap = new HashMap<>();
    private ButtonLogic buttonLogic;
    private MessageCentre messageCentre;
    private ChangeLogic changeLogic;
    
    public VendingMachineLogic(VendingMachine vm) {
	vendingMachine = vm;



	
	//register selection buttons
	

	
	//register returnButton
	
	// vm.getReturnButton().register(this);
	//buttonToIndex.put(vm.getReturnButton(), vm.getNumberOfSelectionButtons());
	
	

    }


    
//TODO make display return to drink pop state when coins returned or amount in is 0
//TODO place hardware out of order code in all enable/disable functions for all hardware
//TODO place exact change LED logic somewhere
//TODO I need to fix the display for insufficient funds how do I put the product cost in

    


}
