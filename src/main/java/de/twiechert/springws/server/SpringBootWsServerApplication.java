package de.twiechert.springws.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @author Tayfun Wiechert <wiechert@campus.tu-berlin.de>
 */

@EnableWebSocket
@SpringBootApplication
public class SpringBootWsServerApplication {

    private static final Logger LOG = LoggerFactory.getLogger(SpringBootWsServerApplication.class);





    public static void main(final String[] args) {

        SpringApplication.run(SpringBootWsServerApplication.class, args);
    }


}
