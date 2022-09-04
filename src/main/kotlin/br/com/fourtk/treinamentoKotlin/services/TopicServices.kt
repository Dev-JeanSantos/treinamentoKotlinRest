package br.com.fourtk.treinamentoKotlin.services

import br.com.fourtk.treinamentoKotlin.requestDTO.TopicRequestDTO
import br.com.fourtk.treinamentoKotlin.model.Topic
import br.com.fourtk.treinamentoKotlin.responseDTO.TopicResponseDTO
import org.springframework.stereotype.Service
import java.util.Collections
import java.util.stream.Collectors

@Service
class TopicServices (
    private var topics: List<Topic> = ArrayList(),
    private val cursoService: CursoService,
    private val authorService: AuthorService
)
{
    fun listar(): List<TopicResponseDTO> {
        return topics.stream().map { t -> TopicResponseDTO(
            id = t.id,
            title = t.title,
            message = t.message,
            dateCreate = t.dateCreate,
            status = t.status
        ) }.collect(Collectors.toList())
    }

    fun getById(id: Long): TopicResponseDTO {
        val possibleTopic =  topics.stream().filter{
            t -> t.id == id
        }.findFirst().get()

        return TopicResponseDTO(
            id = possibleTopic.id,
            title = possibleTopic.title,
            message = possibleTopic.message,
            dateCreate = possibleTopic.dateCreate,
            status = possibleTopic.status
        )
    }

    fun insertTopic(topicRequestDTO: TopicRequestDTO) {
        topics = topics.plus(Topic(
            id = topics.size.toLong() + 1,
            title = topicRequestDTO.title,
            message = topicRequestDTO.message,
            course = cursoService.getById(topicRequestDTO.idCourse),
            author = authorService.getById(topicRequestDTO.idAuthor)
        ))
    }
}