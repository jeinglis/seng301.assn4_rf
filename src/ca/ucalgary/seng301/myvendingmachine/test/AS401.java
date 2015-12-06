package ca.ucalgary.seng301.myvendingmachine.test;

import java.util.Arrays;

import ca.ucalgary.seng301.vendingmachine.hardware.*;
import ca.ucalgary.seng301.vendingmachineLogic.TestLogic;
import ca.ucalgary.seng301.vendingmachineLogic.VendingMachineLogic;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AS401{

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
	public void good_Return_Button_Before_Purchase() {
		
		//configure("Coke", "water","stuff"; 250, 250, 205)
		String [] popNames = {"Coke","water","stuff"};
		Integer [] popCosts = {250,250,205};
		vm.configure(Arrays.asList(popNames), Arrays.asList(popCosts));
		
		//load(1, 1, 2, 0; 1, 1, 1)
		int[] coinCounts = {1,1,2,0};
		int[] productCounts = {1,1,1};
		testLogic.loadCoins(coinCounts);
		testLogic.loadProducts(productCounts);
		//vm.getDisplay().
		
//		insert(100)
//		insert(100)
//		insert(25)
//		insert(25)
		testLogic.insertCoin(100);
		testLogic.insertCoin(100);
		testLogic.insertCoin(25);
		testLogic.insertCoin(25);
		vm.getReturnButton().press();


//		press(0)
		vm.getSelectionButton(0).press();
		
//		extract()
		Object [] extractExpected = {new Integer(250)};

		assertArrayEquals(extractExpected, testLogic.extract());
		
//		unload()
//		CHECK_TEARDOWN(65; 0;"Coke", "water", "stuff")
		Object [] unloadExpected = {new Integer(65),new Integer(0),new String("Coke"),new String("water"),new String("stuff")};
		assertArrayEquals( unloadExpected, testLogic.unload());

	}

}
