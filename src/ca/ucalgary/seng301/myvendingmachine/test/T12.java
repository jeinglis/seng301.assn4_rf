package ca.ucalgary.seng301.myvendingmachine.test;

import java.util.Arrays;

import ca.ucalgary.seng301.vendingmachine.hardware.*;
import ca.ucalgary.seng301.vendingmachineLogic.TestLogic;
import ca.ucalgary.seng301.vendingmachineLogic.VendingMachineLogic;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class T12 {

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
	    vm.getCoinReceptacle().register(vmLogic.getButtonHandler().getChangeHandler());
	    testLogic = new TestLogic(vm);


	}
	
	
	@Test
	public void goot_approximate_change_with_credit() {

		
//		configure("stuff"; 140)
		String [] popNames = {"stuff"};
		Integer [] popCosts = {140};
		vm.configure(Arrays.asList(popNames), Arrays.asList(popCosts));
		
//		load(0, 5, 1, 1; 1)
		int[] coinCounts = {0, 5, 1, 1};
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
//		CHECK_DELIVERY(155, "stuff")
		Object [] extractExpected = {"stuff",new Integer(155)};
		assertArrayEquals(extractExpected, testLogic.extract() );

//		unload()
//		CHECK_TEARDOWN(320; 0)
		Object [] unloadExpected = {new Integer(320),new Integer(0)};
		assertArrayEquals( unloadExpected, testLogic.unload());
		
		
//		load(10, 10, 10, 10; 1)
		int[] coinCounts2 = {10, 10, 10, 10};
		int[] productCounts2 = {1};
		testLogic.loadCoins(coinCounts2);
		testLogic.loadProducts(productCounts2);
		
//		insert(25)
//		insert(100)
//		insert(10)		
		testLogic.insertCoin(25);
		testLogic.insertCoin(100);
		testLogic.insertCoin(10);
		
//		press(0)
		vm.getSelectionButton(0).press();
		
//		extract()
//		CHECK_DELIVERY(0, "stuff")
		Object [] extractExpected2 = {"stuff",new Integer(0)};
		assertArrayEquals(extractExpected2, testLogic.extract() );

//		unload()
//		CHECK_TEARDOWN(1400; 135)
		Object [] unloadExpected2 = {new Integer(1400),new Integer(135)};
		assertArrayEquals( unloadExpected2, testLogic.unload());
		
	}
}
