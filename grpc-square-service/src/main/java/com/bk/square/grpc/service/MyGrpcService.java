package com.bk.square.grpc.service;


import com.bk.match.model.Input;
import com.bk.match.model.Output;
import com.bk.match.model.SquareRpcGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class MyGrpcService extends SquareRpcGrpc.SquareRpcImplBase {

    @Override
    public void findSquareUnary(Input request, StreamObserver<Output> responseObserver) {
        int number = request.getNumber();
        responseObserver.onNext(
                Output.newBuilder().setNumber(number).setResult(number * number).build()
        );
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<Input> findSquareBiStream(StreamObserver<Output> responseObserver) {
        return new StreamObserver<>() {
            @Override
            public void onNext(Input input) {
                var number = input.getNumber();
                Output output = Output.newBuilder()
                        .setNumber(number)
                        .setResult(number * number).build();
                responseObserver.onNext(output);
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onCompleted();
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

}