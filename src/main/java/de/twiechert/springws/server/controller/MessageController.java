package de.twiechert.springws.server.controller;

import de.twiechert.springws.common.model.RequestMessage;
import de.twiechert.springws.common.model.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import static de.twiechert.springws.server.config.WebsocketConfiguration.WS_TOPIC_DESTINATION_PREFIX;

/**
 * @author Tayfun Wiechert <wiechert@campus.tu-berlin.de>
 */
@Controller
public class MessageController {


    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);

    public static final String SAMPLE_ENDPOINT_MESSAGE_MAPPING = "/sampleEndpoint";

    public static final String SAMPLE_ENDPOINT_WITHOUT_RESPONSE_MESSAGE_MAPPING = "/sampleEndpointWithoutResponse";

    public static final String WS_TOPIC = WS_TOPIC_DESTINATION_PREFIX+"/messages";
    public static final String WS_TOPIC_NO_RESPONSE = WS_TOPIC_DESTINATION_PREFIX+"/messagesNoResponse";

    @MessageMapping(SAMPLE_ENDPOINT_MESSAGE_MAPPING)
    @SendTo(WS_TOPIC)
    public ResponseMessage processRequest(RequestMessage message) throws Exception {
        LOG.info("Received new request message {}. Will respond after one second.", message);
        Thread.sleep(1000); // simulated delay
        return new ResponseMessage("Hello", message.getRef());
    }

    @MessageMapping(SAMPLE_ENDPOINT_WITHOUT_RESPONSE_MESSAGE_MAPPING)
    @SendTo(WS_TOPIC_NO_RESPONSE)
    public void processRequestWithoutResponse(RequestMessage message) throws Exception {
        LOG.info("Received new request without responses message {}. Will respond after one second.", message);
        Thread.sleep(1000); // simulated delay
      //  return new ResponseMessage("Hello", message.getRef());
    }



}
