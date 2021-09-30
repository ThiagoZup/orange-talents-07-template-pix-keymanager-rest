package br.com.zupacademy.thiago.shared.handler

import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.hateoas.JsonError
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
internal class GlobalExceptionHandlerTest {

    val requestGenerica = HttpRequest.GET<Any>("/")

    @Test
    internal fun `deve retornar 404 quando statusException for NOT_FOUND`() {

        val mensagem = "não encontrado"
        val notFoundException = StatusRuntimeException(Status.NOT_FOUND
                                    .withDescription(mensagem)
        )

        val response = GlobalExceptionHandler().handle(requestGenerica, notFoundException)

        assertEquals(HttpStatus.NOT_FOUND, response.status)
        assertNotNull(response.body())
        assertEquals(mensagem, (response.body() as JsonError).message)
    }

    @Test
    internal fun `deve retornar 400 quando statusException for INVALID_ARGUMENT`() {

        val mensagem = "Dados da requisição inválidos"
        val notFoundException = StatusRuntimeException(Status.INVALID_ARGUMENT)

        val response = GlobalExceptionHandler().handle(requestGenerica, notFoundException)

        assertEquals(HttpStatus.BAD_REQUEST, response.status)
        assertNotNull(response.body())
        assertEquals(mensagem, (response.body() as JsonError).message)
    }

    @Test
    internal fun `deve retornar 422 quando statusException for ALREADY_EXISTS`() {

        val mensagem = "já existente"
        val notFoundException = StatusRuntimeException(Status.ALREADY_EXISTS
            .withDescription(mensagem)
        )

        val response = GlobalExceptionHandler().handle(requestGenerica, notFoundException)

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.status)
        assertNotNull(response.body())
        assertEquals(mensagem, (response.body() as JsonError).message)
    }

    @Test
    internal fun `deve retornar 500 quando statusException for outro`() {

        val description = "já existente"
        val notFoundException = StatusRuntimeException(Status.UNAVAILABLE
            .withDescription(description)
        )

        val mensagem = "Não foi possível completar a requisição devido ao erro: ${description} (${Status.UNAVAILABLE.code})"

        val response = GlobalExceptionHandler().handle(requestGenerica, notFoundException)

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.status)
        assertNotNull(response.body())
        assertEquals(mensagem, (response.body() as JsonError).message)
    }

}