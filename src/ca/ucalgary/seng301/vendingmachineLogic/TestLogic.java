package ca.ucalgary.seng301.vendingmachineLogic;

import java.util.ArrayList;
import java.util.List;

import PurchaseFacade.Coin;
import PurchaseFacade.Product;
import ca.ucalgary.seng301.vendingmachine.hardware.CoinRack;
import ca.ucalgary.seng301.vendingmachine.hardware.DisabledException;
import ca.ucalgary.seng301.vendingmachine.hardware.ProductRack;
import ca.ucalgary.seng301.vendingmachine.hardware.SimulationException;
import ca.ucalgary.seng301.vendingmachine.hardware.VendingMachine;
import static org.junit.Assert.*;

public class TestLogic {
	VendingMachine vm;
	public TestLogic(VendingMachine vendingMachine){
		vm = vendingMachine;	
	}
	
	public void insertCoin(int coin){
		try {
			vm.getCoinSlot().addCoin(new Coin(coin));
		} catch (DisabledException e) {
			fail("Device has been disabled");
			e.printStackTrace();
		}
		
	}
	public Object[] extract(){
		
		List<Object> extractDelivery = new ArrayList<>();
		int deliveryCoin = 0;

		for(Object delivery: vm.getDeliveryChute().removeItems()){

			if(delivery.getClass().getName() == "PurchaseFacade.Coin"){
				deliveryCoin += (Integer)((Coin) delivery).getValue();
			}
			else{
				extractDelivery.add(((Product)delivery).getName());
			}
		}
		extractDelivery.add((Integer)deliveryCoin);
				
		Object[] extract = extractDelivery.toArray();
		return extract;
	}
	
	public Object[] unload(){
		

		List<Object> unloadActual = new ArrayList<>();
		List<Coin> unloadCoin = new ArrayList<>();
		List<Coin> unloadStorage = new ArrayList<>();
		int coinValue = 0;
		int coinsInValue = 0;
		List<Product> unloadPopcan = new ArrayList<>();

		
		
		for(int i = 0; i < vm.getNumberOfCoinRacks(); i++){
			unloadCoin = vm.getCoinRack(i).unloadWithoutEvents();
			if(!unloadCoin.isEmpty()){
			for(Coin coin: unloadCoin)
				coinValue += coin.getValue();
			}	
		}
		unloadActual.add((Integer)coinValue);
		
		unloadStorage = vm.getStorageBin().unloadWithoutEvents();
		if(!unloadStorage.isEmpty()){
			for(Coin coin: unloadStorage)
				coinsInValue += coin.getValue();
		}
		unloadActual.add((Integer)coinsInValue);


		for(int i = 0; i < vm.getNumberOfProductRacks(); i++){	
			unloadPopcan = vm.getProductRack(i).unloadWithoutEvents();
			if(!unloadPopcan.isEmpty())
				unloadActual.add(unloadPopcan.get(0).getName());
		}
		
		return unloadActual.toArray();
	}
	
    
    /**
     * A convenience method for constructing and loading a set of pop cans into
     * the machine.
     * 
     * @param popCounts
     *            A variadic list of ints each representing the number of pops
     *            to create and load into the corresponding rack.
     * @throws SimulationException
     *             If the number of arguments is different than the number of
     *             racks, or if any of the counts are negative.
     */
    public void loadProducts(int... popCounts) {
	if(popCounts.length != vm.getNumberOfProductRacks())
	    throw new SimulationException("Pop counts have to equal number of racks");

	int i = 0;
	for(int popCount : popCounts) {
	    if(popCount < 0)
		throw new SimulationException("Each count must not be negative");

	    ProductRack pcr = vm.getProductRack(i);
	    String name = vm.getProductKindName(i);
	    for(int pops = 0; pops < popCount; pops++)
		pcr.loadWithoutEvents(new Product(name));

	    i++;
	}
    }

    /**
     * A convenience method for constructing and loading a set of coins into the
     * machine.
     * 
     * @param coinCounts
     *            A variadic list of ints each representing the number of coins
     *            to create and load into the corresponding rack.
     * @throws SimulationException
     *             If the number of arguments is different than the number of
     *             racks, or if any of the counts are negative.
     */
    public void loadCoins(int... coinCounts) {
	if(coinCounts.length != vm.getNumberOfCoinRacks())
	    throw new SimulationException("Coin counts have to equal number of racks");

	int i = 0;
	for(int coinCount : coinCounts) {
	    if(coinCount < 0)
		throw new SimulationException("Each count must not be negative");

	    CoinRack cr = vm.getCoinRack(i);
	    int value = vm.getCoinKindForRack(i);
	    for(int coins = 0; coins < coinCount; coins++)
		cr.loadWithoutEvents(new Coin(value));

	    i++;
	}
    }

}
