package br.com.fourtk.treinamentoKotlin.services

import br.com.fourtk.treinamentoKotlin.mapper.TopicRequestMapper
import br.com.fourtk.treinamentoKotlin.mapper.TopicResponseMapper
import br.com.fourtk.treinamentoKotlin.model.Topic
import br.com.fourtk.treinamentoKotlin.requestDTO.TopicRequestDTO
import br.com.fourtk.treinamentoKotlin.requestDTO.UpdateTopicRequestDTO
import br.com.fourtk.treinamentoKotlin.responseDTO.TopicResponseDTO
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicServices (
    private var topics: List<Topic> = ArrayList(),
    private val topicoResponseMapper: TopicResponseMapper,
    private val topicoRequestMapper: TopicRequestMapper
)
{
    fun listar(): List<TopicResponseDTO> {
        return topics.stream().map {
                t -> topicoResponseMapper.map(t)
        }.collect(Collectors.toList())
    }

    fun getById(id: Long): TopicResponseDTO {
        val possibleTopic =  topics.stream().filter{
            t -> t.id == id
        }.findFirst().get()
        return topicoResponseMapper.map(possibleTopic)
    }

    fun insertTopic(topicRequestDTO: TopicRequestDTO) {
        val topic = topicoRequestMapper.map(topicRequestDTO)
        topic.id = topics.size.toLong() + 1
        topics = topics.plus(topic)
    }

    fun update(updateTopicRequestDTO: UpdateTopicRequestDTO) {
        val topic = topics.stream().filter {t -> t.id == updateTopicRequestDTO.id}.findFirst().get()
        //(Update) Delete old topic and create new topic
        topics = topics.minus((topic)).plus(
            Topic(
                id = updateTopicRequestDTO.id,
                title = updateTopicRequestDTO.title,
                message = updateTopicRequestDTO.message,
                author = topic.author,
                course = topic.course,
                answer = topic.answer,
                status = topic.status,
                dateCreate = topic.dateCreate
            )
        )
    }
    fun delete(id: Long) {
        val topic = topics.stream().filter{t -> t.id == id}.findFirst().get()
        topics = topics.minus(topic)
    }
}