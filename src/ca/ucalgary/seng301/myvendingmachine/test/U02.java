package ca.ucalgary.seng301.myvendingmachine.test;

import java.util.Arrays;

import ca.ucalgary.seng301.vendingmachine.hardware.*;
import ca.ucalgary.seng301.vendingmachineLogic.TestLogic;
import ca.ucalgary.seng301.vendingmachineLogic.VendingMachineLogic;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class U02 {

	private VendingMachine vm;
	private VendingMachineLogic vmLogic;
	private TestLogic testLogic;
	@Before
	public void setUp() throws Exception {

		
//		construct(5, 10, 25, 100; 3; 10; 10; 10)
		int[] coins  = {5, 10, 25, 100};
		int buttons  = 3;
		int cRackCap =10;
		int pRackCap =10;
		int receptCap=10;
		vm = new VendingMachine(coins, buttons, cRackCap,pRackCap, receptCap);
		vmLogic = new VendingMachineLogic(vm);
	    testLogic = new TestLogic(vm);

	}
	
	
	@Test(expected = SimulationException.class)
	public void bad_costs_list() {

		
//		configure("Coke", "water", "stuff"; 250, 250 /* the cost of "stuff" is not defined */) // This SHOULD cause an error, but DOES NOT in the dummy!
		String [] popNames = {"Coke", "water", "stuff"};
		Integer [] popCosts = {250, 250};	
		vm.configure(Arrays.asList(popNames), Arrays.asList(popCosts));
	
		
//		load(1, 1, 2, 0; 1, 1, 1)
		int[] coinCounts = {1, 1, 2, 0};
		int[] productCounts = {1, 1, 1};
		testLogic.loadCoins(coinCounts);
		testLogic.loadProducts(productCounts);
		
//		unload()
//		CHECK_TEARDOWN(0; 0) // This passes, but we should not get this far
		Object [] unloadExpected = {new Integer(0),new Integer(0)};
		assertArrayEquals( unloadExpected, testLogic.unload());

	}
}
