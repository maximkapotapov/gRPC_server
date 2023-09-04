package com.mp.server;

import com.mp.gRPC_server.GreetingsServiceGrpc;
import com.mp.gRPC_server.GreetingsServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class GreetingsServiceImpl extends GreetingsServiceGrpc.GreetingsServiceImplBase {
    @Override
    public void greeting(GreetingsServiceOuterClass.HelloRequest request,
                         StreamObserver<GreetingsServiceOuterClass.HelloResponse> responseObserver) {
        for (int i = 0; i < 10000; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            GreetingsServiceOuterClass.HelloResponse response =
                    GreetingsServiceOuterClass.HelloResponse.newBuilder().setGreeting("Hello, new user " + request.getName()).build();

            responseObserver.onNext(response);
        }

        responseObserver.onCompleted();
    }
}
