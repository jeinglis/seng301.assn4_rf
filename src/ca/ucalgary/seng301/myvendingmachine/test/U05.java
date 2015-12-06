package ca.ucalgary.seng301.myvendingmachine.test;


import ca.ucalgary.seng301.vendingmachine.hardware.*;
import ca.ucalgary.seng301.vendingmachineLogic.TestLogic;
import ca.ucalgary.seng301.vendingmachineLogic.VendingMachineLogic;

import org.junit.Test;


public class U05 {

	private VendingMachine vm;
	private VendingMachineLogic vmLogic;
	@SuppressWarnings("unused")
	private TestLogic testLogic;

	@Test(expected = SimulationException.class)
	public void bad_coin_kind() {
		
//		construct(0;1; 10; 10; 10)
		int[] coins  = {0};
		int buttons  = 1;
		int cRackCap =10;
		int pRackCap =10;
		int receptCap=10;
		vm = new VendingMachine(coins, buttons, cRackCap,pRackCap, receptCap);
		vmLogic = new VendingMachineLogic(vm);
	    vm.getCoinReceptacle().register(vmLogic.getButtonHandler().getChangeHandler());
	    testLogic = new TestLogic(vm);
	}
}
