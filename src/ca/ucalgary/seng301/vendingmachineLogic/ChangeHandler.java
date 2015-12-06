package ca.ucalgary.seng301.vendingmachineLogic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ca.ucalgary.seng301.vendingmachine.Coin;
import ca.ucalgary.seng301.vendingmachine.hardware.*;


public class ChangeHandler implements CoinReceptacleListener  {
    private Map<Integer, Integer> valueToIndexMap = new HashMap<>();
    private VendingMachine vendingMachine = null;
    
	public ChangeHandler(VendingMachine vm){
		vendingMachine = vm;
	    valueToIndexMap = new HashMap<>();

	}

    private Map<Integer, List<Integer>> changeHelper(ArrayList<Integer> values, int index, int changeDue) {
    	System.out.printf("int change Helper\n"); //TODO delete this

	if(index >= values.size()){
		System.out.printf("Test returning null from changeHelper\n"); //TODO delete this
	    return null;
	}
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

    protected int deliverChange(int cost, int entered) throws CapacityExceededException, EmptyException, DisabledException {
		System.out.printf("\nin deliver change. cost is %d and entered is %d\n",cost,entered); //TODO delete this

	int changeDue = entered - cost;
	System.out.printf("changeDue %d\n",changeDue); //TODO delete this

	if(changeDue < 0)
	    throw new InternalError("Cost was greater than entered, which should not happen");

	ArrayList<Integer> values = new ArrayList<>();

	for(Integer ck : valueToIndexMap.keySet())
	    values.add(ck);

	Map<Integer, List<Integer>> map = changeHelper(values, 0, changeDue);
	System.out.printf("changeDue 2 %d\n",changeDue); //TODO delete this
	if(changeDue == 0)//TODO added by James remove
		return 0;//TODO added by James remove
	
	List<Integer> res = map.get(changeDue);//TODO error
	System.out.printf("After res error in deliverChange\n"); //TODO delete this

	if(res == null) {
	    // An exact match was not found, so do your best
	    Iterator<Integer> iter = map.keySet().iterator();
	    Integer max = 0;
	    while(iter.hasNext()) {

		Integer temp = iter.next();
    	System.out.printf("Test1 max = %d\n",max); //TODO delete this
		if(temp > max)
		System.out.printf("Test2 temp = %d\n",temp); //TODO delete this

		    max = temp;
	    }
	    res = map.get(max);
    	System.out.printf("Test 3 res = %d\n",res); //TODO delete this
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
