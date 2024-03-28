package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.Test;
import com.Onboarding3.AMS.TestServiceGrpc;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class TestServiceImpl extends TestServiceGrpc.TestServiceImplBase {
    @Override
    public void testFunction(Test request, StreamObserver<Empty> responseObserver) {

        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void testFunction2(Empty request, StreamObserver<Empty> responseObserver) {
        super.testFunction2(request, responseObserver);
    }
}