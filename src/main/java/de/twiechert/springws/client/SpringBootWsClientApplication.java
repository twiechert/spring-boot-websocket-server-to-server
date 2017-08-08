package de.twiechert.springws.client;

import de.twiechert.springws.client.config.SessionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * @author Tayfun Wiechert <wiechert@campus.tu-berlin.de>
 */

@EnableWebSocket
@SpringBootApplication
@EnableScheduling
public class SpringBootWsClientApplication {


    @Autowired
    SessionHandler sessionHandler;

    @Autowired
    SessionHandler.SessionHandlerThatExpectsNoResponse sessionHandlerThatExpectsNoResponse;

    @Autowired
    WebSocketStompClient stompClient;


    @Value("${server.url}")
    private String urlEndpoint;


    @Value("${server.url-no-response}")
    private String urlEndpointNoResponse;




    @Scheduled(fixedDelay=5000)
    public void sendMessageToServer() throws ExecutionException, InterruptedException {
       stompClient.connect(urlEndpoint, sessionHandler);
       stompClient.connect(urlEndpointNoResponse, sessionHandlerThatExpectsNoResponse);

    }

    public static void main(final String[] args) {

        SpringApplication.run(SpringBootWsClientApplication.class, args);
    }



}
