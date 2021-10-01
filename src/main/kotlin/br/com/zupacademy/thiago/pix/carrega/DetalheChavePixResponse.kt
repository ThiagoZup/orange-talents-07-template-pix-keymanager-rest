package br.com.zupacademy.thiago.pix.carrega

import br.com.zupacademy.thiago.CarregaChavePixResponse
import br.com.zupacademy.thiago.pix.model.enums.TipoChave
import br.com.zupacademy.thiago.pix.model.enums.TipoConta
import com.fasterxml.jackson.annotation.JsonFormat
import io.micronaut.core.annotation.Introspected
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Introspected
class DetalheChavePixResponse(grpcResponse: CarregaChavePixResponse) {

    val pixId = grpcResponse.pixId
    val tipo  = TipoChave.valueOf(grpcResponse.chave.tipo.name)
    val chave = grpcResponse.chave.chave

    //@field:JsonFormat(pattern = "dd/MM/yyyy")
    val criadaEm = grpcResponse.chave.criadaEm.let {
        LocalDateTime.ofInstant(
            Instant.ofEpochSecond(it.seconds, it.nanos.toLong()),
            ZoneOffset.UTC)
    }

    val tipoConta = TipoConta.valueOf(grpcResponse.chave.conta.tipo.name)

    val conta = mapOf(
        Pair("tipo", tipoConta),
        Pair("instituicao", grpcResponse.chave.conta.instituicao),
        Pair("nomeDoTitular", grpcResponse.chave.conta.nomeDoTitular),
        Pair("cpfDoTitular", grpcResponse.chave.conta.cpfDoTitular),
        Pair("agencia", grpcResponse.chave.conta.agencia),
        Pair("numero", grpcResponse.chave.conta.numeroDaConta))
}
