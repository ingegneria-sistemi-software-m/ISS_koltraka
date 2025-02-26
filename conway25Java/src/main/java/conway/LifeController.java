package conway;
import java.util.concurrent.TimeUnit;
import conway.devices.ConwayOutput;

public class LifeController {
    private int generationTime = 1000;
    private Life life;
    private IOutDev outdev;

    public LifeController(Life game) {  
        this.life = game;
        this.outdev = new ConwayOutput();	
    }

    
    //Called by ConwayInputMock
    public void start(){
		System.out.println("---------Initial----------");
		//La griglia è visualizzata con un ciclo
		this.life.getGrid().displayGrid(this.outdev);
		play(); 		   	
    }
    
    protected void play() {
		//while (true) {
		for( int i=1;i<=5;i++){
			try {
				TimeUnit.MILLISECONDS.sleep(generationTime);
				System.out.println("---------Epoch --- "+i );
				life.computeNextGen();
				this.life.getGrid().displayGrid(this.outdev);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}    	
    }

	

}
