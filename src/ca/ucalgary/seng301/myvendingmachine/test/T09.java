package ca.ucalgary.seng301.myvendingmachine.test;

import java.util.Arrays;

import ca.ucalgary.seng301.vendingmachine.hardware.*;
import ca.ucalgary.seng301.vendingmachineLogic.TestLogic;
import ca.ucalgary.seng301.vendingmachineLogic.VendingMachineLogic;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class T09 {

	private VendingMachine vm;
	private VendingMachineLogic vmLogic;
	private TestLogic testLogic;
	@Before
	public void setUp() throws Exception {
			
		
		
//		construct(5, 10, 25, 100; 1; 10; 10; 10)
		int[] coins  = {5, 10, 25, 100};
		int buttons  = 1;
		int cRackCap =10;
		int pRackCap =10;
		int receptCap=10;
		vm = new VendingMachine(coins, buttons, cRackCap,pRackCap, receptCap);
		vmLogic = new VendingMachineLogic(vm);
	    vm.getCoinReceptacle().register(vmLogic);
	    testLogic = new TestLogic(vm);


	}
	
	
	@Test
	public void good_insert_and_press_exact_change() {

		
//		configure("stuff"; 140)
		String [] popNames = {"stuff"};
		Integer [] popCosts = {140};
		vm.configure(Arrays.asList(popNames), Arrays.asList(popCosts));
		
//		load(1, 6, 1, 1; 1)
		int[] coinCounts = {1, 6, 1, 1};
		int[] productCounts = {1};
		testLogic.loadCoins(coinCounts);
		testLogic.loadProducts(productCounts);
		
//		insert(100)
//		insert(100)
//		insert(100)		
		testLogic.insertCoin(100);
		testLogic.insertCoin(100);
		testLogic.insertCoin(100);

//		press(0)
		vm.getSelectionButton(0).press();
		
//		extract()
//		CHECK_DELIVERY(160, "stuff")
		Object [] extractExpected2 = {"stuff",new Integer(160)};
		assertArrayEquals(extractExpected2, testLogic.extract() );
		
//		unload()
//		CHECK_TEARDOWN(330; 0)
		Object [] unloadExpected = {new Integer(330),new Integer(0)};
		assertArrayEquals( unloadExpected, testLogic.unload());

	}
}
