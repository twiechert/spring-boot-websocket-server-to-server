package de.twiechert.springws.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import static de.twiechert.springws.server.controller.MessageController.SAMPLE_ENDPOINT_MESSAGE_MAPPING;
import static de.twiechert.springws.server.controller.MessageController.SAMPLE_ENDPOINT_WITHOUT_RESPONSE_MESSAGE_MAPPING;

/**
 * @author Tayfun Wiechert <wiechert@campus.tu-berlin.de>
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

    public static final String WS_ENDPOINT_PREFIX = "/app";

    public static final String WS_TOPIC_DESTINATION_PREFIX = "/topic";


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        config.enableSimpleBroker(WS_TOPIC_DESTINATION_PREFIX);
        /*
           The prefix under which endpoints are available
         */
        config.setApplicationDestinationPrefixes(WS_ENDPOINT_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*
        Here we register the single endpoints
         */
        registry.addEndpoint(SAMPLE_ENDPOINT_MESSAGE_MAPPING)
                .setAllowedOrigins("*")
                .withSockJS();

        registry.addEndpoint(SAMPLE_ENDPOINT_WITHOUT_RESPONSE_MESSAGE_MAPPING)
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
