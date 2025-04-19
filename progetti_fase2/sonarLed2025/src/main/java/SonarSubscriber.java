package main.java;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import unibo.basicomm23.utils.CommUtils;


public class SonarSubscriber {
    String brokerUrl = "tcp://localhost:1883";
    String clientId  = "sonar_subscriber"; 
    String topic     = "sensor/sonar"; 

    public void doJob() {
        try {
            MqttClient client = new MqttClient(brokerUrl, clientId);
 
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    CommUtils.outblue("Connessione persa: " + cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                   System.out.println("messageArrived | Payload: " + message.getPayload()); //byte[]
                   CommUtils.outmagenta("messageArrived | " + message); //converted in String
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Non necessario per i subscriber, usato solo per i publisher
                }
            }); 
            
            // Connessione al broker
            client.connect();
            CommUtils.outcyan(clientId + " | Connesso al broker: " + brokerUrl);
            // Sottoscrizione al topic
            client.subscribe(topic);
            CommUtils.outcyan(clientId + " | Sottoscritto al topic: " + topic);
        } catch (MqttException e) {
        	CommUtils.outred("ERROR " + e.getMessage() );
        }
    }
    
	public static void main(String[] args) {
        new SonarSubscriber().doJob();	
    }

}
