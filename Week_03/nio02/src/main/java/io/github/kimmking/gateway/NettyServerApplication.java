package io.github.kimmking.gateway;


import io.github.kimmking.gateway.inbound.HttpInboundServer;

import java.util.Arrays;

public class NettyServerApplication {

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";
    private final static String GATEWAY_PORT = "8888";

    public final static String HTTPCLIENT_MODE = "httpclient";
    public final static String OKHTTP_MODE = "okhttp";
    public final static String NETTY_MODE = "netty";

    public static void main(String[] args) {
        System.setProperty("outBoundHandlerType", NETTY_MODE);

        String gatewayPort = System.getProperty("gatewayPort", GATEWAY_PORT);
        String[] servers = {"http://localhost:8801",
                "http://localhost:8802",
                "http://localhost:8803",
                "http://localhost:8804",
                "http://localhost:8805"};
        int port = Integer.parseInt(gatewayPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");
        HttpInboundServer server = new HttpInboundServer(port, servers);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " started at http://localhost:" + port + " for servers:" + Arrays.toString(servers));
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
