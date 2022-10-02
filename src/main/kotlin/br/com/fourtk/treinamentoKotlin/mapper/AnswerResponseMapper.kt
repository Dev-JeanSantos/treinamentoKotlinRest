package br.com.fourtk.treinamentoKotlin.mapper

import br.com.fourtk.treinamentoKotlin.model.Answer
import br.com.fourtk.treinamentoKotlin.model.Topic
import br.com.fourtk.treinamentoKotlin.responseDTO.AnswerResponseDTO
import br.com.fourtk.treinamentoKotlin.responseDTO.TopicResponseDTO
import org.springframework.stereotype.Component

@Component
class AnswerResponseMapper: Mapper<Answer, AnswerResponseDTO> {
    override fun map(t: Answer): AnswerResponseDTO {
        return AnswerResponseDTO(
            id = t.id,
            title = t.topic.title,
            message = t.message,
            dateCreate = t.dateCreate,
            dateAlter = t.topic.dateAlter,
            status = t.topic.status,
            idAuthor = t.author.id,
            idTopic = t.topic.id,
            soluction = t.solution
        )
    }
}