package br.com.zupacademy.thiago.shared.grpc

import br.com.zupacademy.thiago.KeymanagerRegistraServiceGrpc
import br.com.zupacademy.thiago.KeymanagerRemoveServiceGrpc
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import jakarta.inject.Singleton

@Factory
class KeymanagerGrpcFactory{

    @Singleton
    fun registraClientStub(@GrpcChannel("registra") channel: ManagedChannel): KeymanagerRegistraServiceGrpc.KeymanagerRegistraServiceBlockingStub? {

        val channel: ManagedChannel = ManagedChannelBuilder
            .forAddress("localhost", 50051)
            .usePlaintext()
            .maxRetryAttempts(10)
            .build()

        return KeymanagerRegistraServiceGrpc.newBlockingStub(channel)
    }

    @Singleton
    fun removeClientStub(@GrpcChannel("remove") channel: ManagedChannel): KeymanagerRemoveServiceGrpc.KeymanagerRemoveServiceBlockingStub? {

        val channel: ManagedChannel = ManagedChannelBuilder
            .forAddress("localhost", 50051)
            .usePlaintext()
            .maxRetryAttempts(10)
            .build()

        return KeymanagerRemoveServiceGrpc.newBlockingStub(channel)
    }

}