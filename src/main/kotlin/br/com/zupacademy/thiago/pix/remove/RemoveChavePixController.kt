package br.com.zupacademy.thiago.pix.remove

import br.com.zupacademy.thiago.KeymanagerRemoveServiceGrpc
import br.com.zupacademy.thiago.RemoveChavePixRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import java.util.*

@Validated
@Controller("/api/v1/clientes/{clienteId}")
class RemoveChavePixController(private val removeChavePixCliente: KeymanagerRemoveServiceGrpc.KeymanagerRemoveServiceBlockingStub) {

    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Delete("/pix/{pixId}")
    fun remove(clienteId: UUID, pixId: UUID): HttpResponse<Any> {

        LOGGER.info("[$clienteId] removendo uma nova chave pix com $pixId")

        val grpcResponse = removeChavePixCliente.remove(RemoveChavePixRequest.newBuilder()
            .setClienteId(clienteId.toString())
            .setPixId(pixId.toString())
            .build()
        )
        return HttpResponse.ok()
    }
}