package br.com.fourtk.treinamentoKotlin.responseDTO

import br.com.fourtk.treinamentoKotlin.model.StatusTopic
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

data class TopicResponseDTO  (
    val id: Long?,
    val title: String,
    val message: String,
    val status: StatusTopic,
    val dateCreate: LocalDateTime,
    val dateAlter: LocalDate?
    ): Serializable
