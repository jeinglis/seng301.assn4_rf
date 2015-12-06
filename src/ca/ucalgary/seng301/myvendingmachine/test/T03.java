package ca.ucalgary.seng301.myvendingmachine.test;

import ca.ucalgary.seng301.vendingmachine.hardware.*;
import ca.ucalgary.seng301.vendingmachineLogic.TestLogic;
import ca.ucalgary.seng301.vendingmachineLogic.VendingMachineLogic;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class T03 {

	private VendingMachine vm;
	private VendingMachineLogic vmLogic;
	private TestLogic testLogic;
	@Before
	public void setUp() throws Exception {
		
		//construct(5, 10, 25, 100; 3; 10; 10; 10) 
		int[] coins  = { 5, 10, 25, 100 };
		int buttons  = 3;
		int cRackCap =10;
		int pRackCap =10;
		int receptCap=10;
		vm = new VendingMachine(coins, buttons, cRackCap,pRackCap, receptCap);
		vmLogic = new VendingMachineLogic(vm);
	    vm.getCoinReceptacle().register(vmLogic.getButtonHandler().getChangeHandler());
	    testLogic = new TestLogic(vm);

	}
	
	
	@Test
	public void good_insert_and_press_exact_change() {
		

//		extract()
//		CHECK_DELIVERY(0)
		Object [] extractExpected = {new Integer(0)};
		assertArrayEquals(extractExpected, testLogic.extract());
		
//		unload()
//		CHECK_TEARDOWN(315; 0; "water", "stuff")
		Object [] unloadExpected = {new Integer(0),new Integer(0)};
		assertArrayEquals(unloadExpected,testLogic.unload());

	}

}