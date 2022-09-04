package br.com.fourtk.treinamentoKotlin.mapper

import br.com.fourtk.treinamentoKotlin.model.Topic
import br.com.fourtk.treinamentoKotlin.responseDTO.TopicResponseDTO
import org.springframework.stereotype.Component

@Component
class TopicResponseMapper: Mapper<Topic, TopicResponseDTO> {
    override fun map(t: Topic): TopicResponseDTO {
        return TopicResponseDTO(
            id = t.id,
            title = t.title,
            message = t.message,
            dateCreate = t.dateCreate,
            status = t.status
        )
    }
}