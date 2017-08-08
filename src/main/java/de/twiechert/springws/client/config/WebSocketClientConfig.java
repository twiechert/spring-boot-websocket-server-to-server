package de.twiechert.springws.client.config;

import de.twiechert.springws.common.model.RequestMessage;
import net.moznion.random.string.RandomStringGenerator;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tayfun Wiechert <wiechert@campus.tu-berlin.de>
 */
@Configuration
public class WebSocketClientConfig {


    @Bean
    FactoryBean<RequestMessage> requestMessage(final RandomStringGenerator randomStringGenerator) {
        return new FactoryBean<RequestMessage>() {
            @Override
            public RequestMessage getObject()  {
                return new RequestMessage(randomStringGenerator.generateByRegex("\\w+\\d*\\s[0-9]{0,3}X"));
            }

            @Override
            public Class<?> getObjectType() {
                return RequestMessage.class;
            }

            @Override
            public boolean isSingleton() {
                return false;
            }
        };

    }

    @Bean
    RandomStringGenerator randomStringGenerator() {
        return new RandomStringGenerator();
    }

    @Bean
    public WebSocketClient webSocketClient()
    {
        List<Transport> transports = new ArrayList<Transport>(1);
        transports.add(new WebSocketTransport( new StandardWebSocketClient()) );
        return new SockJsClient(transports);
    }

    @Bean
    public WebSocketStompClient webSocketStompClient(WebSocketClient webSocketClient) {
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.setTaskScheduler(new ConcurrentTaskScheduler());
        return  stompClient;
    }
}
