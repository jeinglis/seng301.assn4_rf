package FundsFacade;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import PurchaseFacade.Coin;
import ca.ucalgary.seng301.vendingmachine.hardware.*;


public class ChangeHandler implements CoinReceptacleListener  {
    private Map<Integer, Integer> valueToIndexMap = new HashMap<>();
    private VendingMachine vendingMachine;
    
	public ChangeHandler(VendingMachine vm){
		vendingMachine = vm;	    
	    vm.getCoinReceptacle().register(this);
		for(int i = 0; i < vm.getNumberOfCoinRacks(); i++) {
		    int value = vm.getCoinKindForRack(i);
		    valueToIndexMap.put(value, i);
		}
	 }

    public Map<Integer, List<Integer>> changeHelper(ArrayList<Integer> values, int index, int changeDue) {
	if(index >= values.size())
	    return null;

	int value = values.get(index);
	Integer ck = valueToIndexMap.get(value);
	CoinRack cr = vendingMachine.getCoinRack(ck);

	Map<Integer, List<Integer>> map = changeHelper(values, index + 1, changeDue);

	if(map == null) {
	    map = new TreeMap<>(new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
		    return o2 - o1;
		}
	    });
	    map.put(0, new ArrayList<Integer>());
	}

	Map<Integer, List<Integer>> newMap = new TreeMap<>(map);
	for(Integer total : map.keySet()) {
	    List<Integer> res = map.get(total);

	    for(int count = cr.size(); count >= 0; count--) {
		int newTotal = count * value + total;
		if(newTotal <= changeDue) {
		    List<Integer> newRes = new ArrayList<>();
		    if(res != null)
			newRes.addAll(res);

		    for(int i = 0; i < count; i++)
			newRes.add(ck);

		    newMap.put(newTotal, newRes);
		}
	    }
	}

	return newMap;
    }

    public int deliverChange(int cost, int entered) throws CapacityExceededException, EmptyException, DisabledException {
	int changeDue = entered - cost;

	
	if(changeDue < 0)
	    throw new InternalError("Cost was greater than entered, which should not happen");

	ArrayList<Integer> values = new ArrayList<>();
	for(Integer ck : valueToIndexMap.keySet())
	    values.add(ck);

	Map<Integer, List<Integer>> map = changeHelper(values, 0, changeDue);

	List<Integer> res = map.get(changeDue);
	if(res == null) {
	    // An exact match was not found, so do your best
	    Iterator<Integer> iter = map.keySet().iterator();
	    Integer max = 0;
	    while(iter.hasNext()) {
		Integer temp = iter.next();
		if(temp > max)
		    max = temp;
	    }
	    res = map.get(max);
	}

	for(Integer ck : res) {
	    CoinRack cr = vendingMachine.getCoinRack(ck);
	    cr.releaseCoin();
	    changeDue -= vendingMachine.getCoinKindForRack(ck);
	}

	return changeDue;
    }
     

	@Override
	public void coinAdded(CoinReceptacle receptacle, Coin coin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void coinsRemoved(CoinReceptacle receptacle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void coinsFull(CoinReceptacle receptacle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}

    
	
	
}
