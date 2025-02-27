package kevin;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyWebSocketHandler extends TextWebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> sendUpdate(session), 1, 2, TimeUnit.SECONDS);
    }

    private void sendUpdate(WebSocketSession session) {
        try {
            session.sendMessage(new TextMessage("Aggiornamento dal server: " + System.currentTimeMillis()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}