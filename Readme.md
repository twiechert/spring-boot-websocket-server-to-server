# WebSocket based server to server communication in Spring Boot

This sample project realizes the websocket based communication between two Spring Boot applications.
The applications exchange JSON serialized Java POJOs and do some dummy logging.

# Run the Application

Despite the description "server to server", there is always one party initaiting the connection, called the client.
Thus, this repository contains two programs, the client app and the server app. First start the server app ``SpringBootWsServerApplication`` and then
the client app ``SpringBootWsClientApplication`` and observe the programs output. Make sure, both use different HTTP server ports, when running locally:

``java de.twiechert.springws.server.SpringBootWsServerApplication --server.port=9000``


``java de.twiechert.springws.server.SpringBootWsClientApplication --server.port=9001``



