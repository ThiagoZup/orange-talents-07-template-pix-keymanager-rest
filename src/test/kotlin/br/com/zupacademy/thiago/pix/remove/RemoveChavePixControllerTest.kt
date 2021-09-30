package br.com.zupacademy.thiago.pix.remove

import br.com.zupacademy.thiago.KeymanagerRemoveServiceGrpc
import br.com.zupacademy.thiago.RegistraChavePixResponse
import br.com.zupacademy.thiago.RemoveChavePixResponse
import br.com.zupacademy.thiago.pix.registra.NovaChavePixRequest
import br.com.zupacademy.thiago.shared.grpc.KeymanagerGrpcFactory
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import java.util.*

@MicronautTest
internal class RemoveChavePixControllerTest {

    @field:Inject
    lateinit var removeStub: KeymanagerRemoveServiceGrpc.KeymanagerRemoveServiceBlockingStub

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    internal fun `deve remover uma chave pix existente`() {

        val clienteId = UUID.randomUUID().toString()
        val pixId = UUID.randomUUID().toString()

        val grpcResponse = RemoveChavePixResponse.newBuilder()
            .setClientId(clienteId)
            .setPixId(pixId)
            .build()

        given(removeStub.remove(Mockito.any())).willReturn(grpcResponse)

        val request = HttpRequest.DELETE<Any>("api/v1/clientes/$clienteId/pix/$pixId")
        val response = client.toBlocking().exchange(request, Any::class.java)

        Assertions.assertEquals(HttpStatus.OK, response.status)
    }

    @Factory
    @Replaces(factory = KeymanagerGrpcFactory::class)
    internal class MockitoStubFactory {

        @Singleton
        fun stubMock() = Mockito.mock(KeymanagerRemoveServiceGrpc.KeymanagerRemoveServiceBlockingStub::class.java)
    }
}