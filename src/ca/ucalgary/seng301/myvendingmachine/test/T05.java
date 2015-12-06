package ca.ucalgary.seng301.myvendingmachine.test;

import java.util.Arrays;

import ca.ucalgary.seng301.vendingmachine.hardware.*;
import ca.ucalgary.seng301.vendingmachineLogic.TestLogic;
import ca.ucalgary.seng301.vendingmachineLogic.VendingMachineLogic;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class T05 {

	private VendingMachine vm;
	private VendingMachineLogic vmLogic;
	private TestLogic testLogic;
	@Before
	public void setUp() throws Exception {
		
		
		//construct(100, 5, 25, 10; 3; 2; 10; 10)
		int[] coins  = {100, 5, 25, 10};
		int buttons  = 3;
		int cRackCap = 2;
		int pRackCap =10;
		int receptCap=10;
		vm = new VendingMachine(coins, buttons, cRackCap,pRackCap, receptCap);
		vmLogic = new VendingMachineLogic(vm);
	    testLogic = new TestLogic(vm);


	}
	
	
	@Test
	public void good_scrambled_coin_kinds() {
		
		
		//configure("Coke", "water", "stuff"; 250, 250, 205)
		String [] popNames = {"Coke", "water", "stuff"};
		Integer [] popCosts = {250, 250, 205};
		vm.configure(Arrays.asList(popNames), Arrays.asList(popCosts));
		
		//load(0, 1, 2, 1; 1, 1, 1)
		int[] coinCounts = {0, 1, 2, 1};
		int[] productCounts = {1, 1, 1};
		testLogic.loadCoins(coinCounts);
		testLogic.loadProducts(productCounts);

//		press(0)
		vm.getSelectionButton(0).press();
		
//		extract()
//		CHECK_DELIVERY(0)
		Object [] extractExpected = {new Integer(0)};
		assertArrayEquals(extractExpected, testLogic.extract() );
		
//		insert(100)
//		insert(100)
//		insert(100)		
		testLogic.insertCoin(100);
		testLogic.insertCoin(100);
		testLogic.insertCoin(100);

//		press(0)
		vm.getSelectionButton(0).press();
		
//		extract()
//		CHECK_DELIVERY(50, "Coke")
		Object [] extractExpected2 = {"Coke",new Integer(50)};
		assertArrayEquals(extractExpected2, testLogic.extract() );
		
//		unload()
//		CHECK_TEARDOWN(215; 100; "water", "stuff")
		Object [] unloadExpected = {new Integer(215),new Integer(100),new String("water"),new String("stuff")};
		assertArrayEquals( unloadExpected, testLogic.unload());

	}
}
