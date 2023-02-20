package com.reeinvent.backend.synonym.grpc;

import com.reeinvent.backend.*;
import com.reeinvent.backend.Void;
import com.reeinvent.backend.exceptions.GRPCException;
import com.reeinvent.backend.synonym.service.SynonymService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SynonymServiceGRPC extends SynonymServiceGrpc.SynonymServiceImplBase {

    @Autowired
    private SynonymService synonymService;

    @Override
    public void upsertWord(GRPCWord request, StreamObserver<GRPCWord> responseObserver) {
        try {
            responseObserver.onNext(synonymService.upsertWord(request));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new GRPCException(Status.fromThrowable(e), e.getMessage(), e));
        }
    }

    @Override
    public void deleteWord(GRPCWord request, StreamObserver<Void> responseObserver) {
        try {
            responseObserver.onNext(synonymService.deleteWord(request));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new GRPCException(Status.fromThrowable(e), e.getMessage(), e));
        }
    }

    @Override
    public void attachSynonym(GRPCSynonym request, StreamObserver<Void> responseObserver) {
        try {
            responseObserver.onNext(synonymService.attachSynonym(request));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new GRPCException(Status.fromThrowable(e), e.getMessage(), e));
        }
    }

    @Override
    public void detachSynonym(GRPCSynonym request, StreamObserver<Void> responseObserver) {
        try {
            responseObserver.onNext(synonymService.detachSynonym(request));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new GRPCException(Status.fromThrowable(e), e.getMessage(), e));
        }
    }

    @Override
    public void getAllWords(Void request, StreamObserver<GRPCWords> responseObserver) {
        try {
            responseObserver.onNext(synonymService.getWords(request));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new GRPCException(Status.fromThrowable(e), e.getMessage(), e));
        }
    }

    @Override
    public void getSynonymsByWord(GRPCWord request, StreamObserver<GRPCWord> responseObserver) {
        try {
            responseObserver.onNext(synonymService.getSynonymsByWord(request));
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(new GRPCException(Status.fromThrowable(e), e.getMessage(), e));
        }
    }
}
