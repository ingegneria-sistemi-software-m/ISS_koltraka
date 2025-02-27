package conway;

import conway.devices.ConwayInputMock;

public class MainConway  {

    public static void main(String[] args) {
    	//configureTheSystem
        Life life           = new Life( 3,3 );
        LifeController cc   = new LifeController(life);   
        ConwayInputMock cim = new ConwayInputMock(cc, life);
        //start the system
        cim.simulateUserControl();
    }

}