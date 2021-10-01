package br.com.zupacademy.thiago.pix.carrega

import br.com.zupacademy.thiago.ListaChavesPixResponse
import br.com.zupacademy.thiago.pix.model.enums.TipoChave
import br.com.zupacademy.thiago.pix.model.enums.TipoConta
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class ChavePixResponse(chavePix: ListaChavesPixResponse.ChavePix) {

    val id = chavePix.pixId
    val chave = chavePix.chave
    val tipo = TipoChave.valueOf(chavePix.tipo.name)
    val tipoDeConta = TipoConta.valueOf(chavePix.tipoDeConta.name)
    val criadaEm = chavePix.criadaEm.let {
        LocalDateTime.ofInstant(Instant.ofEpochSecond(it.seconds, it.nanos.toLong()),
                                ZoneOffset.UTC)
    }
}
