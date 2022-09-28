package br.com.fourtk.treinamentoKotlin.model

import br.com.fourtk.treinamentoKotlin.responseDTO.TopicResponseDTO
import java.time.LocalDate
import java.time.LocalDateTime

object TopicResponseDTOTest {
    fun build() = TopicResponseDTO(
            id = 1,
            title = "Kotlin basico",
            message = "Implementando com Kotlin",
            status = StatusTopic.NOT_ANSWERED,
            dateCreate = LocalDateTime.now(),
            dateAlter = LocalDate.now()
    )
}