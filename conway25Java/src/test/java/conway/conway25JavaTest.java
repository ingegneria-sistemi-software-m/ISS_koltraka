package conway;

import org.junit.Assert;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import conway.devices.ConwayInputMock;
import conway.devices.ConwayOutput;

//By default, JUnit comes with a bundled copy of hamcrest-core

public class conway25JavaTest {
	private static ConwayInputMock cim;
	private static Life life;
	private static LifeController cc;

	@BeforeClass
	public static void setup() {
		System.out.println("setup");
    	//configureTheSystem
        life = new Life( 3,3 );
        cc   = new LifeController(life);   
        cim  = new ConwayInputMock(cc,life);		
	}
	
	@After
	public void down() {
		System.out.println("--- done ---");
	}
	
	@Test
	public void test1() {
		System.out.println("prima");
		
		life.getGrid().switchCellState( 0, 1 );
		life.getGrid().switchCellState( 1, 1 );
		life.getGrid().switchCellState( 2, 1 );	
		life.getGrid().displayGrid(new ConwayOutput());
		
		life.computeNextGen();
		
		Assert.assertTrue("La cella (1,0) dovrebbe essere viva", life.getGrid().getCellState(1, 0));
		Assert.assertTrue("La cella (1,1) dovrebbe essere viva", life.getGrid().getCellState(1, 1));
		Assert.assertTrue("La cella (1,2) dovrebbe essere viva", life.getGrid().getCellState(1, 2));	
		System.out.println("dopo");
		life.getGrid().displayGrid(new ConwayOutput());
	}
}

//Con gradlew test, controllare - logs/apptest.log - build/reports/tests/test/index.html

