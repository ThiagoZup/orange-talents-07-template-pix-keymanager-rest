package br.com.zupacademy.thiago.pix.carrega

import br.com.zupacademy.thiago.CarregaChavePixRequest
import br.com.zupacademy.thiago.KeymanagerCarregaServiceGrpc
import br.com.zupacademy.thiago.KeymanagerListaServiceGrpc
import br.com.zupacademy.thiago.ListaChavesPixRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.slf4j.LoggerFactory
import java.util.*

@Controller("/api/v1/clientes/{clienteId}")
class CarregaChavePixController(
    val carregaChavePixClient: KeymanagerCarregaServiceGrpc.KeymanagerCarregaServiceBlockingStub,
    val listaChavesPixClient: KeymanagerListaServiceGrpc.KeymanagerListaServiceBlockingStub){

    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Get("/pix/{pixId}")
    fun carrega(clienteId: UUID, pixId: UUID): HttpResponse<Any> {

        LOGGER.info("[$clienteId] carregando chave pix por id: $pixId")

        val grpcResponse = carregaChavePixClient.carrega(CarregaChavePixRequest.newBuilder()
                                                            .setPixId(CarregaChavePixRequest.FiltroPorPixId.newBuilder()
                                                                .setClienteId(clienteId.toString())
                                                                .setPixId(pixId.toString())
                                                                .build())
                                                            .build())
    return HttpResponse.ok(DetalheChavePixResponse(grpcResponse))

    }

    @Get("/pix")
    fun lista(clienteId: UUID): HttpResponse<Any> {

        LOGGER.info("[$clienteId] listando chaves pix")

        val grpcResponse = listaChavesPixClient.lista(ListaChavesPixRequest.newBuilder()
            .setClienteId(clienteId.toString())
            .build())

        val chaves = grpcResponse.chavesList.map {ChavePixResponse(it)}
        return HttpResponse.ok(chaves)
    }
}