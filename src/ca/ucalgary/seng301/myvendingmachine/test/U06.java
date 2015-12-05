package ca.ucalgary.seng301.myvendingmachine.test;


import ca.ucalgary.seng301.vendingmachine.hardware.*;
import ca.ucalgary.seng301.vendingmachineLogic.TestLogic;
import ca.ucalgary.seng301.vendingmachineLogic.VendingMachineLogic;

import org.junit.Before;
import org.junit.Test;

public class U06 {

	private VendingMachine vm;
	private VendingMachineLogic vmLogic;
	@SuppressWarnings("unused")
	private TestLogic testLogic;
	@Before
	public void setUp() throws Exception {

		
//		construct(5, 10, 25, 100; 3; 10; 10; 10)
		int[] coins  = {5, 10, 25,100};
		int buttons  = 3;
		int cRackCap =10;
		int pRackCap =10;
		int receptCap=10;
		vm = new VendingMachine(coins, buttons, cRackCap,pRackCap, receptCap);
		vmLogic = new VendingMachineLogic(vm);
	    vm.getCoinReceptacle().register(vmLogic.getChangeHandler());
	    testLogic = new TestLogic(vm);
	}

	
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void bad_button_number() {

//		press(3)
		vm.getSelectionButton(3).press();
		
	}
}
