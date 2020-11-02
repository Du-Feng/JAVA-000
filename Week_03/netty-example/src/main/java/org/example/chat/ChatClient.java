package org.example.chat;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class ChatClient {

    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));
    static String clientName;

    static final String BYE = "bye";

    public static void main(String[] args) throws Exception {

        /*
         * Get name of the user for this chat session.
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        if (scanner.hasNext()) {
            clientName = scanner.nextLine();
            System.out.println("Welcome " + clientName);
        }

        /*
         * Configure the client.
         */

        // Since this is client, it doesn't need boss group. Create single group.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group) // Set EventLoopGroup to handle all events for client.
                    .channel(NioSocketChannel.class)// Use NIO to accept new connections.
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            /*
                             * Socket/channel communication happens in byte streams. String decoder &
                             * encoder helps conversion between bytes & String.
                             */
                            p.addLast(new StringDecoder());
                            p.addLast(new StringEncoder());

                            // This is our custom client handler which will have logic for chat.
                            p.addLast(new ChatClientHandler());

                        }
                    });

            // Start the client.
            ChannelFuture f = b.connect(HOST, PORT).sync();

            Channel sayHi = f.sync().channel();
            sayHi.writeAndFlush("[" + clientName + "] is online");
            sayHi.flush();

            /*
             * Iterate & take chat message inputs from user & then send to server.
             */
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                Channel channel = f.sync().channel();
                channel.writeAndFlush("[" + clientName + "]: " + input);
                channel.flush();
                if(BYE.equalsIgnoreCase(input)) {
                    break;
                }
            }

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }
}
