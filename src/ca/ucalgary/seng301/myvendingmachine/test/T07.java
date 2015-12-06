package ca.ucalgary.seng301.myvendingmachine.test;

import java.util.Arrays;

import ca.ucalgary.seng301.vendingmachine.hardware.*;
import ca.ucalgary.seng301.vendingmachineLogic.TestLogic;
import ca.ucalgary.seng301.vendingmachineLogic.VendingMachineLogic;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class T07 {

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
	
	
	@Test
	public void good_changing_configuration() {
		
//		configure("A", "B", "C"; 5, 10, 25)
		String [] popNames = {"A", "B", "C"};
		Integer [] popCosts = {5, 10, 25};
		vm.configure(Arrays.asList(popNames), Arrays.asList(popCosts));
		
//		load(1, 1, 2, 0; 1, 1, 1)
		int[] coinCounts = {1, 1, 2, 0};
		int[] productCounts = {1, 1, 1};
		testLogic.loadCoins(coinCounts);
		testLogic.loadProducts(productCounts);
		
//		configure("Coke", "water", "stuff"; 250, 250, 205)
		String [] popNames2 = {"Coke", "water", "stuff"};
		Integer [] popCosts2 = {250, 250, 205};
		vm.configure(Arrays.asList(popNames2), Arrays.asList(popCosts2));
		
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
//		CHECK_DELIVERY(50, "A")
		Object [] extractExpected2 = {"A",new Integer(50)};
		assertArrayEquals(extractExpected2, testLogic.extract() );
		
//		unload()
//		CHECK_TEARDOWN(315; 0; "B", "C")
		Object [] unloadExpected = {new Integer(315),new Integer(0),new String("B"),new String("C")};
		assertArrayEquals( unloadExpected, testLogic.unload());
		
//		load(1, 1, 2, 0; 1, 1, 1)
		int[] coinCounts2 = {1, 1, 2, 0};
		int[] productCounts2 = {1, 1, 1};
		testLogic.loadCoins(coinCounts2);
		testLogic.loadProducts(productCounts2);
		
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
		Object [] extractExpected3 = {"Coke",new Integer(50)};
		assertArrayEquals(extractExpected3, testLogic.extract() );
		
//		unload()
//		CHECK_TEARDOWN(315; 0; "water", "stuff")
		Object [] unloadExpected2 = {new Integer(315),new Integer(0),new String("water"),new String("stuff")};
		assertArrayEquals( unloadExpected2, testLogic.unload());
		

	}
}
