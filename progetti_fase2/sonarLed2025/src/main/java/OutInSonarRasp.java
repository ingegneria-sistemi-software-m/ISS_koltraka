package main.java;
import it.unibo.kactor.ActorBasic;
import unibo.basicomm23.mqtt.MqttConnection;
import unibo.basicomm23.utils.CommUtils;

/*
 * Used by 
 */
public class OutInSonarRasp  {
    private final String name = "OutInSonarRasp";
 	protected MqttConnection  mqttConn;
  	protected ActorBasic owner; 
  	protected String topicin;
  	protected String topicout;
 	
 	public OutInSonarRasp(ActorBasic owner, String topicin, String topicout) {
 		this.owner    = owner;
 		this.topicin  = topicin;
 		this.topicout = topicout;
 		connectMqttInOut();
	}
 
	protected void connectMqttInOut() {  
		try {
			mqttConn = owner.getMqtt().getMqttConn();			
 		    if(chcekBrokerConnection()) {
 		    	mqttConn.subscribe(topicin, owner); //genera kernel_rawmsg
 		    	CommUtils.outcyan(name + " | subscribe a "+ topicin + "done " + owner.getName()); 
 		    }
		} catch (Exception e) {
 			e.printStackTrace();
		}	
	}

  	protected boolean chcekBrokerConnection() {
  		while( ! mqttConn.isConnected() ) {
  			CommUtils.outcyan(name + " | waiting for mqttConn ... "  );
  			CommUtils.delay(200);
	  	}
	  	CommUtils.outcyan(name + " | mqtt connection done " + owner.getName()); 
	  	return true;
	}
	
  	/* qua ci andranno eventuali messaggi al programma python che pubblica le misurazioni del sonar */
//	public void display(String msg) {		    
//		try {
//			CommUtils.outcyan(name + " | display to GUI " + msg + " topic=" +  topicout);
//			if( mqttConn != null ) {
//				//CommUtils.outcyan(name + " | display to GUI " + msg + " topic=" +  topicout);
//				//mqttConn.forward(msg);
//				mqttConn.publish("guiin",msg);
//			}
// 
//		} catch (Exception e) {
// 			e.printStackTrace();
//		}		
//	}
}
