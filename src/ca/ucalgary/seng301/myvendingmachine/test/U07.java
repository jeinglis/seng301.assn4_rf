package ca.ucalgary.seng301.myvendingmachine.test;

import java.util.Arrays;

import ca.ucalgary.seng301.vendingmachine.hardware.*;
import ca.ucalgary.seng301.vendingmachineLogic.TestLogic;
import ca.ucalgary.seng301.vendingmachineLogic.VendingMachineLogic;

import org.junit.Before;
import org.junit.Test;

public class U07 {

	private VendingMachine vm;
	private VendingMachineLogic vmLogic;
	@SuppressWarnings("unused")
	private TestLogic testLogic;
	@Before
	public void setUp() throws Exception {

		
//		construct(1; 1; 10; 10; 10)
		int[] coins  = {5, 10, 25,100};
		int buttons  = 3;
		int cRackCap =10;
		int pRackCap =10;
		int receptCap=10;
		vm = new VendingMachine(coins, buttons, cRackCap,pRackCap, receptCap);
		vmLogic = new VendingMachineLogic(vm);
	    testLogic = new TestLogic(vm);
	    

	}

	
	
	@Test(expected = SimulationException.class)
	public void bad_empty_name() {
//	    configure(""; 1)
		String [] popNames = {""};
		Integer [] popCosts = {1};
		vm.configure(Arrays.asList(popNames), Arrays.asList(popCosts));
	}
}
