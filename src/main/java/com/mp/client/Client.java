package com.mp.client;

import com.mp.gRPC_server.GreetingsServiceGrpc;
import com.mp.gRPC_server.GreetingsServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class Client {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forTarget("localhost:8080").usePlaintext().build();

        GreetingsServiceGrpc.GreetingsServiceBlockingStub stub
                = GreetingsServiceGrpc.newBlockingStub(channel);

        GreetingsServiceOuterClass.HelloRequest request
                = GreetingsServiceOuterClass.HelloRequest.newBuilder().setName("Maxim").build();

        Iterator<GreetingsServiceOuterClass.HelloResponse> response = stub.greeting(request);

        while (response.hasNext())
            System.out.println(response.next());

        channel.shutdownNow();
    }
}
