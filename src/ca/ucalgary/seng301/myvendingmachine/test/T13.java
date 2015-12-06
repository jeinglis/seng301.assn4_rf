package ca.ucalgary.seng301.myvendingmachine.test;

import java.util.Arrays;

import ca.ucalgary.seng301.vendingmachine.hardware.*;
import ca.ucalgary.seng301.vendingmachineLogic.TestLogic;
import ca.ucalgary.seng301.vendingmachineLogic.VendingMachineLogic;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class T13 {

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
	public void good_need_to_store_payment() {

		
//		configure("stuff"; 135)
		String [] popNames = {"stuff"};
		Integer [] popCosts = {135};
		vm.configure(Arrays.asList(popNames), Arrays.asList(popCosts));
		
//		load(10, 10, 10, 10; 1)
		int[] coinCounts = {10, 10, 10, 10};
		int[] productCounts = {1};
		testLogic.loadCoins(coinCounts);
		testLogic.loadProducts(productCounts);

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
