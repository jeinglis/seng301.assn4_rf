/*
 * I have excluded this test from my Test Suite 
 * in a hardware setting. It doesn't make sense to test
 * whether you are able to configure before constructing,
 * since that would be impossible anyway.
 */





package ca.ucalgary.seng301.myvendingmachine.test;

import java.util.Arrays;

import ca.ucalgary.seng301.vendingmachine.hardware.*;
import ca.ucalgary.seng301.vendingmachineLogic.TestLogic;
import ca.ucalgary.seng301.vendingmachineLogic.VendingMachineLogic;
import static org.junit.Assert.*;

import org.junit.Test;

public class U01 {

	private VendingMachine vm;
	private VendingMachineLogic vmLogic;
	private TestLogic testLogic;

	@Test(expected = NullPointerException.class)
	public void bad_configure_before_construct() {
		
		
//		configure("Coke", "water", "stuff"; 250, 250, 205) // This SHOULD cause an error, but DOES NOT!
		String [] popNames = {"Coke", "water", "stuff"};
		Integer [] popCosts = {250, 250, 205};
		vm.configure(Arrays.asList(popNames), Arrays.asList(popCosts));


//		construct(5, 10, 25, 100; 3; 10; 10; 10)
		int[] coins  = {5, 10, 25, 100};
		int buttons  = 3;
		int cRackCap =10;
		int pRackCap =10;
		int receptCap=10;
		vm = new VendingMachine(coins, buttons, cRackCap,pRackCap, receptCap);
		vmLogic = new VendingMachineLogic(vm);
	    vm.getCoinReceptacle().register(vmLogic);
	    testLogic = new TestLogic(vm);

		
//		load(1, 1, 2, 0; 1, 1, 1)
		int[] coinCounts = {1, 1, 2, 0};
		int[] productCounts = {1, 1, 1};
		testLogic.loadCoins(coinCounts);
		testLogic.loadProducts(productCounts);

//		unload()
//		CHECK_TEARDOWN(65; 0; "Coke", "water", "stuff") // This causes an error for the dummy but should not
		Object [] unloadExpected = {new Integer(65),new Integer(0),new String("Coke"),new String("water"),new String("stuff")};
		assertArrayEquals( unloadExpected, testLogic.unload());

	}
}
