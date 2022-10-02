package br.com.fourtk.treinamentoKotlin.responseDTO

import br.com.fourtk.treinamentoKotlin.model.StatusTopic
import br.com.fourtk.treinamentoKotlin.model.User
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

data class AnswerResponseDTO  (
    val id: Long?,
    val title: String,
    val message: String,
    val status: StatusTopic,
    val dateCreate: LocalDateTime,
    val dateAlter: LocalDate?,
    val idAuthor: Long?,
    val idTopic: Long?,
    val soluction: Boolean
    ): Serializable
