package br.com.zupacademy.thiago.shared.enums

import br.com.zupacademy.thiago.pix.model.enums.TipoChave
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@MicronautTest
internal class TipoChaveTest {

    @Nested
    inner class AleatoriaTest {

        @Test
        fun `deve ser valido quando chave aleatoria for nula ou vazia`() {

            val tipoChave = TipoChave.ALEATORIA

            assertTrue(tipoChave.valida(""))
            assertTrue(tipoChave.valida(null))
        }
    }

    @Nested
    inner class CpfTest {

        @Test
        fun `deve ser valido quando cpf for numero valido`(){

            val tipoChave = TipoChave.CPF

            assertTrue(tipoChave.valida("00000000000"))

        }

        @Test
        fun `nao deve ser valido quando cpf for numero invalido`() {

            val tipoChave = TipoChave.CPF

            assertFalse(tipoChave.valida("0"))

        }

        @Test
        fun `nao deve ser valido quando cpf nao informado`() {

            val tipoChave = TipoChave.CPF

            assertFalse(tipoChave.valida(""))
            assertFalse(tipoChave.valida(null))

        }

    }

    @Nested
    inner class CelularTest {

        @Test
        fun `deve ser valido quando celular for numero valido`(){

            val tipoChave = TipoChave.CELULAR

            assertTrue(tipoChave.valida("+5500000000000"))

        }

        @Test
        fun `nao deve ser valido quando celular for numero invalido`() {

            val tipoChave = TipoChave.CELULAR

            assertFalse(tipoChave.valida("0"))
            assertFalse(tipoChave.valida("+55a0000000000"))

        }

        @Test
        fun `nao deve ser valido quando celular nao informado`() {

            val tipoChave = TipoChave.CELULAR

            assertFalse(tipoChave.valida(""))
            assertFalse(tipoChave.valida(null))

        }

    }

    @Nested
    inner class EmailTest {

        @Test
        fun `deve ser valido quando email for valido`(){

            val tipoChave = TipoChave.EMAIL

            assertTrue(tipoChave.valida("zupperson@zup.com.br"))

        }

        @Test
        fun `nao deve ser valido quando email for invalido`() {

            val tipoChave = TipoChave.CELULAR

            assertFalse(tipoChave.valida("zupperson.zup.com"))
            assertFalse(tipoChave.valida("zupperson@"))
            assertFalse(tipoChave.valida("@zup.com.br"))
            assertFalse(tipoChave.valida("zupperson@zup"))

        }

        @Test
        fun `nao deve ser valido quando celular nao informado`() {

            val tipoChave = TipoChave.CELULAR

            assertFalse(tipoChave.valida(""))
            assertFalse(tipoChave.valida(null))

        }

    }

    @Nested
    inner class UnknownTest {

        @Test
        fun `nao deve ser valido quando chave desconhecida`() {

            val tipoChave = TipoChave.UNKNOWN_TIPO_CHAVE

            assertFalse(tipoChave.valida("zupperson@zup.com.br"))
            assertFalse(tipoChave.valida(""))
            assertFalse(tipoChave.valida(null))
            assertFalse(tipoChave.valida("+5500000000000"))
            assertFalse(tipoChave.valida("00000000000"))

        }
    }
}