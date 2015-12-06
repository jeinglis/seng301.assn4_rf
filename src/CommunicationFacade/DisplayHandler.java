package CommunicationFacade;

import ca.ucalgary.seng301.vendingmachine.hardware.AbstractHardware;
import ca.ucalgary.seng301.vendingmachine.hardware.AbstractHardwareListener;
import ca.ucalgary.seng301.vendingmachine.hardware.Display;
import ca.ucalgary.seng301.vendingmachine.hardware.DisplayListener;

public class DisplayHandler implements DisplayListener {
	


	@Override
	public void enabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractHardware<AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageChange(Display display, String oldMsg, String newMsg) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
