package br.com.fourtk.treinamentoKotlin.services

import br.com.fourtk.treinamentoKotlin.dtos.TopicRequestDTO
import br.com.fourtk.treinamentoKotlin.model.Topic
import org.springframework.stereotype.Service

@Service
class TopicServices (
    private var topics: List<Topic> = ArrayList(),
    private val cursoService: CursoService,
    private val authorService: AuthorService
)
{
    fun listar(): List<Topic> {
        return topics
    }

    fun getById(id: Long): Topic {
        return topics.stream().filter({
            t -> t.id == id
        }).findFirst().get()
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