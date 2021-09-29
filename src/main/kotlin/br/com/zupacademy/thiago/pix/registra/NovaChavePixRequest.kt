package br.com.zupacademy.thiago.pix.registra

import br.com.zupacademy.thiago.RegistraChavePixRequest
import br.com.zupacademy.thiago.TipoDeChave
import br.com.zupacademy.thiago.TipoDeConta
import br.com.zupacademy.thiago.pix.model.enums.TipoChave
import br.com.zupacademy.thiago.pix.model.enums.TipoConta
import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Introspected
class NovaChavePixRequest(
    @field:NotNull val tipoConta: TipoConta,
    @field:NotBlank @field:Size(max = 77) val chave: String,
    @field:NotNull val tipoChave: TipoChave) {

    fun toGrpcModel(clienteId: UUID): RegistraChavePixRequest {
        if(!this.valida()){
            throw IllegalArgumentException("Chave pix inválida")
        }
        return RegistraChavePixRequest.newBuilder()
            .setClienteId(clienteId.toString())
            .setTipoDeConta(TipoDeConta.valueOf(this.tipoConta.name))
            .setTipoDeChave(TipoDeChave.valueOf(this.tipoChave.name))
            .setChave(chave)
            .build()
    }

    fun valida(): Boolean {
        return this.tipoChave.valida(this.chave)
    }
}
