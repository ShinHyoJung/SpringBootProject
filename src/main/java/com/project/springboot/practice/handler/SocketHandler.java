package com.project.springboot.practice.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;

@Component
public class SocketHandler extends TextWebSocketHandler {
    HashMap<String, WebSocketSession> sessionHashMap = new HashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String msg = message.getPayload();
        for(String key : sessionHashMap.keySet()) {
            WebSocketSession wss = sessionHashMap.get(key);
            try {
                wss.sendMessage(new TextMessage(msg));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        sessionHashMap.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status ) throws Exception {
        sessionHashMap.remove(session.getId());
        super.afterConnectionClosed(session,status);
    }

}
