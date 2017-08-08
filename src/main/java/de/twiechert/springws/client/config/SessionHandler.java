package de.twiechert.springws.client.config;

import de.twiechert.springws.common.model.RequestMessage;
import de.twiechert.springws.common.model.ResponseMessage;
import de.twiechert.springws.server.SpringBootWsServerApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

import static de.twiechert.springws.server.config.WebsocketConfiguration.WS_ENDPOINT_PREFIX;
import static de.twiechert.springws.server.controller.MessageController.SAMPLE_ENDPOINT_MESSAGE_MAPPING;
import static de.twiechert.springws.server.controller.MessageController.WS_TOPIC;

/**
 * @author Tayfun Wiechert <wiechert@campus.tu-berlin.de>
 */
@Component
public class SessionHandler extends StompSessionHandlerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(SessionHandler.class);
    private static final int messageToSend = 12;

    @Autowired
    private FactoryBean<RequestMessage> requestMessageFactoryBean;


    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        try {
            for(int i=0; i <messageToSend;i++) {
                RequestMessage requestMessage = requestMessageFactoryBean.getObject();
                LOG.info("Connection has been established, will send request message {}", requestMessage);
                session.subscribe(WS_TOPIC, this);
                session.send(WS_ENDPOINT_PREFIX+SAMPLE_ENDPOINT_MESSAGE_MAPPING, requestMessage);
            }
        } catch(Exception e) {
            LOG.error("Error while sending data");
        }

    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return ResponseMessage.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        LOG.info("Response has been received {}", payload);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        super.handleException(session, command, headers, payload, exception);
    }
}
