package conway.player;


import unibo.basicomm23.utils.CommUtils;

import java.net.URI;

import javax.websocket.*;

@ClientEndpoint
public class PlayerClient {
	private Session session;
	private static String server_endpoint = "ws://localhost:7110/wsupdates";

    public PlayerClient(String uri) throws Exception {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.connectToServer(this, new URI(uri));
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("Connected to server");
    }

    @OnMessage
    public void onMessage(String message) {
//        System.out.println("Received message: " + message);
    }

    @OnClose
    public void onClose() {
        System.out.println("Disconnected from server");
    }

    public void sendMessage(String message) throws Exception {
        session.getBasicRemote().sendText(message);
    }
    
    public void workWithGame( ) {
        try {
        	
//        	sendMessageOnWs("clear");
//        	
        	CommUtils.delay(2000);
        	
        	sendMessage("cell-10-10");
        	sendMessage("cell-11-10");
        	sendMessage("cell-12-10");
        	
        	new Thread(() -> {
        	    System.out.println("Eseguo nel nuovo thread: " + Thread.currentThread().getName());
        	    for(int i=0; i<5; i++) {
        	    	CommUtils.outgreen("Player thread | i'm working");
        	    	CommUtils.delay(1000);
        	    }
        	}).start();
            
//        	sendMessage("start");
//            CommUtils.delay(3000);
//            sendMessage("stop");
            
            
        } catch (Exception e) {
        	CommUtils.outred("ConwayCallerWs | ERROR:" +e.getMessage());
        }    	
    }

    public static void main(String[] args) throws Exception {
    	PlayerClient client = new PlayerClient(PlayerClient.server_endpoint);
    	client.workWithGame(); 
    	CommUtils.delay(10000); //To checek broadcasted messages
    	CommUtils.outmagenta("ConwayCallerWs | BYE" );
    }
}
